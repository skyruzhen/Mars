package com.ruzhen.service.impl;

import com.ruzhen.pojo.Student;
import com.ruzhen.service.ISpringDaoService;
import com.ruzhen.template.StudentJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈serviceImp〉
 *
 * @author lizhen
 * @create 2018/7/17
 * @since 1.0.0
 */
@Service
public class SpringDaoServiceImpl implements ISpringDaoService {
    @Autowired
    private StudentJDBCTemplate studentJDBCTemplate;

    @Override
    public List<Student> queryForInt() {
        return studentJDBCTemplate.listStudents();
    }
}