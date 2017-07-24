package cn.form1.domain;

/**
 * 为多表查询所创建的关联类
 */
public class ArticleJoinClass {

    private Integer id;
    private Integer class_one_id;
    private Integer class_two_id;
    private String class_two;

    private YiClass tempClass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClass_one_id() {
        return class_one_id;
    }

    public void setClass_one_id(Integer class_one_id) {
        this.class_one_id = class_one_id;
    }

    public Integer getClass_two_id() {
        return class_two_id;
    }

    public void setClass_two_id(Integer class_two_id) {
        this.class_two_id = class_two_id;
    }

    public String getClass_two() {
        return class_two;
    }

    public void setClass_two(String class_two) {
        this.class_two = class_two;
    }

    public YiClass getTempClass() {
        return tempClass;
    }

    public void setTempClass(YiClass tempClass) {
        this.tempClass = tempClass;
    }
}
