package cn.form1.domain;

public class YiArticle extends Page {
    private Integer id;

    private String classOne;

    private Integer classOneId;

    private String classTwo;

    private Integer classTwoId;

    private String title;

    private String image;

    private Boolean isHome;

    private Boolean isRelease;

    private Integer sort;

    private Integer clickNum;

    private Integer managerId;

    private Integer ctime;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassOne() {
        return classOne;
    }

    public void setClassOne(String classOne) {
        this.classOne = classOne == null ? null : classOne.trim();
    }

    public Integer getClassOneId() {
        return classOneId;
    }

    public void setClassOneId(Integer classOneId) {
        this.classOneId = classOneId;
    }

    public String getClassTwo() {
        return classTwo;
    }

    public void setClassTwo(String classTwo) {
        this.classTwo = classTwo == null ? null : classTwo.trim();
    }

    public Integer getClassTwoId() {
        return classTwoId;
    }

    public void setClassTwoId(Integer classTwoId) {
        this.classTwoId = classTwoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Boolean getIsHome() {
        return isHome;
    }

    public void setIsHome(Boolean isHome) {
        this.isHome = isHome;
    }

    public Boolean getIsRelease() {
        return isRelease;
    }

    public void setIsRelease(Boolean isRelease) {
        this.isRelease = isRelease;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getCtime() {
        return ctime;
    }

    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}