package spring.boot.exemples;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class PersonListener {

	@PrePersist
	@PreUpdate
	public void personPreSave(Person pPerson) {

		try {

			CryptographicHelper lCryptographicHelper = new CryptographicHelper();

			pPerson.setPassword(lCryptographicHelper.encrypt(pPerson.getPassword()));

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

	}

	@PostLoad
	public void personPostLoad(Person pPerson) {

		try {

			CryptographicHelper lCryptographicHelper = new CryptographicHelper();

			pPerson.setPassword(lCryptographicHelper.decrypt(pPerson.getPassword()));

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

	}

	private class CryptographicHelper {

		private final String ALGO = "AES";
		private final byte[] keyValue = new byte[] { 'b', 'a', '2', 'c', '5', 'b', 'f', 'e', 'e', '1', 'e', '2', 'd',
				'd', 'c', 'c' };

		public String encrypt(String Data) throws Exception {
			Key key = generateKey();
			Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] encVal = c.doFinal(Data.getBytes());
			String encryptedValue = new BASE64Encoder().encode(encVal);
			return encryptedValue;
		}

		public String decrypt(String encryptedData) throws Exception {
			Key key = generateKey();
			Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.DECRYPT_MODE, key);
			byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
			byte[] decValue = c.doFinal(decordedValue);
			String decryptedValue = new String(decValue);
			return decryptedValue;
		}

		private Key generateKey() throws Exception {

			Key key = new SecretKeySpec(keyValue, ALGO);
			return key;
		}
	}

}
