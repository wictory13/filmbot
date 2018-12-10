package bot;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import dialog.Dialog;
import dialog.Phrases;
import storage.APIHandler;

import storage.FilmRatingsDatabase;

import structures.User;
import utils.UserUtils;

public class ChatBot {
	private APIHandler apiDatabase;
	private FilmRatingsDatabase ratingsDatabase;

	public ChatBot(APIHandler apiDatabase, FilmRatingsDatabase ratingsDatabase) throws Exception {
		this.apiDatabase = apiDatabase;
		this.ratingsDatabase = ratingsDatabase;
	}

	public void startChat(InputStream inputStream, OutputStream outputStream) throws Exception {

		Scanner scan = new Scanner(inputStream);
		PrintStream printStream = new PrintStream(outputStream);

		printStream.println(Phrases.HELLO);
		String name = scan.nextLine();

		User user = UserUtils.getUser(name, name);
        
		Dialog dialog = new Dialog(user, apiDatabase, ratingsDatabase);

		printStream.println(dialog.startDialog());
		try {
			while (true) {
				String req = scan.nextLine();
				if ("/exit".equals(req)) {
					UserUtils.saveUser(user);
					break;
				}
				String answer = dialog.processInput(req);
				printStream.println(answer);
			}
		} finally {
			scan.close();
		}
	}

}
