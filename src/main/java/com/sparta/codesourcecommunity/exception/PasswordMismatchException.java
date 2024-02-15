package com.sparta.codesourcecommunity.exception;

public class PasswordMismatchException extends IllegalArgumentException {

    public PasswordMismatchException() {
        super("변경할 비밀번호가 일치하지 않습니다.");
    }
}
