package mapping;

import input.Player;

import java.awt.Color;
import java.awt.image.BufferedImage;

import rendering.Frame;

public class RayCaster {
	World w;
	Player p;
	Frame f;
	double[] columnsDistance;
	double FPS, currentFPS;
	Color[] columnColor;
	BufferedImage[] columnTexture;
	Color[][] floorColor;
	Color[][] ceilingColor;
	long fpsCheck;
	long lastCheck;

	public RayCaster(World w) {
		this.w = w;
		p = w.getPlayer();
	}

	public double getFPS() {
		return FPS;
	}

	public void traceShit() {
		if (lastCheck == 0) {
			lastCheck = System.currentTimeMillis();
		}
		fpsCheck = System.currentTimeMillis() - lastCheck;
		if (fpsCheck >= 1000) // checking FPS
		{
			FPS = currentFPS;
			currentFPS = 0;
			fpsCheck = 0;
			lastCheck = System.currentTimeMillis();
		}
		for (int i = 0; i < columnsDistance.length; ++i) { // resetting columns
			columnsDistance[i] = -1;
		}
		double a = p.getAngle();
		double pX = p.getX();
		double pY = p.getY();
		double inc = p.getFOV() / f.getScreenWidth();
		int column = 0;
		for (double currentAngle = a - p.getFOV() / 2; currentAngle < a
				+ p.getFOV() / 2; currentAngle += inc) {
			double hor = 0, vert = 0;
			BufferedImage horImg = null, vertImg = null;
			Color horColor = Color.BLACK, vertColor = Color.BLACK;
			double relX, relY, dX, dY;
			double actX = 0, actY = 0;
			int checkDir = 0, signX, signY;
			double workingAngle = currentAngle;
			if (workingAngle > 2 * Math.PI)
				workingAngle -= 2 * Math.PI;
			else if (workingAngle < 0)
				workingAngle += 2 * Math.PI;
			/*
			 * now it is 0 to 2PI time to evaluate by quadrant STATUS:
			 * 
			 * QUADI = FINISHED QUADII = FINISHED QUADIII = IN PROGRESS QUADIV =
			 * NOT STARTED
			 */
			double actualAngle = workingAngle; // for doing horizontal and
												// vertical

			if (workingAngle < Math.PI / 2) {
				// first, quadrant I
				actY = Math.floor(pY);
				relY = Math.abs(pY - actY);
				relX = relY * Math.tan(workingAngle);
				actX = pX + relX;
				dY = 1; // distance between Ys
				dX = ((dY + relY) * Math.tan(workingAngle)) - relX; // distance
																	// between
																	// Xs
				checkDir = -1; // check the block above, because of how the
								// coordinates are
				signX = 1;
				signY = -1;
			} else if (workingAngle < Math.PI) {
				// next, quadrant II
				workingAngle -= Math.PI / 2;
				actY = Math.ceil(pY);
				relY = actY - pY;
				relX = relY / Math.tan(workingAngle);
				actX = pX + relX;
				dY = 1;
				dX = (((relY + dY) / Math.tan(workingAngle)) - relX);
				checkDir = 0;
				signX = 1;
				signY = 1;
			} else if (workingAngle < 1.5 * Math.PI) {
				// next, quadrant III
				workingAngle -= Math.PI;
				actY = Math.ceil(pY);
				relY = actY - pY;
				relX = relY * Math.tan(workingAngle);
				actX = pX - relX;
				dY = 1;
				dX = ((Math.tan(workingAngle) * (dY + relY)) - relX);
				checkDir = 0;
				signX = -1;
				signY = 1;
			} else {
				// finally, quadrant IV
				workingAngle -= (Math.PI) * 1.5;
				actY = Math.floor(pY);
				relY = pY - actY;
				relX = relY / Math.tan(workingAngle);
				actX = pX - relX;
				dY = 1;
				dX = ((dY + relY) / Math.tan(workingAngle)) - relX;
				checkDir = -1;
				signX = -1;
				signY = -1;
			}

			// first, horizontal checks
			while (actY + checkDir < w.getWorldHeight() && actY + checkDir >= 0
					&& actX < w.getWorldWidth() && actX >= 0) {
				if (w.getTileAt((int) Math.floor(actX), (int) Math.floor(actY)
						+ checkDir) == 1) { // checking for hits
					hor = Math.sqrt((relX * relX) + (relY * relY));
					// System.out.println("HOR HIT = " + hor + " RELX = " + relX
					// + " RELY = " + relY);
					horColor = w.getTileColorAt((int) Math.floor(actX),
							(int) Math.floor(actY) + checkDir);
					horImg = w.getTileTextureAt(actX, actY + checkDir, true);
					break;
				}
				relY += dY;
				relX += dX;
				actX = pX + (signX * relX);
				actY = pY + (signY * relY);
			}

			if (actualAngle < Math.PI / 2) {
				// first, quadrant I
				actX = Math.ceil(pX);
				relX = actX - pX;
				relY = Math.abs(relX / Math.tan(workingAngle));
				actY = pY - relY;
				dX = 1; // distance between Xs
				dY = (((dX + relX) / Math.tan(workingAngle)) - relY); // distance
																		// between
																		// Ys
				checkDir = 0;
				signX = 1;
				signY = -1;
			} else if (actualAngle < Math.PI) {
				// next, quadrant II
				actX = Math.ceil(pX);
				relX = actX - pX;
				relY = relX * Math.tan(workingAngle);
				actY = pY + relY;
				dX = 1;
				dY = ((relX + dX) * (Math.tan(workingAngle)) - relY);
				checkDir = 0;
				signX = 1;
				signY = 1;
			} else if (actualAngle < 1.5 * Math.PI) {
				// next, quadrant III
				actX = Math.floor(pX);
				relX = pX - actX;
				relY = relX / Math.tan(workingAngle);
				actY = pY + relY;
				dX = 1;
				dY = ((dX + relX) / Math.tan(workingAngle)) - relY;
				checkDir = -1;
				signX = -1;
				signY = 1;
			} else {
				// finally, quadrant IV
				actX = Math.floor(pX);
				relX = pX - actX;
				relY = relX * Math.tan(workingAngle);
				actY = pY - relY;
				dX = 1;
				dY = ((dX + relX) * Math.tan(workingAngle)) - relY;
				checkDir = -1;
				signX = -1;
				signY = -1;
			}

			// next, vertical checks
			while (actY < w.getWorldHeight() && actY > 0
					&& actX < w.getWorldWidth() && actX > 0) {
				if (w.getTileAt((int) Math.floor(actX) + checkDir,
						(int) Math.floor(actY)) == 1) { // checking for hits
					vert = Math.sqrt((relX * relX) + (relY * relY));
					// System.out.println("VERT HIT = " + vert + " RELX = " +
					// relX + " RELY = " + relY);
					vertColor = w.getTileColorAt((int) Math.floor(actX)
							+ checkDir, (int) Math.floor(actY));
					vertImg = w.getTileTextureAt(actX + checkDir, actY, false);
					break;
				}
				relY += dY;
				relX += dX;
				actX = pX + (signX * relX);
				actY = pY + (signY * relY);

			}
			if ((hor <= vert || vert <= 0) && hor > 0) {
				columnsDistance[column] = hor;
				columnColor[column] = horColor;
				columnTexture[column] = horImg;
				// System.out.println("HOR SELECT");
			} else if (vert > 0) {
				columnsDistance[column] = vert;
				columnColor[column] = vertColor;
				columnTexture[column] = vertImg;
				// System.out.println("VERT SELECT");
			}
			// System.out.println("HOR = " + hor + " WHILE VERT = " + vert);
			if (columnsDistance[column] > 0) {
				double fish = Math.cos((column * (p.getFOV() / f
						.getScreenWidth())) - (p.getFOV() / 2));
				// System.out.println(fish);
				// uses trig to fix the fisheye distortion (works well right
				// now!)
				columnsDistance[column] *= fish;
			}

			/*
			 * floor casting starts here, all done with walls! going pixel-by-pixel
			 * hopefully it doesn't shit on the performance... here goes nothin
			 */
			for(int widthPX = 0; widthPX < f.getScreenWidth(); ++widthPX)
			{
				double height = columnsDistance[column];
				
			}
			
			if (column < f.getScreenWidth() - 1)
				++column;
		}
		++currentFPS;

	}

	public double[] getColumnArray() {
		return columnsDistance;
	}

	public Color[] getColorArray() {
		return columnColor;
	}

	public BufferedImage[] getTextureArray() {
		return columnTexture;
	}

	public void setFrame(Frame f) {
		this.f = f;
		columnsDistance = new double[f.getScreenWidth()];
		columnColor = new Color[f.getScreenWidth()];
		columnTexture = new BufferedImage[f.getScreenWidth()];
	}

	public double tan(double trig) {
		if (Math.tan(trig) == 0) {
			return Math.tan(trig + 0.01);
		} else
			return Math.tan(trig);
	}
}
