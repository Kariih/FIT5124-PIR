package sc;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import Utils.EncryptAES2;
import Utils.EncryptionRSA;
import server.Records;

//class to present the processes in the secure coprocessor
public class SC {
	
	//secret key used for AES encryption
	private final String AES_SECRET_KEY = "testkey";
	
	//private GenerateRsaKeys cryptoKeys;
	private KeyPair rsaKeyPair;
	private EncryptAES2 aes;
	private EncryptionRSA rsa;
	private SwapRecordsCreateLookup swap;
	private Records records;
	

	public SC() {
		aes = new EncryptAES2();
		rsa = new EncryptionRSA();
		records = new Records();
	}
	
	//Take the records from server, swap them and encrypt with AES
	public void encodeRecordsAndSwap() throws Exception {
		records = new Records();
		swap = new SwapRecordsCreateLookup();
		records.setRecords(swap.swapArray(records.getRecords()));
		for (String record: records.getRecords()) {
			records.setEncRecords(aes.encrypt(record, AES_SECRET_KEY));
		}	
	}
	
	//Create a RSA key pair for SC and sending the public key to server
	public PublicKey getRSAKey() throws NoSuchAlgorithmException {
		rsaKeyPair = rsa.generateRSAKeyPair();
		return rsaKeyPair.getPublic();
	}
	
	//Decrypt the query before looking up and find the record encrypted on server
	public byte[] decryptRSAMessageAndFindRecord(byte[] encIdentifier) throws Exception {
		String identifier = rsa.decryptRSA(rsaKeyPair.getPrivate(), encIdentifier);
		int indexOfIdentifier = swap.getIndexOfIdentifier(identifier);
		System.out.println("Record for " + identifier + " found at index: " + indexOfIdentifier);
		return records.getRecordFromIndex(indexOfIdentifier);
		
	}
	//encrypt symmetric key before sending to client for decryption
	public byte[] encryptAndReturnSymmetricKey(PublicKey clientRSAKey) throws Exception {
		return rsa.encryptRSA(clientRSAKey, AES_SECRET_KEY);
	}
}
