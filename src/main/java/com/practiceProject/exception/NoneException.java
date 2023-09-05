package com.practiceProject.exception;

public class NoneException extends CommonException {
    public NoneException(String msg) {
        super();
        this.code = "-8";
        this.msg = "존재하지 않는 데이터가 있습니다.";
    }
}
