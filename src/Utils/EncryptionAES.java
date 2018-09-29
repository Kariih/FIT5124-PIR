package Utils;

import java.nio.ByteBuffer;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionAES {
	
	private SecureRandom secureRandom;
	
	public EncryptionAES() {
		secureRandom = new SecureRandom();

	}
	public SecretKey generateKeyAES() {
		byte[] key = new byte[16];
		secureRandom.nextBytes(key);
		return new SecretKeySpec(key, 0, 16, "AES");
		
	}
	public byte[] encryptAES (String record, SecretKey key) throws Exception{
		byte[] iv = new byte[12];
		secureRandom.nextBytes(iv);	
		final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);
		cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
		
		byte[] cipherText = cipher.doFinal(record.getBytes());
		
		ByteBuffer byteBuffer = ByteBuffer.allocate(4 + iv.length + cipherText.length);
		byteBuffer.putInt(iv.length);
		byteBuffer.put(iv);
		byteBuffer.put(cipherText);
		return byteBuffer.array();		
	}

}
