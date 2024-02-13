package com.sparta.codesourcecommunity.board.dto;

import com.sparta.codesourcecommunity.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter


public class BoardGetResponseDto {
  private Long boardId;
  private String title;
  private String contents;
  private String nickname;

  public BoardGetResponseDto(Board board) {
    this.boardId = board.getBoardId();
    this.title = board.getTitle();
    this.contents = board.getContents();
    this.nickname = board.getMember().getNickname();
  }




}
