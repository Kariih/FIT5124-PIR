package sc;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import Utils.EncryptAES2;
import Utils.EncryptionRSA;
import Utils.GenerateRsaKeys;
import server.Records;

public class SC {
	
	private final String AES_SECRET_KEY = "testkey";
	
	private GenerateRsaKeys cryptoKeys;
	private EncryptAES2 aes;
	private EncryptionRSA rsa;
	private SecretKey keyAES;
	private SwapRecordsCreateLookup swap;
	private Records records;
	
	public SC() {
		aes = new EncryptAES2();
		rsa = new EncryptionRSA();
		records = new Records();
	}
	
	public void encodeRecordsAndSwap() throws Exception {
		records = new Records();
		swap = new SwapRecordsCreateLookup();
		records.setRecords(swap.swapArray(records.getRecords()));
		for (String record: records.getRecords()) {
			records.setEncRecords(aes.encrypt(record, AES_SECRET_KEY));
		}	
	}
	
	public PublicKey getRSAKey() throws NoSuchAlgorithmException {
		cryptoKeys = new GenerateRsaKeys();
		return cryptoKeys.getPubKey();
	}
	public byte[] decryptRSAMessageAndFindRecord(byte[] message) throws Exception {
		String identifier = rsa.decryptRSA(cryptoKeys.getPrivateKey(), message);
		return records.getRecordFromIndex(swap.getIndexOfIdentifier(identifier));
		
	}
	public byte[] encryptAndReturnSymmetricKey(PublicKey clientRSAKey) throws Exception {
		return rsa.encryptRSA(clientRSAKey, AES_SECRET_KEY);
	}
}
