package com.practiceProject.exception;

public class UserException extends CommonException{

    public UserException(String msg) {
        this.code = "-9";
        this.msg = msg + " 사용자 입니다.";
    }
}
