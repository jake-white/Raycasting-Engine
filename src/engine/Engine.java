package engine;

import input.InputManager;
import input.Player;
import input.PlayerMovement;

import javax.swing.Timer;

import mapping.RayCaster;
import mapping.World;
import rendering.Frame;
import rendering.Renderer;

public class Engine {
	World w;
	RayCaster t;
	Frame f;
	InputManager i;
	Player p;
	PlayerMovement pM;
	final double WALK_SPEED = 0.1;

	public Engine(World w, RayCaster t, Renderer r, Frame f, InputManager i, PlayerMovement pM) {
		this.w = w;
		this.t = t;
		this.f = f;
		this.i = i;
		this.p = w.getPlayer();
		this.pM = pM;
	}

	public void start() {
		Timer t = new Timer(8, new TimeCounter(this));

		t.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void tick() {
		if (p.getAngle() > 2 * Math.PI)
			p.updateAngle(-2 * Math.PI);
		else if (p.getAngle() < 0)
			p.updateAngle(2 * Math.PI);
		pM.parseInput();
		t.traceShit();
		f.repaint();
	}
	public void parseInput()
	{
	}
}
