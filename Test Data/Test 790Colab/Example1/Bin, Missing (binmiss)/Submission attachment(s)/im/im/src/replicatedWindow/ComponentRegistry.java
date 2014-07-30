package replicatedWindow;

import java.awt.Component;
import java.awt.Container;

public interface ComponentRegistry {
	public  void register(Component aComponent);

	public  Component getComponent(int id) ;

	public  int getComponentId(Component aComponent) ;
    public  void registerComponentTree(Component aComponent ) ;

}
