package com.example.springboot.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/5/22.
 */
public class User implements Serializable{
    private int id;
    private String name;
    private int age;
    private String favorite[];

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getFavorite() {
        return favorite;
    }

    public void setFavorite(String[] favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", favorite=" + Arrays.toString(favorite) +
                '}';
    }
}
