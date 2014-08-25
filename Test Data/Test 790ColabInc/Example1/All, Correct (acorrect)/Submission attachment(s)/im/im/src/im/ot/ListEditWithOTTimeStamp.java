package im.ot;

import java.io.Serializable;

import trace.ot.OTTimeStampedListEditInfo;


public interface ListEditWithOTTimeStamp extends Serializable{

	public abstract TransformableListEdit getTransformableListEdit();

	public abstract void setTransformableListEdit(TransformableListEdit newVal);
	public OTTimeStamp getOTTimeStamp() ;
	public void setOTTimeStamp(OTTimeStamp otTimeStamp) ;

	OTTimeStampedListEditInfo toOTTimeStampedListEditInfo();

	

}