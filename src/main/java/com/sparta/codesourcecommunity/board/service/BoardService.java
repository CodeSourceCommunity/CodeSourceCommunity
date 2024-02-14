package com.sparta.codesourcecommunity.board.service;

import com.sparta.codesourcecommunity.board.dto.BoardGetResponseDto;
import com.sparta.codesourcecommunity.board.dto.BoardRequestDto;
import com.sparta.codesourcecommunity.board.dto.BoardResponseDto;
import com.sparta.codesourcecommunity.board.entity.Board;
import com.sparta.codesourcecommunity.board.repository.BoardRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {


    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public List<BoardGetResponseDto> getAllBoard()
    {
        List<Board> boardList = boardRepository.findAll();
        List<BoardGetResponseDto> boardResponseDtos = new ArrayList<>();
        for(int i=0; i<boardList.size(); i++) {
            boardResponseDtos.add(new BoardGetResponseDto(boardList.get(i)));
        }
        return boardResponseDtos;
    }



    public BoardGetResponseDto getBoardById(Long id) {
        Board board = boardRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("잘못된 Board ID 입니다."));
        return new BoardGetResponseDto(board);
    }

    @Transactional
    public Board createBoard(BoardRequestDto board) {
        return boardRepository.save(board.toEntity());
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto updateBoard) {
        Board board = boardRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("잘못된 Board ID 입니다."));
        board.update(updateBoard.getTitle(), updateBoard.getContents());

        BoardResponseDto boardResponseDto = new BoardResponseDto(board);
        return boardResponseDto;
    }

    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
