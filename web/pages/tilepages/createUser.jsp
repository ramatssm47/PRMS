<%-- 
    Document   : createUser
    Created on : Sep 4, 2014, 5:04:59 PM
    Author     : Preeti
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <link rel="stylesheet" href="/phoenix/scripts/jquery/jquery-ui.css" />
            <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="/phoenix/scripts/jquery/jquery-1.11.1.js"></script>
        <script src="/phoenix/scripts/jquery/jquery-ui.js"></script>
        <script src="/phoenix/scripts/jquery/jquery-ui.min.js"></script>
        <script src="/phoenix/scripts/jquery/jquery.ui.timepicker.js"></script>
<fmt:setBundle basename="ApplicationResources" />
<c:set var="t" value="true" />
<title><fmt:message key="title.createUser" /></title>
<script>
 
   function resetFields(){
        $("#nm").val("");
        $("#un").val("");
        $("#pwd").val("");
        $("#repwd").val("");
        $("#em").val("");
        $("#co").val("");
        
   }
   function resetPwdField()
   {
       $("#pwd").val("");
        $("#repwd").val("");
   }
  function onload(){
        
        if('${inserted}'=='true'){
            alert("User Created Sucessfully");
            resetFields();
        }
        if('${message}'!='')
        {
            alert("User Already Exist");
            resetUserId();
        }
        
}
    function check() {
 if (document.getElementById('pwd').value!= document.getElementById('repwd').value) {
alert('incorrect password confirmation');
resetPwdField();
} else {
// input is valid -- reset the error message
document.forms["myform"].submit();
}
}
</script>
</head>
<body onload="onload();">
	<form id="myform" action="${pageContext.request.contextPath}/controller/createUser"
		method=post>
		<h2>
			<fmt:message key="title.createUser" />
		</h2>
		<table >
                       <tr>
				<td><fmt:message key="fieldLabel.userid" /></td>
				<td><input type="text" id="nm" name="userid" value="${param['name']}"
                                           size=15 maxlength=20 required></td>
			</tr>
			<tr>
				<td><fmt:message key="fieldLabel.userName" /></td>
				<td><input type="text" id="un" name="name" value="${param['name']}"
                                           size=15 maxlength=20 required></td>
			</tr>
			<tr>
				<td><fmt:message key="fieldLabel.password" /></td>
                                <td><input type="password" id="pwd" name="password" pattern="[a-zA-Z0-9]{8,}" title="Minimum 8 characters" 
                                           value="${param['name']}" size=15 maxlength=20 required></td>
			</tr>
                        <tr>
				<td><fmt:message key="fieldLabel.repassword" /></td>
                                <td><input type="password" id="repwd" name="repassword" value="${param['name']}" size=15 maxlength=20 title="incorrect entry" required></td>
			</tr>
                        <tr>
				<td><fmt:message key="fieldLabel.emailid" /></td>
				<td><input type="text" id="em" name="emailid"
                                           value="${param['name']}" size=15 maxlength=20 required></td>
			</tr>
                        <tr>
				<td><fmt:message key="fieldLabel.contacts" /></td>
                                <td><input type="text" id="co" name="contact"
                                           value="${param['name']}" size=15 maxlength=8 required></td>
			</tr>
                        <tr>
                                <td><fmt:message key="fieldLabel.userrole" /></td>
                                
                                <td><select name="role1" multiple="true" required>
                                     
                                        <c:forEach var="role"  items="${roles}">
                                            <option value="<c:out value="${role.roleId}"/>"><c:out value="${role.roleName}"/></option>
                                        </c:forEach>
                                            
                                    </select></td>
                                                 
                           <!--<td><input type="text" name="role" value="${param['name']}" size=15 maxlength=20></td> -->
			</tr>
			<tr >
                            <td colspan="2" align="center"><input type="submit" onclick="check();" value="Create"> &nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="button" value="Reset" onclick="resetFields();"></td>
			</tr>
		</table>
	</form>
</body>
</html>