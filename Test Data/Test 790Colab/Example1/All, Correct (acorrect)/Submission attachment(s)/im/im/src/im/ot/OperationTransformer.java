package im.ot;

import im.ListEdit;

public interface OperationTransformer {
	public ListEdit transform (TransformableListEdit transformed, TransformableListEdit other);

}
