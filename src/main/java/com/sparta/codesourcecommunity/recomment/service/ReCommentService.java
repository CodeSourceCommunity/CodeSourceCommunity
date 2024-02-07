package com.sparta.codesourcecommunity.recomment.service;

import com.sparta.codesourcecommunity.recomment.repository.ReCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReCommentService {

    private final ReCommentRepository reCommentRepository;
}
