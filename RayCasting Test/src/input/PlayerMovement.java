package input;

import java.awt.event.KeyEvent;

public class PlayerMovement {
	Player p;
	InputManager i;
	final double PLAYER_ROTATION_SPEED = 0.04;
	final double PLAYER_WALKING_SPEED = 0.1;
	final double PLAYER_RUNNING_SPEED = 0.2;
	final double PLAYER_STRAFING_SPEED = 0.05;

	final int PLAYER_ROTATION_LEFT = KeyEvent.VK_Q;
	final int PLAYER_ROTATION_RIGHT = KeyEvent.VK_E;

	final int PLAYER_WALK_FORWARDS = KeyEvent.VK_W;
	final int PLAYER_WALK_BACKWARDS = KeyEvent.VK_S;
	final int PLAYER_STRAFE_LEFT = KeyEvent.VK_A;
	final int PLAYER_STRAFE_RIGHT = KeyEvent.VK_D;

	public PlayerMovement(Player p, InputManager i) {
		this.p = p;
		this.i = i;
	}

	public void parseInput() {
		double workingSpeed;
		if (i.input(KeyEvent.VK_SHIFT)) {
			workingSpeed = PLAYER_RUNNING_SPEED;
		} else
			workingSpeed = PLAYER_WALKING_SPEED;
		double a = p.getAngle();
		double workingAngle = a;
		double workingX, workingY, signX, signY;
		if (i.input(PLAYER_ROTATION_RIGHT))
			p.updateAngle(PLAYER_ROTATION_SPEED);
		if (i.input(PLAYER_ROTATION_LEFT))
			p.updateAngle(-PLAYER_ROTATION_SPEED);

		if (i.input(PLAYER_WALK_FORWARDS)) {
			if (workingAngle < Math.PI / 2) {
				signX = 1;
				signY = -1;
				workingX = Math.abs(workingSpeed
						* Math.sin(workingAngle))
						* signX;
				workingY = Math.abs(workingSpeed
						* Math.cos(workingAngle))
						* signY;

			} else if (workingAngle < Math.PI) {
				workingAngle += Math.PI / 2;
				signX = 1;
				signY = 1;
				workingX = Math.abs(workingSpeed
						* Math.cos(workingAngle))
						* signX;
				workingY = Math.abs(workingSpeed
						* Math.sin(workingAngle))
						* signY;

			} else if (workingAngle < 1.5 * Math.PI) {
				workingAngle += Math.PI;
				signX = -1;
				signY = 1;
				workingX = Math.abs(workingSpeed
						* Math.sin(workingAngle))
						* signX;
				workingY = Math.abs(workingSpeed
						* Math.cos(workingAngle))
						* signY;
			} else {
				workingAngle += Math.PI * 1.5;
				signX = -1;
				signY = -1;
				workingX = Math.abs(workingSpeed
						* Math.cos(workingAngle))
						* signX;
				workingY = Math.abs(workingSpeed
						* Math.sin(workingAngle))
						* signY;
			}

			p.updateX(workingX);
			p.updateY(workingY);

		} else if (i.input(PLAYER_WALK_BACKWARDS)) {
			if (workingAngle < Math.PI / 2) {
				signX = -1;
				signY = 1;
				workingX = Math.abs(workingSpeed
						* Math.sin(workingAngle))
						* signX;
				workingY = Math.abs(workingSpeed
						* Math.cos(workingAngle))
						* signY;

			} else if (workingAngle < Math.PI) {
				workingAngle -= Math.PI / 2;
				signX = -1;
				signY = -1;
				workingX = Math.abs(workingSpeed
						* Math.cos(workingAngle))
						* signX;
				workingY = Math.abs(workingSpeed
						* Math.sin(workingAngle))
						* signY;

			} else if (workingAngle < 1.5 * Math.PI) {
				workingAngle -= Math.PI;
				signX = 1;
				signY = -1;
				workingX = Math.abs(workingSpeed
						* Math.sin(workingAngle))
						* signX;
				workingY = Math.abs(workingSpeed
						* Math.cos(workingAngle))
						* signY;
			} else {
				workingAngle -= Math.PI * 1.5;
				signX = 1;
				signY = 1;
				workingX = Math.abs(workingSpeed
						* Math.cos(workingAngle))
						* signX;
				workingY = Math.abs(workingSpeed
						* Math.sin(workingAngle))
						* signY;
			}
			//		System.out.println(workingX + ", " + workingY);
			p.updateX(workingX);
			p.updateY(workingY);
		}

		if (i.input(PLAYER_STRAFE_LEFT)) {

		} else if (i.input(PLAYER_STRAFE_RIGHT)) {

		}

	}

}
