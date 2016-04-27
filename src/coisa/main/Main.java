package coisa.main;

import java.util.Scanner;

import coisa.encryption.Encryption;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application implements Runnable {

	private String path;

	private StackPane mainPane = new StackPane();

	private Button encryptBt = new Button("Criptografar");
	private Button decryptBt = new Button("Desriptografar");

	public static void main(String[] args) {
		launch();
	}

	@Deprecated
	public void run() {
		Scanner input = new Scanner(System.in);
		System.out.println("Chave: ");
		Encryption.setKeyString(input.nextLine());
		try {
			while (true) {
				System.out.print(">");
				String order = input.nextLine();

				String[] stuff = order.split(" ");

				if (stuff[0].equals("encrypt")) {
					String fileName = stuff[1];
					encryptFile(fileName);
				} else if (stuff[0].equals("decrypt")) {
					String fileName = stuff[1];
					decryptFile(fileName);
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			input.close();
		}
	}

	private void encryptFile(String fileName) throws Exception {
		String filePath = path + fileName;
		String cipheredFilePath = path + "ciphered_" + fileName;

		Encryption.encrypt(filePath, cipheredFilePath);
	}

	private void decryptFile(String fileName) throws Exception {
		String filePath = path + fileName;
		String decipheredFilePath = path + fileName.replace("ciphered", "deciphered");

		Encryption.decrypt(filePath, decipheredFilePath);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(mainPane, 500, 300);

		setupButtons();

		primaryStage.setTitle("Olá");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void setupButtons() {
		mainPane.getChildren().add(encryptBt);
		mainPane.getChildren().add(decryptBt);
	}
}
