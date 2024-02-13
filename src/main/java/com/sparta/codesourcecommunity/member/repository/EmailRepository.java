package com.sparta.codesourcecommunity.member.repository;

import com.sparta.codesourcecommunity.member.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {

    Email findByEmail(String email);
    void deleteByEmail(String email);
}
