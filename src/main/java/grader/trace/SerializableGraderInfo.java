package grader.trace;

import java.util.Date;

import util.trace.TraceableInfo;

public class SerializableGraderInfo extends GraderInfo implements CSVSerializable {

	public SerializableGraderInfo(String aMessage, Object aFinder) {
		super(aMessage, aFinder);
	}
	
	public static final int COLUMNS_USED = 3; 

	@Override
	public String toCSVRow() {
		long timeStamp = getTimeStamp();
		Date date = new Date(timeStamp);
		String[] parts = date.toString().split(" ");
		String time = parts[3];
		return getTimeStamp() + ","  + time + "," + this.getClass().getSimpleName();
	}
	
	public static long timeStampFromCSVRow(String[] aRow) {
		return Long.parseLong(aRow[0]);		
	}
	
	public static String classNameFromCSVRow(String[] aRow) {
		return aRow[2];
	}

}
