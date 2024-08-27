package com.example.team1.Prometheus.aop;

import jakarta.servlet.ServletException;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;


//전역 예외처리
@RestControllerAdvice
@ControllerAdvice
public class ControllerException {
//    Logger log = Logger.getLogger("warnning");


//TODO ServletException => return RuntimeException(e);
//TODO Ioexception return RuntimeException()

    @ExceptionHandler(ServletException.class)
    public RuntimeException ServletException(ServletException s){
        return new RuntimeException(s.getMessage());
    }
    @ExceptionHandler(IOException.class)
    public RuntimeException IOException(IOException i){
        return new RuntimeException(i.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public void NullPointerException(NullPointerException n) {
//        ErrorResponse response = new ErrorResponse(404, n.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public String Exception(Exception e) {
        System.out.println("Exception");
        return "sql grammar error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String RuntimeException(RuntimeException r) {
        System.out.println("run time exception");
        return "run time exception";
    }


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
