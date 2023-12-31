package com.itheima.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {

        if (ex.getMessage().contains("Duplicate entry")) {
            String[] strings = ex.getMessage().split(" ");
            String msg = strings[2] + "已存在";
            return R.error(msg);
        }

        return R.error("未知错误");
    }


    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException ex) {

        return R.error(ex.getMessage());

    }

}
