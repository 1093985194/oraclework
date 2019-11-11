package com.oyzy.oraclework.bean;

import java.util.List;

public class Custom {
    private Integer Cid;
    private String Cname;
    private String Sex;
    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public Integer getCid() {
        return Cid;
    }

    public void setCid(Integer cid) {
        Cid = cid;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "Cid=" + Cid +
                ", Cname='" + Cname + '\'' +
                ", Sex='" + Sex + '\'' +
                '}';
    }
}
