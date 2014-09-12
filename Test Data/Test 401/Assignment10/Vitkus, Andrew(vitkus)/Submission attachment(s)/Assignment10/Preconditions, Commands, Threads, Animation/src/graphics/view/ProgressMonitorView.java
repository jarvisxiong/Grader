package graphics.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class ProgressMonitorView extends JFrame implements Runnable, PropertyChangeListener {
	private JProgressBar progressBar;
	
	public ProgressMonitorView(int steps) {
		super("Animation Progress");
		progressBar = new JProgressBar(0, steps);
		progressBar.setStringPainted(true);
		add(progressBar);
	}

	@Override
	public void run() {
		setSize(250, 50);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
        setLocationByPlatform(true);
		setVisible(true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("Step")) {
			Object o = evt.getNewValue();
			if (o instanceof Integer) {
				progressBar.setValue((Integer)o);
			}
		}
	}
}
