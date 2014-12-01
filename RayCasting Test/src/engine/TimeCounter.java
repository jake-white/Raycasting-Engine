package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeCounter implements ActionListener {
	Engine e;

	public TimeCounter(Engine e) {
		this.e = e;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		e.tick();

	}

}
