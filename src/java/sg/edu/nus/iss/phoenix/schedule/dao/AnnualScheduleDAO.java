/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nus.iss.phoenix.schedule.dao;

import java.sql.SQLException;
import java.util.List;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author a0120086r
 */
public interface AnnualScheduleDAO {
    
    public AnnualSchedule getAnnualSchedule(Integer year);
    public List<AnnualSchedule> getListOfAnnualSchedules() throws SQLException;
public boolean insertYearSchedule(ProgramSlot ps) throws SQLException;
    
}