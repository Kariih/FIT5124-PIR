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
		
		//Encrypt existing record with AES and shuffle the indexes
		server.encryptRecords();
		
		//Create query for secure coprocessor to handle, and encrypt the identifier
		//on client side with secure coprocessor's RSA public key
		String queryName = "John";
		System.out.println("Named to be queried: " + queryName);
		byte[] identifier = client.encryptIdentifierOnClient(server.getPublicKey(), queryName);
		
		//Send query to the secure coprocessor and returned the AES encrypted response
		//and send client's public key to secure coprocessor.
		byte[] encryptedRecord = server.fetchRecord(identifier);
		byte[] RSAEncryptedAESKey = server.getAESKey(client.getRsaPublicKey());
		
		//Decrypt response on client based on AES symmetric key
		String record = client.decryptRecord(encryptedRecord, RSAEncryptedAESKey);
		System.out.println("Record found on Server: " + record);
	}
	
}
