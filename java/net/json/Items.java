package net.json;

import javax.swing.JTextField;

public class Items {

	public static Items instance = new Items();
	
	public void getTools(JTextField modIDField, String name) {
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
	
	public void getNormalItem(JTextField modIDField, String name) {
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
	
	public void writeToItemModelFile(String s) {
		JSON.instance.writeToItemModelFile(s);
	}
}