package com.sparta.codesourcecommunity.exception;

public class NotFoundReCommentException extends IllegalArgumentException{

    public NotFoundReCommentException() {super("해당 대댓글을 찾을 수 없습니다.");}
}
