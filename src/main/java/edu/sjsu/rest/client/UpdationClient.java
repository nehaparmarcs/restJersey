package edu.sjsu.rest.client;

import java.sql.SQLException;

import edu.sjsu.rest.pojo.RegisterDetails;

public class UpdationClient {

	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		RegisterDetails rd = new RegisterDetails();
		rd.setBootStrapID(111);
		rd.setClientID(1232);
		rd.setManufactureNo("MAN NO XX32. updated");
		rd.setModelNo("Model X.Y10 updated" );
		rd.setVersionNo("Version 10.0.0.1 updated");
		
		try {
			RestClient.updateRecordFromDbUserTable(rd);
			System.out.println("\nClients data is ::"+rd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
