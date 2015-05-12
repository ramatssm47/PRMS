/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nus.iss.phoenix.schedule.dao;

import java.sql.SQLException;
import java.util.List;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 *
 * @author a0120086r
 */
public interface WeeklyScheduleDAO {
    public WeeklySchedule getWeeklySchedule(Integer wsid);
    public List<WeeklySchedule> getListOfWeeklySchedules(int year) throws SQLException;
        public List<ProgramSlot> getListOfProgramSlots(int weekId)throws SQLException;
public int insertWeek(ProgramSlot ps) throws SQLException;
}
