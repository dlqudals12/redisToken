package com.practiceProject.exception;

public class NoAccessUserException extends CommonException {

    public NoAccessUserException() {
        super();
        this.code = "-6";
        this.msg = "접근 할 수 없는 사용자 입니다.";
    }

}
