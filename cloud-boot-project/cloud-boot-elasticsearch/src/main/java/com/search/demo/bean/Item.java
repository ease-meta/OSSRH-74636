package com.search.demo.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * item实体类
 *
 * @author sk
 * @date 2020/3/30 11:20
 */
public class Item {

    private Long id;

    private String type;

    private String title;

    private String sellPoint;

    private BigDecimal price;

    private Integer num;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Item() {
    }

    public Item(Long id, String type, String title, String sellPoint, BigDecimal price, Integer num, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.sellPoint = sellPoint;
        this.price = price;
        this.num = num;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 需要分词的字段
     * @return
     */
    public static List<String> list(){
        List<String> list = new ArrayList<>();
        list.add("title");
        return list;
    }
}
