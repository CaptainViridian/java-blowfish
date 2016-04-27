package coisa.main;

import java.io.File;
import java.util.Scanner;

import coisa.encryption.Encryption;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application implements Runnable {

	private final String PATH = "/tmp/";

	public Main() {
		File root = new File(PATH);

		if (!root.exists()) {
			root.mkdirs();
		}
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		launch();
		System.out.println("Chave: ");
		Encryption.setKeyString(new Scanner(System.in).nextLine());
		new Main().run();
	}

	public void run() {
		Scanner input = new Scanner(System.in);
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
		String filePath = PATH + fileName;
		String cipheredFilePath = PATH + "ciphered_" + fileName;

		Encryption.encrypt(filePath, cipheredFilePath);
	}

	private void decryptFile(String fileName) throws Exception {
		String filePath = PATH + fileName;
		String decipheredFilePath = PATH + fileName.replace("ciphered", "deciphered");

		Encryption.decrypt(filePath, decipheredFilePath);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane mainPane = new StackPane();
		Label msg = new Label("olá");

		mainPane.getChildren().add(msg);

		Scene scene = new Scene(mainPane, 250, 220);
		primaryStage.setTitle("Olá");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
