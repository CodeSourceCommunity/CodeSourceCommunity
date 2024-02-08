package com.sparta.codesourcecommunity.recomment.service;

import com.sparta.codesourcecommunity.comment.entity.Comment;
import com.sparta.codesourcecommunity.comment.repository.CommentRepository;
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

@Service
@RequiredArgsConstructor
public class ReCommentService {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final ReCommentRepository reCommentRepository;

    public ReCommentResponseDto createReComment(Long commentId, ReCommentRequestDto reCommentRequestDto, MemberDetailsImpl memberDetails) {
        Member member = memberRepository.findById(memberDetails.getMember().getMemberId()).orElseThrow();
        Comment comment = commentRepository.findById(commentId).orElseThrow();
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
}
