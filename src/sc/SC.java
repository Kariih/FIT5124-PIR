package sc;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.SecretKey;

import Utils.EncryptionAES;
import Utils.EncryptionRSA;
import Utils.GenerateRsaKeys;
import server.Records;

public class SC {
	
	GenerateRsaKeys cryptoKeys;
	EncryptionAES aes;
	EncryptionRSA rsa;
	SecretKey keyAES;
	
	public SC() {
		aes = new EncryptionAES();
		rsa = new EncryptionRSA();
	}
	
	public void encodeRecordsWithAES() throws Exception {
		Records records = new Records();
		keyAES = aes.generateKeyAES();
		for (String record: records.getRecords()) {
			System.out.println(aes.encryptAES(record, keyAES));
		}	
	}
	
	public PublicKey getRSAKey() throws NoSuchAlgorithmException {
		cryptoKeys = new GenerateRsaKeys();
		return cryptoKeys.getPubKey();
	}
	public String decryptRSAMessage(byte[] message) throws Exception {
		return rsa.decryptRSA(cryptoKeys.getPrivateKey(), message);
	}
	public byte[] encryptRecordOnServer(String record, PublicKey pubKey) throws Exception {
		return rsa.encryptRSA(pubKey, record.toString());
	}

}
