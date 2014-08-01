package trace.ot;

import trace.im.CommunicatedListEditInfo;

public class LocatableUserOTTimeStampedListEditInfo extends OTListEditInfo{
	String location;
	public LocatableUserOTTimeStampedListEditInfo(String aMessage, String aLocation,
			CommunicatedListEditInfo aListEdit, UserOTTimeStampInfo anOTTimeStamp,
			Object aFinder) {
		super(aLocation, aMessage, aListEdit, anOTTimeStamp, aFinder);
//		location = aLocation;
	}
//	public String getLocation() {
//		return location;
//	}
//	public static String toString(ListEditInfo aListEdit, UserOTTimeStampInfo anOTTimeStamp, String aSourceOrDestination) {
//		return aSourceOrDestination + "-->" + toString(aListEdit, anOTTimeStamp);
//	}
//	
}
