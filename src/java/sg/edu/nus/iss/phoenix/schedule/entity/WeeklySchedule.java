/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nus.iss.phoenix.schedule.entity;

import java.util.List;

public class WeeklySchedule {
    
    private int weeklyScheduleId;
    int weekNumber;
    List<ProgramSlot> programSlots;
    
    
    public int getWeekNumber(){
        return weekNumber;
    }
    
    public void setWeekNumber(int weekNumber){
        this.weekNumber=weekNumber;
    }
    
    public List<ProgramSlot> getAllWeeklySchedules(){
        return programSlots;
    }
    public void setAllProgramSlots(List<ProgramSlot> programSlotsList){
        this.programSlots=programSlotsList;
    }
    public void addProgramSlotToList(ProgramSlot programSlot){
        this.programSlots.add(programSlot);
    }

    /**
     * @return the weeklyScheduleId
     */
    public int getWeeklyScheduleId() {
        return weeklyScheduleId;
    }

    /**
     * @param weeklyScheduleId the weeklyScheduleId to set
     */
    public void setWeeklyScheduleId(int weeklyScheduleId) {
        this.weeklyScheduleId = weeklyScheduleId;
    }
}
