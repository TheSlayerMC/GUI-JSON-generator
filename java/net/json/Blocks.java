package net.json;

import javax.swing.JTextField;

public class Blocks {

	public static Blocks instance = new Blocks();
	
	public void getNormalBlocks(JTextField modIDField, String name, String name2) {
		writeToItemModelFile("{");
		writeToItemModelFile("	\"parent\": \"" + modIDField.getText().toLowerCase() + ":block/" + name2 + "\",");
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
		writeToBlockModelFile("		\"all\": \"" + modIDField.getText().toLowerCase() + ":blocks/" + name2 + "\"");
		writeToBlockModelFile("	}");
		writeToBlockModelFile("}");

		writeToBlockStateFile("{");
		writeToBlockStateFile("	\"variants\": {");
		writeToBlockStateFile("		\"normal\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "\"}");
		writeToBlockStateFile("	}");
		writeToBlockStateFile("}");
	}
	
	public void getCrossedBlocks(JTextField modIDField, String name) {
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
		writeToBlockModelFile("	\"parent\": \"block/cross\",");
		writeToBlockModelFile("	\"textures\": {");
		writeToBlockModelFile("		\"cross\": \"" + modIDField.getText().toLowerCase() + ":blocks/" + name + "\"");
		writeToBlockModelFile("	}");
		writeToBlockModelFile("}");

		writeToBlockStateFile("{");
		writeToBlockStateFile("	\"variants\": {");
		writeToBlockStateFile("		\"normal\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "\"}");
		writeToBlockStateFile("	}");
		writeToBlockStateFile("}");
	}
	
	public void getStairs(JTextField modIDField, String name, String name2) {
		writeToBlockModelFile("{");
		writeToBlockModelFile("	\"parent\": \"block/stairs\",");
		writeToBlockModelFile("	\"textures\": {");
		writeToBlockModelFile("		\"bottom\": \"" + modIDField.getText().toLowerCase() + ":blocks/" + name + "\",");
		writeToBlockModelFile("		\"top\": \"" + modIDField.getText().toLowerCase() + ":blocks/" + name + "\",");
		writeToBlockModelFile("		\"side\": \"" + modIDField.getText().toLowerCase() + ":blocks/" + name + "\"");
		writeToBlockModelFile("	}");
		writeToBlockModelFile("}");
		
		writeToItemModelFile("{");
		writeToItemModelFile("	\"parent\": \"" + modIDField.getText().toLowerCase() + ":block/" + name2 + "\",");
		writeToItemModelFile("	\"display\": {");
		writeToItemModelFile("		\"thirdperson\": {");
		writeToItemModelFile("			\"rotation\": [ 10, -45, 170 ],");
		writeToItemModelFile("			\"translation\": [ 0, 1.5, -2.75 ],");
		writeToItemModelFile("			\"scale\": [ 0.375, 0.375, 0.375 ]");
		writeToItemModelFile("		},");
		writeToItemModelFile("		\"gui\": {");
		writeToItemModelFile("			\"rotation\": [ 0, 180, 0 ]");
		writeToItemModelFile("		}");
		writeToItemModelFile("	}");
		writeToItemModelFile("}");

		name = name2;
		
		writeToBlockStateFile("{");
		writeToBlockStateFile("	\"variants\": {");
		writeToBlockStateFile("		\"facing=east,half=bottom,shape=straight\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "\" },");
		writeToBlockStateFile("		\"facing=west,half=bottom,shape=straight\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "\", \"y\": 180, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=south,half=bottom,shape=straight\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "\", \"y\": 90, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=north,half=bottom,shape=straight\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "\", \"y\": 270, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=east,half=bottom,shape=outer_right\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_outer\" },");
		writeToBlockStateFile("		\"facing=west,half=bottom,shape=outer_right\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_outer\", \"y\": 180, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=south,half=bottom,shape=outer_right\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_outer\", \"y\": 90, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=north,half=bottom,shape=outer_right\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_outer\", \"y\": 270, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=east,half=bottom,shape=outer_left\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_outer\", \"y\": 270, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=west,half=bottom,shape=outer_left\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_outer\", \"y\": 90, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=south,half=bottom,shape=outer_left\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_outer\" },");
		writeToBlockStateFile("		\"facing=north,half=bottom,shape=outer_left\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_outer\", \"y\": 180, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=east,half=bottom,shape=inner_right\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_inner\" },");
		writeToBlockStateFile("		\"facing=west,half=bottom,shape=inner_right\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_inner\", \"y\": 180, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=south,half=bottom,shape=inner_right\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_inner\", \"y\": 90, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=north,half=bottom,shape=inner_right\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_inner\", \"y\": 270, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=east,half=bottom,shape=inner_left\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_inner\", \"y\": 270, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=west,half=bottom,shape=inner_left\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_inner\", \"y\": 90, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=south,half=bottom,shape=inner_left\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_inner\" },");
		writeToBlockStateFile("		\"facing=north,half=bottom,shape=inner_left\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_inner\", \"y\": 180, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=east,half=top,shape=straight\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "\", \"x\": 180, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=west,half=top,shape=straight\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "\", \"x\": 180, \"y\": 180, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=south,half=top,shape=straight\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "\", \"x\": 180, \"y\": 90, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=north,half=top,shape=straight\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "\", \"x\": 180, \"y\": 270, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=east,half=top,shape=outer_right\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_outer\", \"x\": 180, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=west,half=top,shape=outer_right\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_outer\", \"x\": 180, \"y\": 180, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=south,half=top,shape=outer_right\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_outer\", \"x\": 180, \"y\": 90, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=north,half=top,shape=outer_right\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_outer\", \"x\": 180, \"y\": 270, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=east,half=top,shape=outer_left\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_outer\", \"x\": 180, \"y\": 90, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=west,half=top,shape=outer_left\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_outer\", \"x\": 180, \"y\": 270, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=south,half=top,shape=outer_left\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_outer\", \"x\": 180, \"y\": 180, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=north,half=top,shape=outer_left\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_outer\", \"x\": 180, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=east,half=top,shape=inner_right\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_inner\", \"x\": 180, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=west,half=top,shape=inner_right\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_inner\", \"x\": 180, \"y\": 180, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=south,half=top,shape=inner_right\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_inner\", \"x\": 180, \"y\": 90, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=north,half=top,shape=inner_right\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_inner\", \"x\": 180, \"y\": 270, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=east,half=top,shape=inner_left\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_inner\", \"x\": 180, \"y\": 90, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=west,half=top,shape=inner_left\":  { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_inner\", \"x\": 180, \"y\": 270, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=south,half=top,shape=inner_left\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_inner\", \"x\": 180, \"y\": 180, \"uvlock\": true },");
		writeToBlockStateFile("		\"facing=north,half=top,shape=inner_left\": { \"model\": \"" + modIDField.getText().toLowerCase() + ":" + name + "_inner\", \"x\": 180, \"uvlock\": true }");
		writeToBlockStateFile("	}");
		writeToBlockStateFile("}");
	}
	
	public void writeToBlockStateFile(String s) {
		JSON.instance.writeToBlockStateFile(s);
	}
	
	public void writeToBlockModelFile(String s) {
		JSON.instance.writeToBlockModelFile(s);
	}
	
	public void writeToItemModelFile(String s) {
		JSON.instance.writeToItemModelFile(s);
	}
}