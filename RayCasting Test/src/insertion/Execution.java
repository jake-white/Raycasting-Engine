package insertion;

import input.ConfigLoader;
import input.InputManager;
import input.Player;
import input.PlayerMovement;

import java.io.File;

import mapping.RayCaster;
import mapping.World;
import rendering.Frame;
import rendering.Renderer;
import rendering.Screen;
import engine.Engine;

public class Execution {
	public static void main(String[] args) {
		World w = new World(new File("res/world.png"), new Player());
		ConfigLoader.loadAllConfigs();
		Renderer r = new Renderer();
		InputManager i = new InputManager();
		PlayerMovement pM = new PlayerMovement(w.getPlayer(), i);
		RayCaster t = new RayCaster(w);
		Screen s = new Screen(t, w);
		Frame f = new Frame(s, i);
		s.setFrame(f);
		t.setFrame(f);
		Engine e = new Engine(w, t, r, f, i, pM);
		e.start();
	}

}
