<%@ page import ="java.sql.*" %>
<%
    String id=request.getParameter("id");
    Class.forName("com.mysql.jdbc.Driver");
    
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/charityrun","root", "system");
    Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    ResultSet rs;
    rs = st.executeQuery("select * from societies where id="+id);
    if(rs.next()){
        rs.updateString("status", "true");
        rs.updateRow();
        
       response.sendRedirect("viewpending.jsp");
    }
%>