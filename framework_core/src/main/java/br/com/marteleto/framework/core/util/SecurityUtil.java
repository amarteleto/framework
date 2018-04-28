package br.com.marteleto.framework.core.util;


import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class SecurityUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String ENCODE = "UTF-8";
	
	public static String stringEncrypt(String value, String type) {
		if (value != null && !"".equals(value.trim()) && type != null && !"".equals(type.trim())) {
			try {
		    	MessageDigest messageDigest = MessageDigest.getInstance(type);
		    	byte[] bytes = messageDigest.digest(value.getBytes(ENCODE));
		    	StringBuilder stringBuilder = new StringBuilder();
		    	for (byte b : bytes){
		    		stringBuilder.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
		    	}
		    	return stringBuilder.toString();
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
				throw new SecurityException(ex);
			}
		}
		return null;
	}
		
	public static String convertStringToMd5(String value) {
		return SecurityUtil.stringEncrypt(value,"MD5");
	}
	
	public static String convertStringToSHA256(String value) {
		return SecurityUtil.stringEncrypt(value,"SHA-256");
	}
	
	public static String convertStringToSHA512(String value) {
		return SecurityUtil.stringEncrypt(value,"SHA-512");
	}
	
	public static String encryptAES(String value, String key) {
	    String result = null;
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(ENCODE), "AES");
            AlgorithmParameterSpec algorithmParameterSpec = new IvParameterSpec(new byte[16]);
            Cipher cifra = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cifra.init(Cipher.ENCRYPT_MODE, secretKeySpec,algorithmParameterSpec);
            byte[] cifrado = cifra.doFinal(value.getBytes(ENCODE));
            result = Base64.encodeBase64String(cifrado);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | UnsupportedEncodingException ex) {
			throw new SecurityException(ex);
		}
	    return result;
	}
	
	public static String decryptAES(String value, String key) {
	    String result = null;
	    try {
	    	SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(ENCODE), "AES");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(new byte[16]);
            byte[] decoded = Base64.decodeBase64(value.getBytes(ENCODE));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec,paramSpec);
            result = new String(cipher.doFinal(decoded));
	    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException | InvalidAlgorithmParameterException ex) {
	    	throw new SecurityException(ex);
	    }
	    return result;
	}
}
