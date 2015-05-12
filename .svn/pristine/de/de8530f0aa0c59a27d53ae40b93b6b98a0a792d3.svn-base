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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.dao.JDBCFactory;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.user.entity.Presenter;
import sg.edu.nus.iss.phoenix.user.entity.Producer;

/**
 *
 * @author Ram,Dinesh,Gautam,Vasundhara,Vijay,Anu
 */
/**
 *  This class contains all database * handling that is needed to 
 * permanently store and retrieve Schedule object
 * instances.
 */
public class ScheduleDAOImpl implements ScheduleDAO {

//    
    private Connection conn;
//               
/* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`schedule`.dao.impl.ScheduleDAO#createSChedule(sg.edu.nus.iss.`phoenix`.`schedule`.entity.Schedule)
	 */
    @Override
    public boolean createSchedule(ProgramSlot ps, int weekId) throws SQLException {
        boolean returnParam = false;
        conn = JDBCFactory.getConnection();
        try {
            if (conn != null) {

                String sql2 = "INSERT INTO phoenix.programslot (`PName`,`DayNo`,`PRESID`,`PRODID`,`Status`,`radio-program_progID`,`DateFrom`,`DateTo`,`WSIDF`)VALUES(?,?,?,?,1,?,?,?,?)";
                PreparedStatement pmt = conn.prepareStatement(sql2);

                pmt.setString(1, ps.getProgram().getName());
                pmt.setInt(2, ps.getDayNumber());
                pmt.setInt(3, ps.getPresenter().getPresenterId());
                pmt.setInt(4, ps.getProducer().getProducerId());
                pmt.setInt(5, ps.getProgram().getRadioId());
                pmt.setTimestamp(6, new Timestamp(ps.getSlotFrom().getTime()));
                pmt.setTimestamp(7, new Timestamp(ps.getSlotTo().getTime()));
                pmt.setInt(8, weekId);
                int isProgramSlotInserted = pmt.executeUpdate();
                if (isProgramSlotInserted > 0) {
                    returnParam = true;
                }

            }
            return returnParam;
        } catch (SQLException e) {
            throw e;
        } finally {
            conn.close();
        }

    }

    public List<ProgramSlot> validateTimeSlot() {

        ProgramSlot ps = new ProgramSlot();
        ArrayList<ProgramSlot> slotFromResults = new ArrayList<ProgramSlot>();
        ArrayList<ProgramSlot> slotToResults = new ArrayList<ProgramSlot>();

        String sql = "select slotFrom,slotTo from phoenix.programslot";

        try {
            conn = JDBCFactory.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ps.setSlotFrom(rs.getDate("slotFrom"));
                //ps.setSlotTo(rs.getDate("slotTo"));
                slotFromResults.add(ps);
                slotToResults.add(ps);
            }
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return slotFromResults;

    }
/* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`schedule`.dao.impl.ScheduleDAO#getListOfProgramSlots(sg.edu.nus.iss.`phoenix`.`schedule`.entity.Schedule)
	 */
    public List<ProgramSlot> getListOfProgramSlots(int weekId, int year) throws SQLException {
        System.out.println("PS:1");
        AnnualSchedule annualSchedule = new AnnualSchedule();
        WeeklySchedule weeklySchedule = new WeeklySchedule();
        List<ProgramSlot> programSlotList = new ArrayList<ProgramSlot>();
        try {
            conn = JDBCFactory.getConnection();
            System.out.println("PS:2");

            //gautam
            String sql = "select w.WeekNo,w.Year,r2.progID,r2.typicalDuration,r2.description,u.name as presenter ,u2.name as producer, u.UID , p.psid,p.pname as pname,p.dayno,p.presid,p.prodid,p.status,p.datefrom,p.dateto,p.wsidf \n"
                    + "from User u  join ProgramSlot p on p.PRESID=u.UID and (p.status=1 and u.status=1 )  join\n"
                    + "User u2 on p.prodid=u2.uid and(u2.Status=1 and p.status=1)join\n"
                    + "`radio-program` r2 on p.pname=r2.name  and(r2.status=1 and p.status=1)join\n"
                    + "WeeklySchedule w on p.WSIDF=w.wsid and (w.weekno=? and p.status=1 and w.year=?);";
            PreparedStatement listOfProgramSlotstmt = conn.prepareStatement(sql);
            listOfProgramSlotstmt.setInt(1, weekId);
            listOfProgramSlotstmt.setInt(2, year);
            ResultSet listOfProgramSlotResultSet = listOfProgramSlotstmt.executeQuery();
            while (listOfProgramSlotResultSet.next()) {
                ProgramSlot programSlot = new ProgramSlot();
                Presenter presenter = new Presenter();
                presenter.setPresenterId(listOfProgramSlotResultSet.getInt("presid"));
                presenter.setPresenterName(listOfProgramSlotResultSet.getString("presenter"));
                programSlot.setPresenter(presenter);
                Producer producer = new Producer();
                producer.setProducerId(listOfProgramSlotResultSet.getInt("prodid"));
                producer.setProducerName(listOfProgramSlotResultSet.getString("producer"));
                programSlot.setProducer(producer);
                programSlot.setSlotId(listOfProgramSlotResultSet.getInt("psid"));
                programSlot.setSlotFrom(listOfProgramSlotResultSet.getTimestamp("dateFrom"));
                programSlot.setSlotTo(listOfProgramSlotResultSet.getTimestamp("dateTo"));
                RadioProgram radioProgram = new RadioProgram();
                radioProgram.setDescription(listOfProgramSlotResultSet.getString("description"));
                radioProgram.setName(listOfProgramSlotResultSet.getString("pname"));
                radioProgram.setRadioId(listOfProgramSlotResultSet.getInt("progid"));
                radioProgram.setTypicalDuration(listOfProgramSlotResultSet.getTime("typicalduration"));
                programSlot.setProgram(radioProgram);
                programSlotList.add(programSlot);
                annualSchedule.setYear(listOfProgramSlotResultSet.getInt("year"));
                weeklySchedule.setWeekNumber(listOfProgramSlotResultSet.getInt("weekno"));
                weeklySchedule.setWeeklyScheduleId(listOfProgramSlotResultSet.getInt("wsidf"));
                weeklySchedule.setAllProgramSlots(programSlotList);
                List<WeeklySchedule> listWeekSchedule = new ArrayList<>();
                listWeekSchedule.add(weeklySchedule);
                annualSchedule.setAllWeeklySchedules(listWeekSchedule);

            }
            //

//                        String sqlForProgramSlot = "select * from Phoenix.ProgramSlot where WSIDF=" + weekId;
//                        System.out.println("PS:3");
//                    Statement listOfProgramSlotstmt = con.createStatement();
//                    ResultSet listOfProgramSlotResultSet = listOfProgramSlotstmt.executeQuery(sqlForProgramSlot);
//                            while (listOfProgramSlotResultSet.next()) {
//                                ProgramSlot  programSlot=new ProgramSlot();
//                                programSlot.setProgramId(listOfProgramSlotResultSet.getInt("PSID"));
//                                programSlot.setDayNumber(listOfProgramSlotResultSet.getInt("DayNo"));
//                                programSlot.setPresenterID(listOfProgramSlotResultSet.getInt("PRESID"));
//                                programSlot.setProducerID(listOfProgramSlotResultSet.getInt("PRODID"));
//                                programSlot.setRadioProgramID(listOfProgramSlotResultSet.getInt("radio-program_progID"));
//                                programSlot.setSlotFrom(listOfProgramSlotResultSet.getTimestamp("DateFrom"));
//                             //   System.out.println("df: "+listOfProgramSlotResultSet.getTimestamp("DateFrom"));
//                                programSlot.setSlotTo(listOfProgramSlotResultSet.getTimestamp("DateTo"));
//                                programSlot.setWeeklyScheduleID(listOfProgramSlotResultSet.getInt("WSIDF"));
//                               // System.out.println("PS:"+programSlot.getProgramId());
//                                programSlotList.add(programSlot);
            //                          }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return programSlotList;
    }
/* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`schedule`.dao.impl.RadioProgramDAO#getProgramSlotByWeekAndYear(sg.edu.nus.iss.`phoenix`.`schedule`.entity.Schedule)
	 */
    @Override
    public AnnualSchedule getProgramSlotByWeekAndYear(int weekId, int year) throws SQLException {

        AnnualSchedule annualSchedule = new AnnualSchedule();
        WeeklySchedule weeklySchedule = new WeeklySchedule();
        List<ProgramSlot> programSlotList = new ArrayList<ProgramSlot>();
        conn = JDBCFactory.getConnection();

        String sql = "select w.WeekNo,w.Year,r2.progID,r2.typicalDuration,r2.description,u.name as presenter ,u2.name as producer, u.UID , p.psid,p.pname as pname,p.dayno,p.presid,p.prodid,p.status,p.datefrom,p.dateto,p.wsidf \n"
                + "from User u  join ProgramSlot p on p.PRESID=u.UID and (p.status=1 and u.status=1 )  join\n"
                + "User u2 on p.prodid=u2.uid and(u2.Status=1 and p.status=1)join\n"
                + "`radioprogram` r2 on p.pname=r2.name  and(r2.status=1 and p.status=1)join\n"
                + "WeeklySchedule w on p.WSIDF=w.wsid and (w.wsid=? and p.status=1 and w.year=?);";
        PreparedStatement listOfProgramSlotstmt = conn.prepareStatement(sql);
        listOfProgramSlotstmt.setInt(1, weekId);
        listOfProgramSlotstmt.setInt(2, year);
        ResultSet listOfProgramSlotResultSet = listOfProgramSlotstmt.executeQuery();
        while (listOfProgramSlotResultSet.next()) {
            ProgramSlot programSlot = new ProgramSlot();
            Presenter presenter = new Presenter();
            presenter.setPresenterId(listOfProgramSlotResultSet.getInt("presid"));
            presenter.setPresenterName(listOfProgramSlotResultSet.getString("presenter"));
            programSlot.setPresenter(presenter);
            Producer producer = new Producer();
            producer.setProducerId(listOfProgramSlotResultSet.getInt("prodid"));
            producer.setProducerName(listOfProgramSlotResultSet.getString("producer"));
            programSlot.setProducer(producer);
            programSlot.setSlotId(listOfProgramSlotResultSet.getInt("psid"));
            programSlot.setSlotFrom(listOfProgramSlotResultSet.getTimestamp("dateFrom"));
            programSlot.setSlotTo(listOfProgramSlotResultSet.getTimestamp("dateTo"));
            RadioProgram radioProgram = new RadioProgram();
            radioProgram.setDescription(listOfProgramSlotResultSet.getString("description"));
            radioProgram.setName(listOfProgramSlotResultSet.getString("pname"));
            radioProgram.setRadioId(listOfProgramSlotResultSet.getInt("progid"));
            radioProgram.setTypicalDuration(listOfProgramSlotResultSet.getTime("typicalduration"));
            programSlot.setProgram(radioProgram);
            programSlotList.add(programSlot);
            annualSchedule.setYear(listOfProgramSlotResultSet.getInt("year"));
            weeklySchedule.setWeekNumber(listOfProgramSlotResultSet.getInt("weekno"));
            weeklySchedule.setWeeklyScheduleId(listOfProgramSlotResultSet.getInt("wsidf"));
            weeklySchedule.setAllProgramSlots(programSlotList);
            List<WeeklySchedule> listWeekSchedule = new ArrayList<>();
            listWeekSchedule.add(weeklySchedule);
            annualSchedule.setAllWeeklySchedules(listWeekSchedule);

        }//To change body of generated methods, choose Tools | Templates.
        conn.close();
        return annualSchedule;
    }
/* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`schedule`.dao.impl.ScheduleDAO#copySChedule(sg.edu.nus.iss.`phoenix`.`schedule`.entity.Schedule)
	 */
    @Override
    public Boolean copySchedule(AnnualSchedule annualSchedule) throws SQLException {

        
        conn = JDBCFactory.getConnection();
        String sql = "update ProgramSlot set datefrom=? where pname='prog2'";
        PreparedStatement pmt = conn.prepareStatement(sql);
        // java.sql.Date sqlDate = new java.sql.Date(annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).getSlotFrom().getTime());
        //java.sql.Timestamp=new java.sql.Timestamp(annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).getSlotFrom().getTime());
        Timestamp time = new Timestamp(annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).getSlotFrom().getTime());
        System.out.println("SQLDATE" + time);
        pmt.setTimestamp(1, time);
        pmt.execute();
        return false;
    }

    @Override
    public Boolean deleteSchedule(int slotId) throws SQLException {
        Boolean deleted = false;
        try {
            conn = JDBCFactory.getConnection();
            String sql = "update programslot set status=0 where psid="+slotId;
            int updated = conn.createStatement().executeUpdate(sql);
            if(updated>0){
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
        }
        return deleted;
    }

     public boolean checkProgramSlotExists(Date dateFrom ,Date dateTo ,int weekNumber,int dayNo){
        boolean validSlot=true;
        try{
        
        conn=JDBCFactory.getConnection();
        String sqlTocheckProgramSlotExists="select ps.*,w.WeekNo,w.WSID from ProgramSlot ps join WeeklySchedule w on  w.WSID=ps.WSIDF where w.Year=? and w.WeekNo=? and ps.dayNo=?";
        PreparedStatement stmtTocheckProgramSlotExists=conn.prepareStatement(sqlTocheckProgramSlotExists);
           SimpleDateFormat formatNowYear = new SimpleDateFormat("yyyy");
        stmtTocheckProgramSlotExists.setInt(1, Integer.parseInt(formatNowYear.format(dateFrom)));
        stmtTocheckProgramSlotExists.setInt(2, weekNumber);
        stmtTocheckProgramSlotExists.setInt(3, dayNo);
        ResultSet rsTocheckProgramSlotExists=stmtTocheckProgramSlotExists.executeQuery();
        while(rsTocheckProgramSlotExists.next())
        {
             Date dateFromDb=rsTocheckProgramSlotExists.getTimestamp("dateFrom"); 
             Date dateToDb=rsTocheckProgramSlotExists.getTimestamp("dateTo");
           if((dateFrom.compareTo(dateFromDb)>=0 )&& (dateTo.compareTo(dateToDb)<=0)){
              
               validSlot=false; 
           }else
           {
           }
           
        
        }
        
        }catch(SQLException e){e.printStackTrace();}
        return validSlot;
    }
/* (non-Javadoc)
	 * @see sg.edu.nus.iss.`phoenix`.`schedule`.dao.impl.ScheduleDAO#modifySChedule(sg.edu.nus.iss.`phoenix`.`schedule`.entity.Schedule)
	 */
    @Override
    public Boolean modifySchedule(AnnualSchedule annualSchedule) throws SQLException {
        try {

            conn = JDBCFactory.getConnection();
            String sql = "update Programslot set pname=?,`radio-program_progID`=?,presid=?,prodid=? where psid=?";
            PreparedStatement modifyPreparedStatement = conn.prepareStatement(sql);
            modifyPreparedStatement.setString(1, annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).getProgram().getName());
            modifyPreparedStatement.setInt(2, annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).getProgram().getRadioId());
            modifyPreparedStatement.setInt(3, annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).getPresenter().getPresenterId());
            modifyPreparedStatement.setInt(4, annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).getProducer().getProducerId());
            modifyPreparedStatement.setInt(5, annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).getSlotId());
            return modifyPreparedStatement.executeUpdate()>0;

        }finally{
            conn.close();
        }

    }

}
