package graphics.types;

import java.awt.Color;
import util.annotations.Tags;

@Tags({ "Colorable" })
public interface IColorable extends IColored {
	public void setColor(Color newColor);
}
