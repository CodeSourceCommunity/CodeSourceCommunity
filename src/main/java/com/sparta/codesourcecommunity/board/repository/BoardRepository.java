package com.sparta.codesourcecommunity.board.repository;

import com.sparta.codesourcecommunity.board.entity.Board;
import com.sparta.codesourcecommunity.recomment.entity.ReComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {

}
