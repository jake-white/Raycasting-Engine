package exec;

import input.InputManager;
import mapcalculations.World;
import rendering.Display;
import rendering.Frame;

public class ExecutionFrame {

	private static Frame frame;
	private static Display d;
	private static InputManager i;
	public static World mainWorld;
	public static void main(String[] args)
	{
		i = new InputManager();
		d = new Display();
		frame = new Frame(d, i);
		mainWorld = new World();
	}
}
