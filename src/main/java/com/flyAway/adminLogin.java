package com.flyAway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminLogin")
public class adminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(uname);
		System.out.println(password);
		PrintWriter out = response.getWriter();
		
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway", "root", "root");
			
			Statement st = connection.createStatement();
			
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM adminlogin");
			
			
			
			
			while(rs.next()) {
				
				String unameVerify =rs.getString(2);
				String passwordVerify = rs.getString(3);
				
				if(uname.contains(unameVerify) && password.contains(passwordVerify)) {
					response.sendRedirect("adminHome.html");
					
				} else {			
					response.sendRedirect("adminLogin"+"."+"html");
				}
			}
				
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		} finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
