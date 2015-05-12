package sg.edu.nus.iss.phoenix.core.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PhoenixFrontController
 */
public class PhoenixFrontController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhoenixFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        processRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        processRequest(request, response);
    }

    /**
     * Process requests from clients.
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String action = FCUtilities.stripPath(pathInfo);
        System.out.println("PATH" + pathInfo);
        System.out.println("ACTION" + action);
        String result = chooseUseCase(action);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(result);
        rd.forward(request, response);
    }

    private String chooseUseCase(String action) {
        switch (action) {
            case "login":
                return "/LoginController/login";
            case "searchrp":
                return "/ProcessController/search";
            case "setuprp":
                return "/ProcessController/process";
            case "crudrp":
                return "/CRUDRpController";
            case "loadrp":
                return "/ProcessController/load";
            case "deleterp":
                return "/ProcessController/delete";
            case "logout":
                return "/LoginController/logout";
            case "loadCreateUser":
                return "/UserController/loadCreateUser";
            case "createUser":
                return "/UserController/createUser";
            case "loadCreateSchedule":
                return "/ScheduleController/loadCreateSchedule";
            case "userListPage":
                return "/ReviewSelectUser/userListPage";
            case "createSchedule":
                return "/ScheduleController/createSchedule";
            case "modifyUser":
                return "/UserController/modifyUser";
            case "loadmodifyUser":
                return "/UserController/loadmodifyUser";
            case "loadDeleteUser":
                return "/UserController/loadDeleteUser";
            case "deleteUser":
                return "/UserController/deleteUser";
            case "loadCopySchedule":
                return "/ScheduleController/loadCopySchedule";
            case "selectSchedule":
                System.out.println("hellos");
                return "/ReviewSelectScheduleController/selectYearSchedule";
            case "getWeeksByYear":
                return "/ReviewSelectScheduleController/selectWeekSchedule";

            case "getProgramSlotByWeek":
                return "/ReviewSelectScheduleController/selectProgramSlot";
            case "getProgramSlotByWeekAndYear":
                return "/ReviewSelectScheduleController/getProgramSlotByWeekAndYear";
            case "copySchedule":
                return "/ScheduleController/copySchedule";
            case "loadDeleteSchedule":
                return "/ScheduleController/loadDeleteSchedule";
            case "deleteSchedule":
                return "/ScheduleController/deleteSchedule";
            case "loadModifySchedule":
                return "/ScheduleController/loadModifySchedule";
            case "modifySchedule":
                return "/ScheduleController/modifySchedule";
            case "loadViewSchedule":
                return "/ScheduleController/loadViewSchedule";
            default:
                return "/welcome.jsp";
        }
    }

}
