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

@WebServlet("")
public class createtables extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection connection = null;
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway", "root", "root");
			Statement st = connection.createStatement();
			String userTable="CREATE TABLE IF NOT EXISTS `flyaway`.`user` (\r\n"
					+ "  `userId` INT NOT NULL,\r\n"
					+ "  `userName` VARCHAR(45) NOT NULL,\r\n"
					+ "  `userEmail` VARCHAR(45) NOT NULL,\r\n"
					+ "  `userPassword` VARCHAR(45) NOT NULL,\r\n"
					+ "  PRIMARY KEY (`userId`));";
			String adminLoginTable="CREATE TABLE IF NOT EXISTS `flyaway`.`adminlogin` (\r\n"
					+ "  `idadminLogin` INT NOT NULL,\r\n"
					+ "  `adminId` VARCHAR(45) NOT NULL,\r\n"
					+ "  `adminPassword` VARCHAR(45) NOT NULL,\r\n"
					+ "  PRIMARY KEY (`idadminLogin`));";
			String airlinesTable="CREATE TABLE IF NOT EXISTS `flyaway`.`airlines` (\r\n"
					+ "  `idairlines` INT NOT NULL,\r\n"
					+ "  `airline` VARCHAR(45) NOT NULL,\r\n"
					+ "  PRIMARY KEY (`idairlines`));";
			String flightsTable="CREATE TABLE IF NOT EXISTS `flyaway`.`flights` (\r\n"
					+ "  `flightId` INT NOT NULL,\r\n"
					+ "  `flightsName` VARCHAR(45) NOT NULL,\r\n"
					+ "  `airlineId` INT NOT NULL,\r\n"
					+ "  `sdId` INT NOT NULL,\r\n"
					+ "  `ticketPrice` DECIMAL(11,2) NOT NULL,\r\n"
					+ "  PRIMARY KEY (`flightId`),\r\n"
					+ "  INDEX `airlineId_idx` (`airlineId` ASC) VISIBLE,\r\n"
					+ "  INDEX `sdId_idx` (`sdId` ASC) VISIBLE,\r\n"
					+ "  CONSTRAINT `airlineId`\r\n"
					+ "    FOREIGN KEY (`airlineId`)\r\n"
					+ "    REFERENCES `flyaway`.`airlines` (`idairlines`)\r\n"
					+ "    ON DELETE NO ACTION\r\n"
					+ "    ON UPDATE NO ACTION,\r\n"
					+ "  CONSTRAINT `sdId`\r\n"
					+ "    FOREIGN KEY (`sdId`)\r\n"
					+ "    REFERENCES `flyaway`.`mastersourcedestination` (`sdId`)\r\n"
					+ "    ON DELETE NO ACTION\r\n"
					+ "    ON UPDATE NO ACTION);\r\n"
					+ "\r\n"
					+ "";
			String mastersourcedestination="CREATE TABLE IF NOT EXISTS `flyaway`.`mastersourcedestination` (\r\n"
					+ "  `sdId` INT NOT NULL,\r\n"
					+ "  `source` VARCHAR(45) NOT NULL,\r\n"
					+ "  `destination` VARCHAR(45) NOT NULL,\r\n"
					+ "  PRIMARY KEY (`sdId`));\r\n"
					+ "";
			
			String adminIdPass = "INSERT IGNORE INTO `flyaway`.`adminlogin` (`idadminLogin`, `adminId`, `adminPassword`) VALUES ('1','admin@gmail.com', '1');\r\n"
					+ "";
			st.executeUpdate(userTable);
			st.executeUpdate(adminLoginTable);
			st.executeUpdate(airlinesTable);
			st.executeUpdate(flightsTable);
			st.executeUpdate(mastersourcedestination);
			st.executeUpdate(adminIdPass);
			
			out.println("");
			response.sendRedirect("search.html");
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
