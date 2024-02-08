package com.sparta.codesourcecommunity.member.controller;

import com.sparta.codesourcecommunity.common.CommonResponseDto;
import com.sparta.codesourcecommunity.member.exception.DuplicateMemberException;
import com.sparta.codesourcecommunity.member.exception.NotFoundMemberException;
import com.sparta.codesourcecommunity.member.exception.NotMatchPasswordException;
import com.sparta.codesourcecommunity.member.exception.PasswordMismatchException;
import com.sparta.codesourcecommunity.member.exception.SamePasswordException;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class MemberExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponseDto> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.joining(", "));

        return ResponseEntity.badRequest()
            .body(new CommonResponseDto(errorMessage, HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<CommonResponseDto> handleDuplicateMemberException(
        DuplicateMemberException e) {
        return ResponseEntity.badRequest()
            .body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(NotFoundMemberException.class)
    public ResponseEntity<CommonResponseDto> handleNotFoundMemberException(
        NotFoundMemberException e) {
        return ResponseEntity.badRequest()
            .body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(NotMatchPasswordException.class)
    public ResponseEntity<CommonResponseDto> handleNotMatchPasswordException(
        NotMatchPasswordException e) {
        return ResponseEntity.badRequest()
            .body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(SamePasswordException.class)
    public ResponseEntity<CommonResponseDto> handleSamePasswordException(SamePasswordException e) {
        return ResponseEntity.badRequest()
            .body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<CommonResponseDto> handlePasswordMismatchException(
        PasswordMismatchException e) {
        return ResponseEntity.badRequest()
            .body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

}
