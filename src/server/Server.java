package server;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import sc.SC;

public class Server {
	
	SC sc = null;
	
	public Server (){
		sc = new SC();
	}
	
	public PublicKey getPublicKey() throws NoSuchAlgorithmException {
		return sc.getRSAKey();
	}
	public String fetchRecord(byte[] encIdentifier) throws Exception {
		return sc.decryptRSAMessage(encIdentifier);
	}
}
