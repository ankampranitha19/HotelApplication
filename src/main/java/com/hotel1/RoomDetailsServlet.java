package com.hotel1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ResponseCache;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.awt.Color.pink;

public class RoomDetailsServlet extends HttpServlet {
    Connection con=null;
    PreparedStatement ps=null;

    public void init(ServletConfig config) throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/hotel";
            String user="root";
            String password="root";
            con=DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeofroom=request.getParameter("typeofroom");
        String location=request.getParameter("location");
        String sql="select*from details where typeofroom=? and location=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,typeofroom);
            ps.setString(2,location);
            ResultSet rs=ps.executeQuery();
            HttpSession hs=request.getSession();
            PrintWriter pw=response.getWriter();
            if(rs.next())
            {



                response.sendRedirect("./login.html");


            }
            else
            { pw.println("<html><body bgcolor ='wheat'>Room choice successfull</body></html>");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
