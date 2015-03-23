package graphics.view;

public class ProgressMonitor {
	private ProgressMonitorView view;
	private ProgressMonitorController controller;
	
	public ProgressMonitor(int stepCount) {
		view = new ProgressMonitorView(stepCount);
		controller = new ProgressMonitorController(stepCount);
		
		controller.addPropertyChangeListener(view);
		
		javax.swing.SwingUtilities.invokeLater(view);
	}

	public void stepComplete() {
		controller.stepComplete();
	}
}
