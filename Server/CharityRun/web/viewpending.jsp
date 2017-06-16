<%@ page import ="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-color: gray">
        <div style="width: 100%;height: 100px;padding-top: 20px;background-color: black; color: whitesmoke; text-align: center; font-size: 36px;">
         Un-Verified Account   
        </div>
    <center>
        <table border="0">
            
        <%
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/charityrun","root", "system");
    Statement st = con.createStatement();
    ResultSet rs;
    rs = st.executeQuery("select * from societies where status='false'");
    while (rs.next()) {
        %>
        <tr>
        <tr>
       <td style="color: white; font-size: 25px;">ID :
        <% out.println(rs.getString("id"));
        %>
       </td>
        </tr> 
        <br>
        <tr>
        <td style="color: white; font-size: 25px;">
        Name :
        <% out.println(rs.getString("name"));
        %>
        </td>
        </tr>
        <br>
       
        <br>
        <tr>
        <td style="color: white; font-size: 25px;">
        PanCard :
        <% out.println(rs.getString("pan_card_no"));
        %>
        </td>
        </tr>
        <br>
        <tr>
        <td style="color: white; font-size: 25px;">
        Regdno :
        <% out.println(rs.getString("registration_no"));
        %>
        </td>
        </tr>
        
        <br>
        <tr>
        <td style="color: white; font-size: 25px;">
        Address :
        <% out.println(rs.getString("address"));
        %>
        </td>
        </tr>
        <br>
        <tr>
        <td style="color: white; font-size: 25px;">
        Headname :
        <% out.println(rs.getString("head_name"));
        %>
        </td>
        </tr>
        <br>
        <tr>
        <td style="color: white; font-size: 25px;">
        Phone :
        <% out.println(rs.getString("phone"));
        %>
        </td>
        </tr>
        <tr>
            <td>
                
            </td>
        </tr>
        <tr>
            <td>
                
            </td>
        </tr>
        
        <tr>
            <td >
            <a style="color: black; font-size: 25px;background-color: white;padding-left: 20px;padding-right: 20px;padding-top: 5px;text-decoration: none;border: 2px;border-color: black;padding-bottom: 5px" href="verify.jsp?id=<%=rs.getInt("id")%>">Verify</a>    
            </td>
        </tr>
            
            
        <%
    }

%>
    </body>
</html>
