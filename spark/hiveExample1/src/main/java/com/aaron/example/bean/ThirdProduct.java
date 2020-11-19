package com.aaron.example.bean;

import java.io.Serializable;

/**
 * <p>第三方产品实体类</p>
 * <p> Aaron.Qiu </p>
 * <p>Version: 1.0.0</p>
 * <p>Create Date： 2020-10-22 16:20:29 </p>
 */
public class ThirdProduct implements Serializable {
    private Long id;
    private String part_no;
    private String brand;
    private Integer entity_catetory_id;
    private String entity_catetory_name;
    private String third_part_no;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPart_no() {
        return part_no;
    }

    public void setPart_no(String part_no) {
        this.part_no = part_no;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getEntity_catetory_id() {
        return entity_catetory_id;
    }

    public void setEntity_catetory_id(Integer entity_catetory_id) {
        this.entity_catetory_id = entity_catetory_id;
    }

    public String getEntity_catetory_name() {
        return entity_catetory_name;
    }

    public void setEntity_catetory_name(String entity_catetory_name) {
        this.entity_catetory_name = entity_catetory_name;
    }

    public String getThird_part_no() {
        return third_part_no;
    }

    public void setThird_part_no(String third_part_no) {
        this.third_part_no = third_part_no;
    }
}
