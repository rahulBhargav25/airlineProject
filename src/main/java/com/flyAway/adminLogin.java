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
		//data retrieval from input form field 
		String uname = request.getParameter("email");
		String password = request.getParameter("password");

		PrintWriter out = response.getWriter();
		
		Connection connection = null;
		try {
			//loading driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//jdbc connection passed in connection obj
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway", "root", "root");
			//statement obj
			Statement st = connection.createStatement();
			//ResultSet to access data from db 
			java.sql.ResultSet rs = st.executeQuery("SELECT * FROM adminlogin");
			
			
			
			//admin validation using Resultset to retrieve data fromDB
			while(rs.next()) {
				
				String unameVerify =rs.getString(2);
				String passwordVerify = rs.getString(3);
				
				if(uname.contains(unameVerify) && password.contains(passwordVerify)) {
					//Successful validation will be redirected to admin home
					response.sendRedirect("adminHome.html");
					
				} else {
					//sucessful validation will be redirected to admin Login
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
