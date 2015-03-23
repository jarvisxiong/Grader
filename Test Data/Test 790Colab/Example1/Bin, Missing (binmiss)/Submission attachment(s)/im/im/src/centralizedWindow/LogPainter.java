package centralizedWindow;

import util.awt.ListenablePainter;
import util.awt.SerializableGraphicsRequest;

public interface LogPainter extends ListenablePainter{
	
	public void add (SerializableGraphicsRequest theRequest);
	public void clear();

}
