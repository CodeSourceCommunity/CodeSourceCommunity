package com.sparta.codesourcecommunity.exception;

public class SamePasswordException extends IllegalArgumentException {

    public SamePasswordException() {
        super("현재 비밀번호와 변경할 비밀번호가 같습니다.");
    }
}
