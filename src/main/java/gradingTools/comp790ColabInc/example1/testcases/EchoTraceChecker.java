package gradingTools.comp790ColabInc.example1.testcases;

import static bus.uigen.query.QueryUtility.inOrder;
import static bus.uigen.query.QueryUtility.indexOf;
import static bus.uigen.query.QueryUtility.indicesOf;
import static bus.uigen.query.QueryUtility.valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.trace.Traceable;
import util.trace.TraceableLog;
import util.trace.Tracer;
import util.trace.console.ConsoleInput;
import util.trace.console.ConsoleOutput;
import util.trace.query.ClassInstanceFound;
import util.trace.query.ClassInstanceMissing;
import bus.uigen.trace.TraceUtility;
import bus.uigen.trace.query.QueryTargetFound;
import bus.uigen.trace.query.QueryTargetMissing;


public class EchoTraceChecker {
	static Class[] expectedClasses = {
			ConsoleOutput.class,
	};
	
	public static Class[] expectedClasses() {
		return expectedClasses;
	}
	
	public static boolean checkEchoer(List<Traceable> aTraceableList) {
		Tracer.setKeywordPrintStatus(ClassInstanceMissing.class, true);
		Tracer.setKeywordPrintStatus(ClassInstanceFound.class, true);
		Tracer.setKeywordPrintStatus(QueryTargetFound.class, true);
		Tracer.setKeywordPrintStatus(QueryTargetMissing.class, true);
		
//		Class[] anExpectedClasses = {
//				ConsoleInput.class,				
//				ConsoleOutput.class,
//		};
		// the old way
//		List<Integer> anIndexList = indicesOf(aTraceableList, anExpectedClasses, false);
//		List<Integer> anOutOfOrderList = indicesOfOutOfOrderIndices(/*anExpectedClasses,*/ anIndexList);
//		if (anOutOfOrderList.size() != 0) {
//			System.out.println("Out of order indices:" + anOutOfOrderList);
//			return false;
//		}
//
//		List<Integer> anInvalidIndices = indicesOfInvalidIndices(anIndexList);
//		if (anInvalidIndices.size() != 0) {
//			System.out.println("Missing events:" + missingClasses(anExpectedClasses, anInvalidIndices));
//			return false;
//		}
		// the new way
		List<Integer> anIndexList = indicesOf(aTraceableList, expectedClasses, true);
		if (!valid(anIndexList))
			return false;
//		List<Integer> anOutOfOrderList = indicesOfOutOfOrderIndices(/*anExpectedClasses,*/ anIndexList);
//		if (anOutOfOrderList.size() != 0) {
//			System.out.println("Out of order indices:" + anOutOfOrderList);
//			return false;
//		}
//
//		List<Integer> anInvalidIndices = indicesOfInvalidIndices(anIndexList);
//		if (anInvalidIndices.size() != 0) {
//			System.out.println("Missing events:" + missingClasses(anExpectedClasses, anInvalidIndices));
//			return false;
//		}
		ConsoleInput anInput = (ConsoleInput) aTraceableList.get(anIndexList.get(0));
		ConsoleOutput anOutput = (ConsoleOutput) aTraceableList.get(anIndexList.get(1));
		return anOutput.getOutput().toLowerCase().contains(anInput.getInput().toLowerCase());		
		
	}
	public static boolean compareInputOutSequences(
			List<Traceable> aTestTraceableList, 
			List<Traceable> aCorrectTraceableList) {
//		Class[] anExpectedClasses = {ConsoleOutput.class};
//		List<Traceable> aFilteredTest = TraceUtility.filterTraceList(aTestTraceableList, anExpectedClasses);		
//		List<Traceable> aFilteredCorrect = TraceUtility.filterTraceList(aCorrectTraceableList, anExpectedClasses);
//		
		TraceableLog traceableLog = TraceUtility.startNewTrace();
//		String[] aProperties = {"Output"};
		
//		ObjectQuery[] objectQueries = traceablesToQueries(aCorrectTraceableList);
//		boolean retVal = inOrder(aFilteredTest, objectQueries, anExpe);
		boolean retVal = inOrder(aTestTraceableList, aCorrectTraceableList, expectedClasses, true);
		TraceUtility.stopExistingTrace(traceableLog);
		return retVal;		
	}

	public static boolean matchInputOutSequences(List<Traceable> aTraceableList) {
		Integer aStartIndex = 0;
		boolean retVal = true;
		while (true) {
			Integer anInputIndex = indexOf(aTraceableList, ConsoleInput.class, aStartIndex, new ArrayList());
			aStartIndex = anInputIndex + 2; // at one input and output occured after this

			if (anInputIndex < 0) 
//				return true; 
				return retVal; 
			ConsoleInput aConsoleInput = (ConsoleInput) aTraceableList.get(anInputIndex);
			// ignore history and quit commands
			if (!EchoUtilities.isInput(aConsoleInput.getInput())) {
				continue;
			}
			// start getting trace of comparison result traces
			TraceableLog traceableLog = TraceUtility.startNewTrace();
			boolean checkInputEcho = checkInputEcho(aTraceableList, aConsoleInput, anInputIndex);
			TraceUtility.stopExistingTrace(traceableLog);
			// stop getting trace of comparisons
			// does not seem we do anyting with traceable log
			if (!checkInputEcho ) {
				Tracer.info("Did not find  echo for input:" + aConsoleInput.getInput());
				 retVal = false;
//				return false;
			}
//			Tracer.info("Fouund echo for input:" + aConsoleInput.getInput());

//			aStartIndex = anInputIndex + 2; // at one input and output occured after this
		}
			
		
	}

	
	public static boolean checkInputEcho(List<Traceable> aTraceableList, ConsoleInput anInput, int anInputIndex ) {
//		Class[] anExpectedClasses = {
//				ConsoleOutput.class,
//		};
		Map<String, Object> propertyToValue = new HashMap();
		propertyToValue.put(ConsoleOutput.OUTPUT, EchoUtilities.echo(anInput.getInput()));
		
		return inOrder(aTraceableList, expectedClasses, propertyToValue, anInputIndex + 1, true);
		
	}

	
	
	

}
