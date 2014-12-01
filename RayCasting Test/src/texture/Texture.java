package texture;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
	String blockType;
	Color blockColor;
	BufferedImage img;
	BufferedImage[] columns;

	public Texture(String blockType, Color blockColor) {
		this.blockType = blockType;
		this.blockColor = blockColor;
		System.out.println(blockType);
		try {
			img = ImageIO.read(new File("res/textures/" + blockType + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		columns = new BufferedImage[img.getWidth()];
		calcColumns();
	}

	public void calcColumns() {
		for (int i = 0; i < img.getWidth(); ++i) {
			BufferedImage correctColumn = img.getSubimage(i, 0, 1,
					img.getHeight());
			
			columns[i] = correctColumn;
		}
	}

	public BufferedImage getColumnsAt(int location) {
		return columns[location];
	}

	public int getWidth() {
		return img.getWidth();
	}

	public int getHeight() {
		return img.getHeight();
	}

	public Color getColor() {
		return blockColor;
	}
}
