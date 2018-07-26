package com.ruzhen.mapper;

import com.ruzhen.pojo.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 〈一句话功能简述〉<br>
 * 〈学生映射类〉
 *
 * @author lizhen
 * @create 2018/7/17
 * @since 1.0.0
 */
public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setAge(resultSet.getInt("age"));
        return student;
    }
}