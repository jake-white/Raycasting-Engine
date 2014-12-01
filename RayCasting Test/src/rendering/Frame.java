package rendering;

import input.InputManager;

import javax.swing.JFrame;

public class Frame extends JFrame {
	double screenWidth = 800, screenHeight = 600;

	public Frame(Screen s, InputManager i) {
		super();
		setSize((int) screenWidth, (int) screenHeight);
		getContentPane().add(s);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s.setVisible(true);
		addKeyListener(i);
		setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public int getScreenWidth() {
		screenWidth = this.getWidth();
		return (int) screenWidth;
	}

	public int getScreenHeight() {
		screenHeight = this.getHeight();
		return (int) screenHeight;
	}

}
