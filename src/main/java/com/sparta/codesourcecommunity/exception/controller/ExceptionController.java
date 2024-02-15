package com.sparta.codesourcecommunity.exception.controller;

import com.sparta.codesourcecommunity.exception.DeletionNotAllowedException;
import com.sparta.codesourcecommunity.exception.DuplicateMemberException;
import com.sparta.codesourcecommunity.exception.NotFoundBoardException;
import com.sparta.codesourcecommunity.exception.NotFoundCommentException;
import com.sparta.codesourcecommunity.exception.NotFoundMemberException;
import com.sparta.codesourcecommunity.exception.NotFoundReCommentException;
import com.sparta.codesourcecommunity.exception.NotMatchPasswordException;
import com.sparta.codesourcecommunity.exception.PasswordMismatchException;
import com.sparta.codesourcecommunity.exception.SamePasswordException;
import com.sparta.codesourcecommunity.exception.UnmodifiableException;
import com.sparta.codesourcecommunity.exception.dto.ExceptionDto;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.joining(", "));

        return ResponseEntity.badRequest()
            .body(new ExceptionDto(errorMessage, HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<ExceptionDto> handleDuplicateMemberException(
        DuplicateMemberException e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler({
        NotFoundMemberException.class,
        NotFoundBoardException.class,
        NotFoundCommentException.class,
        NotFoundReCommentException.class
    })
    public ResponseEntity<ExceptionDto> handleNotFoundException(Exception e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(NotMatchPasswordException.class)
    public ResponseEntity<ExceptionDto> handleNotMatchPasswordException(
        NotMatchPasswordException e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(SamePasswordException.class)
    public ResponseEntity<ExceptionDto> handleSamePasswordException(SamePasswordException e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ExceptionDto> handlePasswordMismatchException(
        PasswordMismatchException e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
    @ExceptionHandler(UnmodifiableException.class)
    public ResponseEntity<ExceptionDto> handleUnmodifiableException(
        UnmodifiableException e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(DeletionNotAllowedException.class)
    public ResponseEntity<ExceptionDto> handleDeletionNotAllowedException(
        DeletionNotAllowedException e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }



}
