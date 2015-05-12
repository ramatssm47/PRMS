<%-- 
    Document   : errorPage
    Created on : Sep 15, 2014, 11:27:32 AM
    Author     : vasundharabhatia
--%>

<%@page import="sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <head>
        <link rel="stylesheet" href="/phoenix/css/main.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select Scheduled Program Page</title>
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
        <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
        <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
        <script src="/scripts/jquery/jquery.session.js"></script>
        <script>

            function sentProgramSlot(slotDetails)
            {
//                opener.document.testfrm.valuefrompage.value = slotDetails;
//
                var strs = slotDetails.split("/");
                opener.document.testfrm.radioId.value = strs[0];
                opener.document.testfrm.year.value = strs[1];
                opener.document.testfrm.radioProgram.value = strs[2];
                opener.document.testfrm.producer.value = strs[3];
                opener.document.testfrm.presenter.value = strs[5];
                
                var timeSlotFromTest=strs[6].split(" ")[1].split(":");
                var timeSlotFrom=timeSlotFromTest[0]+":"+timeSlotFromTest[1];
               
                
                var timeSlotToTest=strs[4].split(" ")[1].split(":");
                var timeSlotTo=timeSlotToTest[0]+":"+timeSlotToTest[1];
                
                opener.document.testfrm.timepicker1.value = timeSlotTo;
                opener.document.testfrm.timepicker.value =timeSlotFrom;
                opener.document.testfrm.week.value = strs[7];
                opener.document.testfrm.producerId.value = strs[9];
                opener.document.testfrm.presenterId.value = strs[8];
                opener.document.testfrm.duration.value = strs[10];
                var month = strs[6].split(" ")[0].split("-")[1];
                opener.document.testfrm.month.value = month;
                var date = strs[6].split(" ")[0];
                opener.document.testfrm.datepicker.value = date;
                opener.document.testfrm.dayNumber.value = strs[6].split(" ")[0].split("-")[2];
                opener.document.testfrm.slotId.value = strs[11];
                var date=new Date(strs[6].split(" ")[0]);
                //opener.document.testfrm.dayNumber.value=date.getDay();
                opener.document.testfrm.datepicker.value=date;
                self.close();
            }

            function getWeeksForYear() {
                var listItems = "";
                var year = $("#annualScheduleYear").val();
                $.ajax({
                    url: 'http://localhost:8080/phoenix/controller/getWeeksByYear',
                    dataType: 'JSON',
                    data: {year: year},
                    type: 'POST',
                    cache: false,
                    success: function (data)
                    {
                        $('#weekScheduleYear').empty();
                        var array = JSON.parse(JSON.stringify(data));

                        for (var i = 0; i < array.length; i++) {

                            listItems += "<option value='" + array[i].WeeklyScheduleId + "'>" + array[i].WeekNumber + "</option>";

                        }

                        $("#weekScheduleYear").html(listItems);
                    }

                });

            }

            function getProgramSlots() {
                var splitter = "/";

                var weekid = $("#weekScheduleYear").val();
                $.ajax({
                    url: 'http://localhost:8080/phoenix/controller/getProgramSlotByWeekAndYear',
                    dataType: 'JSON',
                    data: {weekid: weekid,year: $("#annualScheduleYear").val()},
                    type: 'POST',
                    cache: false,
                    success: function (data)
                    {
                        if(data!=null){
                          //alert(s);
                        $('#tbody').empty();
                         var s = JSON.stringify(data);
                         
                            var obj = "\'" + s + "\'";

                            var ob = $.parseJSON(s);
                    
                        var eachrow;
                         var array = JSON.parse(s);
                         <c:if test="${forViewSchedule eq 'true'}" >                              
                        eachrow += "<tr><th>Year</th><th>Radio Program Name</th><th>Producer Name</th><th>Presenter Name</th><th>Slot From</th><th>Slot To</th><th>Week NUmber</th></tr> "
                            </c:if>
                            <c:if test="${forViewSchedule ne 'true'}" > 
                        eachrow += "<tr><th>Select</th><th>Year</th><th>Radio Program Name</th><th>Producer Name</th><th>Presenter Name</th><th>Slot From</th><th>Slot To</th><th>Week NUmber</th></tr> "
                            </c:if>
                        
                        
                       
                        for (var i = 0; i < array.length; i++) {
                          
                            if(i%2==0){
                                eachrow +='<tr class="even">';
                            }else{
                                eachrow +='<tr class="odd">';
                            }
                            <c:if test="${forViewSchedule eq 'true'}" >                              
                                eachrow += ""        
                            </c:if>
                            <c:if test="${forViewSchedule ne 'true'}" > 
                                eachrow += "<td><input type='radio' value='"+array[i].radioProgramID + splitter + array[i].year +  splitter + array[i].programName + splitter + array[i].producerName + splitter + array[i].slotTo + splitter + array[i].presenterName + splitter + array[i].slotFrom +splitter+array[i].weekNumber+splitter+array[i].presenterId+splitter+array[i].producerId+splitter+array[i].duration+splitter+array[i].slotId+"' name='selected'/></td>"
                            </c:if>
                                var timeSlotFromTest=array[i].slotFrom.split(" ")[1].split(":");
                var timeSlotFrom=timeSlotFromTest[0]+":"+timeSlotFromTest[1];
               
                
                var timeSlotToTest=array[i].slotTo.split(" ")[1].split(":");
                var timeSlotTo=timeSlotToTest[0]+":"+timeSlotToTest[1];
                                    eachrow+= "<td>" + array[i].year + "</td>"
                                    + "<td>" + array[i].programName + "</td>"
                                    + "<td>" + array[i].producerName + "</td>"
                                    + "<td>" + array[i].presenterName + "</td>"
                                    + "<td>" + timeSlotFrom + "</td>"                                 
                                    + "<td>" + timeSlotTo + "</td>"
                                    + "<td>" + array[i].weekNumber + "</td>"
                            eachrow+="</tr>"
                            $('#tbody').append(eachrow);
                            eachrow = "";
                        }
                        $('input[name="selected"]').on('change', function () {

                            sentProgramSlot($(this).val());
                            
                        });
                        }else{alert('Not data found for the selected year and week');}
                    }

                });
            }



        </script>
    </head>
    <body>
        <c:if test="${forViewSchedule eq 'true'}" > <h1> View Scheduled Program</h1></c:if>
       <c:if test="${forViewSchedule ne 'true'}" > <h1> Review Select Scheduled Program</h1></c:if>
        <center>
            <table class="framed">
                <tr>
                    <td align="right">
                        Year :  &nbsp;&nbsp;&nbsp;
                    </td>
                    <td>
                    <select id="annualScheduleYear" name='annualScheduleYear' onchange="getWeeksForYear()"  >
                           <option value="Year" selected="selected">YEAR</option>
                        <c:forEach items="${annualSchedules}" var="annualSchedule">

                            <option value="${annualSchedule.year}">${annualSchedule.year}</option>

                        </c:forEach>
                    </select>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        Week : &nbsp;&nbsp;&nbsp;
                    </td>
                    <td>
                         <select id="weekScheduleYear" >
                         </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="button" value="Submit" onclick="getProgramSlots();"/>
                    </td>
                </tr>
            </table>
        </center>        
        <table id="tbody" class="borderAll">
                <thead>
                    <tr>
                    <c:if test="${forViewSchedule ne 'true'}" > <th>Select</th> </c:if>
                        <th>Year</th>
                        <th>Radio Program Name</th>
                        <th>Presenter</th>
                        <th>Producer</th>
                        <th>Slot From</th>
                        <th>Slot To</th>
                        <th>Week Number</th>

                    </tr>
                </thead>               
            </table>           
    </body>

</html>