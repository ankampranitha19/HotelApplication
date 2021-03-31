package com.hotel1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.awt.Color.pink;

public class DeleteServlet extends HttpServlet {

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

        PrintWriter pw=response.getWriter();
        String sql="delete from hotel1 where name='akshitha'";
        try {
            ps=con.prepareStatement(sql);
            int x=ps.executeUpdate();
            if(x!=0)
            {
              pw.println("<html><body bgcolor='wheat'>row deleted successfully<body></html>");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
