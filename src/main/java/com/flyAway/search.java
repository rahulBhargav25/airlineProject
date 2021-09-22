package com.flyAway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.function.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		
		//conversion of double into string
		final DecimalFormat df2 = new DecimalFormat("#.##");
		
		Connection connection = null;
		PrintWriter out = response.getWriter();
		
		//accessing parameters from form field input of seach.html
		String source = request.getParameter("source");
		String destination = request.getParameter("Destination");
		String date = request.getParameter("bokingDate");
		int noOfPassengers = Integer.parseInt(request.getParameter("no of seats"));
		String seats = Integer.toString(noOfPassengers);
		
		
			try {
				//loading driver
				Class.forName("com.mysql.cj.jdbc.Driver");
				//jdbc connection passed in connection obj
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway", "root", "root");
				//SQL query to access data from db as per user specification
				String sql="SELECT flightId, flightsName, ticketPrice from flyaway.flights "
						+ "JOIN mastersourcedestination ON flights.sdId=mastersourcedestination.sdId JOIN airlines ON flights.airlineId=airlines.idairlines "
						+ "where source= "+   
						"'"+
						source
						+"'"
						+ " and destination= "
						+"'"
						+destination
						+"'";
				//statement obj
				Statement st = connection.createStatement();
				//ResultSet to access data from db 
				ResultSet rs = st.executeQuery(sql);
				
				out.println("<html>");
				out.println("<head>");
				out.println("<style>");
				out.println("th {"
						+"background-color: #04AA6D;\r\n"
						+ "  color: white;"
						+ "padding-left: 25px;"
						+ "}");
				out.println("th, td {\r\n"
						+ "  border-bottom: 1px solid transparent;\r\n"
						+ "padding-left: 25px;\r\n"
						+ "padding:15px;"
						+  "text-align: center;"
						+"color: black;"
						+"border-radius:40px;"
						+ "	margin-bottom: 30px;\r\n"
						+ "	border-radius: 25px;\r\n"
						+ "	box-shadow: inset 8px 8px 8px #cbced1, inset \r\n"
						+ "	-8px -8px 8px #fff;\r\n"
						+"}");
				out.println("td:hover {transform: scale(1.05);"
						+"box-shadow: 0 0 40px -10px rgba(0, 0, 0, 0.75);" 
						+"}");
				out.println("td {"
						+"transition: 0.3s;" 
						+"border-radius: 40px;"
						+"border: 1px transparent #ccc;"
						+"background: #fff;"
						+""
						+"}");
				out.println("* {\r\n"
						+ "	box-sizing: border-box;\r\n"
						+ "}\r\n"
						+ "body {\r\n"
						+ "	margin: 0;\r\n"
						+ "	height: 100vh;\r\n"
						+ "	width: 100vw;\r\n"
						+ "	overflow: hidden;\r\n"
						+ "	font-family: \"Lato\", sans-serif;\r\n"
						+ "	font-weight: 700;\r\n"
						+ "	display: flex;\r\n"
						+ "	align-items: center;\r\n"
						+ "	justify-content: center;\r\n"
						+ "	color: #555;\r\n"
						+ "	background-color: #ecf0f3;\r\n"
						+"border-radius:20px;"
						+ "}");
				
				out.println("tr {\r\n"
						+ "	width: 100%;\r\n"
						+ "	padding: 75px 5px 5px 5px;\r\n"
						+ "}"
						+ "tr {\r\n"
						+ "	border: none;\r\n"
						+ "	outline: none;\r\n"
						+ "	background: none;\r\n"
						+ "	font-size: 18px;\r\n"
						+ "	color: #555;\r\n"
						+ "	padding: 20px 10px 20px 5px;\r\n"
						+ "	\r\n"
						+ "}"
						+ "td th {\r\n"
						+ "	margin-bottom: 30px;\r\n"
						+ "	border-radius: 25px;\r\n"
						+ "	box-shadow: inset 8px 8px 8px #cbced1, inset \r\n"
						+ "	-8px -8px 8px #fff;\r\n"
						+ "}");
				
				out.println("button {\r\n"
						+ "	display: flex;\r\n"
						+ "	text-decoration: none;\r\n"
						+ "	outline: none;\r\n"
						+ "	border: none;\r\n"
						+ "	cursor: pointer;\r\n"
						+ "	width: 100%;\r\n"
						+ "	height: 60px;\r\n"
						+ "	border-radius: 30px;\r\n"
						+ "	font-weight: 700;\r\n"
						+ "	font-family: \"Lato\", sans-serif;\r\n"
						+ "	color: #fff;\r\n"
						+ "	align-items: center;\r\n"
						+ "	justify-content: center;\r\n"
						+ "	text-align: center;\r\n"
						+ "	background-color: gray;\r\n"
						+ "	box-shadow: 3px 3px 8px #b1b1b1, -3px -3px 8px #fff;\r\n"
						+ "	\r\n"
						+ "}");
				
				out.println("tbody {\r\n"
						+ "	width: 400px;\r\n"
						+ "	height: 700px;\r\n"
						+ "	padding: 20px 5px 5px 5px;\r\n"
						+ "	border-radius: 40px;\r\n"
						+ "	background-color: #ecf0f3;\r\n"
						
						
						+ "	box-shadow: 13px 13px 20px #cbced1,\r\n"
						+ "	-13px -13px 20px #fff;\r\n"
						+ "	\r\n"
						+ "}");
				out.println("table {"
						+"width:40%;"
						+"display:flex;"
						+"justify-content:center;"
						+ "border-collapse: separate;"
						 +" border-spacing: 0 15px;"
						+"}");
				out.println("</style>");
				out.println("</head>");
				out.println("<body>");
				out.println("<table>");
				out.println("<tr>");			
				out.println("<th>Flight Id</th>");
				out.println("<th>Flight Name</th>");
				out.println("<th>Ticket Price</th>");
				out.println("<th>View Ticket</th>");
				out.println("</tr>");
				
			
				
				
				
				while(rs.next()) {
					int fId = rs.getInt(1);
					String flightId = Integer.toString(fId);
					
					String flightName = rs.getString(2);
					double price = rs.getDouble(3);
					double ticketPrice = price*noOfPassengers;
					String tp = df2.format(ticketPrice);
					//data passed on to Registerform using hidden input fields
					out.println("<tr><form method="+"post"+" action="+"Registerform"+">");
					out.println("<td><input type="+"hidden"+" name="+"seats"+" value="+seats+"><input type="+"hidden"+" name="+"flightId"+" value="+flightId+">"+flightId+"</td>");
					out.println("<td><input type="+"hidden"+" name="+"Date"+" value="+date+"><input type="+"hidden"+" name="+"flightName"+" value="+flightName+">"+flightName+"</td>");
					out.println("<td><input type="+"hidden"+" name="+"tp"+" value="+tp+">"+tp+"</div></td>");
					out.println("<td><button type="+"submit"+">View Ticket</button></td>");
				
					out.println("</form></tr>");
					
				}
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
				
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
     