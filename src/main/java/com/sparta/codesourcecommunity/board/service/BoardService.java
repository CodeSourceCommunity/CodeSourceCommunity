package com.sparta.codesourcecommunity.board.service;

import com.sparta.codesourcecommunity.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
}
