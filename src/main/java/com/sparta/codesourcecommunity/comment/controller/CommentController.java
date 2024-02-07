package com.sparta.codesourcecommunity.comment.controller;

import com.sparta.codesourcecommunity.comment.dto.CommentRequestDto;
import com.sparta.codesourcecommunity.comment.dto.CommentResponseDto;
import com.sparta.codesourcecommunity.security.MemberDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
public class CommentController {

    @PostMapping("")
    public CommentResponseDto createComment(){

        return null;
    }

    @GetMapping("")
    public ResponseEntity<?> readComment(){

        return null;
    }

    @PatchMapping("/{commentId}")
    public CommentResponseDto updateComment(){

        return null;
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(){

        return null;
    }




}
