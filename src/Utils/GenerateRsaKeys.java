package Utils;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class GenerateRsaKeys {
	
	private PrivateKey privKey = null;
	public PublicKey pubKey = null;
	EncryptionRSA rsa = null;

	public GenerateRsaKeys() throws NoSuchAlgorithmException{
		rsa = new EncryptionRSA();
		KeyPair key = rsa.generateRSAKeyPair();
		privKey = key.getPrivate();
		pubKey = key.getPublic();
	}
	
	public PublicKey getPubKey() {
		return this.pubKey;
	}
	public PrivateKey getPrivateKey() {
		return this.privKey;
	}
}
