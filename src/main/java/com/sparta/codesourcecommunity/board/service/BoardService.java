package com.sparta.codesourcecommunity.board.service;

import com.sparta.codesourcecommunity.board.dto.BoardRequestDto;
import com.sparta.codesourcecommunity.board.dto.BoardResponseDto;
import com.sparta.codesourcecommunity.board.entity.Board;
import com.sparta.codesourcecommunity.board.repository.BoardRepository;
import com.sparta.codesourcecommunity.member.repository.MemberRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public BoardService(MemberRepository memberRepository, BoardRepository boardRepository) {
      this.memberRepository = memberRepository;
      this.boardRepository = boardRepository;
    }

    @Transactional
    public List<Board> getAllBoard()
    {
        return boardRepository.findAll();
    }

    @Transactional
    public BoardResponseDto getBoardById(Long id) {
        Board board = boardRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("잘못된 Board ID 입니다."));
        return new BoardResponseDto(board);
    }

    @Transactional
    public Board createBoard(BoardRequestDto board) {
        return boardRepository.save(board.toEntity());
    }

    @Transactional
    public Board updateBoard(Long id, BoardRequestDto updateBoard) {
        Board board = boardRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("잘못된 Board ID 입니다."));
        board.update(updateBoard.getTitle(), board.getContents());

        return boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}

