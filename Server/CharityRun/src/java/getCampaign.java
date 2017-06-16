/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author PARASP
 */
@WebServlet(urlPatterns = {"/getCampaign"})
public class getCampaign extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        JSONObject mobj=new JSONObject();
        JSONArray obj=new JSONArray();
     
        //String name = request.getParameter("ngo");
        try {
            

            Class.forName("com.mysql.jdbc.Driver");
           Connection bal2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/charityrun", "root", "system");
            Statement stmt2 = bal2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs2;
           
           // rs2.close();
            stmt2.close();
            Statement stmt3 = bal2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs3 = stmt3.executeQuery("select * from campaigns where status='íncomplete' order by timestamp desc ");
            int i = 1;
            
            while (rs3.next()) {
                JSONObject json=new JSONObject();
                json.put("id", rs3.getInt("id"));
                json.put("ngo" , rs3.getString("campaign_by"));

                json.put("campaign_name" , rs3.getString("campaign_name"));
                json.put("campaign_description", rs3.getString("description"));
                json.put("points_needed" , rs3.getString("points_needed"));
                json.put("points_earned" , rs3.getString("points_earned"));
                
                obj.add(json);
                i++;
            }
            
            bal2.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        mobj.put("key", obj);
        response.getWriter().printf(mobj.toString());

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
