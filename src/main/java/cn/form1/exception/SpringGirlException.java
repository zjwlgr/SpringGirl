package cn.form1.exception;

import cn.form1.enums.ResultEnum;

/**
 * 创建自己的Exception类
 */

//必须继承RuntimeException，spring框架才能回滚异常
public class SpringGirlException extends RuntimeException {

    private Integer code;

    public SpringGirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());//调用父类的构造方法，将msg传给父类，父类就可以打印这个msg
        this.code = resultEnum.getCode();//为code赋值
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
