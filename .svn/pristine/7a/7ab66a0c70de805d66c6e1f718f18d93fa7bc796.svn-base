/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Preeti,Gautam,Vijay
 */
package sg.edu.nus.iss.phoenix.user.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.core.controller.FCUtilities;
import sg.edu.nus.iss.phoenix.user.delegate.UserDelegate;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 *
 * @author User
 */
@WebServlet("/UserController/*")
public class UserController extends HttpServlet {

    public UserController() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           UserDelegate userDelegate = new UserDelegate();
        if (FCUtilities.stripPath(request.getPathInfo()).equals("loadCreateUser")) {
           
            try {

                List<Role> listOfRoles = userDelegate.getAllRolesDelegate();
                request.setAttribute("roles", listOfRoles);

            } catch (SQLException ex) {
              //  Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            // cuser=userDelegate.insertUserDetails(User user);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/createUser.jsp");;
            rd.forward(request, response);
        } else if (FCUtilities.stripPath(request.getPathInfo()).equals("createUser")) {
            User insertU = new User();
            String uid = request.getParameter("userid");
            String nm = request.getParameter("name");
            String pwd = request.getParameter("password");
            String email = request.getParameter("emailid");
            String con = request.getParameter("contact");
            String[] rl = request.getParameterValues("role1");
            insertU.setUserId(uid);
            insertU.setUserName(nm);
            insertU.setPassword(pwd);
            insertU.setEmailId(email);
            insertU.setContact(con);
            List<Role> lr = new ArrayList<Role>();
            for (String a : rl) {
                System.out.println("-->"+a);
                Role role = new Role();
                role.setRoleId(Integer.parseInt(a));
                lr.add(role);
            }

            UserDelegate userDel = new UserDelegate();
            try {
               if((userDelegate.validateDetailsDelegate(insertU))!=true)
               {
                insertU.setRoles(lr);
                
                boolean isInserted = userDel.saveDetailsDelegate(insertU);
                request.setAttribute("inserted", isInserted);
                
                request.setAttribute("roles", userDel.getAllRolesDelegate());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/createUser.jsp");;
                rd.forward(request, response);
                }else {
                   request.setAttribute("roles", userDel.getAllRolesDelegate());
                   request.setAttribute("message", "User Already Exists");
                   RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/createUser.jsp");
                rd.forward(request, response);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (FCUtilities.stripPath(request.getPathInfo()).equals("loadmodifyUser")) {
            
            try {
System.out.println("1111111111");
                List<Role> listOfRoles = userDelegate.getAllRolesDelegate();
                request.setAttribute("roles", listOfRoles);
                System.out.println("22222222");
                List<User> listOfUsers = userDelegate.getAllUsers();
                System.out.println("listFOusers:");
               System.out.println("33333333");
                System.out.println("listFOusers:"+listOfUsers);
                request.setAttribute("listOfUsers", listOfUsers);

            } catch (SQLException ex) {
              //  Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/pages/modifyUser.jsp");
            rd.forward(request, response);

        } else if (FCUtilities.stripPath(request.getPathInfo()).equals("modifyUser")) {
            UserDelegate modUserDelegate = new UserDelegate();
            User updateUser = new User();
            
            updateUser.setUserId(request.getParameter("userid"));
            updateUser.setUserName(request.getParameter("name"));
            updateUser.setEmailId(request.getParameter("emailid"));
            updateUser.setContact(request.getParameter("contact"));
           // HttpSession session=request.getSession();
           // System.out.println(session.getAttribute("roleSelected"));
            String[] values = request.getParameterValues("role");
            List<Role> roleList=new ArrayList<Role>();
            for(String s:values){
                Role role=new Role();
                role.setRoleName(s);
                roleList.add(role);
            }
            updateUser.setRoles(roleList);
            
            try{
           Boolean updated=modUserDelegate.ModifyUserDelegate(updateUser);
                System.out.println("res:"+updated);
            if(updated){
            RequestDispatcher rd=request.getRequestDispatcher("/pages/errorPage.jsp");
               rd.forward(request, response);   
            }else{  
                RequestDispatcher rd=request.getRequestDispatcher("/pages/modifiedUserDetails.jsp");
                request.setAttribute("modifiedUserDetails", updateUser);
               rd.forward(request, response);
                 }
            
            }catch(SQLException e){
            RequestDispatcher rd=request.getRequestDispatcher("/pages/errorPage.jsp");
               rd.forward(request, response);
            }
            
        } else if (FCUtilities.stripPath(request.getPathInfo()).equals("getuserDetailsById")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            User u = new User();
            System.out.println("R:" + request.getParameter("id"));
            try {
                u = userDelegate.getUserDetails(request.getParameter("id"));
                System.out.println("W:" + u);
            } catch (Exception e) {

            }

            Gson gson = new Gson();
            String jo = gson.toJson(u);

            out.print(jo);
        } else if (FCUtilities.stripPath(request.getPathInfo()).equals("loadDeleteUser"))
            {
             try {
                 List<User> userDetails=userDelegate.displayAllUserDelegate();
                 request.setAttribute("details",userDetails);
          
                 RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/deleteUser.jsp");;   
                 rd.forward(request, response);
             }
           
             catch (SQLException ex) {
                 Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
             }
            } else if (FCUtilities.stripPath(request.getPathInfo()).equals("deleteUser"))
            {
                try{
                    User delUser=new User();
                    UserDelegate userDelete = new UserDelegate();
                    String dl = request.getParameter("radios");
                    delUser.setuId(dl);
                    boolean del=userDelete.userDeleteDelegate(delUser);
                    request.setAttribute("deleted", del);
                    List<User> userDetails=userDelegate.displayAllUserDelegate();
                    request.setAttribute("details",userDetails);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/deleteUser.jsp");;   
                 rd.forward(request, response);
                }catch(SQLException ex)
                {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
    }
        
        
        

    }



