package im.ot;

import im.AListEdit;
import im.ListEdit;

public class AnOperationTransformer implements OperationTransformer {
	public ListEdit transform (TransformableListEdit transformed, TransformableListEdit other) {
		//Message.info("original:" +  transformed + ", other: " + other);
		int transformedIndex = transformed.getListEdit().getIndex();
//		char ch;
		if (transformedIndex > other.getListEdit().getIndex())
			transformedIndex++;
		else if (transformedIndex == other.getListEdit().getIndex()) {
//			if (transformed.getEdit().getChar() == other.getEdit().getChar())
//				return null;
			if (!transformed.isServer())
				transformedIndex++;
		}
		ListEdit retVal = new AListEdit(transformed.getListEdit().getOperationName(), 
				transformedIndex, transformed.getListEdit().getElement());
		//Message.info("transformed:" +  retVal + ", other: " + other);
		return retVal;
	}
}
