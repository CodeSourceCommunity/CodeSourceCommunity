package com.sparta.codesourcecommunity.member.service;

import com.sparta.codesourcecommunity.member.dto.MemberRequestDto;
import com.sparta.codesourcecommunity.member.entity.Member;
import com.sparta.codesourcecommunity.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
            throw new IllegalArgumentException("이미 존재하는 유저 입니다.");
        }

        Member member = new Member(email, nickname, password, introduce);
        memberRepository.save(member);
    }


    public void login(MemberRequestDto memberRequestDto) {
        String email = memberRequestDto.getEmail();
        String password = memberRequestDto.getPassword();

        Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("등록된 유저가 없습니다."));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
