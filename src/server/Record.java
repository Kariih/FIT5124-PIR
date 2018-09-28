package server;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Record {
	
	private String name;
	private Date dateOfBirth;
	private Map<Integer, String>log;
	
	public Record(String name, Date dateOfBirth, Map<Integer, String> log) {
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.log = log;
	}
	
	public void addRecord(int id, String desc) {
		if (this.log.get(id) != null){
			log.put(id, desc);
		}else {
			System.out.println("Given id exist in log");
		}
	}
	
	public Map<Integer, String> getRecords() {
		return this.log;
	}
	
	public String birthDateFormatter() {
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		return dateFormat.format(this.birthDateFormatter());
		
	}
	
	public String toString() {
		String entries = "";
		for (Map.Entry<Integer, String> entry : log.entrySet()) {
			entries.concat(entry.getKey() + ": " + entry.getValue());
		}
		return "Name: " + this.name + "\n Birth: " + this.birthDateFormatter() 
			+ "\n Records: \n" + entries;
	}
}
