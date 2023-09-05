package com.practiceProject.dto.response;

import lombok.Data;

@Data
public class DefaultResponse {

    private String code;
    private String msg;
    private Object result;

    public DefaultResponse() {
        this.code = "0000";
        this.msg = "성공";
        this.result = null;
    }

    public DefaultResponse(Object result) {
        this.code = "0000";
        this.msg = "성공";
        this.result = result;
    }

    public DefaultResponse(String code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

}