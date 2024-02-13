package com.sparta.codesourcecommunity.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "membersBeforeAuth")
public class MemberBeforeEmailAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String password;
    @Column
    private String introduce;

    public MemberBeforeEmailAuth(String email, String nickname, String password, String introduce) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.introduce = introduce;
    }
}
