package Utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

//Class for RSA encryption
public class EncryptionRSA {
	
	public EncryptionRSA() {}
	
	//Generate RSA key pair
	public KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance(Const.INIT_RSA_ENC);
		generator.initialize(Const.RSA_KEY_SIZE);
		return generator.generateKeyPair();
	}
	//Encryption of RSA
	public byte[] encryptRSA(PublicKey pubKey, String message) throws Exception {
        Cipher cipher = getInstaceOfRSA();
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);  
        return cipher.doFinal(message.getBytes());  
    }
	//Decryption of RSA
    public String decryptRSA(PrivateKey privKey, byte [] encMsg) throws Exception {
        Cipher cipher = getInstaceOfRSA();
        cipher.init(Cipher.DECRYPT_MODE, privKey);
        return new String(cipher.doFinal(encMsg));
    }
    
    private Cipher getInstaceOfRSA() throws Exception {
    	return Cipher.getInstance(Const.INIT_RSA_ENC);
    }

}
