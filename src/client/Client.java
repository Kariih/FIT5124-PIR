package client;

import java.security.PublicKey;

import Utils.EncryptionRSA;
import server.Record;

public class Client {
	
	public static byte[] encMessage = null;
	
	public Client() {}
	
	public byte[] encryptIdentifierOnClient(PublicKey pubKey) throws Exception {
		String identifier = "Mary";
		EncryptionRSA rsa = new EncryptionRSA();
		return rsa.encryptRSA(pubKey, identifier);
	}
	
	public Record decryptRecordOnClient() {
		return null;
	}
}
