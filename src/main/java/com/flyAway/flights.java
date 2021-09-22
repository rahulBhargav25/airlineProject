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

@WebServlet("/flights")
public class flights extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway", "root", "root");
			String sql="SELECT * from flyaway.flights JOIN mastersourcedestination ON flights.sdId=mastersourcedestination.sdId JOIN airlines ON flights.airlineId=airlines.idairlines";
			Statement st = connection.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			
			out.println("<html>");
			out.println("<head>");
			out.println("<style>");
			out.println("table {"
					+ "  table-layout: fixed;"
					+ "  width: 100%;"
					+ "  border-collapse: collapse;"
					+ "  border: 3px solid black;"
					+ "}"
					+ ""
					+ "td {"
					+ "justify-content:center;"
					+ "}"
					+ "th, td {"
					+ "  padding: 20px;"
					+ "}");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
			out.println("<table>");
			out.println("<tr>");			
			out.println("<th>Flight Id</th>");
			out.println("<th>Flight Name</th>");
			out.println("<th>Source</th>");
			out.println("<th>Destination</th>");
			out.println("<th>Airline Name</th>");
			out.println("<th>ticket Price</th>");
			
			out.println("</tr>");
			
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>"+rs.getInt(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(7)+"</td>");
				out.println("<td>"+rs.getString(8)+"</td>");
				out.println("<td>"+rs.getString(10)+"</td>");
				out.println("<td>"+rs.getDouble(5)+"</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		} catch (ClassNotFoundException | SQLException e) {
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
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
