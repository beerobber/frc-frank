/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public static Joystick jStick0;
    public static Joystick jStick1;
    public static Joystick jStick2;
    public static Joystick jStick3;
    
    public static Jaguar leftside, rightside;
    public static Compressor comp;
    
    public static RobotDrive drive; 
    public static Solenoid solenoidLower, solenoidUpper;
   
    public void robotInit() {
        
        /**
         * In the constructors below, the numeric parameters specify
         * which cRIO ports control the input/output devices.
         */
        
        // Joystick ports match the numbers in Driver Station setup panel
        jStick0 = new Joystick(1);
        jStick1 = new Joystick(2);
        jStick2 = new Joystick(3);
        jStick3 = new Joystick(4);
        
        // left, right motors on ports 1, 2
        drive = new RobotDrive(1, 2);
        
        // Are these speed controllers connected to something? Check the robot.
        leftside = new Jaguar(3);
        rightside = new Jaguar(4);
        
        // Compressor, using port 4 for both input and output
        comp = new Compressor(4, 4);
    }

    public void teleopInit () {
        comp.start();
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
        // Use y-axis values of 2 joysticks to steer robot, tank-style
        drive.tankDrive(jStick1, jStick2);
        
        double dSpeed;
        boolean bShoot;
        
        // Need to trace wiring to determine what Jaguars on 3 and 4 are doing
        dSpeed = jStick2.getY();
        leftside.set(dSpeed);
        dSpeed = jStick3.getY();
        rightside.set(dSpeed);
        
        // Construct new solenoid control instances for hi and low solenoids
        //solenoidLower = new Solenoid(1);
        //solenoidUpper = new Solenoid(2);
        
        // Set lower solenoid based on joystick trigger
        bShoot = jStick2.getTrigger();
        //solenoidLower.set(bShoot);
        
        // Set upper solenoid based on joystick trigger
        bShoot = jStick3.getTrigger();
        //solenoidUpper.set(bShoot);
      
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
