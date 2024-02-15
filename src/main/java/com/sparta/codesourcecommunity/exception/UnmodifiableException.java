package com.sparta.codesourcecommunity.exception;

public class UnmodifiableException extends IllegalArgumentException{

    public UnmodifiableException() {super("수정 권한이 없습니다.");}
}
