package client;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import Utils.EncryptAES2;
import Utils.EncryptionRSA;
import Utils.GenerateRsaKeys;

public class Client {
	
	EncryptionRSA rsa = null;
	GenerateRsaKeys rsaKeys = null;
	KeyPair rsaKeyPair = null;
	
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
		rsaKeyPair = rsa.generateRSAKeyPair();
		return rsaKeyPair.getPublic();
	}
	
	public String decryptRecordOnClient(byte[] encRecord) throws Exception {
		return rsa.decryptRSA(rsaKeyPair.getPrivate(), encRecord);
	}
	public void decryptRecord(byte[] encRecord, byte[] encSymmetricKey) throws Exception {
		String keyAES = rsa.decryptRSA(rsaKeyPair.getPrivate(), encSymmetricKey);
		EncryptAES2 aes = new EncryptAES2();
		String record = aes.decrypt(encRecord, keyAES);
		System.out.println(record);
	}
}
