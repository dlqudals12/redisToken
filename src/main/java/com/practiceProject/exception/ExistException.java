package com.practiceProject.exception;

public class ExistException extends CommonException {

    public ExistException(String msg) {
        this.code = "-9";
        this.msg = msg + " 이미 존재합니다.";
    }
}
