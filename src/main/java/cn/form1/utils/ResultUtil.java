package cn.form1.utils;

import cn.form1.domain.Result;

/**
 * 封装Result处理方法
 */
public class ResultUtil {

    /*
    * 成功要调用的方法，有Object参数
    * @param object
    * @return Result
    * */
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(1);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    /*
    * 成功要调用的方法，重法重载，没有Object参数的
    * */
    public static Result success(){
        return success(null);
    }

    /*
    * 失败调用的方法
    * @param code 错误代码
    * @param msg 提示信息
    * @return Result
    * */
    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
