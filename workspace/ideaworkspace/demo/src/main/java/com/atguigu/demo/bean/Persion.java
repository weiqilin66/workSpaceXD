package com.atguigu.demo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "persion")
public class Persion {

    private String name;
    private List<String> list;
    private Date birsth;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirsth() {
        return birsth;
    }

    public void setBirsth(Date birsth) {
        this.birsth = birsth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
