package com.sparta.codesourcecommunity.comment.repository;

import com.sparta.codesourcecommunity.board.entity.Board;
import com.sparta.codesourcecommunity.comment.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBoard_BoardId(Long boardId);
}
