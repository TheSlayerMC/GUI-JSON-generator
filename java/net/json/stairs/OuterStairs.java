package net.json.stairs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.json.JSON;

public class OuterStairs {

	protected String  modID;
	protected String path, texture = JSON.instance.texNameField.getText();
	protected BufferedWriter writer;

	public OuterStairs(String path, String modID) {
		this.modID = modID;
		this.path = path;
		File file = null;
		file = new File(path);
		try {
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			writer = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void init(){
		addNames();
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToFile(String text){
		try {
			writer.write(text + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addNames() {
		writeToFile("{");
		writeToFile("	\"parent\": \"block/stairs\",");
		writeToFile("	\"textures\": {");
		writeToFile("		\"bottom\": \"" + modID + ":blocks/" + texture + "\",");
		writeToFile("		\"top\": \"" + modID + ":blocks/" + texture + "\",");
		writeToFile("		\"side\": \"" + modID + ":blocks/" + texture + "\"");
		writeToFile("	}");
		writeToFile("}");
	}
}