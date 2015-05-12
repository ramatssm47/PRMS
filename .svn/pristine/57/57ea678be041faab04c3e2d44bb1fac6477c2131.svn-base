<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts-menu.sf.net/tag" prefix="menu" %>
<html>
    <head>
        <link rel="stylesheet" href="/phoenix/css/menu.css" />
    </head>
    <body>
        <menu:useMenuDisplayer  name="CSSListMenu" id="primary-nav" >
            <c:if test="${empty sessionScope.user}">
                 <menu:displayMenu  name="Login"/>
            </c:if>
            
            <c:set var="containsSm" value="false" />
            <c:forEach var="item" items="${sessionScope.user.roles}">
              <c:if test="${item.roleName eq 'Station Manager'}">
                <c:set var="containsSm" value="true" />
              </c:if>
            </c:forEach>
            
            <c:set var="containsAd" value="false" />
            <c:forEach var="item" items="${sessionScope.user.roles}">
              <c:if test="${item.roleName eq 'Admin'}">
                <c:set var="containsAd" value="true" />
              </c:if>
            </c:forEach>
            
            <c:choose>             
               
                <c:when test="${(containsAd eq true) && (containsSm eq true)}">               
                     <menu:displayMenu name="MaintainSchedule"/>
                     <menu:displayMenu name="MaintainUser"/>
                </c:when>
                <c:when test="${containsSm eq true}">
                     <menu:displayMenu name="MaintainSchedule"/>
                </c:when>
            </c:choose>
            
            
           
            <c:if test="${not empty sessionScope.user}">
                <menu:displayMenu name="Logout"/>
            </c:if>
            
        </menu:useMenuDisplayer>
    </body>
</html>



