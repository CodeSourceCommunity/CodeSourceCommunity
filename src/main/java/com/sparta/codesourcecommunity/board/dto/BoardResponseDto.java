package com.sparta.codesourcecommunity.board.dto;

import com.sparta.codesourcecommunity.board.entity.Board;
import lombok.Getter;

@Getter

public class BoardResponseDto {

    private Long boardId;
    private String title;
    private String contents;
    private String subtitle;

    public BoardResponseDto(Board board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.subtitle = board.getSubtitle();
    }
}