package com.ruzhen.springboot.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.Collection;
import java.util.LinkedHashSet;

@Entity  //1 注解指明这是一个和数据库表映射的实体类
@NamedQuery(name="Person.withNameAndAddressNamedQuery", query = "select p from Person p where p.name=?1 and p.addrees=?2")
public class Person {

    @Id  //2 注解指明这个属性映射为数据库的主键
    @GeneratedValue //3 注解默认使用主键方式生产方式为自增，hibernate回味我们自动生成一个名为HIBERNATE_SEQUENCE的序列
    private Long id;

    private String name;
    private Integer age;
    private String address;

    private Collection<Location> locations = new LinkedHashSet<>();

    public Person() {
        super();
    }

    public Person(String name, Integer age, String address) {
        this.id=id;
        this.name = name;
        this.age = age;
        this.address=address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
