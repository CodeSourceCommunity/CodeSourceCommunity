package com.sparta.codesourcecommunity.exception;

public class NotFoundBoardException extends IllegalArgumentException {

    public NotFoundBoardException() {super("해당 보드를 찾을 수 없습니다");}
}
