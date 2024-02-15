package com.sparta.codesourcecommunity.board.service;

import com.sparta.codesourcecommunity.board.dto.BoardGetResponseDto;
import com.sparta.codesourcecommunity.board.dto.BoardRequestDto;
import com.sparta.codesourcecommunity.board.dto.BoardResponseDto;
import com.sparta.codesourcecommunity.board.entity.Board;
import com.sparta.codesourcecommunity.board.repository.BoardRepository;
import com.sparta.codesourcecommunity.exception.DeletionNotAllowedException;
import com.sparta.codesourcecommunity.exception.NotFoundBoardException;
import com.sparta.codesourcecommunity.exception.UnmodifiableException;
import com.sparta.codesourcecommunity.security.MemberDetailsImpl;
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
    public List<BoardGetResponseDto> getAllBoard() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardGetResponseDto> boardResponseDtos = new ArrayList<>();
        for (Board board : boardList) {
            boardResponseDtos.add(new BoardGetResponseDto(board));
        }
        return boardResponseDtos;
    }


    public BoardGetResponseDto getBoardById(Long id) {
        Board board = boardRepository.findById(id)
            .orElseThrow(NotFoundBoardException::new);
        return new BoardGetResponseDto(board);
    }

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto,
        MemberDetailsImpl memberDetails) {
        Board board = new Board(boardRequestDto.getTitle(), boardRequestDto.getSubtitle(),
            boardRequestDto.getContents(), memberDetails.getMember());
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto updateBoard,
        MemberDetailsImpl memberDetails) {
        Board board = boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);
        if (board.getMember().getMemberId() != memberDetails.getMember().getMemberId()) {
            throw new UnmodifiableException();
        }
        board.update(updateBoard.getTitle(), updateBoard.getSubtitle(), updateBoard.getContents());

        return new BoardResponseDto(board);
    }

    @Transactional
    public void deleteBoard(Long id, MemberDetailsImpl memberDetails) {
        Board board = boardRepository.findById(id).orElseThrow();
        if (board.getMember().getMemberId() != memberDetails.getMember().getMemberId()) {
            throw new DeletionNotAllowedException();
        }
        boardRepository.deleteById(id);
    }
}