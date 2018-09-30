package sc;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class SwapRecordsCreateLookup {

	HashMap<String, Integer> lookupTable;
	
	public SwapRecordsCreateLookup() {
		lookupTable = new HashMap<>();
	}
	
	public ArrayList<String> swapArray(ArrayList<String> list){
	    SecureRandom random = new SecureRandom();
	    for (int i = list.size() - 1; i > 0; i--){
			int index = random.nextInt(i + 1);
			String recordToSwap = list.get(index);
			Collections.swap(list, index, i);
			list.set(i, recordToSwap);
			addToLookupTable(i, recordToSwap);
		}
	    addToLookupTable(list.size()-1, list.get(list.size()-1));
	    
	    return list;
	}
	
	private void addToLookupTable(int index, String identifier) {
		lookupTable.put(identifier.split("-")[1], index);
	}
	public int getIndexOfIdentifier(String identifier) {
		return lookupTable.get(identifier);
	}
}
