package cn.form1.domain;

/**
 * HTTP 请求返回的最外层对象
 */
public class Result<T> {

    //错误代码
    private Integer code;

    //提示信息
    private String msg;

    //具体内容
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
