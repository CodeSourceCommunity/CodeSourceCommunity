package com.sparta.codesourcecommunity.exception;

public class NotFoundMemberException extends IllegalArgumentException {

    public NotFoundMemberException() {
        super("등록된 유저가 없습니다.");
    }
}
