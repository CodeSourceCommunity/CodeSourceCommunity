package com.sparta.codesourcecommunity.member.entity;

import com.sparta.codesourcecommunity.board.entity.Board;
import com.sparta.codesourcecommunity.comment.entity.Comment;
import com.sparta.codesourcecommunity.recomment.entity.ReComment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "members")
public class Member {

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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> board = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comment = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReComment> reComment = new ArrayList<>();


    public Member(String email, String nickname, String password, String introduce) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.introduce = introduce;
    }

    public void UpdateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void UpdatePassword(String changePassword) {
        this.password = changePassword;
    }

    public void UpdateIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
