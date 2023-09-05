package com.practiceProject.exception;

public class NoMatchesException extends CommonException {

    public NoMatchesException(String msg) {
        this.code = "-2";
        this.msg = msg + " 일치하지 않습니다.";
    }
}
