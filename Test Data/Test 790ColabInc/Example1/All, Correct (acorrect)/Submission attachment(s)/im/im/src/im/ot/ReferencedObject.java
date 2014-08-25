package im.ot;

public interface ReferencedObject<ObjectType> {
	public ObjectType getObject();
	public int getReferenceCount();
	public void incrementReferenceCount();
	public void decrementReferenceCount();	

}
