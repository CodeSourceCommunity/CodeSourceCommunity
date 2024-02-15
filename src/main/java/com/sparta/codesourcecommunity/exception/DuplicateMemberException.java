package com.sparta.codesourcecommunity.exception;

public class DuplicateMemberException extends IllegalArgumentException {

    public DuplicateMemberException() {
        super("이미 존재하는 유저 입니다.");
    }
}
