package com.hotel1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UpdateServlet extends HttpServlet {


    Connection con=null;
PreparedStatement ps=null;
    public void init(ServletConfig config) throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/hotel";
            String user="root";
            String password="root";
            con= DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw=response.getWriter();
        String sql="update hotel1 set phone=12345 where name='sankeerth'";
        try {
            ps=con.prepareStatement(sql);
            int x=ps.executeUpdate();
            if(x!=0)
            {
                pw.println("<html><body bgcolor='wheat'>Updated successfully<body></html>");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


