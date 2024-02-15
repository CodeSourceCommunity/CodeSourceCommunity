package com.sparta.codesourcecommunity.recomment.service;

import com.sparta.codesourcecommunity.comment.entity.Comment;
import com.sparta.codesourcecommunity.comment.repository.CommentRepository;
import com.sparta.codesourcecommunity.exception.DeletionNotAllowedException;
import com.sparta.codesourcecommunity.exception.NotFoundCommentException;
import com.sparta.codesourcecommunity.exception.NotFoundMemberException;
import com.sparta.codesourcecommunity.exception.NotFoundReCommentException;
import com.sparta.codesourcecommunity.exception.UnmodifiableException;
import com.sparta.codesourcecommunity.exception.dto.ExceptionDto;
import com.sparta.codesourcecommunity.member.entity.Member;
import com.sparta.codesourcecommunity.member.repository.MemberRepository;
import com.sparta.codesourcecommunity.recomment.dto.ReCommentRequestDto;
import com.sparta.codesourcecommunity.recomment.dto.ReCommentResponseDto;
import com.sparta.codesourcecommunity.recomment.entity.ReComment;
import com.sparta.codesourcecommunity.recomment.repository.ReCommentRepository;
import com.sparta.codesourcecommunity.security.MemberDetailsImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReCommentService {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final ReCommentRepository reCommentRepository;

    public ReCommentResponseDto createReComment(Long commentId, ReCommentRequestDto reCommentRequestDto, MemberDetailsImpl memberDetails) {
        Member member = memberRepository.findById(memberDetails.getMember().getMemberId())
            .orElseThrow(NotFoundMemberException::new);
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(NotFoundCommentException::new);
        ReComment saveReComment = new ReComment(reCommentRequestDto.getReComment(), member, comment);

        reCommentRepository.save(saveReComment);

        return new ReCommentResponseDto(saveReComment);
    }

    public List<ReCommentResponseDto> readReComment(Long commentId) {
        List<ReComment> reCommentList = reCommentRepository.findAllByComment_CommentId(commentId);
        List<ReCommentResponseDto> reCommentResponseDtos = new ArrayList<>();

        for (ReComment reComment : reCommentList) {
            reCommentResponseDtos.add(new ReCommentResponseDto(reComment));
        }

        return reCommentResponseDtos;
    }

    @Transactional
    public ReCommentResponseDto updateReComment(Long recommentId, ReCommentRequestDto reCommentRequestDto, MemberDetailsImpl memberDetails) {
        ReComment reComment = reCommentRepository.findById(recommentId)
            .orElseThrow(NotFoundReCommentException::new);

        if (!(reComment.getMember().getMemberId() == memberDetails.getMember().getMemberId())){
            throw new UnmodifiableException();
        }

        reComment.update(reCommentRequestDto);

        return new ReCommentResponseDto(reComment);
    }

    public ExceptionDto deleteComment(Long recommentId, MemberDetailsImpl memberDetails) {
        ReComment reComment = reCommentRepository.findById(recommentId)
            .orElseThrow(NotFoundReCommentException::new);

        if (!(reComment.getMember().getMemberId() == memberDetails.getMember().getMemberId())){
            throw new DeletionNotAllowedException();
        }

        reCommentRepository.delete(reComment);
        String message = "삭제가 정상적으로 처리되었습니다.";

        return new ExceptionDto(message, 200);

    }
}
