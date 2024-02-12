package com.sparta.codesourcecommunity.board.dto;

import com.sparta.codesourcecommunity.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponseDto {
  private String title;
  private String contents;

  public BoardResponseDto(Board board) {
    this.title = board.getTitle();
    this.contents = board.getContents();
  }
}
