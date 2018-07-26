package com.ruzhen.dao;

import com.ruzhen.pojo.Student;

import javax.sql.DataSource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈数据访问对象接口文件〉
 *
 * @author lizhen
 * @create 2018/7/17
 * @since 1.0.0
 */
public interface StudentDao {
    public void setDataSource(DataSource ds);
    public void create(String name, Integer age);
    public Student getStudent(Integer id);
    public List<Student> listStudents();
    public void delete(Integer id);
    public void update(Integer id, Integer age);
}
