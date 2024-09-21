package com.elice.team1.dbmarket.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


//import com.elice.exception.ContactNotFoundException;

//package com.elice.exception;
//
//public class ContactNotFoundException extends RuntimeException {
//
//    public ContactNotFoundException() {
//        super("연락처를 찾을 수 없습니다.");
//    }
//}
//TODO Ioexception return RuntimeException()

@Aspect
@Component
public class ItemAopException {



    // 테스트를 위한 코드입니다. 수정하지 말아주세요.
    private Logger log = LoggerFactory.getLogger(ItemAopException.class);

    public void setLogger(Logger logger) {
        this.log = logger;
    }
    // 여기까지

    // 지시사항을 참고하여 코드를 작성해 보세요.

    // 호출시 메서드 필요없음
    // @Around("loggingMethod()")
    // public void loggingAroundError(JoinPoint joinPoint){
    //     log.info("[메서드 호출] 호출 메서드: "+joinPoint.getSignature().getName());
    // }


    // 포인트컷 정의

    // 정답
    @Pointcut("execution(* com.elice.service.ContactService.*(..))")
    // 정답
    // @Pointcut("execution(* * com.elice.service.ContactService.*(..))")
    // com.elice.service 패키지 내 모든 메서드 대상
    // 정답
    // @Pointcut("execution(* * com.elice.service.*.*(..))")
    private void targetMethod(){}
    // // 예외 발생 시 로깅처리
    // // ContactNotFoundException이 발생한 경우 로그 기록
    // @AfterThrowing(pointcut = "targetMethod()", throwing = "ex")
    // public void logAfterThrowing(JoinPoint joinPoint, ContactNotFoundException ex) {
    //     // 예외가 발생한 메서드명과 예외 메시지 로그 기록
    //     log.error("[예외 발생] 메서드: {}, 에러 메시지: {}", joinPoint.getSignature().toShortString(), ex.getMessage());
    // }


//    @AfterThrowing(pointcut = "targetMethod()", throwing = "ex")
//    // 메소드명이 logAfterThrowing 아니면 에러가 생김 ㅡㅡ;
//    public void logAfterThrowing(JoinPoint joinPoint, ContactNotFoundException ex){
//        // log.error("[에러발생] "+ joinPoint.getSignature().getName() +"[에러 메세지] " + ex.getMessage() );
//        log.error("[예외 발생] 메서드: {}, 에러 메시지: {}", joinPoint.getSignature().toShortString(), ex.getMessage());
//
//    }
    @AfterThrowing(pointcut = "targetMethod()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex){
    log.error("[예외 발생] 메서드: {}, 에러 메시지: {}", joinPoint.getSignature().toShortString(), ex.getMessage());
    }

}
