package com.practiceProject.exception;

public class RefreshException extends CommonException{

    public RefreshException() {
        this.code = "-01";
        this.msg = "토큰 오류";
    }
}
