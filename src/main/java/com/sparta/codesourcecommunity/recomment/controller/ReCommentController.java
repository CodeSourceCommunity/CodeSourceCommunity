package com.sparta.codesourcecommunity.recomment.controller;

import com.sparta.codesourcecommunity.recomment.dto.ReCommentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board/{boardId}/comment/{commentId}/recomment")
public class ReCommentController {

    @PostMapping("")
    public ReCommentResponseDto createReComment(){

        return null;
    }

    @GetMapping("")
    public ResponseEntity<?> readReComment(){

        return null;
    }

    @PatchMapping("/{recommentId}")
    public ReCommentResponseDto updateReComment(){

        return null;
    }

    @DeleteMapping("/{recommentId}")
    public ResponseEntity<?> deleteReComment(){

        return null;
    }




}
