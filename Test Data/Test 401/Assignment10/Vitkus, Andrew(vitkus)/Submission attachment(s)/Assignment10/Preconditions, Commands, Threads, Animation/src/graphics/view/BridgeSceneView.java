package graphics.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import util.APropertyChangeListenerManager;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.models.PropertyListenerRegisterer;

@SuppressWarnings("serial")
public class BridgeSceneView extends JFrame implements Runnable, PropertyListenerRegisterer {
	private ObservablePainter painter;
	
	private APropertyChangeListenerManager pclm = new APropertyChangeListenerManager(this);
	
	public BridgeSceneView(IBridgeScene scene, CommandInterpreterView civ) {
		super("Bridge Scene");

		painter = new ObservablePainter();

		new AvatarView(scene.getArthur(), painter);
		new AvatarView(scene.getGalahad(), painter);
		new AvatarView(scene.getGuard(), painter);
		new AvatarView(scene.getLancelot(), painter);
		new AvatarView(scene.getRobin(), painter);
		
		painter.addPaintListener(new BackgroundView());
		addPropertyChangeListener(civ);
		
		add(painter);
		
		buildMenu();
	}
	
	private void buildMenu() {
		JMenuBar mbar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		fileMenu.add(quitItem);
		
		JMenu viewMenu = new JMenu("View");
		final JMenuItem interpreterItem = new JMenuItem("Interpreter");
		interpreterItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("interpreter visible changed");
				firePropertyChange(new PropertyChangeEvent(BridgeSceneView.this, "Interpreter Visible", false, true));
			}
			
		});
		viewMenu.add(interpreterItem);
		
		mbar.add(fileMenu);
		mbar.add(viewMenu);
		
		setJMenuBar(mbar);
	}

	@Override
	public void run() {
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
        setLocationByPlatform(true);
		setVisible(true);
	}
	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pclm.addPropertyChangeListener(listener);
	}

	private void firePropertyChange(PropertyChangeEvent evt) {
		pclm.firePropertyChange(evt);
	}
}
