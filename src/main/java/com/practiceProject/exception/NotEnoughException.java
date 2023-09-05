package com.practiceProject.exception;

public class NotEnoughException extends CommonException{
    public NotEnoughException(String msg) {
        super();
        this.code = "-4";
        this.msg = msg + " 부족합니다.";
    }
}
