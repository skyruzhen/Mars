package com.ruzhen.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉<br>
 * 〈数据访问对象（DAO）〉
 *
 * @author lizhen
 * @create 2018/7/17
 * @since 1.0.0
 */
public class SpringDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    public int queryForInt(){
        String sql = "select coutn(*) from student";
        int rowCount = jdbcTemplate.queryForObject(sql, Integer.class);
        return rowCount;
    }
}