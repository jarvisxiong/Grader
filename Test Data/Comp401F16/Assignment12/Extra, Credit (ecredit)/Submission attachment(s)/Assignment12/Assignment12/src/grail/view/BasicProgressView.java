package grail.view;

import java.beans.PropertyChangeEvent;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

import javax.swing.JLabel;

@Tags({"ProgressBarCreator"})
@PropertyNames({"ProgressBar"}) 
@EditablePropertyNames({"ProgressBarMax"})
public class BasicProgressView implements ProgressView{
	private JProgressBar progressBar;
	private JFrame frame;
	private int progressBarMax;
	
	public BasicProgressView() {
		this.frame = new JFrame("Progress Bar");
		final int progressBarMaxDefault = 100;
		final int frameWidth = 400;
		final int frameHeight = 100;
		final int frameXLocation = 1100;
		final int frameYLocation = 0;
		
		frame.setSize(frameWidth, frameHeight);
		frame.setLocation(frameXLocation ,frameYLocation);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		final int upperX = 6;
		final int upperY = 47;
		final int lowerX = 388;
		final int lowerY = 20;
		this.progressBarMax = progressBarMaxDefault;
		progressBar = new JProgressBar();
		this.progressBar.setMaximum(progressBarMaxDefault);
		progressBar.setBounds(upperX, upperY, lowerX, lowerY);
		frame.getContentPane().add(progressBar);
		
		final int upperYTwo = 18;
		final int lowerXTwo = 61;
		final int lowerYTwo = 16;
		JLabel lblProgress = new JLabel("Progress:");
		lblProgress.setBounds(upperX, upperYTwo, lowerXTwo, lowerYTwo);
		frame.getContentPane().add(lblProgress);
		
		frame.setVisible(true);
		
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if ("Progress".equals(evt.getPropertyName())) {
			int currentProgress = (int) evt.getNewValue();
			this.progressBar.setValue(currentProgress);
		}
		frame.toFront();
	}
	
	public void setProgressBarMax(int max) {
		this.progressBarMax = max;
		this.progressBar.setMaximum(max);
	}
	
	public int getProgressBarMax() {
		return this.progressBarMax;
	}

	public JProgressBar getProgressBar() {
		return this.progressBar;
	}

	public void bringToFront() {
		this.frame.toFront();
	}

	public void setLocation(int x, int y) {
		this.frame.setLocation(x, y);
	}
}
