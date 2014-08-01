package echo.modular;

import java.util.List;

import bus.uigen.trace.TraceUtility;
import util.trace.Traceable;

public class FileToTraceableTester {
	public static void main (String[] args) {
		List<Traceable> retVal = TraceUtility.toTraceableList("traceLog.txt");
		System.out.println(retVal);
	}

}
