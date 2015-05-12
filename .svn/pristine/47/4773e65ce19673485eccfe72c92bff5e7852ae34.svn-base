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
import sg.edu.nus.iss.phoenix.schedule.dao.AnnualScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author vasundharabhatia,Gautam,Dinesh
 */
public class AnnualScheduleDAOImpl implements AnnualScheduleDAO {
    
    
    private Connection conn;
    
    @Override
    public AnnualSchedule getAnnualSchedule(Integer year){
        return null;
    }
    
    @Override
    public List<AnnualSchedule> getListOfAnnualSchedules() throws SQLException{
        List<AnnualSchedule> listOfAnnualSchedules = new ArrayList<AnnualSchedule>();
        try {
            System.out.println("list:");
            conn = JDBCFactory.getConnection();
            String sql = "SELECT * FROM Phoenix.AnnualSchedule";
            Statement stmt = conn.createStatement();
            ResultSet annualScheduleResultSet = stmt.executeQuery(sql);
            while (annualScheduleResultSet.next()) {
                AnnualSchedule annualSchedule = new AnnualSchedule();
                annualSchedule.setYear((Integer.decode(annualScheduleResultSet.getString("Year"))));
                
                    
                listOfAnnualSchedules.add(annualSchedule);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    System.out.println("list:"+listOfAnnualSchedules.size());
    
    return listOfAnnualSchedules;
    }
    
    public boolean insertYearSchedule(ProgramSlot ps) throws SQLException
    {
        boolean returnParam = false;
        boolean isalreadyExits = false;
        conn = JDBCFactory.getConnection();
        try {
            if (conn != null) {
                String selectSql = "select * from phoenix.annualschedule where year=" + ps.getYear();
                ResultSet set = conn.createStatement().executeQuery(selectSql);
                if (set.next()) {
                    isalreadyExits = true;
                    returnParam = true;
                }
                if (!isalreadyExits) {
                    String sql = "INSERT INTO phoenix.annualschedule (Year)VALUES(?)";

                    PreparedStatement pmt = conn.prepareStatement(sql);
                    pmt.setString(1, Integer.toString(ps.getYear()));
                    int isScheduleInserted = pmt.executeUpdate();
                    if (isScheduleInserted > 0) {
                        returnParam = true;
                    }
                }

            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            conn.close();
        }
        return returnParam;
    }
}