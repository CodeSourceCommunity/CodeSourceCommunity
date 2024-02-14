package com.sparta.codesourcecommunity.comment.dto;

import com.sparta.codesourcecommunity.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private Long commentId;
    private String comment;
    private String nickname;

    public CommentResponseDto(Comment comment, String nickname) {
        this.commentId = comment.getCommentId();
        this.comment = comment.getContents();
        this.nickname = nickname;
    }
}
