package com.sparta.codesourcecommunity.comment.service;

import com.sparta.codesourcecommunity.board.entity.Board;
import com.sparta.codesourcecommunity.board.repository.BoardRepository;
import com.sparta.codesourcecommunity.comment.dto.CommentRequestDto;
import com.sparta.codesourcecommunity.comment.dto.CommentResponseDto;
import com.sparta.codesourcecommunity.comment.entity.Comment;
import com.sparta.codesourcecommunity.comment.repository.CommentRepository;
import com.sparta.codesourcecommunity.common.CommonResponseDto;
import com.sparta.codesourcecommunity.member.entity.Member;
import com.sparta.codesourcecommunity.member.repository.MemberRepository;
import com.sparta.codesourcecommunity.security.MemberDetailsImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(Long boardId, CommentRequestDto commentRequestDto,
        MemberDetailsImpl memberDetails) {
        Member member = memberRepository.findById(memberDetails.getMember().getMemberId())
            .orElseThrow();
        Board board = boardRepository.findById(boardId).orElseThrow();
        Comment saveComment = new Comment(commentRequestDto.getComment(), member, board);
        commentRepository.save(saveComment);

        return new CommentResponseDto(saveComment, member.getNickname());

    }

    public List<CommentResponseDto> readComment(Long boardId) {
        List<Comment> commentList = commentRepository.findAllByBoard_BoardId(boardId);
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();

        for (Comment comment : commentList) {
            Member member = memberRepository.findById(comment.getMember().getMemberId()).orElseThrow();
            commentResponseDtos.add(new CommentResponseDto(comment, member.getNickname()));
        }

        return commentResponseDtos;
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto,
        MemberDetailsImpl memberDetails) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();

        if (!(comment.getMember().getMemberId() == memberDetails.getMember().getMemberId())) {
            throw new IllegalArgumentException();
        }

        comment.update(commentRequestDto);

        return new CommentResponseDto(comment, comment.getMember().getNickname());

    }

    public CommonResponseDto deleteComment(Long commentId, MemberDetailsImpl memberDetails) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();

        if (!(comment.getMember().getMemberId() == memberDetails.getMember().getMemberId())) {
            throw new IllegalArgumentException();
        }

        commentRepository.delete(comment);

        String message = "삭제가 정상적으로 처리되었습니다.";
        return new CommonResponseDto(message, 200);
    }
}
