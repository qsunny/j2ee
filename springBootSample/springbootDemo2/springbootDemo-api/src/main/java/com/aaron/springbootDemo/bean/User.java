package com.aaron.springbootDemo.bean;

import java.io.Serializable;


/**
 * <p> aaron.qiu </p>
 * <p>Version: 1.0.0</p>
 * <p>Create Date： 2019-08-10 14:47:50 </p>
 * <p>Copyright (c) 2019 ~ 2020 aaron版权所有</p>
 */
public class User implements Serializable{
    
    /**
     * 
     */
    private Long id;
    
    /**
     * 
     */
    private String name;
    
    /**
     * 
     */
    private Integer age;
    
    /**
     * 
     */
    private Long balance;
    
    public Long getId()
    {
        return id;
    }
        
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
        
    public void setName(String name)
    {
        this.name = name;
    }
    public Integer getAge()
    {
        return age;
    }
        
    public void setAge(Integer age)
    {
        this.age = age;
    }
    public Long getBalance()
    {
        return balance;
    }
        
    public void setBalance(Long balance)
    {
        this.balance = balance;
    }

    @Override
    public String toString() {
    return "User{" +
        "id=" + id +
    ", name=" + name +
    ", age=" + age +
    ", balance=" + balance +
    '}';
    }


}
