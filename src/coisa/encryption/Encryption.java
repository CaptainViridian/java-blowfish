package coisa.encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

	private static final String ALGORITHM = "Blowfish";

	private static String keyString;

	public static String encrypt(String entradaArquivo, String saidaArquivo) throws Exception {
		return encrypt(new File(entradaArquivo), new File(saidaArquivo));
	}

	public static String encrypt(File entradaArquivo, File saidaArquivo) throws Exception {
		return doCrypto(Cipher.ENCRYPT_MODE, entradaArquivo, saidaArquivo);
	}

	public static String decrypt(String entradaArquivo, String saidaArquivo) throws Exception {
		return decrypt(new File(entradaArquivo), new File(saidaArquivo));
	}

	public static String decrypt(File entradaArquivo, File saidaArquivo) throws Exception {
		return doCrypto(Cipher.DECRYPT_MODE, entradaArquivo, saidaArquivo);
	}

	private static String doCrypto(int cipherMode, File entradaArquivo, File saidaArquivo) throws Exception {
		Key secretKey = new SecretKeySpec(keyString.getBytes(), ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(cipherMode, secretKey);

		FileInputStream inputStream = new FileInputStream(entradaArquivo);
		byte[] entradaBytes = new byte[(int) entradaArquivo.length()];
		inputStream.read(entradaBytes);

		byte[] outputBytes = cipher.doFinal(entradaBytes);

		FileOutputStream outputStream = new FileOutputStream(saidaArquivo);
		outputStream.write(outputBytes);

		inputStream.close();
		outputStream.close();

		return new String(outputBytes);
	}

	public static String getKeyString() {
		return keyString;
	}

	public static void setKeyString(String keyString) {
		Encryption.keyString = keyString;
	}

}