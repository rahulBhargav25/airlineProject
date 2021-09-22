package com.flyAway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/adminHome")
public class adminHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway", "root", "root");
			String sql = "SELECT * FROM flyaway.mastersourcedestination";
			Statement st =connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			
			out.println("<html>");
			out.println("<head>");
			out.println("<style>");
			out.println("th {"
					+"background-color: #04AA6D;\r\n"
					+ "  color: white;"
					+ "}");
			out.println("th, td {\r\n"
					+ "  border-bottom: 1px solid transparent;\r\n"
					+ "padding: 15px;\r\n"
					+  "text-align: center;"
					+"color: black;"
					+"border-radius:40px;"
					+"width:150px;"
					+"}");
			out.println("tr:hover {transform: scale(1.05);"
					+"box-shadow: 0 0 40px -10px rgba(0, 0, 0, 0.75);" 
					+"}");
			out.println("tr {"
					+"transition: 0.3s;" 
					+"border-radius: 40px;"
					+"border: 1px transparent #ccc;"
					+"background: #fff;"
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
					+"}");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
			out.println("<table>");
			out.println("<tr>");			
			out.println("<th>sdId</th>");
			out.println("<th>Source</th>");
			out.println("<th>Destination</th>");
			out.println("</tr>");
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>"+rs.getInt(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("</tr>");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
