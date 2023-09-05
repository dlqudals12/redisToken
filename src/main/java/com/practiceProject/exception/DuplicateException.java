package com.practiceProject.exception;

public class DuplicateException extends CommonException {
    public DuplicateException(String msg) {
        this.code = "-3";
        this.msg = "이미 존재하는 " + msg + " 입니다.";
    }
}
