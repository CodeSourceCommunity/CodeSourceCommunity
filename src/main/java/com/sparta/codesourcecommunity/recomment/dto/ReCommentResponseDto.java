package com.sparta.codesourcecommunity.recomment.dto;

import com.sparta.codesourcecommunity.recomment.entity.ReComment;
import lombok.Getter;

@Getter
public class ReCommentResponseDto {
    private String reComment;

    public ReCommentResponseDto(ReComment reComment) {
        this.reComment = reComment.getReCommentContent();
    }
}
