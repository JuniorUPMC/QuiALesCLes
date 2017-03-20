package cle.serveur.main;

import java.io.IOException;

import cle.serveur.Receiver;
import cle.serveur.staticvalue.ServerValue;

public class MainServer {

	public static void main(String[] args) {
		Receiver receiver=null;
		if (args.length == 1)
			try {
				receiver = new Receiver(args[0]);
			} catch (IOException e) {
				System.err.println(e.getMessage());
				return;
			}
		else
			receiver = new Receiver();
		
		receiver.switchOn(ServerValue.PORT);
	}

}
