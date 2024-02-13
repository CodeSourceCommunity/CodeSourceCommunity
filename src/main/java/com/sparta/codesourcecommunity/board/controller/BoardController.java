package com.sparta.codesourcecommunity.board.controller;

import com.sparta.codesourcecommunity.board.dto.BoardRequestDto;
import com.sparta.codesourcecommunity.board.dto.BoardResponseDto;
import com.sparta.codesourcecommunity.board.entity.Board;
import com.sparta.codesourcecommunity.board.repository.BoardRepository;
import com.sparta.codesourcecommunity.board.service.BoardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {

  @Autowired
  private final BoardService boardService;
  @Autowired
  private BoardRepository boardRepository;

  public BoardController(BoardService boardService) {
    this.boardService = boardService;
  }


  @GetMapping
  public List<Board> getAllBoard() {
    return boardService.getAllBoard();
  }

  @GetMapping("/{boardId}")
  public BoardResponseDto getBoardById(@PathVariable Long id) {
    return boardService.getBoardById(id);
  }

  @PostMapping
  public Board createBoard(@RequestBody BoardRequestDto board) {
    return boardService.createBoard(board);
  }

  @PatchMapping("/{boardId}")
  public Board updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto updateBoard,
      @PathVariable String boardId) {
    return boardService.updateBoard(id, updateBoard);
  }

  @DeleteMapping("/{boardId}")
  public void deleteBoard(@PathVariable Long id) {
    boardService.deleteBoard(id);
  }
}
