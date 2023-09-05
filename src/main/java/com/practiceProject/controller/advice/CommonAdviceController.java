package com.practiceProject.controller.advice;


import com.practiceProject.dto.response.DefaultResponse;
import com.practiceProject.exception.CommonException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonAdviceController {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity commonException(CommonException e) {

        DefaultResponse res = new DefaultResponse(e.getCode(), e.getMsg(), null);
        return ResponseEntity.ok(res);
    }


}
