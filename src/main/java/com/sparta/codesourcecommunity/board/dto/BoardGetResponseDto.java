package com.sparta.codesourcecommunity.board.dto;

import com.sparta.codesourcecommunity.board.entity.Board;
import lombok.Getter;

@Getter
public class BoardGetResponseDto {
  private Long boardId;
  private String title;
  private String subtitle;
  private String contents;
  private String nickname;

  public BoardGetResponseDto(Board board) {
    this.boardId = board.getBoardId();
    this.title = board.getTitle();
    this.subtitle = board.getSubtitle();
    this.contents = board.getContents();
    this.nickname = board.getMember().getNickname();
  }
}