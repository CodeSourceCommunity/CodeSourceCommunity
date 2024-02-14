package com.sparta.codesourcecommunity.board.controller;

import com.sparta.codesourcecommunity.board.dto.BoardGetResponseDto;
import com.sparta.codesourcecommunity.board.dto.BoardRequestDto;
import com.sparta.codesourcecommunity.board.dto.BoardResponseDto;
import com.sparta.codesourcecommunity.board.service.BoardService;
import com.sparta.codesourcecommunity.common.CommonResponseDto;
import com.sparta.codesourcecommunity.security.MemberDetailsImpl;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    private final BoardService boardService;


    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/{boardId}")
    public BoardGetResponseDto getBoardById(@PathVariable Long boardId) {
        return boardService.getBoardById(boardId);
    }


    @GetMapping
    public List<BoardGetResponseDto> getAllBoard() {
        return boardService.getAllBoard();
    }


    @PostMapping
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto board, @AuthenticationPrincipal
    MemberDetailsImpl memberDetails) {

        return boardService.createBoard(board, memberDetails);
    }

    @PatchMapping("/{boardId}")
    public BoardResponseDto updateBoard(@PathVariable Long boardId,
        @RequestBody BoardRequestDto updateBoard, @AuthenticationPrincipal
    MemberDetailsImpl memberDetails) {
        return boardService.updateBoard(boardId, updateBoard, memberDetails);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<CommonResponseDto> deleteBoard(@PathVariable Long boardId,
        @AuthenticationPrincipal
        MemberDetailsImpl memberDetails) {
        boardService.deleteBoard(boardId, memberDetails);
        return ResponseEntity.ok()
            .body(new CommonResponseDto("게시글이 삭제되었습니다.", HttpStatus.OK.value()));
    }
}