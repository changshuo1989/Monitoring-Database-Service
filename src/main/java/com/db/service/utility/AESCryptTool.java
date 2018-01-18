package com.db.service.utility;

import java.security.Key;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;



public class AESCryptTool {
	
	private static final String ALGORITHM = "AES";
	private static final String KEY = "f520";
	
	private static final int SALT_SEED = 12;
	
	
	public static String encrypt(String plainText, String salt) throws Exception{
		Key key = generateKey(salt);
		Cipher cipher = Cipher.getInstance(AESCryptTool.ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte [] encryptedByteValue = cipher.doFinal(plainText.getBytes("utf-8"));
		String encryptedValue64 = Base64.encodeBase64String(encryptedByteValue);
		return encryptedValue64;
	}
	
	public static String decrypt(String encreptedText, String salt) throws Exception{
		Key key = generateKey(salt);
		Cipher cipher = Cipher.getInstance(AESCryptTool.ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedValue64 = Base64.decodeBase64(encreptedText.getBytes());
		byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
		String decryptedValue = new String(decryptedByteValue,"utf-8");
		return decryptedValue;
	}
	
	
	public static String generateSalt() throws Exception{
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < SALT_SEED) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
	}
	
    private static Key generateKey(String salt) throws Exception 
    {
    	String realKey = AESCryptTool.KEY+salt;
        Key key = new SecretKeySpec(realKey.getBytes(),AESCryptTool.ALGORITHM);
        return key;
    }
}
