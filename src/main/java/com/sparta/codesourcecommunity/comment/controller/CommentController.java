package com.sparta.codesourcecommunity.comment.controller;

import com.sparta.codesourcecommunity.comment.dto.CommentRequestDto;
import com.sparta.codesourcecommunity.comment.dto.CommentResponseDto;
import com.sparta.codesourcecommunity.comment.service.CommentService;
import com.sparta.codesourcecommunity.member.entity.Member;
import com.sparta.codesourcecommunity.security.MemberDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/board/{boardId}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("")
    public CommentResponseDto createComment(
        @PathVariable Long boardId,
        @Valid @RequestBody CommentRequestDto commentRequestDto,
        @AuthenticationPrincipal MemberDetailsImpl memberDetails) {

        return commentService.createComment(boardId, commentRequestDto, memberDetails);
    }

    @GetMapping("")
    public ResponseEntity<?> readComment() {

        return null;
    }

    @PatchMapping("/{commentId}")
    public CommentResponseDto updateComment() {

        return null;
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment() {

        return null;
    }


}
