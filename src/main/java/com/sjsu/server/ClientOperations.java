package com.sjsu.server;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.sjsu.rest.pojo.BillingDetails;
import edu.sjsu.rest.pojo.ResourceVO;

public class ClientOperations implements IClientServiceInterface {
	
	public static String LOCATION_RESOURCE = "2";
	static DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	public static Map<String,String> observeMap = Collections.synchronizedMap(new HashMap<String,String>());
	
	
	public static void main(String a[]){
		
		Date today = Calendar.getInstance().getTime(); 
		
		ClientOperations co = new ClientOperations();
		co.create("2");
		co.create("3");
		co.writeDeviceInfo("2", "2");
		co.writeDeviceAttributes("3", "3", new BillingDetails(df.format(today), null,null));
		System.out.println(co.readDeviceInfo("3", "3"));
		co.delete("2");
	}

	public String create(String clientObjectID) {
		
		DBCollection table = getMongoTable("LwM2M", "client_data");
		
		BasicDBObject document = new BasicDBObject();
		document.put("object_id", clientObjectID);
		
//		ClientData clientData = new ClientData();
//		clientData.setObjID(clientObjectID);
		
		
		table.insert(document);
		
		//Searching to check if data was inserted for the given client
				BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.put("object_id", clientObjectID);
				DBCursor cursor = table.find(searchQuery);
				while (cursor.hasNext()) {
					DBObject nextDocument = cursor.next();
					String detailsObj = nextDocument.toString();
					System.out.println("Object created ==> "+detailsObj +"\n\n");
				}
		
		return "Created 2.01";
		
	}

	public String delete(String clientObjectID) {
		
		DBCollection table = getMongoTable("LwM2M", "client_data");
		
		//find the element. If present, then delete it
		BasicDBObject deleteQuery = new BasicDBObject().append("object_id", clientObjectID);
		table.remove(deleteQuery);
		
		return "Deleted 2.01";
	}

	public String writeDeviceInfo(String clientObjectID, String resourceID) {
		
		DBCollection table = getMongoTable("LwM2M", "client_data");
		BasicDBObject updatedClient = new BasicDBObject();
		updatedClient.put("object_id", clientObjectID);
		updatedClient.put("resource_id", resourceID);
		BasicDBObject updatedClientDetails = new BasicDBObject();
		Random randomGenerator = new Random();
		updatedClientDetails.put("X-coordinates", String.valueOf(randomGenerator.nextInt(100)));
		updatedClientDetails.put("Y-coordinates", String.valueOf(randomGenerator.nextInt(100)));
		updatedClient.put("resource_value", updatedClientDetails);
		BasicDBObject updateQuery = new BasicDBObject().append("object_id", clientObjectID);

		table.update(updateQuery, updatedClient);
		
		return "Write done 2.01";
	}
	


	public String writeDeviceAttributes(String clientObjectID, String resourceID, BillingDetails bd) {
		DBCollection table = getMongoTable("LwM2M", "client_data");
		
		BasicDBObject updatedClient = new BasicDBObject();
		updatedClient.put("object_id", clientObjectID);
		updatedClient.put("resource_id", resourceID);
		BasicDBObject updatedClientDetails = new BasicDBObject();
		
		updatedClientDetails.put("start_time", bd.getStart_time());
		updatedClientDetails.put("end_time", bd.getEnd_time());
		updatedClient.put("resource_value", updatedClientDetails);
		BasicDBObject updateQuery = new BasicDBObject().append("object_id", clientObjectID);

		table.update(updateQuery, updatedClient);
		
		
		
		return "Write Done 2.01";
	}

	public String discover(String objectID, String resourceID) {
		DBCollection table = getMongoTable("LwM2M", "client_data");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("object_id", objectID);
		searchQuery.put("resource_id", resourceID);
		DBCursor cursor = table.find(searchQuery);
		while (cursor.hasNext()) {
			return  cursor.next().toString();
		}
		return null;
	}

	public String readDeviceInfo(String objectID, String resourceID) {
		DBCollection table = getMongoTable("LwM2M", "client_data");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("object_id", objectID);
		searchQuery.put("resource_id", resourceID);
		DBCursor cursor = table.find(searchQuery);
		while (cursor.hasNext()) {
			return  cursor.next().toString();
		}
		return null;
	}

	
	//Information reporting interfaces
	public void observe(String objectID, String resourceID) {
		DBCollection table = getMongoTable("LwM2M", "client_data");
		observeMap.put(objectID,resourceID);
		System.out.println("Object is now being observed ==> "+objectID);
		try {
			notifyForChanges(observeMap);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	

	public void cancelObservation(String objectID, String resourceID) {
		DBCollection table = getMongoTable("LwM2M", "client_data");
	
		if(observeMap.containsKey(objectID)){
			observeMap.remove(objectID);
			System.out.println("Observation cancelled for object ==>"+objectID);
		}
		
		
	}

	DBCollection getMongoTable(String database, String table){
		
		@SuppressWarnings("deprecation")
		MongoClient mongoClient = getMongoClient();
		DB db = mongoClient.getDB(database);
		
		return db.getCollection(table);
	}
	
	MongoClient getMongoClient(){
		
		try {
			//Creating mongoDB listening on port 27017
			return new MongoClient("localhost", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * generates the random parameters for location resource.
	 * @return ResourceVO
	 */
	ResourceVO getLocationResource(){
		Random randomGenerator = new Random();
		return new ResourceVO("x-coordinate", String.valueOf(randomGenerator.nextInt(100)), "Y-coordinate", String.valueOf(randomGenerator.nextInt(100)));	
	}

	public String enableObject(String objId, String resourceID, String enable) {
		
		DBCollection table = getMongoTable("LwM2M", "client_data");
		BasicDBObject updatedClient = new BasicDBObject();
		updatedClient.put("object_id", objId);
		updatedClient.put("resource_id", resourceID);
		BasicDBObject updatedClientDetails = new BasicDBObject();
		updatedClientDetails.put("enable", enable);
		updatedClient.put("resource_value", updatedClientDetails);
		BasicDBObject updateQuery = new BasicDBObject().append("object_id", objId);

		table.update(updateQuery, updatedClient);
		
		return "Enabled 2.01";
	
		
		
	}

	private void notifyForChanges(Map<String, String> observeMap) throws InterruptedException {

		//Location sensor's periodic information
		thread.start();
		
		
	}
	
	private Thread thread = new Thread(new Runnable() {
		public void run() {
			while(true){
				for(String objId:observeMap.keySet()){
					
				DBCollection table = getMongoTable("LwM2M", "client_data");
				BasicDBObject updatedClient = new BasicDBObject();
				updatedClient.put("object_id", objId);
				updatedClient.put("resource_id", observeMap.get(objId));
				BasicDBObject updatedClientDetails = new BasicDBObject();
				Random randomGenerator = new Random();
				updatedClientDetails.put("X-coordinates", String.valueOf(randomGenerator.nextInt(100)));
				updatedClientDetails.put("Y-coordinates", String.valueOf(randomGenerator.nextInt(100)));
				updatedClient.put("resource_value", updatedClientDetails);
				BasicDBObject updateQuery = new BasicDBObject().append("object_id", objId).append("resource_id", observeMap.get(objId));

				table.update(updateQuery, updatedClient);
				System.out.println("Client Notification :: New Location data available ==>"+ updatedClient);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				
			}
			
		}
		});

		

	
	
}
