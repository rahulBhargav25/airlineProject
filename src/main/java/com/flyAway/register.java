package com.flyAway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
		
//		PrintWriter out = response.getWriter();
//		out.println("response being sent to the client");
//		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String name = request.getParameter("uname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		session.setAttribute("name", name);

		
		
		
		Connection connection = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway", "root", "root");
			String sqlUser="INSERT INTO `flyaway`.`user` (`userId`, `userName`, `userEmail`, `userPassword`) VALUES (?,?,?,?);\r\n"
					+ "";
			
			
			PreparedStatement ps = connection.prepareStatement(sqlUser);
			ps.setInt(1, 113);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, password);
			int count2 = ps.executeUpdate();
			System.out.println(count2+" records affected");  
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if(connection!=null) {
				try {
					connection.close();
					response.sendRedirect("tickets");
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
