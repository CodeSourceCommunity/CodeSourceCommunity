package com.sparta.codesourcecommunity.security;

import com.sparta.codesourcecommunity.member.entity.Member;
import com.sparta.codesourcecommunity.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsService {

    private final MemberRepository memberRepository;

    public UserDetails getMemberDetails(String email) {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Not found" + email));
        return new MemberDetailsImpl(member);
    }

}
