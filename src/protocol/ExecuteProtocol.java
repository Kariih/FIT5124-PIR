package protocol;

import client.Client;
import server.Server;

public class ExecuteProtocol {
	private static Server server;
	private static Client client;
	
	public static void main(String [] args) throws Exception {
		//Initialize a instance of server and client
		server = new Server();	
		client = new Client();
		
		//Encrypt existing DB with AES and shuffle the indexes
		server.encryptRecords();
		
		//Create query for SC
		byte[] identifier = client.encryptIdentifierOnClient(server.getPublicKey());
		
		//Send query to SC and encrypt response
		byte[] encryptedRecord = server.fetchRecord(identifier);
		byte[] RSAEncryptedAESKey = server.getAESKey(client.getRsaPublicKey());
		
		//Decrypt response on client
		client.decryptRecord(encryptedRecord, RSAEncryptedAESKey);
		
		//System.out.println(encryptedRecord);
	//	System.out.println(RSAEncryptedAESKey);

		
		//		System.out.println(client.decryptRecordOnClient(encryptedRecord));
		
	}
	
}
