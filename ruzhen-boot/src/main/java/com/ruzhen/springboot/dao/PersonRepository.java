package com.ruzhen.springboot.dao;

import com.ruzhen.springboot.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, String> {

    Person findByName(String name);  //支持方法名查询

    @Query("{'age':?0}") //2 支持@Query查询，查询参数构造JSON字符串
    List<Person> withQueryFindByAge(Integer age);
}
