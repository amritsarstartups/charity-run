import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

public class addNewCampaign extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        JSONObject json=new JSONObject();
     String name=   request.getParameter("name");
      String desc=  request.getParameter("description");
      String funds=  request.getParameter("funds");
       String from_date= request.getParameter("from_date");
      String to_date=  request.getParameter("to_date");
      int points=Integer.parseInt(funds.trim())*5;
      String ngo=request.getParameter("posted_by");//email
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection bal = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/charityrun", "root", "system");
            Statement stmt = bal.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from campaigns");
            rs.moveToInsertRow();
            rs.updateString("campaign_name",name);
            rs.updateString("description",desc);
            rs.updateString("campaign_by",ngo);
            rs.updateInt("points_needed",points);
            rs.updateString("from_date",from_date);
            rs.updateString("to_date",to_date);
            
            rs.insertRow();
            json.put("info","success");

        } catch (Exception e) {
            
            e.printStackTrace();
        }
  
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
