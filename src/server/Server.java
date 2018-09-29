package server;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import sc.SC;

public class Server {
	
	private SC sc = null;
	
	public Server (){
		sc = new SC();
	}
	
	public void encryptRecords() throws Exception {
		sc.encodeRecordsWithAES();
	}
	
	public PublicKey getPublicKey() throws NoSuchAlgorithmException {
		return sc.getRSAKey();
	}
	public byte[] fetchRecord(byte[] encIdentifier, PublicKey pubKey) throws Exception {
		sc.decryptRSAMessage(encIdentifier);
		
		//TODO FIND RECORD HERE
		
		return sc.encryptRecordOnServer("hi", pubKey);
	}
}
