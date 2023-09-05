package com.practiceProject.exception;

public class RefreshException extends CommonException{

    public RefreshException() {
        this.code = "-01";
        this.msg = "리프레시 토큰이 만료되었습니다.";
    }
}
