package com.maistruk.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.maistruk.springapp.dao.StudentDao;
import com.maistruk.springapp.exception.StudentException;
import com.maistruk.springapp.model.Student;

@Service
public class StudentService {
    
    @Autowired
    StudentDao repository;

    public void create(Student student) throws StudentException {
        if (repository.findByFullName(student.getFirstName(), student.getLastName()) != null) {
            throw new StudentException("Student with this name already exist");
        }
        repository.create(student);
    }
    
    public void update(Student student) throws StudentException {
        if(repository.getById(student.getId()) == null) {
            throw new StudentException("Student not found");
        }
        if (repository.findByFullName(student.getFirstName(), student.getLastName()) != null) {
            throw new StudentException("Student with this name already exist");
        }
        repository.update(student);
    }

    public Student getById(Integer id) throws StudentException {
        Student student = repository.getById(id);
        if(student == null) {
            throw new StudentException("Student not found");
        }
        return student;
    }
    
    public List<Student> getAll(){
        return repository.findAll();
    }
    
    public void delete(Integer id) throws StudentException {
        if(repository.getById(id) == null) {
            throw new StudentException("Student not found");
        }
        repository.delete(id);
    }

}
