/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nus.iss.phoenix.schedule.entity;

import java.util.List;


public class AnnualSchedule {
    int year;
    List<WeeklySchedule> weeklySchedules;
    
    
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    
    public List<WeeklySchedule> getAllWeeklySchedules(){
        return weeklySchedules;
    }
    public void setAllWeeklySchedules(List<WeeklySchedule> weeklySchedulesList){
        this.weeklySchedules=weeklySchedulesList;
    }
    public void addWeeklyScheduleToList(WeeklySchedule weeklySchedule){
        this.weeklySchedules.add(weeklySchedule);
    }
}
