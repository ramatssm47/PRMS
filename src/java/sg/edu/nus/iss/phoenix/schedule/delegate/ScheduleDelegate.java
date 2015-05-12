/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nus.iss.phoenix.schedule.delegate;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.exceptions.SlotAlreadyExistsException;
import sg.edu.nus.iss.phoenix.core.exceptions.durationNotMatchException;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

/**
 *
 * @author a0120086r
 */
public class ScheduleDelegate {
    
    private ScheduleService service;

    public ScheduleDelegate() {
        super();
        service = new ScheduleService();
    }
    
    public boolean createScheduleDelegate(ProgramSlot ps) throws SQLException{
        return service.createScheduleService(ps);
    }
    
    public void validateTimeSlotDelegate(Date slotFrom) throws SlotAlreadyExistsException{
        service.validateTimeSlot(slotFrom);
    }
    
    public void durationValidateDelegate(Date slotFrom, Date slotTo, int duration) throws durationNotMatchException{
        service.durationValidate(slotFrom, slotTo, duration);
    }
    
    public List<AnnualSchedule> getAllAnnualSchedulesDelegate() throws SQLException{
        return service.getAllAnnualSchedulesService();
    }
    public List<WeeklySchedule> getAllWeeklySchedulesDelegate(int year) throws SQLException{
        System.out.print("Delegate"+year);
        return service.getAllWeeklySchedulesService(year);
    }
    
    public List<ProgramSlot> getAllProgramSlotsDelegate(int weekId,int year) throws SQLException{
        return service.getAllProgramSlotsService(weekId,year);
    }
    
      public AnnualSchedule getProgramSlotByWeekAndYear(int weekId, int year)throws SQLException
      {
          return service.getProgramSlotByWeekAndYear(weekId,year);
      }
      
      public Boolean copySchedule(AnnualSchedule annualSchedule)throws SQLException{
          return service.copySchedule(annualSchedule);
      }
    
    public Boolean deleteSchedule(int slotId)throws SQLException{
        return service.deleteSchedule(slotId);
    }
    
    public Boolean modifySchedule(AnnualSchedule annualSchedule)throws SQLException{
        return service.modifySchedule(annualSchedule);
    }
    public boolean checkProgramSlotExists(Date dateFrom ,Date dateTo ,int weekNumber,int dayNo){
             return service.checkProgramSlotExists(dateFrom, dateTo, weekNumber, dayNo);
         }
}
