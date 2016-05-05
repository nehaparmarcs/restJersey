package com.sjsu.server;

import edu.sjsu.rest.pojo.BillingDetails;
import edu.sjsu.rest.pojo.ClientData;

public interface IClientServiceInterface {
	
	/**
	 * Creates a Client object w.r.t. Device Management interface.
	 * @param clientObjectID
	 * @return String returns a json string on the status of object creation.
	 */
	String create(String clientObjectID);
	
	/**
	 * Creates a Client object w.r.t. Device Management interface.
	 * @param clientObjectID
	 * @return String returns a json string on the status of object creation.
	 */
	String delete(String clientObjectID);

	/**
	 * writes object information will use it for putting payment information.
	 * @param clientObjectID
	 * @param resourceID
	 */
	String writeDeviceInfo(String clientObjectID, String resourceID);
	
	/**
	 * Used for payment module where we will update the start time and end time.
	 * @param clientObjectID
	 * @param resourceID
	 * @param bd
	 */
	String writeDeviceAttributes(String clientObjectID, String resourceID, BillingDetails bd);
	
	/**
	 * returns the attributes and resource value of that particular resource should be returned to the Server
	 * @param objectID
	 * @param objectInstanceID
	 * @param resourceID
	 * @return 
	 */
	String discover(String objectID,String resourceID);
	
	/**
	 * This operation allow the server to read clients information.
	 * @param objectID
	 * @param resourceID
	 * @return json value with complete client object.
	 */
	String readDeviceInfo(String objectID,String resourceID);
	
	String enableObject(String objectID,String resourceID, String enable);
	
	
	/**
	 * The method is used to receive any changes that happen on the client.
	 * Server receives notifications when new values are available.
	 * 
	 */
	void observe(String objectID,String resourceID);
	
	/**
	 * To stop observing a method for changes in the resources.
	 */
	void cancelObservation(String objectID,String resourceID);

}
