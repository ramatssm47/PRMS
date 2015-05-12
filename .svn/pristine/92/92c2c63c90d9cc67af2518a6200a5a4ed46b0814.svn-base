<%-- 
    Document   : UserListPage
    Created on : Sep 15, 2014, 10:13:26 PM
    Author     : vijay
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/phoenix/css/main.css" />
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
        <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
        <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
        <link rel="stylesheet" href="/phoenix/scripts/jquery/jquery-ui.css" />
        
        <script>
            
             function accessParent(id,name){
                                    if ('${roleType}'=='presenter'){                                 
                                           window.opener.document.getElementById("presenter").value = name;
                                            window.opener.document.getElementById("presenterId").value = id;
                       }else{                        
                                           window.opener.document.getElementById("producer").value = name;
                                            window.opener.document.getElementById("producerId").value = id;
                       }
                       window.close();
                }
            $(document).ready(function() {


                $('#searchButton').click(function() {

                    //get from DB%

                    $.ajax({
                        url: 'http://localhost:8080/phoenix/ReviewSelectUser/searchUserOnUserId',
                        dataType: 'JSON',
                        data: {userid: $("#search").val(),roleType:'<%=request.getAttribute("roleType")%>'},
                        type: 'POST',
                        cache: false,
                        success: function(response)
                        {
                            var s = JSON.stringify(response);
                            var obj = "\'" + s + "\'";

                            var ob = $.parseJSON(s);
                            var output = '<tr><th>Select</th><th>userID</th><th>userName</th><th>Email ID</th><th>Contact</th><th>Assigned Roles</th></tr>';
                            for (var i in ob) {
                                if(i%2==0){
                                    output +='<tr class="even">';
                                }else{
                                    output +='<tr class="even">';
                                }
                                output += '<td class="nowrap"><input name="rad" onClick="accessParent(\'' + ob[i].uId + '\',\'' + ob[i].userName + '\');" type="radio"  value=' + ob[i].uId + '/></td>';

                                output += '<td>' + ob[i].userId + '</td>';
                                output += '<td>' + ob[i].userName + '</td>';
                                output += '<td>' + ob[i].emailId + '</td>';
                                output += '<td>' + ob[i].contact + '</td>';
                                output += '<td>';
                                for (var j in ob[i].roles) {
                                    output += ob[i].roles[j].roleName;
                                }
                                output += '</td></tr>'  ;                     
                            }
                            $("#dataTable").empty();
                            $("#dataTable").append(output);


                        },
                        error: function(request, textStatus, errorThrown)
                        {
                            alert("error:" + errorThrown);
                        },
                        complete: function(request, textStatus)
                        {
                            
                        }
                    });
                });
            });

        </script>
    </head>
    <body>
        <h2>
            Search <c:out value="${roleType}"/>
	</h2>
    <center>
        <table class="framed">
            <tr >
            <th width="45%">
                Name
            </th>
            <th width="55%">
                Description
            </th>
            </tr>
            <tr>
                <td >
                    User Name
                </td>
                <td>
                     <input type="text" id="search">
                </td>      
            </tr>
            <tr>
                                    <td colspan="3" align="center"><input type="button" id="searchButton" value="Search"> <input
						type="reset" value="Reset"></td>
				</tr>
        </table>
       
    </center>  
    <table class="borderAll" id="dataTable">
                <tr>
                    <th>Select</th>
                    <th>User Id</th>
                    <th>UserName</th>
                    <th>Email</th>
                    <th>Contact</th>
                    <th>Assigned Roles</th>

                </tr>

                <c:forEach var="users" items="${listOfUsers}" varStatus="status">
                    <c:forEach var="role" items="${users.roles}" >
                        <tr class="${status.index%2==0?'even':'odd'}" id="userData${status.index}">

                            <td><input type="radio" name="userSelectRadio" value="${users.uId}" onclick="accessParent('${users.uId}', '${users.userName}');"</td>
                            <td><c:out value="${users.userId}"/></td>
                            <td><c:out value="${users.userName}"/></td>
                            <td><c:out value="${users.emailId}"/></td>
                            <td><c:out value="${users.contact}"/></td>                                                                                         
                            <td> <c:out value="${role.roleName}"></c:out></td>
                            </tr>
                    </c:forEach>
                </c:forEach>
        </div>
    </table>
</body>
</html>
