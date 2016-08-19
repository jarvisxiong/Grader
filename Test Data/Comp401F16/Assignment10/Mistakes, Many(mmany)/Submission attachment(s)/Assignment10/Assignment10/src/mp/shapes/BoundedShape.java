package mp.shapes;

import util.annotations.StructurePattern;
import util.annotations.Tags;

@Tags({"BoundedShape"})
@StructurePattern("Bean Pattern")
public interface BoundedShape extends Locatable{
    public int getWidth();
    public int getHeight() ;
}
