/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.exceptions.SlotAlreadyExistsException;
import sg.edu.nus.iss.phoenix.core.exceptions.durationNotMatchException;
import sg.edu.nus.iss.phoenix.schedule.dao.AnnualScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.WeeklyScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.ScheduleDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;





/**
 *
 * @author a0120086r
 */
public class ScheduleService {
    
    private static final Logger logger = Logger.getLogger(ScheduleService.class.getName());
    
        DAOFactory factory;
	ScheduleDAO psDAO;
       
        AnnualScheduleDAO asDAO;
        WeeklyScheduleDAO wsDAO;

	public ScheduleService() {
		super();
		// TODO Auto-generated constructor stub
		factory = new DAOFactory();
		psDAO = factory.getPsDAO();
                
                asDAO=factory.getAsDAO();
		wsDAO=factory.getWsDAO();
		
	}
        
        public boolean createScheduleService(ProgramSlot ps) throws SQLException{
            boolean returnParam = false;
            boolean isYear = asDAO.insertYearSchedule(ps);
            if (isYear) {
                 int weekId = wsDAO.insertWeek(ps);
                if (weekId>0) {
                    boolean isProgramInserted = psDAO.createSchedule(ps,weekId);
                    if (isProgramInserted) {
                        returnParam = true;
                    }
                }

            } else {

            }
            return returnParam;
        }
        
        public void validateTimeSlot(Date slotFrom) throws SlotAlreadyExistsException{
            ScheduleDAOImpl sdi = new ScheduleDAOImpl();
            List<ProgramSlot> list = sdi.validateTimeSlot();
               
            for(ProgramSlot date:list){
                 if(date.equals(slotFrom))
                     continue;
                 else
                     throw new SlotAlreadyExistsException();
             }
        
            
                
        }
        
        public void durationValidate(Date slotFrom, Date slotTo, int duration) throws durationNotMatchException{
           
            long diff = slotTo.getTime() - slotFrom.getTime();
            
            long diffMinutes = diff / (60 * 1000);
            if(diffMinutes!=duration){
                throw new durationNotMatchException();
            } 
        }
        
         public List<AnnualSchedule> getAllAnnualSchedulesService() throws SQLException{
            return asDAO.getListOfAnnualSchedules();
        }
        public List<WeeklySchedule> getAllWeeklySchedulesService(int year) throws SQLException{
            
            return wsDAO.getListOfWeeklySchedules(year);
        }
        public List<ProgramSlot> getAllProgramSlotsService(int weekId,int year) throws SQLException{
           
            return psDAO.getListOfProgramSlots(weekId,year);
        }
        
        public AnnualSchedule getProgramSlotByWeekAndYear(int weekId, int year)throws SQLException
      {
          return psDAO.getProgramSlotByWeekAndYear(weekId,year);
      }
        
        
        public Boolean copySchedule(AnnualSchedule annualSchedule)throws SQLException{
          return psDAO.copySchedule(annualSchedule);
      }
        
            public Boolean deleteSchedule(int slotId)throws SQLException{
                return psDAO.deleteSchedule(slotId);
            }
            
         public Boolean modifySchedule(AnnualSchedule annualSchedule)throws SQLException{
             return psDAO.modifySchedule(annualSchedule);
         }
         
         public boolean checkProgramSlotExists(Date dateFrom ,Date dateTo ,int weekNumber,int dayNo){
             return psDAO.checkProgramSlotExists(dateFrom, dateTo, weekNumber, dayNo);
         }
    
}
