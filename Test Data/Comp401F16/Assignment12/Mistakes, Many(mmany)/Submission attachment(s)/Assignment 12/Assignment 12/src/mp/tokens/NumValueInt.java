package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;

@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public interface NumValueInt extends Token {
public int getValue();
}
