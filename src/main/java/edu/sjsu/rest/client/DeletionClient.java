package edu.sjsu.rest.client;

import java.sql.SQLException;

public class DeletionClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			RestClient.deletRecordFromDbUserTable(111);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
