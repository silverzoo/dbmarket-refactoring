package com.elice.team1.prometheus.common.exception;

public class UnauthorizedModifyByUser extends SecurityException{
    public UnauthorizedModifyByUser(String userName) {
        super("\'" + userName + "\' 님은 수정 권한이 없습니다.");
    }
}
