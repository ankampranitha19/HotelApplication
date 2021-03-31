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

public class RegisterServlet1 extends HttpServlet {

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
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String mobile=request.getParameter("phone");
        int phone=Integer.parseInt(mobile);
        String location=request.getParameter("location");
        PrintWriter pw=response.getWriter();
        String sql="insert into hotel1(name,password,email,phone,location) values(?,?,?,?,?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,password);
            ps.setString(3,email);
            ps.setInt(4,phone);
            ps.setString(5,location);
            int x=ps.executeUpdate();
            if(x!=0)
            {
                response.sendRedirect("./login.html");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
