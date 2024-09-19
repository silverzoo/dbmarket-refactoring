package com.elice.team1.dbmarket.common.exception.handler;


import org.springframework.web.bind.annotation.RestControllerAdvice;


//전역 예외처리
@RestControllerAdvice
public class RestControllerExceptionHandler {
}
//    Logger log = Logger.getLogger("warnning");


//TODO ServletException => return RuntimeException(e);
// TODO NullPointer Exception => userID 에러일경우
//    @ExceptionHandler(ServletException.class)
//    public RuntimeException ServletException(ServletException s){
//        return new RuntimeException(s.getMessage());
//    }
//
//    @ExceptionHandler(NullPointerException.class)
//    public void NullPointerException(NullPointerException n) {
////        ErrorResponse response = new ErrorResponse(404, n.getMessage());
////        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//


//TODO 용량처리 2024-08-28T14:07:53.808+09:00  WARN 3208 --- [Prometheus] [nio-8080-exec-7] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.multipart.MaxUploadSizeExceededException: Maximum upload size exceeded]
//TODO IOException 파일 저장 문제
//    @ExceptionHandler(IOException.class)
//    public RuntimeException IOException(IOException i){
//        return new RuntimeException(i.getMessage());
//    }
//TODO ConstraintViolationException => valid 문제 model이나 body 정보
// TODO
//    @ExceptionHandler(SQLSyntaxErrorException.class)
//    public ResponseEntity<ErrorResponse> SqlSyntaxErrorException(SQLSyntaxErrorException s) {
//        System.out.println("sql syntax error");
//        ErrorResponse response = new ErrorResponse(404, s.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//    @ExceptionHandler(BadSqlGrammarException.class)
//    public ResponseEntity<ErrorResponse> BadSqlGrammarException(BadSqlGrammarException b) {
//        System.out.println("bad grammar");
//        ErrorResponse response = new ErrorResponse(404, b.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }