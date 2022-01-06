package com.yang.app.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 익셉션 발생 시 해당 클래스로 들어온다.
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public String HandleArgumentException(IllegalArgumentException e){
        return "<h1>"+e.getMessage()+"</h1>";
    }

}
