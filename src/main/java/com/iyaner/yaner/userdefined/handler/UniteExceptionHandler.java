package com.iyaner.yaner.userdefined.handler;

import com.iyaner.yaner.entity.utils.CodeEnum;
import com.iyaner.yaner.entity.utils.PageResult;
import com.iyaner.yaner.entity.utils.UniteException;
import com.iyaner.yaner.utils.PageResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UniteExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public PageResult handler(Exception e){
        if(e instanceof UniteException){
            UniteException ue= (UniteException) e;
            return PageResultUtils.error(CodeEnum.ERROR);
        }else{
            return null;
        }
    }

}
