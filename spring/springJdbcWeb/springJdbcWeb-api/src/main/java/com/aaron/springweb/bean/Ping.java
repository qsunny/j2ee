package com.aaron.springweb.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Aaron.Qiu on 2016/12/12.
 */
public class Ping implements Serializable {
    private Integer id;
    private String tag;
    private Date ts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "Ping{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", ts=" + ts +
                '}';
    }
}
