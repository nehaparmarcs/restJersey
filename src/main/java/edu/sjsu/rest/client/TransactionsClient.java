package edu.sjsu.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.sjsu.rest.pojo.BillingDetails;
import edu.sjsu.rest.pojo.ClientData;

public class TransactionsClient {

	public static void main(String a[]){
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String objUri;
		String resourceUrl;
		String[] inputValue;
		
		ClientOperations clientOps = new ClientOperations();
		while (true) {
			System.out.println("Please enter your choice for Device Management/Service Enablement: \n");
			
			System.out.println("1. Create Client Object ");
			System.out.println("2. Write to Client ");
			System.out.println("3. Read From Client ");
			System.out.println("4. Discover Client object ");
			System.out.println("5. Write Attribute to Client ");
			System.out.println("6. Delete Client ");
			System.out.println("7. Enable a Client");
			System.out.println("8. Observe a Client object ");
			System.out.println("9. Cancel observation");
			System.out.println("10. Notification Client");
			
			try {
				
				String userInput = in.readLine();
				if ("q".equalsIgnoreCase(userInput)) {
					System.exit(0);
				}
				int inp = Integer.parseInt(userInput);
				
				switch (inp) {
				  
					case 1:	System.out.println("Enter object uri: ");
							clientOps.create(in.readLine());
					        break;
					  
					case 2: System.out.println("Enter object info to be written: {objuri}|{resource} ");
							inputValue = (in.readLine()).split(" ");
					        clientOps.writeDeviceInfo(inputValue[0],inputValue[1]);
					        break;
					  
					case 3: System.out.println("Enter object uri to be read ==> {objuri}|{resource} ");
							inputValue = (in.readLine()).split(" ");
							//clientOps.readDeviceInfo(inputValue[0],inputValue[1]);
							System.out.println("Read object :: "+clientOps.readDeviceInfo(inputValue[0],inputValue[1]));
						  	break;
					  
					case 4: System.out.println("Enter object uri you want to discover==> {objuri}|{resource}");
							inputValue = (in.readLine()).split(" ");
							System.out.println("Object discovery :: "+clientOps.discover(inputValue[0],inputValue[1]));
					        break;
					        
					case 5: System.out.println("Enter attributes you want to write==> {objuri}|{resource}|{start_time}|{end_time}| {plan_type}");
							inputValue = (in.readLine()).split(" ");
							
							System.out.println(clientOps.writeDeviceAttributes(inputValue[0],inputValue[1], new BillingDetails(inputValue[2],inputValue[3],inputValue[4])));
					        break;
					        
					case 6: System.out.println("Enter object uri you want to delete==> {objuri}|{resource}");
							inputValue = (in.readLine()).split(" ");
							clientOps.discover(inputValue[0],inputValue[1]);
					        break;
					        
					case 7: System.out.println("Enter object uri you want to enable: {objuri}|{resource}|{enable}");
							inputValue = (in.readLine()).split(" ");
							clientOps.enableObject(inputValue[0],inputValue[1],inputValue[2]);    
					        break;
					        
					case 8: System.out.println("Enter object uri you want to observe==> {objuri}|{resource}");
							inputValue = (in.readLine()).split(" ");
							clientOps.observe(inputValue[0],inputValue[1]);
					        break;
			        
					case 9: System.out.println("cancel observation for the client: {objuri}|{resource}");
							inputValue = (in.readLine()).split(" ");
							clientOps.cancelObservation(inputValue[0],inputValue[1]);    
					        break;
				  
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void clienInvokation(BufferedReader in, ClientData cd){}

}
