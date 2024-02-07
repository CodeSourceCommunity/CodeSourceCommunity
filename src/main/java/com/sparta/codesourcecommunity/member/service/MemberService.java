package com.sparta.codesourcecommunity.member.service;

import com.sparta.codesourcecommunity.member.dto.MemberRequestDto;
import com.sparta.codesourcecommunity.member.entity.Member;
import com.sparta.codesourcecommunity.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signup(MemberRequestDto memberRequestDto) {
        String email = memberRequestDto.getEmail();
        String nickname = memberRequestDto.getNickname();
        String password = memberRequestDto.getPassword();
        String introduce = memberRequestDto.getIntroduce();


        if(memberRepository.findByEmail(email).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 유저 입니다.");
        }

        Member member = new Member(email, nickname, password, introduce);
        memberRepository.save(member);
    }
}
