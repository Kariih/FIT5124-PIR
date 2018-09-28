package sc;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import Utils.EncryptionRSA;
import Utils.GenerateCryptoKeys;

public class SC {
	
	GenerateCryptoKeys cryptoKeys = null;
	public SC() {}
	
	public PublicKey getRSAKey() throws NoSuchAlgorithmException {
		cryptoKeys = new GenerateCryptoKeys();
		return cryptoKeys.getPubKey();
	}
	public String decryptRSAMessage(byte[] message) throws Exception {
		EncryptionRSA rsa = new EncryptionRSA();
		return rsa.decryptRSA(cryptoKeys.getPrivateKey(), message);
	}

}
