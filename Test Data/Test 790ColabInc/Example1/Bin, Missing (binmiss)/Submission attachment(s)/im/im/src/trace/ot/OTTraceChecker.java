package trace.ot;

import java.util.ArrayList;
import java.util.List;

import bus.uigen.oadapters.ObjectAdapter;
import bus.uigen.query.AnObjectQuery;
import bus.uigen.query.ObjectQuery;
import bus.uigen.trace.TraceUtility;
import trace.echo.modular.ModularEchoTraceChecker;
import util.trace.Traceable;
import util.trace.TraceableLog;
import util.trace.console.ConsoleInput;
import util.trace.console.ConsoleOutput;
import static bus.uigen.trace.TraceUtility.*;
import static bus.uigen.query.QueryUtility.*;

public class OTTraceChecker extends ModularEchoTraceChecker{
	
	static Class[] otExpectedClasses = {
			OTListEditBuffered.class,
			OTListEditReceived.class,
			OTListEditSent.class,
			OTListEditRemoteCountIncremented.class,
			InitialOTTimeStampCreated.class,
			LocalEditCountIncremented.class,
			LocalSiteCountIncremented.class,
			RemoteSiteCountIncremented.class,
			ConcurrentEdits.class,
			NonConcurrentEdits.class			
			
	};
	

	public static boolean compareOTSequences(
			List<Traceable> aTestTraceableList, 
			List<Traceable> aCorrectTraceableList) {
		System.out.println("Comparing OT Sequences");
		TraceableLog traceableLog = TraceUtility.startNewTrace();
//		String[] aProperties = {"Output"};
		
//		ObjectQuery[] objectQueries = traceablesToQueries(aCorrectTraceableList);
//		boolean retVal = inOrder(aFilteredTest, objectQueries, anExpe);
		boolean retVal = inOrder(aTestTraceableList, aCorrectTraceableList, expectedClasses(), true);
		TraceUtility.stopExistingTrace(traceableLog);
		if (!retVal)
			System.out.println("IM Sequence Test Failed");
		return retVal;		
	
		
		
	}
	
	

}
