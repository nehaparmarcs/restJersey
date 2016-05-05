package edu.sjsu.rest.service;

import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.sjsu.rest.client.ClientCRUDService;
import edu.sjsu.rest.client.RestClient;
import edu.sjsu.rest.pojo.ClientInformation;
import edu.sjsu.rest.pojo.RegisterDetails;

@Path("/rest")
public class RestCRUDService {

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(ClientInformation clientInfo ) {
		
		String result = "";
		
		// Creating serverside mongoDB which is listening on port 27017
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient("localhost", 27017);
		
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB("appDB");
		// gets collection user..similar to table
		DBCollection table = db.getCollection("client_data");
		
		
		
		// create a row (document) in collection based on the object sent from
		// post call
		BasicDBObject document = new BasicDBObject();
		document.put("bootstrap_id", clientInfo.getBootID());
		BasicDBObject details = new BasicDBObject();
		details.put("manufacturer_no", clientInfo.getmNo());
		details.put("version_no", clientInfo.getVerNo());
		document.put("details", details);
		table.insert(document);

		
		
		//Clients CRUD operation ===> insertions at client end now:
		try {
			RestClient.insertRecordIntoDbUserTable(new RegisterDetails(clientInfo.getBootID(), clientInfo.getmNo(), clientInfo.getVerNo(), "M 1", 1222));
			//RestClient.updateRecordFromDbUserTable(new RegisterDetails(clientInfo.getBootID(), clientInfo.getmNo(), clientInfo.getVerNo(), "M 1", 1222));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		result = "==== Client Device Registration initiated on Server======== \n";
		result += "==== Device information is =====>\n==== Manufacturer No. => " + clientInfo.getmNo() + " \n===== Version no. =>" + clientInfo.getVerNo() + " \n";
		result += "\nClient Record is as below : \n\n";

		// searching the DB to get the document for the given client
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("bootstrap_id", clientInfo.getBootID());
		DBCursor cursor = table.find(searchQuery);
		while (cursor.hasNext()) {
			DBObject nextDocument = cursor.next();
			String detailsObj = nextDocument.toString();
			result += detailsObj + "\n";
		}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// sending the registered client information to the client
		return Response.status(201).entity(result).build();
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(ClientInformation checkin) {

		String result = "";
		
		
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient("localhost", 27017);
		
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB("appDB");
		// gets collection user..similar to table
		DBCollection table = db.getCollection("client_data");
		
		
		BasicDBObject updatedClient = new BasicDBObject();
		updatedClient.put("bootstrap_id", checkin.getBootID());
		BasicDBObject updatedClientDetails = new BasicDBObject();
		updatedClientDetails.put("manufacturer_no", checkin.getmNo());
		updatedClientDetails.put("version_no", checkin.getVerNo());
		updatedClient.put("details", updatedClientDetails);
		BasicDBObject updateQuery = new BasicDBObject().append("bootstrap_id", checkin.getBootID());

		table.update(updateQuery, updatedClient);

		//Update at CLient now
		try {
			RestClient.updateRecordFromDbUserTable(new RegisterDetails(checkin.getBootID(), checkin.getmNo(), checkin.getVerNo(), "Updated Now", 1222));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		result =  "==== Client Details successfully updated on Server======== \n";
		result += "==== Client information is as below ====================== \n\n";

		// searching the DB to retrieve the updated details of the client
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("bootstrap_id", checkin.getBootID());
		DBCursor cursor = table.find(searchQuery);
		while (cursor.hasNext()) {
			DBObject nextDocument = cursor.next();
			String details = nextDocument.toString();
			result += details + "\n";
		}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// sending the updated records back to the client
		return Response.status(201).entity(result).build();
	}

	@GET
	@Path("/get/{deviceID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("deviceID") String deviceID) {
		int id = Integer.parseInt(deviceID);
		String result = "";
		
			// Creating serverside mongoDB which is listening on port 27017
				MongoClient mongoClient;
				try {
					mongoClient = new MongoClient("localhost", 27017);
				
				@SuppressWarnings("deprecation")
				DB db = mongoClient.getDB("appDB");
				// gets collection user..similar to table
				DBCollection table = db.getCollection("client_data");
		
				//Read at CLient now
				try {
					RestClient.readRecordsFromTable(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				// searching the DB to retrieve the updated details of the client
				BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.put("bootstrap_id", id);
				DBCursor cursor = table.find(searchQuery);
				while (cursor.hasNext()) {
					DBObject nextDocument = cursor.next();
					String details = nextDocument.toString();
					result += details + "\n";
				}
		
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		// sending the updated records back to the client
		return Response.status(201).entity(result).build();
		
	}
	
	@DELETE
	@Path("/delete/{deviceID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deRegister(@PathParam("deviceID") String deviceID) {
		
		String result = "";
		
		// Creating serverside mongoDB which is listening on port 27017
			MongoClient mongoClient;
			try {
				mongoClient = new MongoClient("localhost", 27017);
			
			@SuppressWarnings("deprecation")
			DB db = mongoClient.getDB("appDB");
			// gets collection user..similar to table
			DBCollection table = db.getCollection("client_data");
		
		int id = Integer.parseInt(deviceID);
		BasicDBObject deleteQuery = new BasicDBObject().append("bootstrap_id", id);

		table.remove(deleteQuery);
		
		//Remove from CLient now
		try {
			RestClient.deletRecordFromDbUserTable(id);;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		result = "==== Client Deregistration successfully done on Server======== \n";
		result = "==== Client with Bootstrap ID => " + id + " is deregistered =======\n\n";
		// sending the response of deRegister function to the client
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return Response.status(201).entity(result).build();
	}
}

