<%-- 
    Document   : errorPage
    Created on : Sep 15, 2014, 11:27:32 AM
    Author     : gautamverma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <script src="/phoenix/scripts/jquery/jquery-1.11.1.js"></script>
        <script src="/phoenix/scripts/jquery/jquery-ui.js"></script>
        <script src="/phoenix/scripts/jquery/jquery-ui.min.js"></script>
        <script src="/phoenix/scripts/jquery/jquery.ui.timepicker.js"></script>
            
        <script>
            
            
             $(document).ready(function () {
                $('#imgclick').click(function (){
                window.location.replace("${pageContext.request.contextPath}/pages/login.jsp");
                });
                
                
              
             });
        </script>
    </head>
    <body>
        <img src="${pageContext.request.contextPath}/img/errorPage.jpeg" id="imgclick"/>
        
    </body>
</html>
