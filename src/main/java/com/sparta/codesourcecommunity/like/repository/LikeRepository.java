package com.sparta.codesourcecommunity.like.repository;

import com.sparta.codesourcecommunity.like.entity.Like;
import com.sparta.codesourcecommunity.recomment.entity.ReComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {

}
