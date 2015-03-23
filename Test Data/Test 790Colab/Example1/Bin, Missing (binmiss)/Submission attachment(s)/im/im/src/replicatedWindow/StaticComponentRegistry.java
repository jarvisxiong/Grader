// deprecated, around for compatibility
package replicatedWindow;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

public class StaticComponentRegistry {
	static List<Component> components = new ArrayList();
	public static final int WTOOLKIT_ID = -2;
	public static final int GLOBAL_CURSOR_EVENT_ID = -3;

	public static void register(Component aComponent) {
		if (components.contains(aComponent))
			return;
		components.add(aComponent);
	}

	public static Component getComponent(int id) {
		if (id < 0 ||  id >= components.size() ) 
			return null;
		return components.get(id);
			
	}

	public static int getComponentId(Component aComponent) {
		return components.indexOf(aComponent);
	}
    public static void registerComponentTree(Component aComponent ) {
    	register(aComponent);
    	if (aComponent instanceof Container) {
    		Container container = (Container) aComponent;
    		Component[] components = container.getComponents();
    		for (Component aChild:components) {
    			registerComponentTree(aChild);    			
    		}
    	}
		
	}
}
