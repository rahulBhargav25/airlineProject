package com.flyAway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PaymentConfirm")
public class PaymentConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Session obj to get user specified data for easier and faster access
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		//retrieving data from session using getAttribute method of session interface
		String flightId = (String) session.getAttribute("flightId");
		String flightName = (String) session.getAttribute("flightName");
		String ticketPrice = (String) session.getAttribute("ticketPrice");
		String seats = (String) session.getAttribute("seats");
		String date = (String) session.getAttribute("date");
		String name = (String) session.getAttribute("name");
		
		
		out.println("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"ISO-8859-1\">\r\n"
				+ "<title>Insert title here</title>\r\n"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"tickets.css\">\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+"  <h1><strong>Payment sucessful</strong></h1>"
				+ "	<div class=\"ticket\">\r\n"
				+ "		<div class=\"row\">\r\n"
				+ "			<h3><strong>Name : </strong></h3>\r\n"
				+ "			<p>"+name+"</p>\r\n"
				+ "		</div>\r\n"
				+ "		<div class=\"row\">\r\n"
				+ "			<h3><strong>Seats : </strong></h3>\r\n"
				+ "			<p>"+seats+"</p>\r\n"
				+ "		</div>\r\n"
				+ "		<div class=\"row\">\r\n"
				+ "			<h3><strong>Flight Id : </strong></h3>\r\n"
				+ "			<p>"+flightId+"</p>\r\n"
				+ "		</div>\r\n"
				+ "		<div class=\"row\">\r\n"
				+ "			<h3><strong>Flight Name : </strong></h3>\r\n"
				+ "			<p>"+flightName+"</p>\r\n"
				+ "		</div>\r\n"
				+ "		<div class=\"row\">\r\n"
				+ "			<h3><strong>Amount to be paid : </strong></h3>\r\n"
				+ "			<p>"+ticketPrice+"</p>\r\n"
				+ "		</div>\r\n"
				+"	    <div class=row>\r\n"
				+ "			<h3><strong>Date : </strong></h3>\r\n"
				+ "			<p>"+date+"</p>\r\n"
				+ "		</div>"
				+ "	</div>\r\n"
				+ "</body>\r\n"
				+ "</html>");
		
		
		
		
		
	}

}
