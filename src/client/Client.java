package client;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import Utils.EncryptionRSA;
import Utils.GenerateRsaKeys;

public class Client {
	
	EncryptionRSA rsa = null;
	GenerateRsaKeys rsaKeys = null;
	KeyPair rsaKeyPar = null;
	
	public static byte[] encMessage = null;
	
	public Client() throws NoSuchAlgorithmException {
		rsa = new EncryptionRSA();
		rsaKeys = new GenerateRsaKeys();
	}
	
	public byte[] encryptIdentifierOnClient(PublicKey pubKey) throws Exception {
		String identifier = "Mary";
		EncryptionRSA rsa = new EncryptionRSA();
		return rsa.encryptRSA(pubKey, identifier);
	}
	
	public PublicKey getRsaPublicKey() throws NoSuchAlgorithmException {	
		rsaKeyPar = rsa.generateRSAKeyPair();
		return rsaKeyPar.getPublic();
	}
	
	public String decryptRecordOnClient(byte[] encRecord) throws Exception {
		return rsa.decryptRSA(rsaKeyPar.getPrivate(), encRecord);
	}
	
}
