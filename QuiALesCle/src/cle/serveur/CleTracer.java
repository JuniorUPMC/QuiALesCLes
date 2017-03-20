package cle.serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CleTracer {
	private String cleGris;
	private String cleBleu;

	public CleTracer() {
		cleGris = "personne";
		cleBleu = "personne";
	}

	public CleTracer(String path) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		cleGris = br.readLine();
		cleBleu = br.readLine();
		br.close();
	}

	public String getCleGris() {
		return cleGris;
	}

	public String getCleBleu() {
		return cleBleu;
	}
	
	public void save(String path) throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
		bw.write(cleGris+"\n"+cleBleu);
		bw.close();
	}
	
	public String toSend(){
		return cleGris+"\n"+cleBleu+"\n";
	}

	public void setCleGris(String cleGris) {
		this.cleGris = cleGris;
	}

	public void setCleBleu(String cleBleu) {
		this.cleBleu = cleBleu;
	}
}
