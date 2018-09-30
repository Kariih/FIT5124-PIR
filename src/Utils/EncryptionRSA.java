package Utils;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class EncryptionRSA {
	
	public EncryptionRSA() {}
	
	public KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance(Const.INIT_RSA_ENC);
		generator.initialize(Const.RSA_KEY_SIZE);
		return generator.generateKeyPair();
	}
	
	public byte[] encryptRSA(PublicKey pubKey, String message) throws Exception {
        Cipher cipher = getInstaceOfRSA();
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);  
        return cipher.doFinal(message.getBytes());  
    }
	
    public String decryptRSA(PrivateKey privKey, byte [] encMsg) throws Exception {
        Cipher cipher = getInstaceOfRSA();
        cipher.init(Cipher.DECRYPT_MODE, privKey);
        return new String(cipher.doFinal(encMsg));
    }
    
	public byte[] rsaKeyWrapper(PublicKey pubKey, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AESWrap");
        cipher.init(Cipher.WRAP_MODE, pubKey);  
        final byte[] wrapped = cipher.wrap(key);
        return wrapped;
    }
	
    public SecretKey rsaKeyUnwrapper(PrivateKey privKey, byte [] encKey) throws Exception {
        Cipher cipher = getInstaceOfRSA();
        cipher.init(Cipher.UNWRAP_MODE, privKey);
        return (SecretKey) cipher.unwrap(encKey, "AES/GCM/NoPadding", Cipher.SECRET_KEY);
    }
    
    private Cipher getInstaceOfRSA() throws Exception {
    	return Cipher.getInstance(Const.INIT_RSA_ENC);
    }

}
