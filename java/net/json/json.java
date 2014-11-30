package net.json;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class json extends JFrame {

	public static final long serialVersionUID = 1L;

	public JTextField modIDField, nameField, texNameField;
	public JLabel modID, name, textureName, copy, version;
	public JButton exit, generate;
	public JCheckBox block, tool, sameTex;
	public boolean isBlock;
	protected BufferedWriter itemModelWriter, blockModelWriter, blockStateWriter;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static String nameOfFile;
	public static JPopupMenu popup;
	public static json instance = new json();

	public static void main(String[] s) {
		//json j = new json();
	}

	public json() {
		block = new JCheckBox("Is Block");
		tool = new JCheckBox("Render in 3D? (Things like tools)");
		sameTex = new JCheckBox("Is the texture same as the object name? (If so you don't have to fill out the texture name)");
		popup = new JPopupMenu("Error!");
		
		nameField = new JTextField(10);
		modIDField = new JTextField(10);
		texNameField = new JTextField(10);

		exit = new JButton("EXIT");
		exit.addActionListener(new ExitHandler());

		generate = new JButton("GENERATE");
		generate.addActionListener(new ExportHandler());

		name = new JLabel("NAME:", SwingConstants.LEFT);
		textureName = new JLabel("TEXTURE NAME:", SwingConstants.LEFT);
		modID = new JLabel("MOD ID:", SwingConstants.LEFT);
		copy = new JLabel("© 2014 The_SlayerMC", SwingConstants.CENTER);
		version = new JLabel("V 0.0.1", SwingConstants.CENTER);

		Container pane = getContentPane();
		pane.setLayout(new GridLayout(15, 1));
		pane.add(modID);
		pane.add(modIDField);
		pane.add(name);
		pane.add(nameField);
		pane.add(textureName);
		pane.add(texNameField);
		pane.add(block);
		pane.add(tool);
		pane.add(sameTex);
		pane.add(generate);
		pane.add(exit);
		pane.add(version);
		pane.add(copy);

		setTitle("JSON Generator");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public class ExportHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			File itemModel = new File("./model/item/" + nameField.getText() + ".json");
			try {
				if(itemModel.exists()) itemModel.delete();
				itemModel.createNewFile();
				itemModelWriter = new BufferedWriter(new FileWriter(itemModel));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if(block.isSelected()) {
				File blockModel = new File("./model/block/" + nameField.getText() + ".json");
				try {
					if(blockModel.exists()) blockModel.delete();
					blockModel.createNewFile();
					blockModelWriter = new BufferedWriter(new FileWriter(blockModel));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			File blockState = new File("./blockstates/" + nameField.getText() + ".json");
			try {
				if(blockState.exists()) blockState.delete();
				blockState.createNewFile();
				blockStateWriter = new BufferedWriter(new FileWriter(blockState));
			} catch (IOException e) {
				e.printStackTrace();
			}

			boolean isTexSame = sameTex.isSelected();
			String name = isTexSame ? nameField.getText() : texNameField.getText();
			
			if(block.isSelected() && !tool.isSelected()) {
				writeToItemModelFile("{");
				writeToItemModelFile("	\"parent\": \"" + modIDField.getText().toLowerCase() + ":block/" + name + "\",");
				writeToItemModelFile("	\"display\": {");
				writeToItemModelFile("		\"thirdperson\": {");
				writeToItemModelFile("			\"rotation\": [ 10, -45, 170 ],");
				writeToItemModelFile("			\"translation\": [ 0, 1.5, -2.75 ],");
				writeToItemModelFile("			\"scale\": [ 0.375, 0.375, 0.375 ]");
				writeToItemModelFile("		}");
				writeToItemModelFile("	}");
				writeToItemModelFile("}");

				writeToBlockModelFile("{");
				writeToBlockModelFile("	\"parent\": \"block/cube_all\",");
				writeToBlockModelFile("	\"textures\": {");
				writeToBlockModelFile("		\"all\": \"" + modIDField.getText().toLowerCase() + ":blocks/" + name + "\"");
				writeToBlockModelFile("	}");
				writeToBlockModelFile("}");

				writeToBlockStateFile("{");
				writeToBlockStateFile("	\"variants\": {");
				writeToBlockStateFile("		\"normal\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "\"}");
				writeToBlockStateFile("	}");
				writeToBlockStateFile("}");
			} 

			else if(!block.isSelected() && tool.isSelected()) {
				writeToItemModelFile("{");
				writeToItemModelFile("	\"parent\": \"builtin/generated\",");
				writeToItemModelFile("	\"textures\": {");
				writeToItemModelFile("		\"layer0\": \"" + modIDField.getText().toLowerCase() + ":items/" + name + "\"");
				writeToItemModelFile("	},");
				writeToItemModelFile("	\"display\": {");
				writeToItemModelFile("		\"thirdperson\": {");
				writeToItemModelFile("			\"rotation\": [ 0, 90, -35 ],");
				writeToItemModelFile("			\"translation\": [ 0, 1.25, -3.5 ],");
				writeToItemModelFile("			\"scale\": [ 0.85, 0.85, 0.85 ]");
				writeToItemModelFile("		},");
				writeToItemModelFile("		\"firstperson\": {");
				writeToItemModelFile("			\"rotation\": [ 0, -135, 25 ],");
				writeToItemModelFile("			\"translation\": [ 0, 4, 2 ],");
				writeToItemModelFile("			\"scale\": [ 1.7, 1.7, 1.7 ]");
				writeToItemModelFile("		}");
				writeToItemModelFile("	}");
				writeToItemModelFile("}");
			}

			if(!block.isSelected() && !tool.isSelected()) {
				writeToItemModelFile("{");
				writeToItemModelFile("	\"parent\": \"builtin/generated\",");
				writeToItemModelFile("	\"textures\": {");
				writeToItemModelFile("		\"layer0\": \"" + modIDField.getText().toLowerCase() + ":items/" + name + "\"");
				writeToItemModelFile("	},");
				writeToItemModelFile("	\"display\": {");
				writeToItemModelFile("		\"thirdperson\": {");
				writeToItemModelFile("			\"rotation\": [ -90, 0, 0 ],");
				writeToItemModelFile("			\"translation\": [ 0, 1, -3 ],");
				writeToItemModelFile("			\"scale\": [ 0.55, 0.55, 0.55 ]");
				writeToItemModelFile("		},");
				writeToItemModelFile("		\"firstperson\": {");
				writeToItemModelFile("			\"rotation\": [ 0, -135, 25 ],");
				writeToItemModelFile("			\"translation\": [ 0, 4, 2 ],");
				writeToItemModelFile("			\"scale\": [ 1.7, 1.7, 1.7 ]");
				writeToItemModelFile("		}");
				writeToItemModelFile("	}");
				writeToItemModelFile("}");
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

			//if(modIDField.getText() == null || nameField.getText() == null || texNameField.getText() == null)
			//	JOptionPane.showMessageDialog(popup, "Fill out all the fields", "ERROR", JOptionPane.ERROR_MESSAGE);

		}

		public void addItemModel() {

		}

		public void addBlockModel() {

		}

		public void addBlockState() {

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
	}

	public class ExitHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
	}
}