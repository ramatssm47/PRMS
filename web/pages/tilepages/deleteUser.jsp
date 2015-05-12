<%-- 
    Document   : deleteUser
    Created on : Sep 14, 2014, 2:56:28 PM
    Author     : Preeti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="ApplicationResources" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title><fmt:message key="title.deleteUser" /></title>
        <script>
            function onload(){
        
        if('${deleted}'=='true'){
            alert("User Deleted Successfully");
           
        }
        
}
        </script>
    </head>
    <body onload="onload();">
        <form id="myform1" action="${pageContext.request.contextPath}/controller/deleteUser"
		method=post>
            <h2>
			<fmt:message key="title.deleteUser" />
		</h2>
            <table class="borderAll">
                <tr><td></td>
                    <th>Name</th><th>User Id</th><th>Email Id</th><th>Contact</th><th>Role(s)</th> </tr>
                
                
                <c:forEach var="nm" items="${details}" varStatus="status">
                        <tr class="${status.index%2==0?'even':'odd'}" id="userData${status.index}">
                    <td><INPUT TYPE="radio" NAME="radios" VALUE="${nm.uId}" CHECKED></td>
                    
                    <td><c:out value="${nm.userName}" /></td>
                    <td><c:out value="${nm.userId}" /></td>
                    <td><c:out value="${nm.emailId}" /></td>
                    <td><c:out value="${nm.contact}" /></td>
                    <td>  <c:forEach var="role"  items="${nm.roles}" varStatus="status">
                                         ${status.index>0?',':''}  <c:out value="${role.roleName}"/>
                                        </c:forEach></td>
                   
</tr>
             </c:forEach>

                        </table>
                <center>
                <table>
                    <tr>
    <td >
        <INPUT TYPE="submit" VALUE="Delete">
    </td>
</tr>
                </table> </center>
        </form>
    </body>
</html>
