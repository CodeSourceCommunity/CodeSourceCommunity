package com.sparta.codesourcecommunity.comment.dto;

import com.sparta.codesourcecommunity.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private Long commentId;
    private String comment;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.comment = comment.getContents();
    }
}
