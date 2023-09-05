package com.practiceProject.exception;

public class DBException extends CommonException {
    public DBException(String msg) {
        super();
        this.code = "-1";
        this.msg = msg + "  실패했습니다.";
    }
}
