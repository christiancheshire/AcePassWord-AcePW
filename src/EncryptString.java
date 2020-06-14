import org.jasypt.util.text.StrongTextEncryptor;

public class EncryptString {
	public String encrypt(String password) {
		String encryptedPW;
		StrongTextEncryptor encryptor = new StrongTextEncryptor();
		encryptedPW = encryptor.encrypt(password);
		return encryptedPW;
	}
}
