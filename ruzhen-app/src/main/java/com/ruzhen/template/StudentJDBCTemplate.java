package com.ruzhen.template;

import com.ruzhen.dao.StudentDao;
import com.ruzhen.mapper.StudentMapper;
import com.ruzhen.pojo.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈jdbc模板类〉
 *
 * @author lizhen
 * @create 2018/7/17
 * @since 1.0.0
 */
public class StudentJDBCTemplate implements StudentDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(String name, Integer age) {
        String SQL = "insert into Student (name, age) values(?,?)";
        jdbcTemplate.update(SQL,name, age);
        logger.info("Created Record Name={}, Age={}",name, age);
        return;
    }

    @Override
    public Student getStudent(Integer id) {
        String SQL = "select * from Student where id = ?";
        Student student = jdbcTemplate.queryForObject(SQL, new Object[]{id}, new StudentMapper());
        return student;
    }

    @Override
    public List<Student> listStudents() {
        String SQL = "select * from Student";
        List<Student> students = jdbcTemplate.query(SQL, new StudentMapper());
        return students;
    }

    @Override
    public void delete(Integer id) {
        String SQL="delete from Student where id=?";
        jdbcTemplate.update(SQL, id);
        logger.info("Deleted Record with ID={}", id);
        return;
    }

    @Override
    public void update(Integer id, Integer age) {
        String SQL = "update Student set age=?where id= ?";
        jdbcTemplate.update(SQL, age, id);
        logger.info("Updated Record with Id={}", id);
        return;
    }
}