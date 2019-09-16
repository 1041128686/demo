package com.my.util;

import com.my.pojo.JsonResult;

public class ResultUtil {

    public static JsonResult ok(Object data){
        return JsonResult.ok(data);
    }
}
