<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="ewcss.css" rel="stylesheet" type="text/css"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>
    </head>
    <body style="background-color: gray">
    <%
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
%>%>
<a href="index.jsp">Please Login</a>
<%} else 
    {
%>
<div style="background-color: black; height: 100px;color: whitesmoke; padding-left: 50px; font-size: 30px;padding-top: 30px;margin-top: 10px">
Welcome <%=session.getAttribute("userid")%>
<a style="float: right;padding-right: 50px; color:white;" href="logout.jsp">Log out</a>
    </div>
<br>
<br>
<a style="text-align: center;margin-left: 400px;color: white; font-size: 25px;" href="viewpending.jsp"> View Pending Requests for new Sign-Up for Society</a>
<br>
<br>
<br>
<br>
<div style="text-align: center">
<% out.println("***********************GENERAL INSTRUCTION*************************");%>
<br>
<% out.println("1) Search Their Social Status");%>
<br>
<% out.println("2) Search Their Legal Status");%>
<br>
<% out.println("   3) Contact their Head");%>
<br>

</div>
<%
    }
%>
    <center>
   
       
    
         </center>
    </body>
</html>