package com.sparta.codesourcecommunity.member.service;

import com.sparta.codesourcecommunity.member.dto.MemberLoginRequestDto;
import com.sparta.codesourcecommunity.member.dto.MemberRequestDto;
import com.sparta.codesourcecommunity.member.dto.MemberResponseDto;
import com.sparta.codesourcecommunity.member.dto.ModifyPasswordDto;
import com.sparta.codesourcecommunity.member.entity.Member;
import com.sparta.codesourcecommunity.member.entity.MemberBeforeEmailAuth;
import com.sparta.codesourcecommunity.member.exception.DuplicateMemberException;
import com.sparta.codesourcecommunity.member.exception.NotFoundMemberException;
import com.sparta.codesourcecommunity.member.exception.NotMatchPasswordException;
import com.sparta.codesourcecommunity.member.exception.PasswordMismatchException;
import com.sparta.codesourcecommunity.member.exception.SamePasswordException;
import com.sparta.codesourcecommunity.member.repository.MemberBeforeAuthRepository;
import com.sparta.codesourcecommunity.member.repository.MemberRepository;
import com.sparta.codesourcecommunity.security.MemberDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberBeforeAuthRepository memberBeforeAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    // 회원가입 양식대로 입력 후 이메일 인증 요청
    public void signup(MemberRequestDto memberRequestDto) {
        if (memberBeforeAuthRepository.findByEmail(memberRequestDto.getEmail()).isPresent()) {
            throw new DuplicateMemberException();
        }

        emailService.joinEmail(memberRequestDto.getEmail());
        memberBeforeAuthRepository.save(
            new MemberBeforeEmailAuth(memberRequestDto.getEmail(), memberRequestDto.getNickname(),
                memberRequestDto.getPassword(), memberRequestDto.getIntroduce()));
    }

    // 이메일 인증이 성공적으로 완료되었을 때 실행
    public void signupComplete(String email){
        MemberBeforeEmailAuth memberBeforeEmailAuth = memberBeforeAuthRepository.findByEmail(email).orElseThrow(NotFoundMemberException::new);

        Member member = new Member(memberBeforeEmailAuth.getEmail(),
            memberBeforeEmailAuth.getNickname(), memberBeforeEmailAuth.getPassword(),
            memberBeforeEmailAuth.getIntroduce());

        if(memberRepository.findByEmail(member.getEmail()).isPresent()){
            throw new DuplicateMemberException();
        }

        memberRepository.save(member);
    }


    // 이메일과 비밀번호를 입력받아 회원임을 검증
    public void login(MemberLoginRequestDto memberLoginRequestDto) {
        String email = memberLoginRequestDto.getEmail();
        String password = memberLoginRequestDto.getPassword();

        Member member = memberRepository.findByEmail(email)
            .orElseThrow(NotFoundMemberException::new);

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new NotMatchPasswordException();
        }
    }

    @Transactional
    public void modifyNickname(MemberRequestDto memberRequestDto, Member memberDto) {
        String nickname = memberRequestDto.getNickname();
        Member member = memberRepository.findById(memberDto.getMemberId())
            .orElseThrow(NotFoundMemberException::new);

        if (!passwordEncoder.matches(memberRequestDto.getPassword(), member.getPassword())) {
            throw new NotMatchPasswordException();
        }

        member.UpdateNickname(nickname);
    }

    @Transactional
    public void modifyPassword(ModifyPasswordDto passwordDto, Member memberDto) {
        String changePassword = passwordEncoder.encode(passwordDto.getChangePassword());
        Member member = memberRepository.findById(memberDto.getMemberId())
            .orElseThrow(NotFoundMemberException::new);

        if (!passwordEncoder.matches(passwordDto.getPassword(), memberDto.getPassword())) {
            throw new NotMatchPasswordException();
        }

        if (passwordDto.getPassword().equals(passwordDto.getChangePassword())) {
            throw new SamePasswordException();
        }

        if (!passwordDto.getChangePassword().equals(passwordDto.getChangePasswordConfirm())) {
            throw new PasswordMismatchException();
        }

        member.UpdatePassword(changePassword);
    }

    @Transactional
    public void modifyIntroduce(MemberRequestDto memberRequestDto, Member memberDto) {
        String introduce = memberRequestDto.getIntroduce();
        Member member = memberRepository.findById(memberDto.getMemberId())
            .orElseThrow(NotFoundMemberException::new);

        if (!passwordEncoder.matches(memberRequestDto.getPassword(), member.getPassword())) {
            throw new NotMatchPasswordException();
        }

        member.UpdateIntroduce(introduce);
    }

    public MemberResponseDto getProfile(MemberDetailsImpl memberDetails) {
        Member member = memberRepository.findById(memberDetails.getMember().getMemberId())
            .orElseThrow(NotFoundMemberException::new);
        return new MemberResponseDto(member.getEmail(), member.getNickname(),
            member.getIntroduce());
    }

    @Transactional
    public void delete(String email) {
        memberBeforeAuthRepository.deleteByEmail(email);
    }
}
