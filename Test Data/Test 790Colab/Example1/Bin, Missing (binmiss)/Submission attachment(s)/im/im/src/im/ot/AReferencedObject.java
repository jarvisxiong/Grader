package im.ot;

public class AReferencedObject implements ReferencedObject{
	Object object;
	int referenceCount;
	public AReferencedObject (Object anObject, int aReferenceCount) {
		object = anObject;
		referenceCount = aReferenceCount;		
	}
	@Override
	public Object getObject() {
		return object;
	}
	@Override
	public int getReferenceCount() {
		return referenceCount;
	}
	@Override
	public void incrementReferenceCount() {
		referenceCount++;
	}
	@Override
	public void decrementReferenceCount() {
		referenceCount--;
		
	}
	
	public String toString() {
		return object.toString() + "RC(" + referenceCount + ")";
	}
	
	

}
