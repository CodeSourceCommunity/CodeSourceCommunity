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
public class BoardRequestDto {
  private String title;
  private String contents;

  public Board toEntity() {
    return new Board(title, contents);
  }
}
