package edu.sjsu.rest.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import edu.sjsu.rest.pojo.ClientResponseDetails;
import edu.sjsu.rest.pojo.RegisterDetails;

public class RestClient {
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/CMPE273";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";

	public static void main(String[] args) {

		RegisterDetails rd = new RegisterDetails();
		rd.setBootStrapID(111);
		rd.setClientID(1232);
		rd.setManufactureNo("Man no.");
		rd.setModelNo("Mod No");
		rd.setVersionNo("Version");
		
		try {
			insertRecordIntoDbUserTable(rd);
			
			rd.setVersionNo("PQ");
			
			rd.setModelNo("Mod 3");
			
			updateRecordFromDbUserTable(rd);
			
			readRecordsFromTable(111);
			
			deletRecordFromDbUserTable(111);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8080/jersey-rest-service/rest/json/post");

			String input = "{\"clientID\":\"1254\",\"serverID\":\"137\"}";

			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			ObjectMapper mapper = new ObjectMapper();
			
			//JSON from String to Object
			ClientResponseDetails obj = mapper.readValue(response.getEntity(String.class), ClientResponseDetails.class);
			
			//String obj = response.getEntity(String.class);
			insertRecordIntoDbUserTable(obj);
			
			
			System.out.println(obj);

		} catch (Exception e) {

			e.printStackTrace();

		}*/

	}
	
	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(
                               DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

	public static void insertRecordIntoDbUserTable(RegisterDetails clientResDetails) throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String insertTableSQL = "INSERT INTO CLIENT_REGISTRATION_DETAILS"
				+ "(bootStrapID,manufacture_no,version_no, model_no, ClientID) " + "VALUES"
				+ "("+ clientResDetails.getBootStrapID() + ",'"+ clientResDetails.getManufactureNo() +"','" +clientResDetails.getVersionNo()+"','" +clientResDetails.getModelNo()+"'," +clientResDetails.getClientID()+")";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			//System.out.println(insertTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(insertTableSQL);
			
			
			System.out.println("");
			System.out.println("============================================================================================");
			System.out.println("============Data Successfully inserted at Client for CLIENT_BOOTSTRAP_INFO =================  ");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

	public static void deletRecordFromDbUserTable(int  bootStrapID) throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String insertTableSQL = "DELETE FROM CLIENT_REGISTRATION_DETAILS"
				+" WHERE bootStrapID = "+ bootStrapID;

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(insertTableSQL);

			//Execute insert SQL statement
			statement.executeUpdate(insertTableSQL);
			
			System.out.println("");
			System.out.println("============================================================================================");
			System.out.println("=========Data Successfully deleted at Client for CLIENT_REGISTRATION_DETAILS ===============  ");
			System.out.println("============================================================================================");
			

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}

	}

	

	public static RegisterDetails readRecordsFromTable(int  bootStrapID) throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		RegisterDetails rd = new RegisterDetails();
		String insertTableSQL = "select bootStrapID,manufacture_no,version_no, model_no, ClientID FROM CLIENT_REGISTRATION_DETAILS"
				+" WHERE bootStrapID = "+ bootStrapID;

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(insertTableSQL);

			//Execute insert SQL statement
			ResultSet rs =  statement.executeQuery(insertTableSQL);
			while(rs.next()){
				rd.setBootStrapID(bootStrapID);
				rd.setClientID(rs.getInt("ClientID"));
				rd.setManufactureNo(rs.getString("manufacture_no"));
				rd.setModelNo(rs.getString("model_no"));
				rd.setVersionNo(rs.getString("version_no"));
				
				
			}
			
			System.out.println("");
			System.out.println("============================================================================================");
			System.out.println("=========Data Successfully fetched from Client for CLIENT_REGISTRATION_DETAILS =============  ");
			System.out.println("============================================================================================");
			
			
			System.out.println(rd);
			

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return rd;

	}

	
	
	public static void updateRecordFromDbUserTable(RegisterDetails crd) throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String insertTableSQL = "UPDATE CLIENT_REGISTRATION_DETAILS set manufacture_no = '" + crd.getManufactureNo() + "' , version_no ='"
				+crd.getVersionNo() + "', model_no = '"+crd.getModelNo() +"', ClientID = "+ crd.getClientID() + " where bootStrapID = "+ crd.getBootStrapID();

		
		//update CLIENT_REGISTRATION_DETAILS set manufacture_no = "M.N. 00", version_no = "vv", model_no = " njhj", ClientID = 8989 where bootStrapID = 111;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(insertTableSQL);

			//Execute insert SQL statement
			statement.executeUpdate(insertTableSQL);
			
			System.out.println("");
			System.out.println("============================================================================================");
			System.out.println("=========Data Successfully updated at Client for CLIENT_REGISTRATION_DETAILS ===============  ");
			System.out.println("============================================================================================");
			

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}

	}

	
	
}
