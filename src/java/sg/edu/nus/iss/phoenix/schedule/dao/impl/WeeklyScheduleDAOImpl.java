/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import sg.edu.nus.iss.phoenix.core.dao.JDBCFactory;
import sg.edu.nus.iss.phoenix.schedule.dao.WeeklyScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 *
 * @author Ram,Gautam,Vasundhara,Anu,Dinesh
 */
public class WeeklyScheduleDAOImpl implements WeeklyScheduleDAO{

    @Resource(name="jdbc/phoenix")
    private Connection con;
    
    @Override
    public WeeklySchedule getWeeklySchedule(Integer wsid) {
        WeeklySchedule weeklyschedule = new WeeklySchedule();
        
        String sql = "SELECT name FROM phoenix.producer where wsid='"+ wsid  +"'";
        ResultSet rs = executeFetchQuery(sql);
        try {
            while(rs.next()){
                weeklyschedule.setWeeklyScheduleId(rs.getInt(wsid));
            }
        }
        catch(SQLException ex) {
            
        }
        
        return weeklyschedule;
        
    }
    private ResultSet executeFetchQuery(String sql) {
        //To change body of generated methods, choose Tools | Templates.
       ResultSet rs = null;
        try{
            
            con = JDBCFactory.getConnection();
            rs=con.createStatement().executeQuery(sql);
            con.close();
        }
        catch(Exception e){
           
        }
        return rs;
    }
    
    @Override
    public List<WeeklySchedule> getListOfWeeklySchedules(int year) throws SQLException{
        List<WeeklySchedule> listOfWeeklySchedules = new ArrayList<WeeklySchedule>();
        List<ProgramSlot> programSlotList=new ArrayList<ProgramSlot>();
        try {
            con = JDBCFactory.getConnection();
            String sqlForWeeklySchedule = "select * from Phoenix.WeeklySchedule where Year=" + year;
                    Statement listOfWeeklySchedulestmt = con.createStatement();
                    ResultSet listOfWeeklyScheduleResultSet = listOfWeeklySchedulestmt.executeQuery(sqlForWeeklySchedule);
                    while (listOfWeeklyScheduleResultSet.next()) {
                        
                        WeeklySchedule weeklySchedule= new WeeklySchedule();
                        weeklySchedule.setWeeklyScheduleId(listOfWeeklyScheduleResultSet.getInt("WSID"));
                        weeklySchedule.setWeekNumber(listOfWeeklyScheduleResultSet.getInt("WeekNo"));
                        //role.setRoleName(roleResultSet.getString("RName"));
                        System.out.println("fhfhg:"+weeklySchedule.getWeeklyScheduleId());
                        String sqlForProgramSlot = "select * from Phoenix.ProgramSlot where WSIDF=" + weeklySchedule.getWeeklyScheduleId();
                    Statement listOfProgramSlotstmt = con.createStatement();
                    ResultSet listOfProgramSlotResultSet = listOfProgramSlotstmt.executeQuery(sqlForProgramSlot);
                            while (listOfProgramSlotResultSet.next()) {
                                ProgramSlot  programSlot=new ProgramSlot();
//                               
                                programSlot.setSlotFrom(listOfProgramSlotResultSet.getDate("DateFrom"));
                                programSlot.setSlotTo(listOfProgramSlotResultSet.getDate("DateTo"));
//                              
                                programSlotList.add(programSlot);
                                
                            }
                           weeklySchedule.setAllProgramSlots(programSlotList);
                           listOfWeeklySchedules.add(weeklySchedule);
                    
            }
                   
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.close();
        }
        System.out.println("weeklist:"+listOfWeeklySchedules.size());
    return listOfWeeklySchedules;
    }

    @Override
    public List<ProgramSlot> getListOfProgramSlots(int weekId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int insertWeek(ProgramSlot ps) throws SQLException {

        int weekId = 0;
        con = JDBCFactory.getConnection();
        boolean returnParam = false;
        if (con != null) {
            String selectSql = "select * from phoenix.weeklyschedule where weekno=" + ps.getWeekNumber();

            ResultSet set = con.createStatement().executeQuery(selectSql);
            if (set.next()) {
                weekId = set.getInt("WSID");
            }
            if (weekId == 0) {
                String sql1 = "INSERT INTO phoenix.weeklyschedule(`Year`,`WeekNo`)VALUES(?,?)";
                PreparedStatement pmt = con.prepareStatement(sql1);
                pmt.setInt(1, ps.getYear());
                pmt.setInt(2, ps.getWeekNumber());
                
                int isScheduleWeekInserted = pmt.executeUpdate();
                if (isScheduleWeekInserted > 0) {
                    returnParam = true;
                }
                if (returnParam) {
                    ResultSet set1 = con.createStatement().executeQuery(selectSql);
                    if (set1.next()) {
                        weekId = set1.getInt("WSID");
                    }
                }
            }

        }

        return weekId;
    }
    
}
