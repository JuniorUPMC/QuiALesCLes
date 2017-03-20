package cle.serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import cle.serveur.staticvalue.FicStatic;

public class Receiver {
	private CleTracer lesCles;

	public Receiver() {
		lesCles = new CleTracer();
	}

	public Receiver(String path) throws IOException {
		lesCles = new CleTracer(path);
	}

	public void switchOn(int port) {
		try {
			BufferedReader br = null;
			new Thread() {
				public void run() {
					listen(port);
				};
			}.start();
			String line = null;
			do {
				br = new BufferedReader(new InputStreamReader(System.in));
				line = br.readLine();
			} while (!line.equals("quit\n"));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				lesCles.save(FicStatic.FICSAVE);
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public void listen(int port) {
		ServerSocket serverSocket = null;
		try {
			while (true) {
				serverSocket = new ServerSocket(port);
				System.out.println("listening port " + port);
				final Socket socket = serverSocket.accept();
				new Thread() {
					public void run() {
						try {
							handleConnection(socket);
						} catch (IOException e) {
							e.printStackTrace();
						}
					};
				}.start();

			}
		} catch (Exception e) {
			try {
				if (serverSocket != null)
					serverSocket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void handleConnection(Socket socket) throws IOException {
		System.out.println("connected to " + socket.getInetAddress());
		BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String gris = bf.readLine();
		String bleu = bf.readLine();
		majCle(gris, bleu);
		bf.close();
		socket.close();

	}

	public synchronized void majCle(String gris, String bleu) {
		System.out.println("clé bleu : " + bleu + "\nclé grise : " + gris);
		lesCles.setCleBleu(bleu);
		lesCles.setCleGris(gris);
	}
}
