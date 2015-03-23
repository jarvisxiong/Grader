package trace.echo.modular;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import echo.monolithic.MonolithicEchoer;
import bus.uigen.oadapters.ObjectAdapter;
import bus.uigen.query.AnObjectQuery;
import bus.uigen.query.ObjectQuery;
import bus.uigen.trace.TraceUtility;
import trace.echo.EchoTraceChecker;
import util.misc.Common;
import util.trace.Traceable;
import util.trace.TraceableLog;
import util.trace.console.ConsoleInput;
import util.trace.console.ConsoleOutput;
import static bus.uigen.trace.TraceUtility.*;
import static bus.uigen.query.QueryUtility.*;

public class ModularEchoTraceChecker {
	public static boolean modularEchoCheck(String aFileName) {
		List<Traceable> retVal = TraceUtility.toTraceableList(aFileName);
		return checkMVCPattern (retVal);

	}
	static Class[] modularExpectedClasses = {
			ListEditInput.class,
			ListEditMade.class,
			ListEditNotified.class,
			ListEditObserved.class,
	};
	
	public static Class[] append(Class[] aClasses1, Class[] aClasses2) {
		List<Class> aList1 = Arrays.asList(aClasses1);
		List<Class> aList2 = Arrays.asList(aClasses2);
		List<Class> list = new ArrayList(aList1.size() + aList2.size());
		list.addAll(aList1);
		list.addAll(aList2);
		return list.toArray(new Class[list.size()]);
	}
	// keep the tests of super class and this one separate
	public static Class[] expectedClasses() {
//		return append(myExpectedClasses, EchoTraceChecker.expectedClasses());
		return modularExpectedClasses;
	}
	
	public static List<Integer> getNextSequenceOfExpectedModularEvents(List<Traceable> aTraceableList, int aStartIndex) {
		TraceableLog aTraceableLog = TraceUtility.startNewTrace();
		// get the first sequence of four events in modularExpected classes
//		List<Integer> anIndexList = indicesOf(aTraceableList, modularExpectedClasses, true, aStartIndex, false);
//		String[] aProperties = {"Index", "Element"};
		List<String> aPropertiesList = Arrays.asList(ListEditInfo.listEditEqualPropertiesArray);


		List<Integer> anIndexList = indicesOf(aTraceableList,  modularExpectedClasses, aPropertiesList, true, aStartIndex, true);

		TraceUtility.stopExistingTrace(aTraceableLog);
		System.out.println(aTraceableLog);
		if (anIndexList == null || !valid(anIndexList)) // did not find the events at all
			return null;
		return anIndexList;
		
	}
	
	public static boolean isNextSequenceOfModularEventsCompatible(List<Traceable> aTraceableList, List<Integer> anIndexList) {
		List aMatchedTraceables = toObjectList(aTraceableList, anIndexList);
//		String[] aProperties = {"OperationName", "Index", "Element"};
//		boolean equalEdits = matches(aMatchedTraceables,  aProperties);
		List<Object> aSources = ObjectAdapter.getPropertyValues(aMatchedTraceables, "EventSource");
		String anInputSouce = (String) aSources.get(0);
		String anActionSource = (String) aSources.get(1);
		String aNotificationSource = (String) aSources.get(2);
		String anObservedSource = (String) aSources.get(3);
		boolean inputNotAction = ! anInputSouce.equals(anActionSource);
		boolean observedNotNotification = !aNotificationSource.equals(anObservedSource);
//		return equalEdits && inputNotAction && observedNotNotification;
		return inputNotAction && observedNotNotification;


		
	}
	
	public static boolean intraSequenceCheckModularEchoer(List<Traceable> aTraceableList) {


//		TraceableLog aTraceableLog = TraceUtility.startNewTrace();
//		// get the first sequence of four events in modularExpected classes
//		List<Integer> anIndexList = indicesOf(aTraceableList, modularExpectedClasses, true);
//		TraceUtility.stopExistingTrace(aTraceableLog);
//		System.out.println(aTraceableLog);
//		if (!valid(anIndexList)) // did not find the events at all
//			return false;
		int startIndex = 0;
		boolean retVal = true;
		boolean foundASequence = false; //if found no instance of events, return false
		while (true) {
		// get the first sequence of four events in modularExpected classes
		List<Integer> anIndexList = getNextSequenceOfExpectedModularEvents(aTraceableList, startIndex);
		if (anIndexList == null || anIndexList.size() == 0)
			return foundASequence && retVal;
		foundASequence = true;
		// found events, now test their equality
//		List aMatchedTraceables = toObjectList(aTraceableList, anIndexList);
//		String[] aProperties = {"OperationName", "Index", "Element"};
//		boolean equalEdits = matches(aMatchedTraceables,  aProperties);
//		List<Object> aSources = ObjectAdapter.getPropertyValues(aMatchedTraceables, "EventSource");
//		String anInputSouce = (String) aSources.get(0);
//		String anActionSource = (String) aSources.get(1);
//		String aNotificationSource = (String) aSources.get(2);
//		String anObservedSource = (String) aSources.get(3);
//		boolean inputNotAction = ! anInputSouce.equals(anActionSource);
//		boolean observedNotNotification = !aNotificationSource.equals(anObservedSource);
//		
//
//		return equalEdits && inputNotAction && observedNotNotification;
		retVal = retVal & isNextSequenceOfModularEventsCompatible(aTraceableList, anIndexList);
		// move past the last matched index
		startIndex = anIndexList.get(anIndexList.size() -1) + 1;
		}
		
	}

	public static boolean compareMVCSequences(
			List<Traceable> aTestTraceableList, 
			List<Traceable> aCorrectTraceableList) {
		TraceableLog traceableLog = TraceUtility.startNewTrace();
		System.out.println("Comparing MVC Sequences");
//		String[] aProperties = {"Output"};
		
//		ObjectQuery[] objectQueries = traceablesToQueries(aCorrectTraceableList);
//		boolean retVal = inOrder(aFilteredTest, objectQueries, anExpe);
		boolean retVal = inOrder(aTestTraceableList, aCorrectTraceableList, expectedClasses(), true);
		TraceUtility.stopExistingTrace(traceableLog);
		if (!retVal)
			System.out.println("MVC Test failed");
		return retVal;		
	
		
		
	}
	

	public static boolean checkMVCPattern(List<Traceable> aTraceableList) {
		int inputIndex = indexOf(aTraceableList, ListEditInput.class, 0);
		if (inputIndex < 0)
			return false;
		ListEditInput anInput = (ListEditInput) aTraceableList.get(inputIndex);
		int actionIndex = indexOf(aTraceableList, ListEditMade.class, inputIndex + 1);
		if (actionIndex < 0)
			return false;
		ListEditMade anAction = (ListEditMade) aTraceableList.get(actionIndex);
		int notificationIndex = indexOf(aTraceableList, ListEditNotified.class, actionIndex + 1);	
		if (notificationIndex < 0)
			return false;
		ListEditNotified aNotification = (ListEditNotified) aTraceableList.get(notificationIndex);

		int observationIndex = indexOf(aTraceableList, ListEditObserved.class, notificationIndex + 1);
		if (observationIndex < 0) {
			return false;
		}
		ListEditObserved anObservation = (ListEditObserved) aTraceableList.get(observationIndex);

		boolean equalEdits = anInput.equalsEdit(anAction) &&
				anAction.equalsEdit(aNotification) &&
				aNotification.equalsEdit(anObservation);
		boolean differentClasses = 
				!(anInput.getEventSource().equals(anAction.getEventSource())) &&
				!(anObservation.getEventSource().equals(anAction.getEventSource()));
		return equalEdits && differentClasses;
	
	}
	

	public static void main (String[] args) {
		System.out.println(modularEchoCheck("traceLogEcho.txt"));
	}
	
	

}
