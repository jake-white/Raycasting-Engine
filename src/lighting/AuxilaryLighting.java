package lighting;

import java.awt.Color;

public class AuxilaryLighting {

	public static Color transformColorByRow(double row, Color original)
	{
		double intensity = 150 / row;
		if (intensity > 1)
			intensity = 1;
		else if (intensity < 0.5)
			intensity = 0.5;
		//		System.out.println(ceilIntensity);
		double R = original.getRed() * intensity;
		double G = original.getGreen() * intensity;
		double B = original.getBlue() * intensity;
		if (R > 255)
			R = 255;
		if (G > 255)
			G = 255;
		if (B > 255)
			B = 255;
		Color shaded = new Color((int) Math.round(R),
				(int) Math.round(G), (int) Math.round(B));
		return shaded;
	}

}
