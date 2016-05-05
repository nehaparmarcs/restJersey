package edu.sjsu.rest.client;

import java.sql.SQLException;

import edu.sjsu.rest.pojo.RegisterDetails;

public class InsertionClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RegisterDetails rd = new RegisterDetails();
		rd.setBootStrapID(111);
		rd.setClientID(1232);
		rd.setManufactureNo("MAN NO XX32.");
		rd.setModelNo("Model X.Y10");
		rd.setVersionNo("Version 10.0.0.1");
		
		try {
			RestClient.insertRecordIntoDbUserTable(rd);
			System.out.println("\nClients data is ::"+rd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
