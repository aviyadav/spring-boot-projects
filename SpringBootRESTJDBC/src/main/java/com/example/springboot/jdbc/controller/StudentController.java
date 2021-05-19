package com.example.springboot.jdbc.controller;

import com.example.springboot.jdbc.dao.StudentDAO;
import com.example.springboot.jdbc.exception.StudentNotFoundException;
import com.example.springboot.jdbc.model.Student;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    
    @Autowired
    StudentDAO dao;
    
    @GetMapping("/students")
    List<Student> getAllStudents() {
        return dao.getAllStudents();
    }
    
    @PostMapping("/addStudent")
    Student addStudent(@RequestBody Student _student) {
        return dao.addStudent(_student);
    }
    
    @GetMapping("/students/{id}")
    Student findStudent(@PathVariable Integer id) throws StudentNotFoundException {
        Student student = dao.findStudentById(id);
        if(student == null) {
            throw new StudentNotFoundException("No student found with id : " + id);
        }
        
        return student;
    }
    
    @PutMapping("/students/{id}")
    Student updateStudent(@RequestBody Student _student, @PathVariable Integer id) {
        return dao.updateStudent(id, _student);
    }
    
    @DeleteMapping("/students/{id}")
    void deleteStudent(@PathVariable Integer id) {
        dao.deleteStudent(id);
    }
}
