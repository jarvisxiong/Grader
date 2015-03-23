package graphics.view;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;

import graphics.avatar.IAvatar;
import graphics.shapes.IImageShape;
import graphics.shapes.IRotatingLine;
import graphics.shapes.IStringShape;
import util.annotations.Tags;

@Tags({ "Observing Bridge Scene Painter" })

@SuppressWarnings("serial")
public class AvatarView extends Component implements IAvatarView {
	private IAvatar avatar;
	private ObservablePainter painter;
	
	public AvatarView(IAvatar avatar, ObservablePainter painter) {
		this.avatar = avatar;
		this.painter = painter;
		attachListeners();
	}
	
	private void attachListeners() {
		painter.addPaintListener(this);
		
		avatar.getArms().getLeftLine().addPropertyChangeListener(this);
		avatar.getArms().getRightLine().addPropertyChangeListener(this);
		avatar.getBody().addPropertyChangeListener(this);
		avatar.getHead().addPropertyChangeListener(this);
		avatar.getLegs().getLeftLine().addPropertyChangeListener(this);
		avatar.getLegs().getRightLine().addPropertyChangeListener(this);
		avatar.getNeck().addPropertyChangeListener(this);
		avatar.getText().addPropertyChangeListener(this);
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(avatar.getBodyColor());
		IRotatingLine all = avatar.getArms().getLeftLine(); //arm left line
		g.drawLine(all.getX(), all.getY(), all.getX()+all.getWidth(), all.getY()+all.getHeight());
		IRotatingLine arl = avatar.getArms().getRightLine(); //arm right line
		g.drawLine(arl.getX(), arl.getY(), arl.getX()+arl.getWidth(), arl.getY()+arl.getHeight());
		IRotatingLine b = avatar.getBody(); //body
		g.drawLine(b.getX(), b.getY(), b.getX()+b.getWidth(), b.getY()+b.getHeight());
		IImageShape h = avatar.getHead(); //head
		Image hImg = Toolkit.getDefaultToolkit().getImage(h.getImageFileName()); //head image
		g.drawImage(hImg, h.getX(), h.getY(), h.getWidth(), h.getHeight(), painter);
		IRotatingLine lll = avatar.getLegs().getLeftLine(); //leg left line
		g.drawLine(lll.getX(), lll.getY(), lll.getX()+lll.getWidth(), lll.getY()+lll.getHeight());
		IRotatingLine lrl = avatar.getLegs().getRightLine(); //leg right line
		g.drawLine(lrl.getX(), lrl.getY(), lrl.getX()+lrl.getWidth(), lrl.getY()+lrl.getHeight());
		IRotatingLine n = avatar.getNeck(); //neck
		g.drawLine(n.getX(), n.getY(), n.getX()+n.getWidth(), n.getY()+n.getHeight());
		IStringShape t = avatar.getText(); //text
		g.drawString(t.getText(), t.getX(), t.getY() + t.getFont().getSize2D());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//System.out.println(evt.getPropertyName());
		painter.repaint();
	}

}
