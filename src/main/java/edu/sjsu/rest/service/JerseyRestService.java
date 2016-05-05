package edu.sjsu.rest.service;


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
import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import edu.sjsu.rest.pojo.ClientDTO;
import edu.sjsu.rest.pojo.ClientRequestDetails;
import edu.sjsu.rest.pojo.ClientResponseDetails;
import edu.sjsu.rest.pojo.RegisterDetails;
import edu.sjsu.util.CommonUtils;

@Path("/json")
public class JerseyRestService {

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(ClientRequestDetails cReqDetails) {
		

		String result = "Data Received : " + cReqDetails;
		
		ClientResponseDetails crDetails = new ClientResponseDetails();

		try{
			// Since 2.10.0, uses MongoClient
			MongoClient mongo = new MongoClient("localhost", 27017);
			
			DB db = mongo.getDB("applicationdb");
			
			DBCollection table = db.getCollection("valid_clients");	
			
			//insert first time
			BasicDBObject document1 = new BasicDBObject();
			document1.put("CLIENT_ID",cReqDetails.getClientID() );
			document1.put("SERVER_ID", cReqDetails.getServerID());
			table.insert(document1);
			
			/**** Find and display ****/
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("CLIENT_ID",cReqDetails.getClientID());

			DBCursor cursor = table.find(searchQuery);
			
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
				crDetails.setClientID(cReqDetails.getClientID());
				crDetails.setServerID(cReqDetails.getServerID());
				crDetails.setBootStrapId("BS"+cReqDetails.getClientID() +""+ cReqDetails.getServerID());
				
				DBCollection tablebs = db.getCollection("BOOTSTRAP_INFO");
				BasicDBObject document = new BasicDBObject();
				document.put("CLIENT_ID",crDetails.getClientID() );
				document.put("SERVER_ID", crDetails.getServerID());
				document.put("BOOTSTRAP_ID", crDetails.getBootStrapId());
				tablebs.insert(document);
			}
			
		
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		catch (MongoException e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(crDetails).build();
		

	}

	
	@DELETE
	@Path("/delete/{userKey}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteClient(@PathParam("userKey") String userKey) {
		
		RegisterDetails rd = null;
	
		
		ClientResponseDetails crDetails = new ClientResponseDetails();

		try{
			
			MongoClient mongo = new MongoClient("localhost", 27017);
			
			DB db = mongo.getDB("applicationdb");
			
			DBCollection table = db.getCollection("BOOTSTRAP_INFO");			
			
			/**** Find and display ****/
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("BOOTSTRAP_ID",userKey);

			DBCursor cursor = table.find(searchQuery);
			
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
		
				/*rd = new  RegisterDetails(cReqDetails.getBootStrapId(), "SS-WYUI", "2.3.3", "Mod 1.1");
				
				DBCollection tablebs1 = db.getCollection("REGISTER_INFO");
				BasicDBObject document1 = new BasicDBObject();
				document1.put("BOOTSTRAP_ID",crDetails.getClientID() );
				document1.put("MANUFACTURE_NO", crDetails.getServerID());
				document1.put("VERSION_NO", crDetails.getBootStrapId());
				document1.put("MODEL_NO", crDetails.getBootStrapId());
				*/
				//tablebs1.insert(document1);
				
			}
			
		
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		catch (MongoException e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(rd).build();
		

	}
	
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerClient(ClientResponseDetails cReqDetails) {
		
		RegisterDetails rd = null;
	
		
		ClientResponseDetails crDetails = new ClientResponseDetails();

		try{
			MongoClient mongo = new MongoClient("localhost", 27017);
			
			DB db = mongo.getDB("applicationdb");
			
			DBCollection table = db.getCollection("BOOTSTRAP_INFO");			
			
			/**** Find and display ****/
			BasicDBObject searchQuery = new BasicDBObject();
			//searchQuery.put("BOOTSTRAP_ID",cReqDetails.getBootStrapId());

			DBCursor cursor = table.find(searchQuery);
			
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
		
				//rd = new  RegisterDetails(111, cReqDetails.getBootStrapId(), "SS-WYUI", "2.3.3", "Mod 1.1",111);
				
				DBCollection tablebs1 = db.getCollection("REGISTER_INFO");
				BasicDBObject document1 = new BasicDBObject();
				document1.put("BOOTSTRAP_ID",crDetails.getClientID() );
				document1.put("MANUFACTURE_NO", crDetails.getServerID());
				document1.put("VERSION_NO", crDetails.getBootStrapId());
				document1.put("MODEL_NO", crDetails.getBootStrapId());
				tablebs1.insert(document1);
				
				
			}
			
		
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		catch (MongoException e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(rd).build();
		

	}

	
	

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateClient(ClientResponseDetails cReqDetails) {
		
		RegisterDetails rd = null;
		
		ClientResponseDetails crDetails = new ClientResponseDetails();

		try{
			MongoClient mongo = new MongoClient("localhost", 27017);
			
			DB db = mongo.getDB("applicationdb");
			
			DBCollection table = db.getCollection("BOOTSTRAP_INFO");			
			
			/**** Find and display ****/
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("BOOTSTRAP_ID",cReqDetails.getBootStrapId());

			DBCursor cursor = table.find(searchQuery);
			
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
		
				//rd = new  RegisterDetails(cReqDetails.getBootStrapId(), "PPPI", "4.0.1", "again 1.1");
				
				DBCollection tablebs1 = db.getCollection("REGISTER_INFO");
				BasicDBObject document1 = new BasicDBObject();
				document1.put("BOOTSTRAP_ID",crDetails.getClientID() );
				document1.put("MANUFACTURE_NO", crDetails.getServerID());
				document1.put("VERSION_NO", crDetails.getBootStrapId());
				document1.put("MODEL_NO", crDetails.getBootStrapId());
				tablebs1.insert(document1);
				
				
			}
			
		
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		catch (MongoException e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(rd).build();
		

	}

	
	
	


}
