package com.sparta.codesourcecommunity.exception;

public class DeletionNotAllowedException extends IllegalArgumentException{

    public DeletionNotAllowedException() {super("삭제 권한이 없습니다.");}
}
