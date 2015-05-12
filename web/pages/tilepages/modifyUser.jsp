<%-- 
    Document   : modifyUser
    Created on : Sep 11, 2014, 8:40:02 PM
    Author     : gautamverma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

        <fmt:setBundle basename="ApplicationResources" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Modify user Page</title>
            <link rel="stylesheet" href="http://jqueryvalidation.org/files/demo/site-demos.css">
                <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
                <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
                    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
                    <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
                    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
                    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
                    <script src="/scripts/jquery/jquery.session.js"></script>
                    <link rel="stylesheet" href="/styles.css">

                        <script>

                            
                            function checkForUser()
                            {
                                alert('sss');
//                                '<%
                                session.setAttribute("currentUser", "gautam");
                                %>//'
//                                '<%if(session.getAttribute("currentUser")==null){
                                      
                                } %>//'
                            }


                            function dispaySelectedUser()
                            {
                                retrieveUserInfo($('input[name="userSelectRadio"]:checked').val());
                            }

                            function retrieveUserInfo(id)
                            {
                                $.ajax({
                                    url: 'http://localhost:8080/phoenix/UserController/getuserDetailsById',
                                    dataType: 'JSON',
                                    data: {id: id},
                                    type: 'POST',
                                    cache: false,
                                    success: function (response)
                                    {


                                        $('#roleSelect').empty();
                                        var s = JSON.stringify(response);
                                        var obj = "\'" + s + "\'";

                                        var ob = $.parseJSON(s);
                                        $.each(ob, function (object, index)
                                        {
                                            if (object == 'userId')
                                                $("#userid").val(index);
                                            if (object == 'userName')
                                                $("#userName").val(index);
                                            if (object == 'emailId')
                                                $("#emailId").val(index);
                                            if (object == 'contact')
                                                $("#contact").val(index);
                                            if (object == 'roles')
                                            {

                                                for (var i = 0; i < index.length; i++)
                                                {
                                                    $('#roleSelect').append('<option value="' + index[i].roleName + '" selected="selected">' + index[i].roleName + '</option>');
                                                    $('#assignedRoles').val($('#assignedRoles').val() + " " + index[i].roleName);
                                                    $("#newRoleSelect option[value='" + index[i].roleName + "']").attr('selected', 'selected');
                                                }


                                            }
                                        });
                                    },
                                    error: function (request, textStatus, errorThrown)
                                    {
                                        alert("error:" + errorThrown);
                                    },
                                    complete: function (request, textStatus)
                                    {

                                    }
                                });
                            }


                            function resetAllValues()
                            {
                                $('#roleSelect').empty();
                            }

                            function check() {

                                document.forms["myform"].submit();
                            }
                            function resetPwdField()
                            {
                                $("#password").val("");
                                $("#repassword").val("");
                            }




                            $(document).ready(function () {
                                
                                '<% session.setAttribute("currentUser", "Station Manager"); %>'
                                if('<%=session.getAttribute("currentUser")%>'!="Station Manager"){
                                        window.location.replace("${pageContext.request.contextPath}/pages/errorPage.jsp");
                                     }

                                $("#Modify").click(function () {
                                    var x;
                                    $('#roleSelect option').each(function () {
                                        x = x + "," + $(this).val();
                                    });

                                });

                                $("#remove").click(function () {
                                    var options = $('#roleSelect option:selected');
                                    var values = $.map(options, function (option) {

                                        $("#roleSelect option[value='" + option.value + "']").remove();
                                    });
                                });
                                $("#searchById").click(function ()
                                {
                                    retrieveUserInfo($("#userid").val());
                                });


                                var dialog;
                                dialog = $("#somediv").dialog({
                                    autoOpen: false,
                                    height: 300,
                                    width: 350,
                                    modal: true,
                                    buttons: {
                                        OK: function () {
                                            $('.newRoleCheck:checked').each(function () {

                                                if ($('#roleSelect option[value=' + $(this).val() + ']').length == 0)
                                                    $('#roleSelect').append('<option value="' + $(this).val() + '" selected="selected">' + $(this).val() + '</option>');
                                                else
                                                    alert('Already added');

                                            });
                                            
                                            $('.newRoleCheck:checked').each(function () {

                                             $(":checkbox[value=" + this.value + "]").attr('checked', false);           
                                            });
                                            dialog.dialog("close");
                                        },
                                        Cancel: function () {
                                            $('.newRoleCheck:checked').each(function () {

                                             $(":checkbox[value=" + this.value + "]").attr('checked', false);           
                                            });
                                            dialog.dialog("close");
                                        }
                                    },
                                    close: function () {
                                        form[ 0 ].reset();
                                        allFields.removeClass("ui-state-error");
                                    }
                                });


                                $("#add").click(function () {

                                    dialog.dialog("open");

                                });
                                $('.btnGetAll').click(function () {

                                    $('.newRoleCheck:checked').each(function () {
                                    });
                                    dialog.dialog("close");
                                });

                            });</script>


                        </head>
                        <body><div id="maindiv">
                                <form id="myform" action="${pageContext.request.contextPath}/controller/modifyUser"
                                      method=post onload="checkForUser();">
                                    <table>
                                        <tr>
                                            <td>
                                                <div align="left">
                                                    <h2>
                                                        <fmt:message key="title.modifyUser" />
                                                    </h2>
                                                    <table >
                                                        <tr>
                                                            <td><fmt:message key="fieldLabel.userid" /></td>
                                                            <td><input type="text" name="userid" id="userid" value="${param['name']}"
                                                                       size=15 maxlength=20 required="true"> <input type="button" value="Search" id="searchById"/> </td>
                                                        </tr>
                                                        <tr>
                                                            <td><fmt:message key="fieldLabel.userName" /></td>
                                                            <td><input type="text" name="name" id="userName" value="${param['name']}"
                                                                       size=15 maxlength=20 required="true"></td>
                                                        </tr>

                                                        <tr>
                                                            <td><fmt:message key="fieldLabel.emailid" /></td>
                                                            <td><input type="text" name="emailid" id="emailId"
                                                                       value="${param['name']}" size=15 maxlength=20></td>
                                                        </tr>
                                                        <tr>
                                                            <td><fmt:message key="fieldLabel.contacts" /></td>
                                                            <td><input type="test" name="contact" id="contact"
                                                                       value="${param['name']}"  required="true" maxlength=8></td>
                                                        </tr>
                                                        <tr>
                                                            <td><fmt:message key="fieldLabel.Assigneduserrole" /></td>
                                                            <td><div id="seldiv"><select name="role" multiple="true" id="roleSelect" required="">

                                                                        <!--   <c:forEach var="role"  items="${roles}">
                                                                               <option value="<c:out value="${role.roleId}"/>"><c:out value="${role.roleName}"/></option>
                                                                        </c:forEach>--->

                                                                    </select>
                                                                    <input type="button" value="Remove" id="remove"/>
                                                                    <input type="button" value="Add" id="add"/>
                                                                    <td>  </td>
                                                                </div>
                                                            </td>


                                                        </tr>
<!--                                                        <tr><td><fmt:message key="fieldLabel.AssignNewrole" /></td>
                                                            <td>
                                                                <div id="seldiv"><select name="role" multiple="multiple" id="newRoleSelect">

                                                        <c:forEach var="role"  items="${roles}">
                                                            <option value="<c:out value="${role.roleName}"/>"><c:out value="${role.roleName}"/></option>
                                                        </c:forEach>

                                                    </select></div>
                                            </td></tr>-->
                                                        <tr >
                                                            <td colspan="2" align="center"><input type="submit" value="Modify" onclick="check();" id="Modify"> &nbsp;&nbsp;&nbsp;&nbsp;
                                                                    <input type="reset" value="Reset" onclick="resetAllValues();"></td>
                                                                        </tr>
                                                                        </table>
                                                                        </div>
                                                                        </td>
                                                                        <td>
                                                                            <table>
                                                                                <tr class="applicationTitle">
                                                                                    <td></td>
                                                                                    <td>User Id</td>
                                                                                    <td>UserName</td>
                                                                                    <td>Email</td>
                                                                                    <td>Contact</td>
                                                                                    <td>Assigned Roles</td>

                                                                                </tr>

                                                                                <c:forEach var="users" items="${listOfUsers}" >
                                                                                    <tr>
                                                                                        <td><input type="radio" name="userSelectRadio" value="${users.userId}" onclick="dispaySelectedUser();"</td>
                                                                                        <td><c:out value="${users.userId}"/></td>
                                                                                        <td><c:out value="${users.userName}"/></td>
                                                                                        <td><c:out value="${users.emailId}"/></td>
                                                                                        <td><c:out value="${users.contact}"/></td>

                                                                                        <td><c:forEach var="role" items="${users.roles}" varStatus="status">${status.index>0?',':''}<c:out value="${role.roleName}"></c:out></c:forEach></td>
                                                                                            </tr>
                                                                                </c:forEach>

                                                                            </table>
                                                                        </td>
                                                                        </tr>
                                                                        </table>

                                                                        <div id="somediv">

                                                                            <fieldset>
                                                                                <legend>Select Appropriate Roles</legend>
                                                                                <input  class="newRoleCheck" type="checkbox"  value="presenter" />presenter <br />
                                                                                <input class="newRoleCheck" type="checkbox" value="producer" />producer<br />
                                                                                <input class="newRoleCheck" type="checkbox"  value="StationManager" />Station Manager<br />


                                                                            </fieldset>


                                                                        </div>

                                                                        </form>
                                                                        </div>
                                                                        </body>

                                                                        </html>
