/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.classroster.dao;

import com.mycompany.classroster.dto.Student;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Ellio
 */
public class ClassRosterDaoFileImpl implements ClassRosterDao
{
    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";
    
    private Map<String, Student> students =new HashMap<>();

    @Override
    public Student addStudent(String studentId, Student student) throws ClassRosterDaoException
    {
        loadRoster();
        Student newStudent = students.put(studentId, student);
        writeRoster();
        return newStudent;
       
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterDaoException
    {
       loadRoster();
       return new ArrayList(students.values());
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterDaoException
    {
       loadRoster();
       return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterDaoException 
    {
        loadRoster();
        Student removedStudent = students.remove(studentId);
        writeRoster();
        return removedStudent;
      
    }
    
    private Student unmarshallStudent(String studentAsText)
    {
        String[] studentTokens = studentAsText.split(DELIMITER);
        String studentId = studentTokens[0];
        Student studentFromFile = new Student(studentId);
        studentFromFile.setFirstName(studentTokens[1]);
        studentFromFile.setLastName(studentTokens[2]);
        studentFromFile.setCohort(studentTokens[3]);
        return studentFromFile;
    }
    
    private void loadRoster() throws ClassRosterDaoException
    {
        Scanner scanner;
         try {
        // Create Scanner for reading the file
        scanner = new Scanner(
                new BufferedReader(
                        new FileReader(ROSTER_FILE)));
    } catch (FileNotFoundException e) {
        throw new ClassRosterDaoException(
                "-_- Could not load roster data into memory.", e);
    }
         String currentLine;
         Student currentStudent;
         
        while (scanner.hasNextLine()) {
        // get the next line in the file
        currentLine = scanner.nextLine();
        // unmarshall the line into a Student
        currentStudent = unmarshallStudent(currentLine);

        // We are going to use the student id as the map key for our student object.
        // Put currentStudent into the map using student id as the key
        students.put(currentStudent.getStudentId(), currentStudent);
        
        //scanner.close();
    }
         
         
    }
    
    private String marshallStudent(Student aStudent)
    {
        String studentAsText = aStudent.getStudentId() + DELIMITER;
        studentAsText += aStudent.getFirstName() + DELIMITER;
        studentAsText += aStudent.getLastName() + DELIMITER;
        studentAsText += aStudent.getCohort();
        return studentAsText;
    }
    
    private void writeRoster() throws ClassRosterDaoException
    {
        PrintWriter out;
            try {
        out = new PrintWriter(new FileWriter(ROSTER_FILE));
    } catch (IOException e) {
        throw new ClassRosterDaoException(
                "Could not save student data.", e);
    }
    String studentAsText;
    List<Student> studentList = this.getAllStudents();
      for (Student currentStudent : studentList) {
        // turn a Student into a String
        studentAsText = marshallStudent(currentStudent);
        // write the Student object to the file
        out.println(studentAsText);
        // force PrintWriter to write line to the file
        out.flush();
    }
      
     out.close();
    }
    
    
}
