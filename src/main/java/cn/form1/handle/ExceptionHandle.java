package cn.form1.handle;

import cn.form1.domain.Result;
import cn.form1.enums.ResultEnum;
import cn.form1.exception.SpringGirlException;
import cn.form1.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常捕获处理类
 */
// @ControllerAdvice是Spring 3.2新增的注解，
// 主要是用来Controller的一些公共的需求的低侵入性增强提供辅助，
// 作用于@RequestMapping标注的方法上。
@ControllerAdvice
public class ExceptionHandle {

    //Logger是Spring自带的一个日志框架
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /*
    * 捕获异常的方法
    * @ExceptionHandler 捕获异常注解
    * */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof SpringGirlException) {//判断捕获的是不是自己定义的异常类

            //如果是，将当前异常强转为SpringGirlException自己的异常类型,,,e为自己的异常类
            SpringGirlException springGirlException = (SpringGirlException) e;

            //e == springGirlException在Service已被实例化，被捕获过来，所以可调用该类中的方法
            return ResultUtil.error(springGirlException.getCode(), springGirlException.getMessage());
            //.getMessage()为调用父类Exception的方法
            //调用统一异常管理方法

        }else {//如果是系统抛出的异常

            logger.error("【系统异常】{}", e);//打印日志，方便调试，在控制台打印错误日志

            //使用枚举 code -1 , msg 未知错误
            ResultEnum resultEnum = ResultEnum.UNKONW_ERROR;

            return ResultUtil.error(resultEnum.getCode(), resultEnum.getMsg());//调用统一异常管理方法
        }
    }
    /*   作用于@RequestMapping标注的方法上
         @ExceptionHandler 捕获异常注解
         所以发生在controller中的异常会被捕获
     */

}
