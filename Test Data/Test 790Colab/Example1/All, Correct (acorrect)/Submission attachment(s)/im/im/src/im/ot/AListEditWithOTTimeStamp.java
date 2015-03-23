package im.ot;

import trace.ot.OTTimeStampedListEditInfo;



public class AListEditWithOTTimeStamp implements ListEditWithOTTimeStamp {
	TransformableListEdit edit;
	OTTimeStamp otTimeStamp;
	public AListEditWithOTTimeStamp(TransformableListEdit theEdit, OTTimeStamp theTimeStamp ) {
		edit = theEdit;
		otTimeStamp = theTimeStamp;
	}
	public  TransformableListEdit getTransformableListEdit() {
		return edit;
	}
	public  void setTransformableListEdit(TransformableListEdit theCharInsertion) {
		edit = theCharInsertion;
	}

	public OTTimeStamp getOTTimeStamp() {
		return otTimeStamp;
	}
	public void setOTTimeStamp(OTTimeStamp otTimeStamp) {
		this.otTimeStamp = otTimeStamp;
	}
	public String toString() {
		return edit.toString() + ", " +  otTimeStamp;
	}
	@Override
	public OTTimeStampedListEditInfo toOTTimeStampedListEditInfo() {
		return new OTTimeStampedListEditInfo(edit.getListEdit().toListEditInfo(), otTimeStamp.toOTTimeStampInfo()) ;
	}
	
}
