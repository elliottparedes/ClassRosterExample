/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.classroster.dao;

import com.mycompany.classroster.dto.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ellio
 */
public class ClassRosterDaoImpl implements ClassRosterDao
{
    private Map<String, Student> students =new HashMap<>();
    
    
    @Override
    public Student addStudent(String studentId, Student student) 
    {
     Student prevStudent = students.put(studentId,student);
     return prevStudent;
    }

    @Override
    public List<Student> getAllStudents() 
    {
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) {
           Student removedStudent = students.remove(studentId);
            return removedStudent;
    }
    
}
