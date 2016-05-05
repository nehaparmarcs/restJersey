package edu.sjsu.rest.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.Random;

public class ClientInvocationHandler {

	static int counter = 0;
	public static void main(String[] args) {
		
	//Random randomGenerator = new Random();
	//int randomInt;
	
		
		//while(counter <=10){
			//randomInt = randomGenerator.nextInt(100);
			//ClientRunnable rc = new ClientRunnable(12234, "Client 1 data: "+randomInt);
			ClientRunnable rc = new ClientRunnable(12234, "Client 1 data: Hello", "1: I am ready for boot strap!");
		    Thread t1 = new Thread(rc);
		    t1.start();
		    
		    //randomInt = randomGenerator.nextInt(77);
		    //ClientRunnable rc2 = new ClientRunnable(56787, "Client 2 data: "+randomInt);
		    ClientRunnable rc2 = new ClientRunnable(56787, "Client 2 data: Hello", "2: I am ready for boot strap!");
		    Thread t2 = new Thread(rc2);
		    t2.start();
		 //counter++;
		    ClientRunnable rc3 = new ClientRunnable(78789, "Client 3 data: Hello", "3: I am ready for boot strap!");
		    Thread t3 = new Thread(rc3);
		    t3.start();
			
		//}	
		
	}
	
	
	
}

/**
 * Class to invoke instance of client
 * @author navdeepdahiya
 *
 */
 class ClientRunnable implements Runnable {

	int id;
	String data;
	String data2;
	
	public ClientRunnable(int id, String data, String data2){
		this.id = id;
		this.data = data;
		this.data2 = data2;
	}

	public void run() {

		try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8080/jersey-service/rest/json/post");

			String input = "{\"id\":\""+id+"\",\"data\":\""+data+"\",\"data2\":\""+data2+"\"}";

			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

	
	 }
	}

