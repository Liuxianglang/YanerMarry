package com.iyaner.yaner.utils;

import com.iyaner.yaner.entity.utils.CodeEnum;
import com.iyaner.yaner.entity.utils.PageResult;

public class PageResultUtils {

    public static PageResult error(CodeEnum codeEnum){
        PageResult result=new PageResult();
        result.setCode(codeEnum.getCode());
        result.setResultNote(codeEnum.getMsg());
        return result;
    }

    public static PageResult success(CodeEnum codeEnum,Object object){
        PageResult result=new PageResult();
        result.setCode(codeEnum.getCode());
        result.setResultNote(codeEnum.getMsg());
        result.setObj(object);
        return result;
    }

    public static PageResult success(Object object){
        return success(CodeEnum.SUCCESS,object);
    }
    public static PageResult success(){
        return success(null);
    }
}
