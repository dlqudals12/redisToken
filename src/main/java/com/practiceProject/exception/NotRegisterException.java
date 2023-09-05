package com.practiceProject.exception;

public class NotRegisterException extends CommonException{
    public NotRegisterException(String msg) {
        super();
        this.code = "-5";
        this.msg = msg + " 등록해주세요.";
    }
}
