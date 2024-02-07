package com.sparta.codesourcecommunity.comment.dto;

import com.sparta.codesourcecommunity.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private String comment;

    public CommentResponseDto(Comment comment) {
        this.comment = comment.getContents();
    }
}
