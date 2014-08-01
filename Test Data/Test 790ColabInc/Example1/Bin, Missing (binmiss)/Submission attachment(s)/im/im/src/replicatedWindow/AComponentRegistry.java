package replicatedWindow;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

public class AComponentRegistry implements ComponentRegistry {
	 List<Component> components = new ArrayList();
	public  final int WTOOLKIT_ID = -2;
	public  final int GLOBAL_CURSOR_EVENT_ID = -3;

	public  void register(Component aComponent) {
		if (components.contains(aComponent))
			return;
		components.add(aComponent);
	}

	public  Component getComponent(int id) {
		if (id < 0 ||  id >= components.size() ) 
			return null;
		return components.get(id);
			
	}

	public  int getComponentId(Component aComponent) {
		return components.indexOf(aComponent);
	}
    public  void registerComponentTree(Component aComponent ) {
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
