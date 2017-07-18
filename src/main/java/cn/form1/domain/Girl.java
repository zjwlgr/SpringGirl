package cn.form1.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity //表示当前Girl类对应了一个数据的表
public class Girl {

    /*
    *   验证框中@NotEmpty、@NotBlank、@NotNull乍一看还是容易弄混的。主要使用情况记录一下：
        @NotEmpty 用在集合类上面
        @NotBlank 用在String上面
        @NotNull    用在基本类型上
    * */

    @Id
    @GeneratedValue  //表示自增
    private Integer id;

    @NotBlank(message = "这个字段必须传")
    private String cupSize;

    @Min(value = 18, message = "age小于18，不可以加入")
    private Integer age;

    @NotNull(message = "金额必传")
    private Double money;

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Girl() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Girl{" +
                "id=" + id +
                ", cupSize='" + cupSize + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
