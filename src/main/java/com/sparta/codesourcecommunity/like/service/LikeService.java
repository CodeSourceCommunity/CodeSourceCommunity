package com.sparta.codesourcecommunity.like.service;

import com.sparta.codesourcecommunity.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
}
