/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ScheduleTestPackage;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.radioprogram.service.RadioProgramService;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;
import sg.edu.nus.iss.phoenix.user.entity.Presenter;
import sg.edu.nus.iss.phoenix.user.entity.Producer;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;
import sg.edu.nus.iss.phoenix.user.service.UserService;
import java.sql.SQLException;

/**
 *
 * @author Preeti
 */
public class ScheduleTest {
    
    ProgramSlot ps=new ProgramSlot();
    ScheduleService scheSer=new ScheduleService();
    RadioProgram radioProg=new RadioProgram();
    RadioProgramService radioProgSer=new RadioProgramService();
    User usr=new User();
    UserService userser=new UserService();
    Presenter pr=new Presenter();
    Producer prod=new Producer();
    Role rl=new Role();
    WeeklySchedule we=new WeeklySchedule();
    
    
    public ScheduleTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException {
        
       List<Role> r=new ArrayList<Role>();
       
        rl.setRoleName("Presenter");
        rl.setRoleId(14);
        r.add(rl);
        usr.setPassword("Preeti123");
        usr.setEmailId("Tarun@gmail.com");
        usr.setContact("23232323");
        usr.setUserId("fdff");
        usr.setUserName("Tarfdfun");
        usr.setRoles(r);
        if(userser.validateDetailsService(usr)==false)
        {
        boolean f=userser.saveDetailsService(usr);
        System.out.println(f);
            System.out.println("gjghgj");
        }else {
            System.out.println("Already Exists");
        }
        
        usr.setuId(Integer.toString(userser.fetchId(usr)));
        rl.setRoleId(userser.fetchRoleId(usr));
        radioProg.setName("PPPPPPPPPP");
        radioProg.setTypicalDuration(new Time(30));
        radioProg.setDescription("YOGAAAAAAAAAAAA");
        radioProgSer.insertRP(radioProg);
        RadioProgram rpg=radioProgSer.findRP(radioProg.getName());
        
        ps.setProgram(rpg);
        ps.setYear(2014);
        ps.setWeekNumber(4);
        ps.setDayNumber(6);
        ps.setSlotFrom(new Time(40));
        ps.setSlotTo(new Time(50));
        pr.setPresenterId(Integer.parseInt(usr.getuId()));
        ps.setPresenter(pr);
        prod.setProducerId(Integer.parseInt(usr.getuId()));
        ps.setProducer(prod);
        ps.setProducer(prod);
        ps.setPresenter(pr);
              
     }
    
    @After
    public void tearDown() {
    //    scheSer.deleteSchedule(23);
    }

    /**
     * Test of createScheduleService method, of class ScheduleService.
     */
    @Test
    public void testCreateScheduleService() throws Exception {
               
        Assert.assertEquals(true, scheSer.createScheduleService(ps));
              

        
        
    }
}
