package mp.Scene;

import java.awt.Color;

public interface StringShape extends Locatable{
	public Color getColor();
	public String getText();
	public void setText(String newVal);
}
