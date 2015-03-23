package editor.ot;

import widgets.Edit;

public interface OperationTransformer {
	public Edit transform (TransformableEdit transformed, TransformableEdit other);

}
