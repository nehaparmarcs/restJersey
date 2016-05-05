package edu.sjsu.rest.client;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ReflectionDBObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import edu.sjsu.rest.pojo.ClientResponseDetails;
import edu.sjsu.rest.pojo.Data;
import edu.sjsu.rest.pojo.FaceBookVO;
import edu.sjsu.rest.pojo.RegisterDetails;
public class RestClientRegistered {


	public static void main(String[] args) {

		try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("https://graph.facebook.com/v2.6/search?q=san%20jose&type=event&access_token=CAACEdEose0cBAJpKSWEOCvVlZCgRDjmMdnsfZBpDgjsfDdEfjOhvyb94zyk1B2y2eywjDrF0x9G1TXIS15thcOjX4ukqja6ursFLUjN1KBBKJIIZCmZBIWCaIPte6F9TDtAJrnyTFvK9yEsAVnfIb18a0yuxW8IZAFNQH8ZC1tiSAFPk6vYk8QI9zk4QbLIzBdWNDDPYyxQPNyPQmasx36nM5FaL1rkOu3DTR7Ujzb81P6RtnIZB2ZAU");


			ClientResponse response = webResource.type("application/json")
					.get(ClientResponse.class);

			if ( response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			ObjectMapper mapper = new ObjectMapper();
			
			FaceBookVO rdetails = mapper.readValue(response.getEntity(String.class), FaceBookVO.class);
			
			
			System.out.println(rdetails);
			
			//insertIntoMongo(rdetails);
			
			findFromMongo();
			
			
			
			System.out.println(rdetails);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	
	public static void findFromMongo(){

		// Creating serverside mongoDB which is listening on port 27017
			MongoClient mongoClient;
			try {
			
				mongoClient = new MongoClient("localhost", 27017);
				
				@SuppressWarnings("deprecation")
				DB db = mongoClient.getDB("ibm");
				DBCollection table = db.getCollection("fbeventdata");
	
			
			// searching the DB to retrieve the updated details of the client
			BasicDBObject searchQuery = new BasicDBObject();
			
			searchQuery.put("Id", "757463417723708");
				
				//DBObject searchQuery = new DBObject();
				//searchQuery.put("Id", "757463417723708");
				
			DBCursor cursor = table.find(searchQuery);
			while (cursor.hasNext()) {
				DBObject nextDocument = cursor.next();
				//System.out.println(nextDocument.get("End_time"));
				//Data data1 = (Data)nextDocument;
				//System.out.println(data1);
				System.out.println(nextDocument.toString());
				String details = nextDocument.toString();
				//result += details + "\n";
			}
	
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
//	public static String deleteFromMongo(FaceBookVO fbVO){
//		
//	}
	
	
	public static void insertIntoMongo(FaceBookVO fbVO){

		// Creating serverside mongoDB which is listening on port 27017
				MongoClient mongoClient;
				try {
		for(Data data : fbVO.getData()){
			
			
				mongoClient = new MongoClient("localhost", 27017);
					
					@SuppressWarnings("deprecation")
					DB db = mongoClient.getDB("ibm");
					// gets collection user..similar to table
					DBCollection table = db.getCollection("fbeventdata");
					// create a row (document) in collection based on the object sent from
					// post call
					
					
					
//					BasicDBObject document = new BasicDBObject();
//					document.put("event_name", data.getName());
//					document.put("event_end_time", data.getEnd_time());
//					document.put("event_start_time", data.getStart_time());
//					document.put("event_id", data.getId());
//					document.put("event_name", data.getDescription());
//					if(data.getPlace()!=null){
//					BasicDBObject details = new BasicDBObject();
//					details.put("event_venue_id", data.getPlace().getId());
//					details.put("event_venue_name", data.getPlace().getName());
//					document.put("venue_details", details);
//					}
					table.insert(data);
			
			
			}
				}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		// create a row (document) in collection based on the object sent from
		// post call
//		BasicDBObject document = new BasicDBObject();
//		document.put("bootstrap_id", clientInfo.getBootID());
//		BasicDBObject details = new BasicDBObject();
//		details.put("manufacturer_no", clientInfo.getmNo());
//		details.put("version_no", clientInfo.getVerNo());
//		document.put("details", details);
//		table.insert(document);

		
		
		//Clients CRUD operation ===> insertions at client end now:
//		try {
//			RestClient.insertRecordIntoDbUserTable(new RegisterDetails(clientInfo.getBootID(), clientInfo.getmNo(), clientInfo.getVerNo(), "M 1", 1222));
//			//RestClient.updateRecordFromDbUserTable(new RegisterDetails(clientInfo.getBootID(), clientInfo.getmNo(), clientInfo.getVerNo(), "M 1", 1222));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		result = "==== Client Device Registration initiated on Server======== \n";
//		result += "==== Device information is =====>\n==== Manufacturer No. => " + clientInfo.getmNo() + " \n===== Version no. =>" + clientInfo.getVerNo() + " \n";
//		result += "\nClient Record is as below : \n\n";
//
//		// searching the DB to get the document for the given client
//		BasicDBObject searchQuery = new BasicDBObject();
//		searchQuery.put("bootstrap_id", clientInfo.getBootID());
//		DBCursor cursor = table.find(searchQuery);
//		while (cursor.hasNext()) {
//			DBObject nextDocument = cursor.next();
//			String detailsObj = nextDocument.toString();
//			result += detailsObj + "\n";
//		}
		
		// sending the registered client information to the client
		//return Response.status(201).entity(result).build();
	
	}
	
}
