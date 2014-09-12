package graphics.types;

import java.awt.Color;
import util.annotations.Tags;

@Tags({ "Colored" })
public interface IColored {
	public boolean isFilled();

	public Color getColor();
}
