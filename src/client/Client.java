package client;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import Utils.EncryptionAES;
import Utils.EncryptionRSA;

public class Client {
	
	EncryptionRSA rsa;
	KeyPair rsaKeyPair;
	
	//initialize client and generate
	public Client() throws NoSuchAlgorithmException {
		rsa = new EncryptionRSA();
	}
	
	//Encryption if the identifier to query before sending to SC
	public byte[] encryptIdentifierOnClient(PublicKey pubKey, String id) throws Exception {
		String identifier = id;
		return rsa.encryptRSA(pubKey, identifier);
	}
	
	//Make RSA key pair for client. Used to decrypt AES symmetric key for decrypting the
	//returned record from server
	public PublicKey getRsaPublicKey() throws NoSuchAlgorithmException {	
		rsaKeyPair = rsa.generateRSAKeyPair();
		return rsaKeyPair.getPublic();
	}
	
	//Decrypt RSA encrypted key and decrypt AES encrypted record with decrypted key
	public String decryptRecord(byte[] encRecord, byte[] encSymmetricKey) throws Exception {
		String keyAES = rsa.decryptRSA(rsaKeyPair.getPrivate(), encSymmetricKey);
		EncryptionAES aes = new EncryptionAES();
		return aes.decrypt(encRecord, keyAES);
	}
}
