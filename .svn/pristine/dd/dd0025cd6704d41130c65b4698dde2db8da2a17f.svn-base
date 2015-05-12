
<%-- 
    Document   : errorPage
    Created on : Sep 15, 2014, 11:27:32 AM
    Author     : Dinesh
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <link rel="stylesheet" href="/phoenix/scripts/jquery/jquery-ui.css" />
    <link rel="stylesheet" href="/phoenix/css/main.css" />
        <script src="/phoenix/scripts/jquery/jquery-1.11.1.js"></script>
        <script src="/phoenix/scripts/jquery/jquery-ui.js"></script>
        <script src="/phoenix/scripts/jquery/jquery-ui.min.js"></script>
        <script src="/phoenix/scripts/jquery/jquery.ui.timepicker.js"></script>
<fmt:setBundle basename="ApplicationResources" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="title.searchrp" /></title>
<script>
    $(document).ready(function() {
    $('input[name=rad]').click(function (){
      alert($(this).val());
    });
    });
    function accessParent(id,name,dur){
     
        window.opener.document.getElementById("radioId").value=id;
        window.opener.document.getElementById("radioProgram").value=name;
        window.opener.document.getElementById("duration").value=dur;
        window.close();
    }
    function ajaxSubmit(){
        var name = $("#name").val();
        var desc = $("#desc").val();
        var isAjax = 'yes';
        $.ajax({
                                    url: '${pageContext.request.contextPath}/controller/searchrp',
                                    dataType: 'JSON',
                                    data: {name: name,desc:desc,isAjax:isAjax},
                                    type: 'POST',
                                    cache: false,
                                    success: function (response)
                                    {


                                        //$('#roleSelect').empty();
                                        var s = JSON.stringify(response);
                                        var obj = "\'" + s + "\'";

                                        var json_obj = $.parseJSON(s);
                                       
                                       var output = '<tr><th>Select</th><th>Program Name</th><th>Program Description</th><th>Program Duration (hh:mm:ss)</th></tr>';
                                       for (var i in json_obj) 
                                        {
                                            
                                            if(i%2==0){
                                                output+='<tr class="even">';
                                            }else{
                                                output+='<tr class="odd">';
                                            }
                                            
                                        output+='<td class="nowrap"><input name="rad" onClick="accessParent(\''+json_obj[i].radioId+'\',\''+json_obj[i].name+'\',\''+json_obj[i].typicalDuration+'\');" type="radio"  value='+json_obj[i].radioId+'/></td>';
					output+='<td class="nowrap">'+json_obj[i].name+'</td>';
                                        output+='<td class="nowrap">'+json_obj[i].description+'</td>';
                                        output+='<td class="nowrap">'+json_obj[i].typicalDuration+'</td> </tr>';
					
				
                                        }
                                        $("#userTable").empty();
     
                                        $("#userTable").append(output);
                                   
                                       
                                       
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
</script>
    
</head>
<body>
	<h2>
		<fmt:message key="title.searchrp" />
	</h2>
	<form action="${pageContext.request.contextPath}/controller/searchrp"
		method=post>
		<center>
			<table class="framed">
				<tr>
					<th width="45%"><fmt:message key="caption.name" /></th>
					<th width="55%"><fmt:message key="caption.desc" /></th>
				</tr>
				<tr>
					<td><fmt:message key="fieldLabel.name" /></td>
					<td><input type="text" name="name" id="name" size=45 maxlength=45></td>
				</tr>
				<tr>
					<td><fmt:message key="fieldLabel.description" /></td>
					<td><input type="text" name="description" id="desc" size=45 maxlength=45></td>
				</tr>
				<tr>
                                    <td colspan="2" align="center"><input type="button" value="Submit" onclick="ajaxSubmit();"> <input
						type="reset" value="Reset"></td>
				</tr>
			</table>
		</center>

	</form>
	<c:if test="${! empty  searchrplist}">
		<table class="borderAll" id="userTable">
			<tr>    <th>Select</th>
				<th><fmt:message key="label.radioprogram.name" /></th>
				<th><fmt:message key="label.radioprogram.description" /></th>
				<th><fmt:message key="label.radioprogram.duration" /></th>
			</tr>
			<c:forEach var="rprogram" items="${searchrplist}" varStatus="status">
				<tr class="${status.index%2==0?'even':'odd'}" id="userData${status.index}">
                                    <td class="nowrap"><input type="radio" name="radios" value="${rprogram.radioId}" onclick="accessParent('${rprogram.radioId}','${rprogram.name}','${rprogram.typicalDuration}')"/>
                                                                  </td>
					<td class="nowrap">${rprogram.name}</td>
					<td class="nowrap">${rprogram.description}</td>
					<td class="nowrap">${rprogram.typicalDuration}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>