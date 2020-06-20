import org.jasypt.util.text.StrongTextEncryptor;

/*
 * Copyright 2020
 * 3 Musketeers Tech
 * Encryptor class with imports from Jasypt 1.9.2
 */
public class Encryptor {
	/*
	 * Encrypts the password to be saved in local data.
	 */
	public String encrypt(String password) {
		String toEncrypt;
		StrongTextEncryptor encryptor = new StrongTextEncryptor();
		toEncrypt = encryptor.encrypt(password);
		return toEncrypt;
	}
	
	/*
	 * Decrypts the password saved in local data, given the username for
	 * that particular account.
	 */
	public String decrypt(String username) {
		String toDecrypt;
		StrongTextEncryptor decryptor = new StrongTextEncryptor();
		toDecrypt = decryptor.decrypt(username);
		return toDecrypt;
	}
}
