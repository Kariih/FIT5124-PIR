package Utils;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

//Class for AES encryption in java is taken from: 
//https://howtodoinjava.com/security/java-aes-encryption-example/

public class EncryptionAES {

    private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public void setKey(String myKey) throws Exception{
        MessageDigest sha = null;
        key = myKey.getBytes(Const.ENCODING);
        sha = MessageDigest.getInstance(Const.INIT_MSG_DIGEST);
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        secretKey = new SecretKeySpec(key, Const.INIT_AES_ENC);
    }
 
    public byte[] encrypt(String strToEncrypt, String secret) throws Exception{
        setKey(secret);
        Cipher cipher = Cipher.getInstance(Const.AES_INSTANCE_SETTINGS);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(strToEncrypt.getBytes(Const.ENCODING));
    }
 
    public String decrypt(byte[] strToDecrypt, String secret) throws Exception{
        setKey(secret);
        Cipher cipher = Cipher.getInstance(Const.AES_INSTANCE_SETTINGS);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(strToDecrypt));
    }
}
