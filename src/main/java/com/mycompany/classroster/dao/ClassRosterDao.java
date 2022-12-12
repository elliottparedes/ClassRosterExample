/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.classroster.dao;

import com.mycompany.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author Ellio
 */
public interface ClassRosterDao 
{
 
    Student addStudent(String studentId, Student student) throws ClassRosterDaoException;
    
    List<Student> getAllStudents() throws ClassRosterDaoException;
    
    Student getStudent(String studentId) throws ClassRosterDaoException;
    
    Student removeStudent(String studentId) throws ClassRosterDaoException;
    
}
