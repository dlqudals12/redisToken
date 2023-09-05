package com.practiceProject.exception;

public class FailException extends CommonException {
    public FailException(String msg) {
        this.code = "-7";
        this.msg = msg + " 관련된 데이터가 있어 실패했습니다.";

    }
}
