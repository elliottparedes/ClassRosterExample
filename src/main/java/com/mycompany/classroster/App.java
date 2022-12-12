/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.classroster;

import com.mycompany.classroster.controller.ClassRosterController;
import com.mycompany.classroster.dao.ClassRosterDaoFileImpl;
import com.mycompany.classroster.ui.ClassRosterView;
import com.mycompany.classroster.ui.UserIO;
import com.mycompany.classroster.ui.UserIOConsoleImpl;
import com.mycompany.classroster.dao.ClassRosterDao;

/**
 *
 * @author Ellio
 */
public class App {

    public static void main(String[] args) 
    {
        // This is constructor based dependecy injection
         UserIO myIo = new UserIOConsoleImpl();
         ClassRosterView myView = new ClassRosterView(myIo);
         // injecting the UserIOConsole -> View
         //classRosterDao myDao = new classRosterDaoImpl();
         ClassRosterDao myDao = new ClassRosterDaoFileImpl();
         // Telling dao which implemention to use
        ClassRosterController controller = new ClassRosterController(myDao,myView);
        // injecting the dao and view into the controller
        controller.run();
     
    }
}
