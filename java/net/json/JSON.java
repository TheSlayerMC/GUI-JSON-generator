package net.json;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.json.stairs.InnerStairs;
import net.json.stairs.OuterStairs;

public class JSON extends JFrame {

	public static final long serialVersionUID = 1L;

	public JTextField modIDField, nameField, texNameField, directoryField;
	public JLabel modID, name, textureName, copy, version, dir;
	public JButton exit, generate, openDir;
	public JCheckBox block, tool, sameTex, reset, stairs, flower, readConfig;
	public boolean showConsole, isBlock, isStairs, isFlower;
	protected BufferedWriter itemModelWriter, blockModelWriter, blockStateWriter, configWriter;
	public static final int WIDTH = 860;
	public static final int HEIGHT = 600;
	public JPopupMenu popup;
	public static JSON instance = new JSON();
	
	public static void main(String[] s) {
		//json j = new json();

	}

	public JSON() {
		//Console c = new Console();
		checkDirectorys();

		block = new JCheckBox("Is block");
		stairs = new JCheckBox("Is the block stairs?");
		stairs.addActionListener(new CheckBoxListener());
		tool = new JCheckBox("Render in 3D? (Things like tools)");
		sameTex = new JCheckBox("Is the texture same as the object name? (If so you don't have to fill out the texture name)");
		reset = new JCheckBox("Reset the Name and Texture after export?");
		flower = new JCheckBox("Is it crossed like a flower?");
		flower.addActionListener(new CheckBoxListener());
		popup = new JPopupMenu("Error!");
		readConfig = new JCheckBox("Read the MODID and the Directory from the config?");
		readConfig.addActionListener(new CheckBoxListener());
		
		ImageIcon icon = new ImageIcon("./icon.png");

		nameField = new JTextField(10);
		modIDField = new JTextField(10);
		texNameField = new JTextField(10);
		directoryField = new JTextField(10);

		exit = new JButton("Exit");
		exit.addActionListener(new ExitHandler());

		generate = new JButton("Generate");
		generate.addActionListener(new ExportHandler());

		openDir = new JButton("Open file directory");
		openDir.addActionListener(new OpenDirHandler());

		name = new JLabel("NAME (The Item or Block name):", SwingConstants.LEFT);
		textureName = new JLabel("TEXTURE NAME:", SwingConstants.LEFT);
		modID = new JLabel("MOD ID (The \"Mod ID\" used in your assets):", SwingConstants.LEFT);
		copy = new JLabel("© 2014 The_SlayerMC", SwingConstants.CENTER);
		version = new JLabel("V 0.0.2", SwingConstants.CENTER);
		dir = new JLabel("Directory (Leave blank if you don't want to change it) I for example would put it \"C:/Users/User/Documents/Essence/main/resources/assets/essence\"", SwingConstants.LEFT);

		Container pane = getContentPane();
		pane.setLayout(new GridLayout(20, 5));
		pane.add(dir);
		pane.add(directoryField);
		pane.add(modID);
		pane.add(modIDField);
		pane.add(name);
		pane.add(nameField);
		pane.add(textureName);
		pane.add(texNameField);
		pane.add(block);
		pane.add(stairs);
		pane.add(flower);
		pane.add(tool);
		pane.add(sameTex);
		pane.add(reset);
		pane.add(readConfig);
		pane.add(generate);
		pane.add(openDir);
		pane.add(exit);
		pane.add(version);
		pane.add(copy);

		setTitle("JSON Generator");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
		setIconImage(icon.getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public class ExportHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String itemModelDir = directoryField.getText().equals("") ? "./models/item/" + nameField.getText() + ".json" : directoryField.getText() + "/models/item/" + nameField.getText() + ".json";
			String blockModelDir = directoryField.getText().equals("") ? "./models/block/" + nameField.getText() + ".json" : directoryField.getText() + "/models/block/" + nameField.getText() + ".json";
			String blockStateDir = directoryField.getText().equals("") ? "./blockstates/" + nameField.getText() + ".json" : directoryField.getText() + "/blockstates/" + nameField.getText() + ".json";

			File itemModel = new File(itemModelDir);
			try {
				if(itemModel.exists()) itemModel.delete();
				itemModel.createNewFile();
				itemModelWriter = new BufferedWriter(new FileWriter(itemModel));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if(!tool.isSelected() && block.isSelected() || stairs.isSelected()) {
				File blockModel = new File(blockModelDir);
				try {
					if(blockModel.exists()) blockModel.delete();
					blockModel.createNewFile();
					blockModelWriter = new BufferedWriter(new FileWriter(blockModel));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			File blockState = new File(blockStateDir);
			try {
				if(blockState.exists()) blockState.delete();
				blockState.createNewFile();
				blockStateWriter = new BufferedWriter(new FileWriter(blockState));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if(sameTex.isSelected() || texNameField.getText().equals(""))
				texNameField = nameField;
			
			if(block.isSelected() && !tool.isSelected()) {
				if(!stairs.isSelected() && !flower.isSelected()) {
					Blocks.instance.getNormalBlocks(modIDField, nameField.getText(), texNameField.getText());
				}

				if(stairs.isSelected()) {
					Blocks.instance.getStairs(modIDField, texNameField.getText(), nameField.getText());
					new OuterStairs(directoryField.getText().equals("") ? "./models/block/" + nameField.getText() + "_outer.json" : directoryField.getText() + "/models/block/" + nameField.getText() + "_outer.json", modIDField.getText().toLowerCase()).init();
					new InnerStairs(directoryField.getText().equals("") ? "./models/block/" + nameField.getText() + "_inner.json" : directoryField.getText() + "/models/block/" + nameField.getText() + "_inner.json", modIDField.getText().toLowerCase()).init();

				}
			}

			else if(!block.isSelected() && tool.isSelected()) {
				Items.instance.getTools(modIDField, nameField.getText());
			}

			if(!block.isSelected() && !tool.isSelected()) {
				Items.instance.getNormalItem(modIDField, texNameField.getText().equals("") ? nameField.getText() : texNameField.getText());
			}

			if(tool.isSelected() && block.isSelected()){
				JOptionPane.showMessageDialog(popup, "Can't have a Block as a Tool aswell...", "ERROR", JOptionPane.ERROR_MESSAGE);
			}

			if(block.isSelected() && !tool.isSelected()) {
				itemModelInit();
				blockModelInit();
				blockStateInit();
			}

			if(!block.isSelected()) {
				itemModelInit();
			}

			if(reset.isSelected()) {
				texNameField.setText("");
				nameField.setText("");
			}

			//if(modIDField.getText() == null || nameField.getText() == null || texNameField.getText() == null)
			//	JOptionPane.showMessageDialog(popup, "Fill out all the fields", "ERROR", JOptionPane.ERROR_MESSAGE);

		}
	}

	public void itemModelInit(){
		try {
			itemModelWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void blockModelInit(){
		try {
			blockModelWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void blockStateInit(){
		try {
			blockStateWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToItemModelFile(String text){
		try {
			itemModelWriter.write(text + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToBlockModelFile(String text){
		try {
			blockModelWriter.write(text + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToBlockStateFile(String text){
		try {
			blockStateWriter.write(text + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToConfig(String text) {
		try {
			configWriter.write(text + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void checkDirectorys() {
		boolean model = new File("./models").mkdir();
		boolean block = new File("./models/block").mkdir();
		boolean item = new File("./models/item").mkdir();
		boolean blockstates = new File("./blockstates").mkdir();

		if(!model || !block || !item || !blockstates) {
			try {
				new File("./models").createNewFile();
				new File("./models/block").createNewFile();
				new File("./models/item").createNewFile();
				new File("./blockstates").createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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

	public class OpenDirHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String dir = directoryField.getText().equals("") ? "./" : directoryField.getText();
			try {
				Desktop.getDesktop().open(new File(dir));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public class ExitHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
	}

	public class CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(stairs.isSelected() || flower.isSelected()) block.setSelected(true);
			if(readConfig.isSelected()) {
				directoryField.setText(getDirectory());
				modIDField.setText(getModID());
			}
		}

		private String getDirectory() {
			return readConfig().substring(10, 72);
		}
		
		private String getModID() {
			return readConfig().substring(79, 90);
		}
	}
}