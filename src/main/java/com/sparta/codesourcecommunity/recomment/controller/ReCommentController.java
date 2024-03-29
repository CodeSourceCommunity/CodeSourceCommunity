package com.sparta.codesourcecommunity.recomment.controller;

import com.sparta.codesourcecommunity.exception.dto.ExceptionDto;
import com.sparta.codesourcecommunity.recomment.dto.ReCommentRequestDto;
import com.sparta.codesourcecommunity.recomment.dto.ReCommentResponseDto;
import com.sparta.codesourcecommunity.recomment.service.ReCommentService;
import com.sparta.codesourcecommunity.security.MemberDetailsImpl;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/boards/{boardId}/comments/{commentId}/recomments")
@RequiredArgsConstructor
public class ReCommentController {

    private final ReCommentService reCommentService;

    @PostMapping("")
    public ReCommentResponseDto createReComment(
        @PathVariable Long commentId,
        @Valid @RequestBody ReCommentRequestDto reCommentRequestDto,
        @AuthenticationPrincipal MemberDetailsImpl memberDetails) {

        return reCommentService.createReComment(commentId, reCommentRequestDto, memberDetails);
    }

    @GetMapping("")
    public List<ReCommentResponseDto> readReComment(@PathVariable Long commentId) {

        return reCommentService.readReComment(commentId);
    }

    @PatchMapping("/{recommentId}")
    public ReCommentResponseDto updateReComment(
        @PathVariable Long recommentId,
        @Valid @RequestBody ReCommentRequestDto reCommentRequestDto,
        @AuthenticationPrincipal MemberDetailsImpl memberDetails) {

        return reCommentService.updateReComment(recommentId, reCommentRequestDto, memberDetails);
    }

    @DeleteMapping("/{recommentId}")
    public ExceptionDto deleteReComment(
        @PathVariable Long recommentId,
        @AuthenticationPrincipal MemberDetailsImpl memberDetails) {

        return reCommentService.deleteComment(recommentId, memberDetails);
    }


}
