package texture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HorizontalTexture {
	String blockType;
	BufferedImage img;
	BufferedImage[] columns;
	public HorizontalTexture(String blockType)
	{
		this.blockType = blockType;
		System.out.println(blockType);
		try {
			img = ImageIO.read(new File("res/textures/" + blockType + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		columns = new BufferedImage[img.getHeight()];
		calcColumns();
	}
	public void calcColumns()
	{
		for(int i = 0; i < img.getHeight(); ++i)
		{
		//	columns[i] = img.getSubimage(0, i, 64, 1);
		}
	}
	public BufferedImage getRowAt(int row)
	{
		while(row >= img.getHeight())
		{
			row-=img.getHeight();
			//makes it 64 or less in our case
		}
		return columns[row];
	}
}
