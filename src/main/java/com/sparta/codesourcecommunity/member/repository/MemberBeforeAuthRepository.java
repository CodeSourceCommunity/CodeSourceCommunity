package com.sparta.codesourcecommunity.member.repository;

import com.sparta.codesourcecommunity.member.entity.MemberBeforeEmailAuth;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBeforeAuthRepository extends JpaRepository<MemberBeforeEmailAuth, Long> {

    void deleteByEmail(String email);
    Optional<MemberBeforeEmailAuth> findByEmail(String email);
}
