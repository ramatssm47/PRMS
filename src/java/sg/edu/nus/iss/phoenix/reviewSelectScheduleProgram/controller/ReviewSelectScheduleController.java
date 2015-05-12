/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vasundhara
 */
package sg.edu.nus.iss.phoenix.reviewSelectScheduleProgram.controller;

import com.google.gson.Gson;
import java.io.IOException;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sg.edu.nus.iss.phoenix.core.controller.FCUtilities;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 *
 * @author gautamverma
 */
@WebServlet("/ReviewSelectScheduleController/*")
public class ReviewSelectScheduleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println("reererer");
            processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println("reerereasdasdsadr");
            processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception e) {
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        ScheduleDelegate sd = new ScheduleDelegate();
        if (FCUtilities.stripPath(request.getPathInfo()).equals("loadCopySchedule")) {

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/copySchedule.jsp");
            rd.forward(request, response);

        } else if (FCUtilities.stripPath(request.getPathInfo()).equals("selectYearSchedule")) {
            List<AnnualSchedule> listOfAnnualSchedules = sd.getAllAnnualSchedulesDelegate();
            System.out.println("list:" + listOfAnnualSchedules.size());
            request.setAttribute("annualSchedules", listOfAnnualSchedules);

            //List<WeeklySchedule> listOfWeeklySchedules=sd.getAllWeeklySchedulesDelegate();
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/tilepages/reviewSelectScheduledProgram.jsp");
            rd.forward(request, response);

        } else if (FCUtilities.stripPath(request.getPathInfo()).equals("selectWeekSchedule")) {
            System.out.println("test" + (request.getParameter("year")));

            int year = (Integer.parseInt(request.getParameter("year")));
            List<WeeklySchedule> listOfWeeklySchedules = sd.getAllWeeklySchedulesDelegate(year);
            JSONArray jsonArray = new JSONArray();
            for (WeeklySchedule weeklySchedule : listOfWeeklySchedules) {
                JSONObject jsonObject = new JSONObject();
                try {

                    jsonObject.put("WeeklyScheduleId", weeklySchedule.getWeeklyScheduleId());
                    jsonObject.put("WeekNumber", weeklySchedule.getWeekNumber());
                    jsonObject.put("ProgramSlots", weeklySchedule.getAllWeeklySchedules());
                } catch (JSONException ex) {
                    Logger.getLogger(ReviewSelectScheduleController.class.getName()).log(Level.SEVERE.SEVERE, null, ex);
                }
                jsonArray.put(jsonObject);
            }
            JSONObject jo = new JSONObject();
            try {
                jo.put("WeeklySchedules", jsonArray);
            } catch (JSONException ex) {
                Logger.getLogger(ReviewSelectScheduleController.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.getWriter().println(jsonArray.toString());

     
        } else if (FCUtilities.stripPath(request.getPathInfo()).equals("selectProgramSlot")) {

            int weekid = Integer.parseInt(request.getParameter("weekid"));
            int year = Integer.parseInt(request.getParameter("year"));
            System.out.println("INMETHOD" + weekid + " " + year);
            List<ProgramSlot> programSlotList = sd.getAllProgramSlotsDelegate(weekid, year);
            System.out.println("PSlist:" + programSlotList.size());
            JSONArray jsonArray = new JSONArray();
            for (ProgramSlot programSlot : programSlotList) {
                JSONObject jsonObject = new JSONObject();
                try {

                } catch (Exception ex) {
                    //Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
                }
                jsonArray.put(jsonObject);
            }
            JSONObject jo = new JSONObject();
            try {
                jo.put("ProgramSlots", jsonArray);
            } catch (JSONException ex) {
                //Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.getWriter().println(jsonArray.toString());

        } else if (FCUtilities.stripPath(request.getPathInfo()).equals("getProgramSlotByWeekAndYear")) {

            try {
                int weekid = Integer.parseInt(request.getParameter("weekid"));
                int year = Integer.parseInt(request.getParameter("year"));
                System.out.println("INMETHODsssssss" + weekid + " " + year);

                //  ScheduleDAOImpl i = new ScheduleDAOImpl();
                AnnualSchedule annualSchedule = sd.getProgramSlotByWeekAndYear(weekid, year);
                Gson gson = new Gson();
                String annualScheduleJson = gson.toJson(annualSchedule);
                System.out.println(annualScheduleJson);
                request.getSession().setAttribute("annualScheduleObject", annualSchedule);
                response.setContentType("application/json");

                JSONArray jsonArray = new JSONArray();
                WeeklySchedule weeklySchedule = annualSchedule.getAllWeeklySchedules().get(0);

                for (ProgramSlot programSlot : weeklySchedule.getAllWeeklySchedules()) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("year", annualSchedule.getYear());
                    jsonObject.put("weekNumber", weeklySchedule.getWeekNumber());
                    jsonObject.put("radioProgramID", programSlot.getProgram().getRadioId());
                    jsonObject.put("radioProgramName", programSlot.getProgram().getName());
                    jsonObject.put("presenterName", programSlot.getPresenter().getPresenterName());
                    jsonObject.put("producerName", programSlot.getProducer().getProducerName());
                    jsonObject.put("slotTo", programSlot.getSlotTo());
                    jsonObject.put("slotFrom", programSlot.getSlotFrom());
                    jsonObject.put("programName", programSlot.getProgram().getName());
                    jsonObject.put("presenterId", programSlot.getPresenter().getPresenterId());
                    jsonObject.put("producerId", programSlot.getProducer().getProducerId());
                    jsonObject.put("duration", programSlot.getProgram().getTypicalDuration());
                    jsonObject.put("slotId", programSlot.getSlotId());
                    //jsonObject.put(("date")), null)
                    jsonArray.put(jsonObject);
                }
                System.out.println("JA" + jsonArray);

                // response.getWriter().println(annualScheduleJson);
                response.getWriter().println(jsonArray);

            } catch (JSONException j) {
                j.printStackTrace();
            }

        }
    }
}
