package rendering;

import input.TextureConfig;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import lighting.AuxilaryLighting;
import mapping.RayCaster;
import mapping.World;

public class Screen extends JPanel {
	RayCaster t;
	World w;
	Frame f;

	public Screen(RayCaster t, World w) {
		this.t = t;
		this.w = w;
	}

	public void setFrame(Frame f) {
		this.f = f;
	}

	public RayCaster getTrace() {
		return t;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		double[] columns = t.getColumnArray();
		BufferedImage[] textures = t.getTextureArray();
		Color[] colors = t.getColorArray();
		Graphics2D scaledImage = g2d;

		for (int row = 1; row < f.getScreenHeight() / 2; ++row) {
			g2d.drawImage(TextureConfig.getCeiling().getRowAt(row), 0, row, f.getScreenWidth(), 1, null);
			g2d.drawImage(TextureConfig.getFloor().getRowAt(row), 0,  f.getScreenHeight() - row, f.getScreenWidth(), 1, null);
		}
		for (int i = 0; i < f.getScreenWidth(); ++i) {
			// System.out.println(columns[i]);
			if (columns[i] != -1) {
				double intensity = 5 / columns[i];
				if (intensity > 1)
					intensity = 1;
				if (colors[i] != null) {
					double red = colors[i].getRed() * intensity;
					double green = colors[i].getGreen() * intensity;
					double blue = colors[i].getBlue() * intensity;
					if (red > 255)
						red = 255;
					if (green > 255)
						green = 255;
					if (blue > 255)
						blue = 255;
					Color shade = new Color((int) Math.round(red),
							(int) Math.round(green), (int) Math.round(blue));
					g2d.setColor(shade);
				}
				int height = (int) ((1 / columns[i]) * ((f.getScreenWidth() / 2) / (w
						.getPlayer().getFOV() / 2)));
				// System.out.println(height);				
				if (textures[i] == null) {
					g2d.drawLine(i, f.getScreenHeight() / 2 - height / 2, i,
							f.getScreenHeight() / 2 + height / 2);
				} else {
					//					Image using = textures[i].getScaledInstance(1, height,
					//							Image.SCALE_FAST);
					// ^ shit's way too slow, creates a standard of 2fps
				//	RescaleOp rescale = new RescaleOp((float) intensity, 0,
				//			null);
					BufferedImage workingImage = textures[i];
				//	workingImage = rescale.filter(workingImage, textures[i]);
					g2d.drawImage(workingImage, i, f.getScreenHeight() / 2
							- height / 2, 1, height, null);
					// ^ shit's moderately fast, creates a standard of 50-70fps
				}

			}
		}
		g2d.setColor(Color.BLACK);
		g2d.drawString("FPS: " + t.getFPS(), 20, 20);
	}

}
