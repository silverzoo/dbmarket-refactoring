package com.elice.team1.dbmarket.common.exception;

public class UnauthorizedCreateByUser extends SecurityException{
    public UnauthorizedCreateByUser(String userName) {
        super("\'" + userName + "\' 님은 생성 권한이 없습니다.");
    }
}
