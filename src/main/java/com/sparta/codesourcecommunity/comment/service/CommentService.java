package com.sparta.codesourcecommunity.comment.service;

import com.sparta.codesourcecommunity.board.entity.Board;
import com.sparta.codesourcecommunity.board.repository.BoardRepository;
import com.sparta.codesourcecommunity.comment.dto.CommentRequestDto;
import com.sparta.codesourcecommunity.comment.dto.CommentResponseDto;
import com.sparta.codesourcecommunity.comment.entity.Comment;
import com.sparta.codesourcecommunity.comment.repository.CommentRepository;
import com.sparta.codesourcecommunity.member.entity.Member;
import com.sparta.codesourcecommunity.member.repository.MemberRepository;
import com.sparta.codesourcecommunity.security.MemberDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(Long boardId, CommentRequestDto commentRequestDto, MemberDetailsImpl memberDetails) {
        Member member = memberRepository.findById(memberDetails.getMember().getMemberId()).orElseThrow();
        Board board = boardRepository.findById(boardId).orElseThrow();
        Comment saveComment = new Comment(commentRequestDto.getComment(), member, board);
        commentRepository.save(saveComment);

        return new CommentResponseDto(saveComment);

    }
}
