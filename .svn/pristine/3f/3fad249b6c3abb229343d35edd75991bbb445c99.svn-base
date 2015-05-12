package UserTestPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;
import org.junit.*;
import sg.edu.nus.iss.phoenix.user.service.UserService;
import static org.junit.Assert.*;

/**
 *
 * @author vasundharabhatia,Vijay
 */
public class UserUnitTest {
    
    private User user=new User();
    List<Role> roles=new ArrayList<Role>();
    UserService userService=new UserService();
    Role role=new Role();
     
  public  List<User> resultBeforeInsert;

 
  public   List<User> searchResult;

    public UserUnitTest() {
    }
    
     @Test
    public void testGetAllRolesService() throws SQLException{
        
       roles=userService.getAllRolesService();
       
       Assert.assertEquals(4, userService.getAllRolesService().size());
       String userIdString=Integer.toString(userService.fetchId(user));
        user.setuId(userIdString);
       userService.userDeleteService(user);
       
    }
    
    @Test
    public void testSaveDetailsService() throws SQLException{
       if(userService.validateDetailsService(user)==false)
         Assert.assertEquals(true, userService.saveDetailsService(user));
    }
    
   
    
    @Test
    public void testModifyUserService() throws SQLException{
        user.setContact("987657");
        Assert.assertEquals(false, userService.ModifyUserService(user));
        user.setContact("12345654");
       
    }
    
    @Test
    public void testGetUserDetails() throws SQLException{
        Assert.assertEquals("testUser", (userService.getUserDetails("testUserId")).getUserName());
        
    }
    
    @Test
    public void testGetAllUsers() throws SQLException{
        
        String userIdString=Integer.toString(userService.fetchId(user));
        user.setuId(userIdString);
        List<User> userList=new ArrayList<User>();
        userList=userService.getAllUsers();
        System.out.println(userList.size());
        
        userService.userDeleteService(user);
        userList=userService.getAllUsers();
        System.out.println(userList.size());
        
        if(userService.validateDetailsService(user)==false)
        userService.saveDetailsService(user);
        userIdString=Integer.toString(userService.fetchId(user));
        user.setuId(userIdString);
        Assert.assertEquals(userList.size()+1, userService.getAllUsers().size());
        userService.userDeleteService(user);
    }
    
    
    
    
    @Test
    public void testUserDeleteService() throws SQLException {
        System.out.println("userDeleteService");
        if(userService.validateDetailsService(user)==false)
        userService.saveDetailsService(user);
        List<User> userListBeforeDelete=new ArrayList<User>();
        
        //before
        userListBeforeDelete=userService.getAllUsers();
        String userIdString=Integer.toString(userService.fetchId(user));
        user.setuId(userIdString);
        userService.userDeleteService(user);
        
        assertEquals(userListBeforeDelete.size()-1,userService.getAllUsers().size());
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    @Test
    public void testSearchUserWithId() throws Exception {
        System.out.println("searchUserWithId");
        //String userid = "fetchRole";
        //String roleType = "";
           
         if(userService.validateDetailsService(user)==false)
         searchResult= userService.searchUserWithId("testUserId", "Presenter");
        for(User  testUser: searchResult){
        assertEquals("testUserId", testUser.getUserId());
    }
        // TODO review the generated test code and remove the default call to fail.
        
        
//        userService.userDeleteService(testUser);
    }
    
    @Test
    public void testGetPresenterProducerList() throws Exception {     
        
        String fetchRole="Presenter";
        System.out.println(userService.getPresenterProducerList(fetchRole).size());
        resultBeforeInsert = userService.getPresenterProducerList(fetchRole);
        if(userService.validateDetailsService(user)==false)
        userService.saveDetailsService(user);
        
        System.out.println(userService.getPresenterProducerList(fetchRole).size());
        assertEquals(resultBeforeInsert.size()+1,userService.getPresenterProducerList(fetchRole).size());
      String userIdString=Integer.toString(userService.fetchId(user));
        user.setuId(userIdString);
       userService.userDeleteService(user);
    }

    /**
     * Test of searchUserWithId method, of class UserService.
     */
    
    
    
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        role.setRoleId(userService.fetchRoleIdForName("Presenter"));
        role.setRoleName("Presenter");
        
        //System.out.println("decfw"+role.getRoleName()+role.getRoleId());
        roles.add(role);
        //System.out.println(roles.size());
        user.setUserName("testUser");
        user.setUserId("testUserId");
        user.setPassword("testpass");
        user.setEmailId("test@test.com");
        user.setContact("12345654");
        user.setRoles(roles);
    }
    
    @After
    public void tearDown() {
        user=null;
        roles=null;
        role=null;
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
