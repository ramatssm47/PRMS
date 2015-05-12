/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.user.entity.Presenter;
import sg.edu.nus.iss.phoenix.user.entity.Producer;

/**
 *
 * @author Ram,Anu,Dinesh,Vijay
 */
// Servlet implementation of class ScheduleController
@WebServlet("/ScheduleController/*")
public class ScheduleController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            ScheduleDelegate sd = new ScheduleDelegate();

            if (FCUtilities.stripPath(request.getPathInfo()).equals("loadCreateSchedule")) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/createSchedule.jsp");;
                request.setAttribute("page", "create");
                rd.forward(request, response);

            } else if (FCUtilities.stripPath(request.getPathInfo()).equals("loadCopySchedule")) {
                request.setAttribute("page", "copy");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/createSchedule.jsp");
                rd.forward(request, response);

            } else if (FCUtilities.stripPath(request.getPathInfo()).equals("createSchedule")) {
                ProgramSlot programSlot = new ProgramSlot();

                programSlot.setYear(Integer.parseInt(request.getParameter("year")));

                //set Presenter Object
                Presenter presenter = new Presenter();
                presenter.setPresenterId(Integer.parseInt(request.getParameter("presenterId")));
                presenter.setPresenterName(request.getParameter("presenter"));
                programSlot.setPresenter(presenter);
                //set Producer Object
                Producer producer = new Producer();
                producer.setProducerId(Integer.parseInt(request.getParameter("producerId")));
                producer.setProducerName(request.getParameter("producer"));
                programSlot.setProducer(producer);

                //set Radio Program Object
                RadioProgram radioProgram = new RadioProgram();
                radioProgram.setName(request.getParameter("radioProgram"));
                radioProgram.setRadioId(Integer.parseInt(request.getParameter("radioId")));
                programSlot.setProgram(radioProgram);

                //set Week Object
                programSlot.setWeekNumber(Integer.parseInt(request.getParameter("week")));

                DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm");

                String slotFromDate = request.getParameter("date") + " " + request.getParameter("fromTime");

                Date fromdate = format.parse(slotFromDate);
                programSlot.setSlotFrom(fromdate);

                //  sd.validateTimeSlotDelegate(fromdate);
                String slotToDate = request.getParameter("date") + " " + request.getParameter("toTime");

                Date todate = format.parse(slotToDate);
                programSlot.setSlotTo(todate);

                programSlot.setDayNumber(Integer.parseInt(request.getParameter("dayNumber")));
                ScheduleDelegate del = new ScheduleDelegate();
                
                if(del.checkProgramSlotExists(fromdate, todate, Integer.parseInt(request.getParameter("week")), Integer.parseInt(request.getParameter("dayNumber")))){
                   boolean isInserted = del.createScheduleDelegate(programSlot);
                if (request.getParameter("copy").equalsIgnoreCase("yes")) {
                    request.setAttribute("page", "copy");
                    request.setAttribute("copied", isInserted);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/createSchedule.jsp");
                    rd.forward(request, response);
                } else {
                    request.setAttribute("page", "create");
                    request.setAttribute("inserted", isInserted);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/createSchedule.jsp");
                    rd.forward(request, response);
                } 
                }else{
                    request.setAttribute("message", "Slot Already Exists");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/createSchedule.jsp");
                    rd.forward(request, response);
                }
                

            } else if (FCUtilities.stripPath(request.getPathInfo()).equals("copySchedule")) {

                AnnualSchedule annualSchedule = (AnnualSchedule) request.getSession().getAttribute("annualScheduleObject");
                System.out.println("Sessionvar:" + annualSchedule);
                String fromTime = request.getParameter("fromTime");
                String toTime = request.getParameter("toTime");
                String date = request.getParameter("date");
                System.out.println(date + "" + request.getParameter("day"));
                Calendar calFrom = Calendar.getInstance();
                calFrom.set(Integer.parseInt(request.getParameter("year")), Integer.parseInt(request.getParameter("month")) - 1, Integer.parseInt(date.split(" ")[2]), Integer.parseInt(fromTime.split(":")[0]), Integer.parseInt(fromTime.split(":")[1]));

                Calendar calTo = Calendar.getInstance();
                calTo.set(Integer.parseInt(request.getParameter("year")), Integer.parseInt(request.getParameter("month")) - 1, Integer.parseInt(date.split(" ")[2]), Integer.parseInt(toTime.split(":")[0]), Integer.parseInt(toTime.split(":")[1]));

                System.out.println("calF" + calFrom.getTime());
                System.out.println("calT" + calTo.getTime());

                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d = calFrom.getTime();

                String fromDate = sdf.format(d);
                Date n = sdf.parse(fromDate);
                System.out.println("NNN:" + n);

                annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).setSlotFrom(calFrom.getTime());
                annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).setSlotFrom(calTo.getTime());
                sd.copySchedule(annualSchedule);

            } else if (FCUtilities.stripPath(request.getPathInfo()).equals("loadDeleteSchedule")) {
                request.setAttribute("page", "delete");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/createSchedule.jsp");
                rd.forward(request, response);
            } else if (FCUtilities.stripPath(request.getPathInfo()).equals("deleteSchedule")) {

                int slotId = Integer.parseInt(request.getParameter("slotId"));
                Boolean deleted = sd.deleteSchedule(slotId);
                request.setAttribute("deleted", deleted);
                request.setAttribute("page", "delete");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/createSchedule.jsp");
                rd.forward(request, response);

            } else if (FCUtilities.stripPath(request.getPathInfo()).equals("modifySchedule")) {

                AnnualSchedule annualSchedule = (AnnualSchedule) request.getSession().getAttribute("annualScheduleObject");
                System.out.println(annualSchedule);

                annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).getPresenter().setPresenterName(request.getParameter("presenter"));
                annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).getPresenter().setPresenterId(Integer.parseInt(request.getParameter("presenterId")));

                annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).getProducer().setProducerName(request.getParameter("producer"));
                annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).getProducer().setProducerId(Integer.parseInt(request.getParameter("producerId")));

                annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).getProgram().setName(request.getParameter("radioProgram"));
                annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).getProgram().setRadioId(Integer.parseInt(request.getParameter("radioId")));
                annualSchedule.getAllWeeklySchedules().get(0).getAllWeeklySchedules().get(0).setSlotId(Integer.parseInt(request.getParameter("slotId")));
                Boolean modified = sd.modifySchedule(annualSchedule);
                request.setAttribute("page", "modify");
                request.setAttribute("modified", modified);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/createSchedule.jsp");
                rd.forward(request, response);
            } else if (FCUtilities.stripPath(request.getPathInfo()).equals("loadModifySchedule")) {
                request.setAttribute("page", "modify");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/createSchedule.jsp");
                rd.forward(request, response);

            } else if (FCUtilities.stripPath(request.getPathInfo()).equals("loadViewSchedule")) {
                List<AnnualSchedule> listOfAnnualSchedules = sd.getAllAnnualSchedulesDelegate();
                request.setAttribute("annualSchedules", listOfAnnualSchedules);
                request.setAttribute("forViewSchedule", "true");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/viewSchedule.jsp");;
                rd.forward(request, response);
            }

        } catch (ParseException | SQLException | ServletException | IOException ex) {
            Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
