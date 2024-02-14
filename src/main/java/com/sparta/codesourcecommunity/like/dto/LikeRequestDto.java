package com.sparta.codesourcecommunity.like.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeRequestDto {

  private Long memberId;
  private Long boardId;

  public LikeRequestDto(Long memberId, Long boardId) {
    this.memberId = memberId;
    this.boardId = boardId;
  }
}
