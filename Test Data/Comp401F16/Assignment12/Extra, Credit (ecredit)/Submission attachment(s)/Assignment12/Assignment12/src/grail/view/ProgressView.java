package grail.view;

import java.beans.PropertyChangeListener;

import javax.swing.JProgressBar;

public interface ProgressView extends PropertyChangeListener{
	public void setProgressBarMax(int max);
	public JProgressBar getProgressBar();
	public int getProgressBarMax();
	public void bringToFront();
	public void setLocation(int x, int y);
}
