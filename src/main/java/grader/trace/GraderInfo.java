package grader.trace;

import java.util.Date;

import util.trace.TraceableInfo;

public class GraderInfo extends TraceableInfo implements CSVSerializable {

	public GraderInfo(String aMessage, Object aFinder) {
		super(aMessage, aFinder);
	}

	@Override
	public String toCSVRow() {
		long timeStamp = getTmeStamp();
		Date date = new Date(timeStamp);
		String[] parts = date.toString().split(" ");
		String time = parts[3];
		return getTmeStamp() + ","  + time + "," + this.getClass().getSimpleName();
	}

}
