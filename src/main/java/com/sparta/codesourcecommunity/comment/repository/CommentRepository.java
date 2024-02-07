package com.sparta.codesourcecommunity.comment.repository;

import com.sparta.codesourcecommunity.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
