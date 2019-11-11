package com.oyzy.oraclework.bean;

public class Goods {
    private Integer Gid;
    private String Gname;

    @Override
    public String toString() {
        return "Goods{" +
                "Gid=" + Gid +
                ", Gname='" + Gname + '\'' +
                '}';
    }

    public Integer getGid() {
        return Gid;
    }

    public void setGid(Integer gid) {
        Gid = gid;
    }

    public String getGname() {
        return Gname;
    }

    public void setGname(String gname) {
        Gname = gname;
    }
}
