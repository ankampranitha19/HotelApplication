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

public class LoginServlet extends HttpServlet {
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
        String sql="select*from hotel1 where name=? and password=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            HttpSession hs=request.getSession();
            PrintWriter pw=response.getWriter();
            if(rs.next())
            {


                hs.setAttribute("email", rs.getString("email"));
                hs.setAttribute("name", rs.getString("name"));
                hs.setAttribute("email", rs.getString("email"));
                hs.setAttribute("location", rs.getString("location"));
                pw.println("<html><body bgcolor='wheat'>login successfull</body></html>");


            }
            else
            {
                response.sendRedirect("./login.html");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
    }
    }

}
