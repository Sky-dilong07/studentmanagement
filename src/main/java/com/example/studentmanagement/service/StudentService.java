package com.example.studentmanagement.service;


import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    public StudentRepository sr;

    // 1. ADD STUDENT
    public Student addStudent(Student student){
        if(student == null){
            return null;
        }
        return sr.save(student);
    }

    //2. GET ALL STUDENT
    public List<Student> getAllStudents() {
        return sr.findAll();
    }

    //3. GET STUDENT BY ID
    public Student getStudentById(int id) {
        Optional<Student> optionalStudent = sr.findById(id);

        if(optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else {
            return null;
        }
    }

    //4. UPDATE STUDENT
    public Student updateStudent(Student student) {
        Optional<Student> optionalStudent = sr.findById(student.getId());

        if(optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();

            existingStudent.setName(student.getName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setCourse(student.getCourse());
            return sr.save(existingStudent);
        } else {
            return null;
        }
    }

    //5. DELETE STUDENT
    public boolean deleteStudent(int id) {
        Optional<Student> optionalStudent = sr.findById(id);

        if(optionalStudent.isPresent()) {
            sr.deleteById(id); 
            return true;
        } else {
            return false;
        }
    }






}
