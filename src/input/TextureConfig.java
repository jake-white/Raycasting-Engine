package input;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import texture.HorizontalTexture;
import texture.Texture;
import texture.TextureLoader;

public class TextureConfig {
	private static File textureConfig = new File("res/configs/textureConfig.txt");
	int textureAmount = 20;
	private static ArrayList<Texture> textures = new ArrayList<Texture>();
	private static HorizontalTexture ceiling;
	private static HorizontalTexture floor;
	public static void load()
	{
		Scanner scan = null;
		try {
			scan = new Scanner(textureConfig);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(scan.hasNextLine())
		{
			String currentParsing = scan.nextLine();
			if(!currentParsing.contains("//"))
			{
			String blockType = currentParsing.substring(0, currentParsing.indexOf(' '));
			String color = currentParsing.substring(currentParsing.indexOf(' ') + 1);
			System.out.println(color);
			String blueGreen = color.substring(color.indexOf(' ') + 1);
			String red = color.substring(0, color.indexOf(' '));
			String green = blueGreen.substring(0, blueGreen.indexOf(' '));
			String blue = blueGreen.substring(blueGreen.indexOf(' ') + 1);
			System.out.println(red + ", " + green + ", " + blue);
			Color blockColor = new Color(Integer.parseInt(red),Integer.parseInt(green), Integer.parseInt(blue));
			textures.add(new Texture(blockType, blockColor));
			}
		}
		ceiling = new HorizontalTexture("ceiling");
		floor = new HorizontalTexture("floor");
		TextureLoader.loadTextures(textures); //doesn't do anything right now
	}
	public static ArrayList<Texture> getTextureArray()
	{
		return textures;
	}
	public static HorizontalTexture getCeiling()
	{
		return ceiling;
	}
	public static HorizontalTexture getFloor()
	{
		return floor;
	}
}
