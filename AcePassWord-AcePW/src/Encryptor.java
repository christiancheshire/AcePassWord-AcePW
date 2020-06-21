import org.jasypt.util.text.StrongTextEncryptor;

/*
 * Copyright 2020
 * 3 Musketeers Tech
 * Encryptor class with imports from Jasypt 1.9.2
 */
public class Encryptor {
	StrongTextEncryptor encryptor = new StrongTextEncryptor();
	
	/*
	 * Encrypts the password to be saved in local data.
	 */
	public String encrypt(String password) {
		String toEncrypt;
		toEncrypt = encryptor.encrypt(password);
		return toEncrypt;
	}
	
	/*
	 * Decrypts the password saved in local data, given the
	 * encrypted text
	 */
	public String decrypt(String encryptedText) {
		String toDecrypt;
		toDecrypt = encryptor.decrypt(encryptedText);
		return toDecrypt;
	}
}
