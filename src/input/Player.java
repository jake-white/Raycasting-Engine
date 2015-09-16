package input;

public class Player {
	double angle = Math.PI/2, FOV = 1, x, y;

	public Player() {

	}

	public void setInsertion(double d, double e) {
		this.x = d;
		this.y = e;
	}

	public double getAngle() {
		return angle;
	}

	public double getFOV() {
		return FOV;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void updateX(double update) {
		x += update;
	}

	public void updateY(double update) {
		y += update;
	}

	public void updateAngle(double update) {
		angle += update;
	}
}
