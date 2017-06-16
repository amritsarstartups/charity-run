import java.sql.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

public class login extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");

        String e = request.getParameter("e");
        JSONObject json=new JSONObject();
        String p = request.getParameter("p");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection bal = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/charityrun", "root", "system");
            Statement stmt = bal.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from users where email='" + e
                    + "' and password='" + p + "'");
            if (rs.next()) {
                json.put("info","success");
                json.put("username", rs.getString("username"));
                json.put("steps",  rs.getString("steps"));
                json.put("multiplier",  rs.getString("multiply"));
                json.put("photo_path",  rs.getString("photo_path"));
        
            rs.close();
            stmt.close();
            bal.close();
            } else {
                json.put("ïnfo", "failure");
                //
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        response.getWriter().printf(json.toString());
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
