package com.sparta.codesourcecommunity.like.controller;

import com.sparta.codesourcecommunity.like.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

  private final LikeService likeService;

//  @PostMapping
//  public ResponseResult<?> insert(@RequestBody @Valid LikeRequestDTO likeRequestDTO) {
//    likeService.insert(likeRequestDTO);
//    return success(null);
//  }
//
//  @DeleteMapping
//  public ResponseResult<?> delete(@RequestBody @Valid LikeRequestDTO likeRequestDTO) {
//    likeService.delete(likeRequestDTO);
//    return success(null);
//  }

}
