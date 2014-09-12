package graphics.view;

import java.awt.Color;
import java.awt.Graphics2D;

import util.annotations.Tags;

@Tags({ "Observing Bridge Scene Painter" })
public class BackgroundView implements IBackgroundView {

	public BackgroundView() { }

	@Override
	public void paint(Graphics2D g) {
		g.setColor(new Color(135, 206, 250));
		g.fillRect(0, 0, 800, 500);
		g.setColor(new Color(133, 94, 66));
		g.fillRect(600, 200, 100, 50); //bridge
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 150, 600, 350); //close land
		g.fillRect(700, 150, 100, 350); //far land
		g.setColor(Color.WHITE);
		g.fillOval(325, 260, 100, 50); //knight area
		g.fillOval(450, 260, 100, 50); //guard area
	}

}
