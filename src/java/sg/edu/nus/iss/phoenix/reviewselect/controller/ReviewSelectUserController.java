/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.reviewselect.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sg.edu.nus.iss.phoenix.core.controller.FCUtilities;
import sg.edu.nus.iss.phoenix.user.controller.UserController;
import sg.edu.nus.iss.phoenix.user.delegate.UserDelegate;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 *
 * @author vijay
 */
@WebServlet("/ReviewSelectUser/*")
public class ReviewSelectUserController extends HttpServlet {

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
        String selection = FCUtilities.stripPath(request.getPathInfo());
        UserDelegate userDelegate = new UserDelegate();

        switch (selection) {
            case "userListPage":
                try {

                    response.setContentType("application/json");
                    List<Role> listOfRoles = userDelegate.getAllRolesDelegate();
                    request.setAttribute("roles", listOfRoles);

                    List<User> listOfUsers = userDelegate.getPresenterProducerList(request.getParameter("role"));
                    request.setAttribute("roleType", request.getParameter("role"));
                    request.setAttribute("listOfUsers", listOfUsers);

                } catch (SQLException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/tilepages/userListPage.jsp");
                rd.forward(request, response);
                break;
//      case "Search":
//              UserDelegate searchDelegate= new UserDelegate();
//              User searchUser= new User();
////              request.getParameter(searchName);
////              searchUser.setUName(searchName);
////               List<User> searchUser=searchDelegate.searchPresenterProducer(searchUser);  
//          
//                break;

            case "searchUserOnUserId":
                try {
                    //request.setAttribute("role","presenter");
                    System.out.println("ininni" + request.getParameter("roleType"));
                    response.setContentType("application/json");
                    PrintWriter out = response.getWriter();
                    List<User> listOfUsers = userDelegate.searchUserWithId(request.getParameter("userid"), request.getParameter("roleType"));
                    Gson gson = new Gson();
                    String jo = gson.toJson(listOfUsers);

                    HttpSession session = request.getSession();
                    //session.setAttribute("retrievedUser", listOfUsers);
                    request.setAttribute("retrievedUser", listOfUsers);

                    request.setAttribute("listOfUsers", listOfUsers);
                    System.out.println(jo);
                    out.println(jo);

                } catch (Exception e) {
                    e.printStackTrace();
                }

        }

    }
}
