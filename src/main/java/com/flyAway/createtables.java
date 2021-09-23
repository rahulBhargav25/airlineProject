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
			//sql for user table
			String userTable="CREATE TABLE IF NOT EXISTS `flyaway`.`user` (\r\n"
					+ "  `userId` INT NOT NULL,\r\n"
					+ "  `userName` VARCHAR(45) NOT NULL,\r\n"
					+ "  `userEmail` VARCHAR(45) NOT NULL,\r\n"
					+ "  `userPassword` VARCHAR(45) NOT NULL,\r\n"
					+ "  PRIMARY KEY (`userId`))";
			//sql for admin table
			String adminLoginTable="CREATE TABLE IF NOT EXISTS `flyaway`.`adminlogin` (\r\n"
					+ "  `idadminLogin` INT NOT NULL,\r\n"
					+ "  `adminId` VARCHAR(45) NOT NULL,\r\n"
					+ "  `adminPassword` VARCHAR(45) NOT NULL,\r\n"
					+ "  PRIMARY KEY (`idadminLogin`))";
			//sql for airline table
			String airlinesTable="CREATE TABLE IF NOT EXISTS `flyaway`.`airlines` (\r\n"
					+ "  `idairlines` INT NOT NULL,\r\n"
					+ "  `airline` VARCHAR(45) NOT NULL,\r\n"
					+ "  PRIMARY KEY (`idairlines`))";
			//sql for data insertion airline table
			String airlinesTableDataR1 = "INSERT ignore INTO `flyaway`.`airlines` (`idairlines`, `airline`) VALUES ('1', 'Vistara')";
			String airlinesTableDataR2 = "INSERT ignore INTO `flyaway`.`airlines` (`idairlines`, `airline`) VALUES ('2', 'KingFischer')";
			String airlinesTableDataR3 =  "INSERT ignore INTO `flyaway`.`airlines` (`idairlines`, `airline`) VALUES ('3', 'british air')";
			String airlinesTableDataR4 = "INSERT ignore INTO `flyaway`.`airlines` (`idairlines`, `airline`) VALUES ('4', 'Air India')";
			String airlinesTableDataR5 = "INSERT ignore INTO `flyaway`.`airlines` (`idairlines`, `airline`) VALUES ('5', 'SpiceJet')";
			String airlinesTableDataR6 = "INSERT ignore INTO `flyaway`.`airlines` (`idairlines`, `airline`) VALUES ('6', 'Indigo')";
			String airlinesTableDataR7 = "INSERT ignore INTO `flyaway`.`airlines` (`idairlines`, `airline`) VALUES ('7', 'Deccan')";
			String airlinesTableDataR8 = "INSERT ignore INTO `flyaway`.`airlines` (`idairlines`, `airline`) VALUES ('8', 'Qatar Airways')";
			String airlinesTableDataR9 = "INSERT ignore INTO `flyaway`.`airlines` (`idairlines`, `airline`) VALUES ('9', 'Americas air')";
			String airlinesTableDataR10 = "INSERT ignore INTO `flyaway`.`airlines` (`idairlines`, `airline`) VALUES ('10', 'Spain Air')";
				
			
			
			
			
			//sql for flights tbale
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
					+ "    ON UPDATE NO ACTION)\r\n"
					+ "\r\n"
					+ "";
			//sql for data insertion flights table
			String flightsTableDataR1 = "INSERT ignore INTO `flyaway`.`flights` (`flightId`, `flightsName`, `airlineId`, `sdId`, `ticketPrice`) VALUES ('1', '101Boeing', '1', '1', '120')";
			String flightsTableDataR2 = "INSERT ignore INTO `flyaway`.`flights` (`flightId`, `flightsName`, `airlineId`, `sdId`, `ticketPrice`) VALUES ('2', '307Boeing', '2', '2', '145')";
			String flightsTableDataR3 =	"INSERT ignore INTO `flyaway`.`flights` (`flightId`, `flightsName`, `airlineId`, `sdId`, `ticketPrice`) VALUES ('3', 'Boe34', '3', '3', '110')";
			String flightsTableDataR4 =	"INSERT ignore INTO `flyaway`.`flights` (`flightId`, `flightsName`, `airlineId`, `sdId`, `ticketPrice`) VALUES ('4', 'w7TE', '4', '4', '114')";
			String flightsTableDataR5 =	"INSERT ignore INTO `flyaway`.`flights` (`flightId`, `flightsName`, `airlineId`, `sdId`, `ticketPrice`) VALUES ('5', 'RTE9', '5', '5', '118')";
			String flightsTableDataR6 =	"INSERT ignore INTO `flyaway`.`flights` (`flightId`, `flightsName`, `airlineId`, `sdId`, `ticketPrice`) VALUES ('6', 'R2D2', '6', '6', '768')";
			String flightsTableDataR7 =	"INSERT ignore INTO `flyaway`.`flights` (`flightId`, `flightsName`, `airlineId`, `sdId`, `ticketPrice`) VALUES ('7', 'JEER', '7', '7', '332')";
			String flightsTableDataR8 =	"INSERT ignore INTO `flyaway`.`flights` (`flightId`, `flightsName`, `airlineId`, `sdId`, `ticketPrice`) VALUES ('8', 'YTR5', '8', '8', '200')";
			String flightsTableDataR9 =	"INSERT ignore INTO `flyaway`.`flights` (`flightId`, `flightsName`, `airlineId`, `sdId`, `ticketPrice`) VALUES ('9', '6TR', '9', '9', '321')";
			String flightsTableDataR10 ="INSERT ignore INTO `flyaway`.`flights` (`flightId`, `flightsName`, `airlineId`, `sdId`, `ticketPrice`) VALUES ('10', 'HAL54', '10', '10', '333')";
			String flightsTableDataR11 ="INSERT ignore INTO `flyaway`.`flights` (`flightId`, `flightsName`, `airlineId`, `sdId`, `ticketPrice`) VALUES ('11', 'TR7', '3', '11', '897')";
			String flightsTableDataR12 ="INSERT ignore INTO `flyaway`.`flights` (`flightId`, `flightsName`, `airlineId`, `sdId`, `ticketPrice`) VALUES ('12', 'KUY67', '5', '12', '786')";
					
			
			
			
			//source destination table creation
			String mastersourcedestination="CREATE TABLE IF NOT EXISTS `flyaway`.`mastersourcedestination` (\r\n"
					+ "  `sdId` INT NOT NULL,\r\n"
					+ "  `source` VARCHAR(45) NOT NULL,\r\n"
					+ "  `destination` VARCHAR(45) NOT NULL,\r\n"
					+ "  PRIMARY KEY (`sdId`));\r\n"
					+ "";
			//data fill up for source and destination
			String mastersourcedestinationDataR1 = "INSERT ignore INTO `flyaway`.`mastersourcedestination` (`sdId`, `source`, `destination`) VALUES ('1', 'New Delhi', 'Kolkata')";
			String mastersourcedestinationDataR2 = 	"INSERT ignore INTO `flyaway`.`mastersourcedestination` (`sdId`, `source`, `destination`) VALUES ('2', 'New Delhi', 'Mumbai')";
			String mastersourcedestinationDataR3 = 	"INSERT ignore INTO `flyaway`.`mastersourcedestination` (`sdId`, `source`, `destination`) VALUES ('3', 'New Delhi', 'Bengluru')";
			String mastersourcedestinationDataR4 = 	"INSERT ignore INTO `flyaway`.`mastersourcedestination` (`sdId`, `source`, `destination`) VALUES ('4', 'New Delhi', 'Hyderabad')";
			String mastersourcedestinationDataR5 = 	"INSERT ignore INTO `flyaway`.`mastersourcedestination` (`sdId`, `source`, `destination`) VALUES ('5', 'Bengluru', 'Kolkata')";
			String mastersourcedestinationDataR6 = 	"INSERT ignore INTO `flyaway`.`mastersourcedestination` (`sdId`, `source`, `destination`) VALUES ('6', 'Bengluru', 'Mumbai')";
			String mastersourcedestinationDataR7 = 	"INSERT ignore INTO `flyaway`.`mastersourcedestination` (`sdId`, `source`, `destination`) VALUES ('7', 'Bengluru', 'New Delhi')";
			String mastersourcedestinationDataR8 = 	"INSERT ignore INTO `flyaway`.`mastersourcedestination` (`sdId`, `source`, `destination`) VALUES ('8', 'Bengluru', 'Hyderabad')";
			String mastersourcedestinationDataR9 = 	"INSERT ignore INTO `flyaway`.`mastersourcedestination` (`sdId`, `source`, `destination`) VALUES ('9', 'Hyderabad', 'Kolkata')";
			String mastersourcedestinationDataR10 = "INSERT ignore INTO `flyaway`.`mastersourcedestination` (`sdId`, `source`, `destination`) VALUES ('10', 'Hyderabad', 'Mumbai')";
			String mastersourcedestinationDataR11 = "INSERT ignore INTO `flyaway`.`mastersourcedestination` (`sdId`, `source`, `destination`) VALUES ('11', 'Hyderabad', 'New Delhi')";
			String mastersourcedestinationDataR12 = "INSERT ignore INTO `flyaway`.`mastersourcedestination` (`sdId`, `source`, `destination`) VALUES ('12', 'Hyderabad', 'Bengluru')";
					
			//sql for admin id and password
			String adminIdPass = "INSERT IGNORE INTO `flyaway`.`adminlogin` (`idadminLogin`, `adminId`, `adminPassword`) VALUES ('1','admin@gmail.com', '1');\r\n"
					+ "";
			
			
			
			st.executeUpdate(userTable);
			st.executeUpdate(adminLoginTable);
			st.executeUpdate(airlinesTable);
			st.executeUpdate(flightsTable);
			st.executeUpdate(mastersourcedestination);
			st.executeUpdate(adminIdPass);
			
			
			
			//source and destination data
			st.executeUpdate(mastersourcedestinationDataR1);
			st.executeUpdate(mastersourcedestinationDataR2);
			st.executeUpdate(mastersourcedestinationDataR3);
			st.executeUpdate(mastersourcedestinationDataR4);
			st.executeUpdate(mastersourcedestinationDataR5);
			st.executeUpdate(mastersourcedestinationDataR6);
			st.executeUpdate(mastersourcedestinationDataR7);
			st.executeUpdate(mastersourcedestinationDataR8);
			st.executeUpdate(mastersourcedestinationDataR9);
			st.executeUpdate(mastersourcedestinationDataR10);
			st.executeUpdate(mastersourcedestinationDataR11);
			st.executeUpdate(mastersourcedestinationDataR12);
			
			
			
			//Airlines table data
			st.executeUpdate(airlinesTableDataR1);
			st.executeUpdate(airlinesTableDataR2);
			st.executeUpdate(airlinesTableDataR3);
			st.executeUpdate(airlinesTableDataR4);
			st.executeUpdate(airlinesTableDataR5);
			st.executeUpdate(airlinesTableDataR6);
			st.executeUpdate(airlinesTableDataR7);
			st.executeUpdate(airlinesTableDataR8);
			st.executeUpdate(airlinesTableDataR9);
			st.executeUpdate(airlinesTableDataR10);
			
			
			
			//Flights table data
			st.executeUpdate(flightsTableDataR1);
			st.executeUpdate(flightsTableDataR2);
			st.executeUpdate(flightsTableDataR3);
			st.executeUpdate(flightsTableDataR4);
			st.executeUpdate(flightsTableDataR5);
			st.executeUpdate(flightsTableDataR6);
			st.executeUpdate(flightsTableDataR7);
			st.executeUpdate(flightsTableDataR8);
			st.executeUpdate(flightsTableDataR9);
			st.executeUpdate(flightsTableDataR10);
			st.executeUpdate(flightsTableDataR11);
			st.executeUpdate(flightsTableDataR12);
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		} finally {
			response.sendRedirect("search.html");
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
