package mapping;

import input.Player;
import input.TextureConfig;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import texture.Texture;
import texture.TextureLoader;

public class World {
	BufferedImage png;
	int[][] worldMap; // 0 = air, 1 = something, 2 = insertion
	Color[][] color;
	File fileName;
	Player p;
	int x, y;

	public World(File fileName, Player p) {
		this.fileName = fileName;
		this.p = p;
		int sum = 0;
		try {
			png = ImageIO.read(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.x = png.getWidth();
		this.y = png.getHeight();
		worldMap = new int[x][y];
		color = new Color[x][y];
		for (int i = 0; i < png.getWidth(); ++i) {
			for (int c = 0; c < png.getHeight(); ++c) {
				Color pixel = new Color(png.getRGB(i, c));
				color[i][c] = pixel;
				if (pixel.equals(Color.WHITE)) {
					worldMap[i][c] = 0;
				} else if (pixel.equals(Color.RED)) {
					// player starting point
					worldMap[i][c] = 2;
					this.getPlayer().setInsertion(i, c);
					System.out.println("Player starting at: " + i + ", " + c);
				} else {
					worldMap[i][c] = 1;
					++sum;
				}
			}

		}
		System.out.println(sum + " BLOCKS EXIST.");
	}

	public double getWorldWidth() {
		return x;
	}

	public double getWorldHeight() {
		return y;
	}

	public int getSizeX() {
		return x - 1;
	}

	public int getSizeY() {
		return y - 1;
	}

	public int getTileAt(int i, int c) {
		return worldMap[i][c];
	}

	public BufferedImage getTileTextureAt(double x, double y, boolean horizontal) {
		BufferedImage solidColor = new BufferedImage(1, 64, BufferedImage.TYPE_INT_RGB);
		Color color = getTileColorAt((int) Math.floor(x),
				(int) Math.floor(y));
		ArrayList<Texture> textures = TextureConfig.getTextureArray();
		Texture using = null;
		for (int i = 0; i < textures.size(); ++i) {
			if (color.equals(textures.get(i).getColor())) {
				using = textures.get(i);
			}
		}
		double workingRatio;
		int pixelLocation;
		if (horizontal) {
			workingRatio = x - Math.floor(x);
		} else {
			workingRatio = y - Math.floor(y);
		}
		if (using != null) {
			pixelLocation = (int) Math.floor(workingRatio * using.getWidth());
			return using.getColumnsAt(pixelLocation);
		}
			return null;

	}

	public Color getTileColorAt(int i, int c) {
		return color[i][c];
	}

	public Player getPlayer() {
		return p;
	}
}
