package net.json;

import java.awt.Container;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Console extends JFrame {

	private static final long serialVersionUID = 1047823744182980810L;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 600;
	public static JTextArea console = new JTextArea();
	public static JScrollPane console1 = new JScrollPane();

	public Console() {
		if(readConfig().contains("showConsole=true")) {
			ImageIcon icon = new ImageIcon("./icon.png");
			Container pane = getContentPane();
			pane.setLayout(new GridLayout(1, 5));
			pane.add(console);
			pack();
			setTitle("JSON Generator");
			setSize(WIDTH, HEIGHT);
			setVisible(true);
			setIconImage(icon.getImage());
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
	}

	public String readConfig(){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("./config.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			StringBuilder sb = new StringBuilder();
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			while(line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				try {
					line = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return sb.toString();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}