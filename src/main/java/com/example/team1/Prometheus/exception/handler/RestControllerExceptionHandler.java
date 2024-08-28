package com.example.team1.Prometheus.exception.handler;

import jakarta.servlet.ServletException;


import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;


//전역 예외처리
@RestControllerAdvice
public class RestControllerExceptionHandler {
//    Logger log = Logger.getLogger("warnning");


//TODO ServletException => return RuntimeException(e);
    // TODO NullPointer Exception => userID 에러일경우
    /*
    Validation failed for object='itemPostDto'. Error count: 1
org.springframework.web.bind.MethodArgumentNotValidException:
Validation failed for argument [0] in public java.lang.String
com.example.team1.Prometheus.controller.RegisterItemController.saveFormToDb(com.example.team1.Prometheus.entity.ItemPostDto,jakarta.servlet.http.HttpServletRequest)
throws java.io.IOException: [Field error in object 'itemPostDto' on field 'itemInfo.price': rejected value [];
codes [typeMismatch.itemPostDto.itemInfo.price,typeMismatch.itemInfo.price,typeMismatch.price,typeMismatch.int,typeMismatch]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [itemPostDto.itemInfo.price,itemInfo.price]; arguments []; default message [itemInfo.price]]; default message [Failed to convert property value of type 'java.lang.String' to required type 'int' for property 'itemInfo.price'; For input string: ""]]

    IOException 컨트롤러단 처리
    제대로 @값을 입력해주세요

    ava.lang.StringIndexOutOfBoundsException: Range [-1, 0) out of bounds for length 0
	at java.base/jdk.internal.util.Preconditions$1.apply(Preconditions.java:55) ~[na:na]
	at java.base/jdk.internal.util.Preconditions$1.apply(Preconditions.java:52) ~[na:na]
	at java.base/jdk.internal.util.Preconditions$4.apply(Preconditions.java:213) ~[na:na]
	at java.base/jdk.internal.util.Preconditions$4.apply(Preconditions.java:210) ~[na:na]
	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:98) ~[na:na]
	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckFromToIndex(Preconditions.java:112) ~[na:na]
	at java.base/jdk.internal.util.Preconditions.checkFromToIndex(Preconditions.java:349) ~[na:na]
	at java.base/java.lang.String.checkBoundsBeginEnd(String.java:4914) ~[na:na]
	at java.base/java.lang.String.substring(String.java:2876) ~[na:na]
	at java.base/java.lang.String.substring(String.java:2849) ~[na:na]
	at com.example.team1.Prometheus.service.RegisterItemService.uploadItemToDb(RegisterItemService.java:43) ~[classes/:na]
	at com.example.team1.Prometheus.controller.RegisterItemController.saveFormToDb(RegisterItemController.java:40) ~[classes/:na]

가격만 10 넣었을 경우
나머지 부분도 같음
     */

//    -------------------------------------------------
//    TODO 사용자예외처리가 안되서 일단 주석처리함
//    -------------------------------------------------

//    @ExceptionHandler(ServletException.class)
//    public RuntimeException ServletException(ServletException s){
//        return new RuntimeException(s.getMessage());
//    }
//    @ExceptionHandler(IOException.class)
//    public RuntimeException IOException(IOException i){
//        return new RuntimeException(i.getMessage());
//    }
//
//    @ExceptionHandler(NullPointerException.class)
//    public void NullPointerException(NullPointerException n) {
////        ErrorResponse response = new ErrorResponse(404, n.getMessage());
////        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public String Exception(Exception e) {
//        System.out.println("Exception");
//        return "sql grammar error";
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public String RuntimeException(RuntimeException r) {
//        System.out.println("run time exception");
//        return "run time exception";
//    }


//    @ExceptionHandler(SQLSyntaxErrorException.class)
//    public ResponseEntity<ErrorResponse> SqlSyntaxErrorException(SQLSyntaxErrorException s) {
//        System.out.println("sql syntax error");
//        ErrorResponse response = new ErrorResponse(404, s.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(BadSqlGrammarException.class)
//    public ResponseEntity<ErrorResponse> BadSqlGrammarException(BadSqlGrammarException b) {
//        System.out.println("bad grammar");
//        ErrorResponse response = new ErrorResponse(404, b.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
}
