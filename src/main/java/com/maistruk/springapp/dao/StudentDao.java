package com.maistruk.springapp.dao;

import java.util.List;

import com.maistruk.springapp.model.Student;

public interface StudentDao {
    
    void create(Student student);
    
    void update(Student student);
    
    Student getById(Integer id);
    
    Student findByFullName(String firstName, String lastName);
    
    void delete(Integer id);
    
    List<Student> findAll();


}
