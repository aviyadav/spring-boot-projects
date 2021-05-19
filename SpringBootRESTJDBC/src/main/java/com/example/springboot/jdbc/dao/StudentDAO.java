package com.example.springboot.jdbc.dao;

import com.example.springboot.jdbc.model.Student;
import java.util.List;

public interface StudentDAO {

    List<Student> getAllStudents();
    public Student findStudentById(int id);
    public Student addStudent(Student _student);
    public Student updateStudent(int id, Student _student);
    public boolean deleteStudent(int id);
}
