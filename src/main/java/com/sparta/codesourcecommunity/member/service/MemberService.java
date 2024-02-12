package com.sparta.codesourcecommunity.member.service;

import com.sparta.codesourcecommunity.member.dto.MemberLoginRequestDto;
import com.sparta.codesourcecommunity.member.dto.MemberRequestDto;
import com.sparta.codesourcecommunity.member.dto.MemberResponseDto;
import com.sparta.codesourcecommunity.member.dto.ModifyPasswordDto;
import com.sparta.codesourcecommunity.member.entity.Member;
import com.sparta.codesourcecommunity.member.exception.DuplicateMemberException;
import com.sparta.codesourcecommunity.member.exception.NotFoundMemberException;
import com.sparta.codesourcecommunity.member.exception.NotMatchPasswordException;
import com.sparta.codesourcecommunity.member.exception.PasswordMismatchException;
import com.sparta.codesourcecommunity.member.exception.SamePasswordException;
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
    private final PasswordEncoder passwordEncoder;

    public void signup(MemberRequestDto memberRequestDto) {
        String email = memberRequestDto.getEmail();
        String nickname = memberRequestDto.getNickname();
        String password = passwordEncoder.encode(memberRequestDto.getPassword());
        String introduce = memberRequestDto.getIntroduce();

        if (memberRepository.findByEmail(email).isPresent()) {
            throw new DuplicateMemberException();
        }

        Member member = new Member(email, nickname, password, introduce);
        memberRepository.save(member);
    }


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
        Member member = memberRepository.findById(memberDetails.getMember().getMemberId()).orElseThrow(NotFoundMemberException::new);
        return new MemberResponseDto(member.getEmail(), member.getNickname(), member.getIntroduce());
    }
}
