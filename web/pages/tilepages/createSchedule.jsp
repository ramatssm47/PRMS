<%-- 
    Document   : createUser
    Created on : Sep 4, 2014, 5:04:59 PM
    Author     : Dinesh,Ram,Anu,Preeti
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <head>
        <fmt:setBundle basename="ApplicationResources" />
        <link rel="stylesheet" href="/phoenix/scripts/jquery/jquery-ui.css" />
        <script src="/phoenix/scripts/jquery/jquery-1.11.1.js"></script>
        <script src="/phoenix/scripts/jquery/jquery-ui.js"></script>
        <script src="/phoenix/scripts/jquery/jquery-ui.min.js"></script>
        <script src="/phoenix/scripts/jquery/jquery.ui.timepicker.js"></script>
        <script>
            var months = [ 01, 02, 03, 04, 05, 06, 07, 08, 09, 10, 11, 12 ];
           
            Date.prototype.getWeek = function() {
                var onejan = new Date(this.getFullYear(), 0, 1);
                return Math.ceil((((this - onejan) / 86400000) + onejan.getDay() + 1) / 7);
            }
            $(document).ready(function() {
                
 	$("#getScheduledProgramButton").click(function (){
            
            window.open("${pageContext.request.contextPath}/controller/selectSchedule", "myWindow", "width=800, height=300");    // Opens a new window
            
        });
                $("#datepicker").datepicker({
                    onSelect: function(date) {

                        var d = new Date(date);
                        $("#week").val(d.getWeek());
                        $("#year").val(d.getFullYear());
                        $("#month").val(months[d.getMonth()]);
                        $("#dayNumber").val(d.getDate());
                    }
                });
                $('#datepickerImage').click(function() {
                    $('#datepicker').datepicker('show');
                });
                $(function() {
                    $('#timepicker').timepicker();
                    $('#timepicker1').timepicker();
                });

            

            $('#timepicker').timepicker({
                // Options
                timeSeparator: ':', // The character to use to separate hours and minutes. (default: ':')
                showLeadingZero: true, // Define whether or not to show a leading zero for hours < 10.

                showMinutesLeadingZero: true, // Define whether or not to show a leading zero for minutes < 10.

                showPeriod: false, // Define whether or not to show AM/PM with selected time. (default: false)
                showPeriodLabels: true, // Define if the AM/PM labels on the left are displayed. (default: true)
                periodSeparator: ' ', // The character to use to separate the time from the time period.
                altField: '#alternate_input', // Define an alternate input to parse selected time to
               // defaultTime: '12:34', // Used as default time when input field is empty or for inline timePicker
                // (set to 'now' for the current time, '' for no highlighted time,


                // trigger options
                showOn: 'focus', // Define when the timepicker is shown.
                // 'focus': when the input gets focus, 'button' when the button trigger element is clicked,
                // 'both': when the input gets focus and when the button is clicked.
                button: null, // jQuery selector that acts as button trigger. ex: '#trigger_button'

                // Localization
                hourText: 'Hour', // Define the locale text for "Hours"
                minuteText: 'Minute', // Define the locale text for "Minute"
                amPmText: ['AM', 'PM'], // Define the locale text for periods

                // Position
                myPosition: 'left top', // Corner of the dialog to position, used with the jQuery UI Position utility if present.
                atPosition: 'left bottom', // Corner of the input to position

                // Events
                //beforeShow: beforeShowCallback, // Callback function executed before the timepicker is rendered and displayed.
                //onSelect: onSelectCallback,   // Define a callback function when an hour / minutes is selected.
                //onClose: onCloseCallback,     // Define a callback function when the timepicker is closed.
                //onHourShow: onHourShow,       // Define a callback to enable / disable certain hours. ex: function onHourShow(hour)
                //onMinuteShow: onMinuteShow,   // Define a callback to enable / disable certain minutes. ex: function onMinuteShow(hour, minute)

                // custom hours and minutes
                hours: {
                    starts: 0, // First displayed hour
                    ends: 23                  // Last displayed hour
                },
                minutes: {
                    starts: 0, // First displayed minute
                    ends: 55, // Last displayed minute
                    interval: 5               // Interval of displayed minutes
                },
                rows: 4, // Number of rows for the input tables, minimum 2, makes more sense if you use multiple of 2
                showHours: true, // Define if the hours section is displayed or not. Set to false to get a minute only dialog
                showMinutes: true, // Define if the minutes section is displayed or not. Set to false to get an hour only dialog

                // buttons
                showCloseButton: false, // shows an OK button to confirm the edit
                closeButtonText: 'Done', // Text for the confirmation button (ok button)
                showNowButton: false, // Shows the 'now' button
                nowButtonText: 'Now', // Text for the now button
                showDeselectButton: false, // Shows the deselect time button
                deselectButtonText: 'Deselect' // Text for the deselect button

            });
             $(".readonly").keydown(function(e){
        e.preventDefault();
    });
            });
            function openDialog(divName){
               var url ='';
               var titleName = '';
               if(divName=='radioProgramDialog'){
                    url = "${pageContext.request.contextPath}/controller/searchrp";
                    titleName = 'Review Select radio Program';
                }else if(divName=='presenterDialog'){
                    url = "${pageContext.request.contextPath}/controller/userListPage?role=presenter";
                     titleName = 'Review Select Presenter';
                }else {
                    url = "${pageContext.request.contextPath}/controller/userListPage?role=producer";
                     titleName = 'Review Select Producer';
                }
                var strWindowFeatures = "location=yes,height=300,width=600,scrollbars=yes,status=yes";
                var win = window.open(url, "_blank", strWindowFeatures);
            }
            
            function onload(){
        
        if('${inserted}'=='true'){
            alert("Progam Slot Created Sucessfully");
            resetFields();
        }else if('${deleted}'=='true'){
            alert("Program Slot Deleted Sucessfully");
        }else if('${modified}'=='true'){
            alert(" Program Slot Updated Sucessfully");
        }else if('${copied}'=='true'){
            alert(" Program Slot Copied Sucessfully");
        }
        if('${message}'!='')
        {
            alert('${message}');
        }
        }
        function getAction(){
    var url='';
    <c:choose>
    <c:when test="${page eq 'copy'}">
        document.testfrm.action = "${pageContext.request.contextPath}/controller/createSchedule?copy=yes";
    </c:when>
         <c:when test="${page eq 'modify'}">
       document.testfrm.action = "${pageContext.request.contextPath}/controller/modifySchedule";
    </c:when>
         <c:when test="${page eq 'delete'}">
       document.testfrm.action = "${pageContext.request.contextPath}/controller/deleteSchedule";
    </c:when>
    <c:otherwise>
       document.testfrm.action = "${pageContext.request.contextPath}/controller/createSchedule?copy=no";
    </c:otherwise>
</c:choose>
    return 1;
    }
function timeDifference() {
     var timeFrom =$('#timepicker').val();
     var timeTo= $('#timepicker1').val();
     var dur=$('#duration').val();
     timeArr3=dur.split(":");
     var minutes3=(timeArr3[0]*60) + parseInt(timeArr3[1]) + parseInt(timeArr3[2]/60);
     timeArr1=timeFrom.split(":");
     var minutes1=(timeArr1[0]*60) + parseInt(timeArr1[1]);
     timeArr2=timeTo.split(":");
     var minutes2=(timeArr2[0]*60)+ parseInt(timeArr2[1]);
     
     var min=minutes2-minutes1;
     if(minutes1>minutes2)
     {
         alert("From Time cannot be greater then To time");
         return false;
     }else if(min!=minutes3)
     {
         alert("Invalid Slot Entry");
         return false;
     }else{
        
       
   return true;
}
}
        </script>
        <style>
            .icon{width: 20px;height: 20px}
        </style>
        <c:set var="t" value="true" />
        <title><fmt:message key="title.createSchedule" /></title>
    </head>
    <body onload="onload();">
        <form name="testfrm" onsubmit="getAction();"
              method=post>
          
        <c:choose>
    <c:when test="${page eq 'copy'}">
        <h2> <fmt:message key="title.copySchedule" /> </h2>
        <input type="button"   name="getScheduledProgramButton" id="getScheduledProgramButton"  value="Click to Copy a scheduled Program"/>
    </c:when>
         <c:when test="${page eq 'modify'}">
       <h2> <fmt:message key="title.modifySchedule" /> </h2>
        <input type="button"   name="getScheduledProgramButton" id="getScheduledProgramButton"  value="Click to Select a scheduled Program"/>
    </c:when>
         <c:when test="${page eq 'delete'}">
       <h2> <fmt:message key="title.deleteSchedule" /> </h2>
        <input type="button"   name="getScheduledProgramButton" id="getScheduledProgramButton"  value="Click to Select a scheduled Program"/>
    </c:when>
    <c:otherwise>
        <h2> <fmt:message key="title.createSchedule" /> </h2>
    </c:otherwise>
</c:choose>
            <table>
                <tr>
                    <td>
                        <table>
                            <tr>
                                <td>
                                    <fmt:message key="fieldLabel.annualSchedule" />
                                </td>
                                <td>
                                    <input type="text" name="year" id="year" class= "readonly" required required/>
                                </td>
                                <td rowspan="4" style="vertical-align: central">
                                    <input type="hidden" name="date" id="datepicker"/>
                                    <c:if test="${(page eq 'create')||(page eq 'copy')}"> 
                                    <img class="icon" src="${pageContext.request.contextPath}/img/date.png" id="datepickerImage"/> 
                                    </c:if>
                                </td>
                                
                                <td>
                                    <fmt:message key="label.radioprogram.name" />
                                </td>
                                <td>
                                    <input type="text" name="radioProgram" id="radioProgram" class= "readonly" required style="width:110px;"/> <input type="text" name="duration" id = "duration" placeholder="Duration" style="width:52px;" class= "readonly" required/>
                                    <input type="hidden" name="radioId" id="radioId" /> 
                                </td>
                                <td>
                                    <c:if test="${(page eq 'create')||(page eq 'modify')}"> <img class="icon" src="${pageContext.request.contextPath}/img/search.png" id="searchrp" onclick="openDialog('radioProgramDialog');"/></c:if>
                                    <div id="radioProgramDialog" title="Review Select Radio Program"></div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <fmt:message key="fieldLabel.month" />
                                </td>
                                <td>
                                    <input type="text" name="month" id="month" class= "readonly" required/>
                                </td>
                                <td>
                                    <fmt:message key="fieldLabel.presenter" />
                                </td>
                                <td>
                                    <input type="text" name="presenter" id="presenter" class= "readonly" required/> 
                                    <input type="hidden" name="presenterId" id="presenterId" value="1" /> 
                                </td>
                                <td>
                                    <c:if test="${(page eq 'create')||(page eq 'modify')}"> <img class="icon" src="${pageContext.request.contextPath}/img/user_search.png" id="userSearchPre" onclick="openDialog('presenterDialog');"/> </c:if>
                                    <div id="presenterDialog" title="Review Select Presenter"></div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <fmt:message key="fieldLabel.weeklySchedule" />
                                </td>
                                <td>
                                    <input type="text" id="week" name="week" class= "readonly" required/>
                                </td>
                                 <td>
                                    <fmt:message key="fieldLabel.producer" />
                                </td>
                                <td>
                                    <input type="text" name="producer" id="producer" class= "readonly" required/> 
                                    <input type="hidden" name="producerId" id="producerId" value="1" /> 
                                </td>
                                <td>
                                    <c:if test="${(page eq 'create')||(page eq 'modify')}"> <img class="icon" src="${pageContext.request.contextPath}/img/user_search.png" id="userSearchPro" onclick="openDialog('producerDialog');"/></c:if>
                                    <div id="producerDialog" title="Review Select Producer"></div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <fmt:message key="fieldLabel.date" />
                                </td>
                                <td>
                                    <input type="text" name="dayNumber" id="dayNumber" class= "readonly" required/>
                                </td>
                                <td>
                                    <fmt:message key="fieldLabel.timeSlot" />      
                                </td>
                                <td>
                                    <input type="text" name="fromTime" id="timepicker" placeholder="From" style="width:50px;" class= "readonly" required/>         

                                    <input type="text" name="toTime" id="timepicker1" placeholder="To" style="width:50px;" class= "readonly" required/>         
                                    <input type="hidden" name="slotId" id="slotId">
                                </td>
                            </tr>

                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" align="center">
                        <table>  
                            <tr>
                            <td colspan="2" align="left">
                                <c:choose>
                                        <c:when test="${page eq 'copy'}">
                                            <input type="submit" name="operation" value="Copy"> &nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:when>
                                             <c:when test="${page eq 'modify'}">
                                           <input type="submit" name="operation" value="Modify"> &nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:when>
                                             <c:when test="${page eq 'delete'}">
                                                 <input type="submit" name="operation" value="Delete"> &nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:when>
                                        <c:otherwise>
                                            <input type="submit" name="operation" value="Create" onclick="return timeDifference();"> &nbsp;&nbsp;&nbsp;&nbsp;
                                           <input type="reset" name="operation" onclick="resetFields();" value="Reset" ></td>
                                        </c:otherwise>
                                </c:choose>
                                
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>

        </form>
    </body>