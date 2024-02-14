package com.sparta.codesourcecommunity.board.service;

import com.sparta.codesourcecommunity.board.dto.BoardRequestDto;
import com.sparta.codesourcecommunity.board.dto.BoardResponseDto;
import com.sparta.codesourcecommunity.board.entity.Board;
import com.sparta.codesourcecommunity.board.repository.BoardRepository;
import com.sparta.codesourcecommunity.member.entity.Member;
import com.sparta.codesourcecommunity.member.repository.MemberRepository;
import com.sparta.codesourcecommunity.security.MemberDetailsImpl;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public BoardResponseDto createSchedule(BoardRequestDto boardRequestDto, MemberDetailsImpl memberDetails) {
        String title = boardRequestDto.getTitle();
        String content = boardRequestDto.getContent();
        String email = memberDetails.getMember().getEmail();

        Member member = memberRepository.findByEmail(email).orElseThrow();

        Board board = new Board(title, content, member);

        boardRepository.save(board);

        return new BoardResponseDto(board);
    }

    public BoardResponseDto readBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        
        return new BoardResponseDto(board);
    }

    public List<BoardResponseDto> readBoards() {
        List<Board> boardList = boardRepository.findAll();

        List<BoardResponseDto> boardResponseDtoList = boardList.stream()
            .map(BoardResponseDto::new)
            .toList();

        return boardResponseDtoList;
    }
}
