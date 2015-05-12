<%-- 
    Document   : copySchedule
    Created on : Sep 15, 2014, 7:43:12 PM
    Author     : vasundharabhatia
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <head>
        <fmt:setBundle basename="ApplicationResources" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/phoenix/scripts/jquery/jquery-ui.css" />
        <script src="/phoenix/scripts/jquery/jquery-1.11.1.js"></script>
        <script src="/phoenix/scripts/jquery/jquery-ui.js"></script>
        <script src="/phoenix/scripts/jquery/jquery-ui.min.js"></script>
        <script src="/phoenix/scripts/jquery/jquery.ui.timepicker.js"></script>
        
        <script>
            
    
    $(document).ready(function() {
        
        $("#getScheduledProgramButton").click(function (){
            
            window.open("${pageContext.request.contextPath}/controller/selectSchedule", "myWindow", "width=800, height=300");    // Opens a new window
            
        });
          
        
         //   $("#testDiv").load('${pageContext.request.contextPath}/controller/selectSchedule');
        // window.open('${pageContext.request.contextPath}/controller/selectSchedule');
            
            var months = [ 01, 02, 03, 04, 05, 06, 07, 08, 09, 10, 11, 12 ];
            function changeDate() {
                alert("dimo");

            }
            Date.prototype.getWeek = function() {
                var onejan = new Date(this.getFullYear(), 0, 1);
                return Math.ceil((((this - onejan) / 86400000) + onejan.getDay() + 1) / 7);
            }
            $("#datepicker").datepicker({
                    onSelect: function(date) {

                        var d = new Date(date);
                        $("#week").val(d.getWeek());
                        $("#year").val(d.getFullYear());
                        $("#month").val(months[d.getMonth()]);
                        $("#date").val(d.getUTCDate());
                    }
                });
                $('#datepickerImage').click(function() {
                    $('#datepicker').datepicker('show');
                });

                $(function() {
                    $('#timepicker').timepicker();
                    $('#timepicker1').timepicker();
                });

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
                defaultTime: '12:34', // Used as default time when input field is empty or for inline timePicker
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
             
        
        
        </script>
        
    </head>
    <body>
          <h2>
                <fmt:message key="title.deleteSchedule" />
            </h2>
<!--        <form action="${pageContext.request.contextPath}/controller/deleteSchedule"
              method=post>-->
            
            <input type="button"   name="getScheduledProgramButton" id="getScheduledProgramButton"  value="Click to Select a scheduled Program"/>
        </form>
        <form name="testfrm" action="${pageContext.request.contextPath}/controller/deleteSchedule"
              method=post>
          
            <table>
                <tr>
                    <td>
                        <table>
                            
                            <tr>
                                <td>
                                    <fmt:message key="fieldLabel.annualSchedule" />
                                </td>
                                <td>
                                    <input type="text" name="year" id="year" readonly="false"/>
                                </td>
                                <td colspan="4" style="vertical-align: central">
                                    <input type="hidden" name="date" id="datepicker"/><img class="icon" src="${pageContext.request.contextPath}/img/date.png" id="datepickerImage"/> 
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <fmt:message key="fieldLabel.month" />
                                </td>
                                <td>
                                    <input type="text" name="month" id="month" readonly="false"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <fmt:message key="fieldLabel.weeklySchedule" />
                                </td>
                                <td>
                                    <input type="text" id="week" name="week" readonly="false"/>
                                </td>
                                
                            </tr>
                            <tr>
                                <td>
                                    <fmt:message key="fieldLabel.date" />
                                </td>
                                <td>
                                    <input type="text" name="day" id="day" readonly="false" />
                                </td>
                            </tr>

                        </table>
                    </td>
                    <td>
                        <table>

                            <tr>
                                <td>
                                    <fmt:message key="label.radioprogram.name" />
                                </td>
                                <td>
                                    <input type="text" name="radioProgram" readonly="true" id="radioProgram"/> 
                                </td>
                                
                            </tr>
                            <tr>
                                <td>
                                    <fmt:message key="fieldLabel.presenter" />
                                </td>
                                <td>
                                    <input type="text" name="presenter" readonly="true" id="presenter"/> 
                                </td>
                                
                            </tr>
                            <tr>
                                <td>
                                    <fmt:message key="fieldLabel.producer" />
                                </td>
                                <td>
                                    <input type="text" name="producer" readonly="true" id="producer"/> 
                                </td>
                                
                            </tr>
                            <tr>
                                <td>
                                    <fmt:message key="fieldLabel.timeSlot" />      
                                </td>
                                <td>
                                    <input type="text" name="fromTime" id="timepicker" placeholder="From" style="width:50px;" readonly="true"/>         

                                    <input type="text" name="toTime" id="timepicker1" placeholder="To" style="width:50px;" readonly="true"/>         
                                </td>
                            </tr>
                            <tr >
                            <td colspan="2" align="left">
                                <input type="submit" name="operation" value="Delete"> &nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="button" name="operation" onclick="resetFields();" value="Reset" ></td>
			</tr>
                        </table>
                    </td>
                </tr>
            </table>
                               
        </form>
    </body>
</html>