package com.example.studentmanagement.controller;


import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    public StudentService studentservice;

    @PostMapping("")
    public Student addStudent(@RequestBody Student student){
        return studentservice.addStudent(student);
    }

    @GetMapping("")
    public List<Student> getAllStudent(){
      return studentservice.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        try {
            int studentId = Integer.parseInt(id);
            return studentservice.getStudentById(studentId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid student ID: must be numeric");
        }
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        student.setId(id);
        return studentservice.updateStudent(student);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        boolean deleted = studentservice.deleteStudent(id);
        if (deleted) {
            return ResponseEntity.ok("Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }



}
