package com.sparta.codesourcecommunity.recomment.repository;

import com.sparta.codesourcecommunity.recomment.entity.ReComment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReCommentRepository extends JpaRepository<ReComment,Long> {

    List<ReComment> findAllByComment_CommentId(Long commentId);
}
