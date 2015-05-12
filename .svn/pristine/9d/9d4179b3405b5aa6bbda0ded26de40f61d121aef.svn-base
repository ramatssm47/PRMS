/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nus.iss.phoenix.schedule.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author a0120086r
 */
/**
 * 
 * @author anu
 * ScheduleDAO. This interface defines the operations that are 
 * performed on schedule entities
 */
public interface ScheduleDAO {
    /** 
     * createSchedule method. This method is to create a schedule.
     * A new row is inserted in the database in the program slot table. 
     * The method returns a boolean true if the schedule is created successfully
     * @param ps
     * this is the class instance to be created
     * @param weekId
     * this is the object instance to hold the week id.
     * 
     */
    public boolean createSchedule(ProgramSlot ps,int weekId) throws SQLException;
    /**
     * ListOfProgramSlots method. This method obtains program slot list,
     * where each slot contains entries from tables in the schema like User,
     * annualSchedule, weeklySchedule. Returns list of program slots
     * @param weekId
     * parameter contains the week id to be saved
     * @param year
     * parameter contains year to be saved
     
     *  
     */
    public List<ProgramSlot> getListOfProgramSlots(int weekId,int year)throws SQLException;
    /**
     * AnnualSchedule method. This method returns program content details of the 
     * program slot in order to do update the contents of schedule through 
     * operations like copy, delete or modify operations. Returns annual schedule program
     * slots by week id and year id.
     * @param weekId
     * parameter contains week id to be saved
     * @param year
     * parameter contains year instance
     */
    public AnnualSchedule getProgramSlotByWeekAndYear(int weekId,int year)throws SQLException;
    /**
     * copySchedule method. This creates a copy of the chosen schedule details.
     * Copy of schedule details is done for existing schedules in the database
     * by opening a jdbc connection to access annual schedule objects
     * @param annualSchedule
     * parameter contains the annual schedule to be returned
     *           
     */
    public Boolean copySchedule(AnnualSchedule annualSchedule)throws SQLException;
    /**
     * deleteSchedule method. This method is to delete the contents of a schedule.
     * This id done by checking for existence of schedule using program slot id key in the database.
     * Database is accessed using jdbc connection. 
     * @param annualSchedule is for instance of schedule to be deleted
     * Method returns boolean  if schedule is deleted successfully.
     */
    public Boolean deleteSchedule(int slotId)throws SQLException;
    /**
     * modifySchedule method. This method updates the contents of a schedule 
     * The update function is carried out based on slot id key in the database
     * @param annualSchedule is for the instance of schedule to be modified
     * @returns a boolean value true if schedule contents are successfully modified
     */
    public Boolean modifySchedule(AnnualSchedule annualSchedule)throws SQLException;
       
    public boolean checkProgramSlotExists(Date dateFrom ,Date dateTo ,int weekNumber,int dayNo);
}
