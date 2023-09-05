package com.practiceProject.exception;

public class ResignException extends CommonException {
    public ResignException() {
        this.code = "-10";
        this.msg = "이미 탈퇴된 사용자입니다.";
    }
}
