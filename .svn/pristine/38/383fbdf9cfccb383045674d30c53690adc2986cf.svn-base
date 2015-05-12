<%-- 
    Document   : modifiedUserDetialsPage
    Created on : Sep 15, 2014, 9:27:15 PM
    Author     : gautamverma
--%>

<%@page import="sg.edu.nus.iss.phoenix.user.entity.Role"%>
<%@page import="sg.edu.nus.iss.phoenix.user.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <script src="//code.jquery.com/jquery-1.10.2.js"></script>
                    <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
                    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
                    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
        <script>
            
            $(document).ready(function (){
               
             $("#goback").click(function (){
                window.location.replace("${pageContext.request.contextPath}/controller/loadmodifyUser"); 
             });
        
            });
        </script>
    </head>

    <body>
        <div align="center"> <b> Modified Details </b></div>
        <br>
        <div align="center">
           
        <table border="2">
            <tr>
                <td>User ID</td>
                <td>${modifiedUserDetails.userId}</td>
            </tr>
            <tr>
                <td>User Name</td>
                <td>${modifiedUserDetails.userName}</td>
            </tr>
            <tr>
                <td>Email ID</td>
                <td>${modifiedUserDetails.emailId}</td>
            </tr>
            <tr>
                <td>Contact</td>
                <td>${modifiedUserDetails.contact}</td>
            </tr>
            <% 
                User userRole=(User)request.getAttribute("modifiedUserDetails");
                    for(Role r:userRole.getRoles()){ %>
                    <tr>    <td>Role</td>
                <td><%=r.getRoleName()%></td><tr>
                <%}%>
            </tr>
        
            <tr>
                <td colspan="2"> <div align="center"> <input type="button" value="Click to Go back" id="goback" /></div> </td>
            </tr>


    </table></div>
</body>
</html>
