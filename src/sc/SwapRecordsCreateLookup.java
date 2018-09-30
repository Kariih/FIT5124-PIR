package sc;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

class SwapRecordsCreateLookup {

	HashMap<String, Integer> lookupTable;
	
	public SwapRecordsCreateLookup() {
		lookupTable = new HashMap<>();
	}
	
	//Swapping the existing record based on Fisher–Yates shuffle and create
	//a lookup table to find encrypted element on server.
	public ArrayList<String> swapArray(ArrayList<String> list){
	    SecureRandom random = new SecureRandom();
	    for (int i = list.size()-1; i >= 0; i--){
			int index = random.nextInt(i + 1);
			String recordToSwap = list.get(index);
			Collections.swap(list, index, i);
			list.set(i, recordToSwap);
			addToLookupTable(i, recordToSwap);
		}
	    System.out.println("Creating lookup table for finding records in ecrypted list");
	    for (Entry<String, Integer> entry : lookupTable.entrySet()) {
			System.out.printf("Id: %5s new index: %d%n", entry.getKey(), entry.getValue());
		}
	    System.out.println();
	    return list;
	}
	
	//Adding record identifier and new index to the lookup table
	private void addToLookupTable(int index, String identifier) {
		lookupTable.put(identifier.split("-")[1], index);
	}
	
	//find encrypted record with help of identifier and lookup table
	public int getIndexOfIdentifier(String identifier) {
		return lookupTable.get(identifier);
	}
}
