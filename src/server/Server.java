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
		sc.encodeRecordsAndSwap();
	}
	
	public PublicKey getPublicKey() throws NoSuchAlgorithmException {
		return sc.getRSAKey();
	}
	public byte[] fetchRecord(byte[] encIdentifier) throws Exception {
		return sc.decryptRSAMessageAndFindRecord(encIdentifier);
	}
	public byte[] getAESKey(PublicKey clientRSAKey) throws Exception {
		return sc.encryptAndReturnSymmetricKey(clientRSAKey);
	}
}
