package mp.ui.mvc;

import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import mp.ui.Avatar;
import mp.ui.BridgeScene;

public interface BridgeSceneController extends KeyListener, MouseListener {
	public void mouseClicked(MouseEvent e);
	public Avatar getCurrAvatar();
	public BridgeScene getBridgeScene();
}
