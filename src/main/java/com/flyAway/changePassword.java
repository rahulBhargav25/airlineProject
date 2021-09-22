package com.flyAway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class changePassword
 */
@WebServlet("/changePassword")
public class changePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oldPassword = request.getParameter("oldpassword");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		if(password.equals(confirmPassword)) {
			Connection connection = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway", "root", "root");
				String verifyPassword = "SELECT * FROM flyaway.adminlogin";
				
				String sql="UPDATE `flyaway`.`adminlogin` SET `adminPassword` ='"+ password+"' WHERE (`idadminLogin` = '1');\r\n"
						+ "";
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(verifyPassword);
				while(rs.next()) {
					String oldpassverify = rs.getString(3);
					if(oldPassword.equals(oldpassverify)) {
						int count = st.executeUpdate(sql);
						PrintWriter out = response.getWriter();
						out.println("<html>"
								+"<head>"
								+"<style>"
								
								
								+"</style>"
								+"</head>"
								+"<body>"
								+"<h1>Your password has been changed</h1>"
								+"<a href="+"adminLogin.html"+">Go Back to login</a>"
								
								+"</body>"
								+"</html>"
								+""
								);
						out.println("");
						
					} else {
						response.sendRedirect("http://localhost:8080/airlineServices/changeAdminPassword.html");
					}
				}
				
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
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
		} else {response.sendRedirect("changeAdminPassword.html");}

		
		
	}

}
