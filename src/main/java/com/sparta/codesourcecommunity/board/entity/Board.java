package com.sparta.codesourcecommunity.board.entity;

import com.sparta.codesourcecommunity.comment.entity.Comment;
import com.sparta.codesourcecommunity.like.entity.Like;
import com.sparta.codesourcecommunity.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany(mappedBy = "board")
    private List<Comment> comment = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<Like> like = new ArrayList<>();

    @ColumnDefault("0")
    @Column(name = "view_count",nullable = false)
    private Integer viewCount;

    public Board(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
