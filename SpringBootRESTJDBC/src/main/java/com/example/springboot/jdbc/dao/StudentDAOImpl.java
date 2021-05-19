package com.example.springboot.jdbc.dao;

import com.example.springboot.jdbc.model.Student;
import com.example.springboot.jdbc.model.StudentMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class StudentDAOImpl implements StudentDAO {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> getAllStudents() {
        return jdbcTemplate.query("select * from students", new StudentMapper());
    }

    @Override
    public Student findStudentById(int id) {
        Student student = null;
        student = jdbcTemplate.queryForObject("select * from students where id = ?", new Object[] { id }, new StudentMapper());
        return student;
    }

    @Override
    public Student addStudent(Student _student) {
        jdbcTemplate.update("insert into students (id, name, age, weight) values (?, ?, ?, ?)", new Object[] { _student.getId(), _student.getName(), _student.getAge(), _student.getWeight()});
        return findStudentById(_student.getId());
    }

    @Override
    public Student updateStudent(int id, Student _student) {
        jdbcTemplate.update("update students set name = ?, age = ?, weight = ? where id = ?", new Object[] {_student.getName(), _student.getAge(), _student.getWeight(), id});
        return findStudentById(id);
    }

    @Override
    public boolean deleteStudent(int id) {
        return jdbcTemplate.update("delete from students where id = ?", new Object[] { id }) > 0;
    }

}
