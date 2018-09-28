package protocol;

import client.Client;
import server.Server;

public class ExecuteProtocol {
	private static Server server;
	private static Client client;
	
	public static void main(String [] args) throws Exception {
		//initPreReq();
		
		//Initialize a instance of server and client
		server = new Server();	
		client = new Client();
		
		//Create query for SC
		byte[] identifier = client.encryptIdentifierOnClient(server.getPublicKey());
		System.out.println(identifier);
		
		//Send query to SC
		System.out.println(server.fetchRecord(identifier));
		
	}
	
}
