package com.sparta.codesourcecommunity.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MemberResponseDto {
    private String email;
    private String nickname;
    private String introduce;

    public MemberResponseDto(String email, String nickname, String introduce) {
        this.email = email;
        this.nickname = nickname;
        this.introduce = introduce;
    }
}
