package edu.sjsu.util;

import java.lang.*;
import java.util.*;

import edu.sjsu.rest.pojo.ClientDTO;

public class CommonUtils {

	static Map<Integer, String> dataMap = new HashMap<Integer,String>();
	static final String VALID_CLIENTS = "12234,56787,78789"; 
	
	public static void storeData(ClientDTO clientDTO){
		
		dataMap.put(clientDTO.getId(), clientDTO.getData());
	}
	
	public static boolean isValidClient(ClientDTO clientDTO){
		
		return VALID_CLIENTS.contains("" + clientDTO.getId());
	}
	
}
