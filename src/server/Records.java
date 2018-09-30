package server;

import java.util.ArrayList;

public class Records {
	
	private ArrayList<String> recordFiles;
	private ArrayList<byte[]> encRecordFiles;
	
	public Records() {
		encRecordFiles = new ArrayList<byte[]>();
		recordFiles = new ArrayList<String>();
		recordFiles.add("1-Mary");
		recordFiles.add("2-Jane");
		recordFiles.add("3-John");
		recordFiles.add("4-Ryan");
		recordFiles.add("5-Megan");
		recordFiles.add("6-James");
		recordFiles.add("7-Tom");
		recordFiles.add("8-Emma");		
	}
	
	public ArrayList<String> getRecords(){
		return recordFiles;
	}
	
	public ArrayList<byte[]> getEncRecords(){
		return encRecordFiles;
	}
	public byte[] getRecordFromIndex(int index) {
		return encRecordFiles.get(index);
	}
	public void setRecords(ArrayList<String> recordFiles) {
		this.recordFiles = recordFiles;
	}
	
	public void setEncRecords(byte[] encRecord) {
		this.encRecordFiles.add(encRecord);
	}

}
