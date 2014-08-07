package gradingTools.comp790ColabInc.example1.testcases;

import static bus.uigen.query.QueryUtility.inOrder;
import static bus.uigen.query.QueryUtility.indicesOf;
import static bus.uigen.query.QueryUtility.valid;

import java.util.List;

import util.trace.Traceable;
import util.trace.TraceableLog;
import util.trace.session.MessageReceived;
import util.trace.session.ReceivedMessageDistributedToListeners;
import util.trace.session.SendDataRequest;
import bus.uigen.trace.TraceUtility;

public class IMTraceChecker extends ModularEchoTraceChecker{
	
	static Class[] imExpectedClasses = {
//			MessageSent.class, // a message may be sent multiple times, so we will not use it
			SendDataRequest.class,
			MessageReceived.class,
			ListEditReceived.class,
			ListEditSent.class,
	};
	static Class[] sendClasses = {
		ListEditSent.class,	 // must be before Message Sent
//		MessageSent.class, 		// a message may be sent multiple times, so we will not use it
		SendDataRequest.class

	};
	static Class[] receiveClasses = {
		// these two events are in the same thread so we can make sure that a pair of them is processed before the next pair
		ReceivedMessageDistributedToListeners.class, // must be before ListEditReceived
		ListEditReceived.class,		

	};
	public static Class[] expectedClasses() {
//		return append(myExpectedClasses, EchoTraceChecker.expectedClasses());
		return imExpectedClasses;
	}
	public static List<Integer> getNextSequenceOfExpectedReceiveEvents(List<Traceable> aTraceableList, int aStartIndex) {
		TraceableLog aTraceableLog = TraceUtility.startNewTrace();
		// get the first sequence of four events in modularExpected classes
		List<Integer> anIndexList = indicesOf(aTraceableList, receiveClasses, true, aStartIndex, true);
		TraceUtility.stopExistingTrace(aTraceableLog);
		System.out.println(aTraceableLog);
		if (!valid(anIndexList)) // did not find the events at all
			return null;
		return anIndexList;
		
	}
	
	public static List<Integer> getNextSequenceOfExpectedSendEvents(List<Traceable> aTraceableList, int aStartIndex) {
		TraceableLog aTraceableLog = TraceUtility.startNewTrace();
		// get the first sequence of four events in modularExpected classes
		List<Integer> anIndexList = indicesOf(aTraceableList, sendClasses, true, aStartIndex, true);
		TraceUtility.stopExistingTrace(aTraceableLog);
		System.out.println(aTraceableLog);
		if (!valid(anIndexList)) // did not find the events at all
			return null;
		return anIndexList;
		
	}
	
	public static boolean isNextSequenceOfReceiveEventsCompatible(List<Traceable> aTraceableList, List<Integer> anIndexList) {
		// list edit is "sent" before the message is sent
		ReceivedMessageDistributedToListeners aMessageReceived = (ReceivedMessageDistributedToListeners) aTraceableList.get(anIndexList.get(0));

		ListEditReceived aListEditReceived = (ListEditReceived) aTraceableList.get(anIndexList.get(1));
		String aMessage= aMessageReceived.getMessage();
//		return aDataText.contains("" + aListEditSent.getIndex() ) &&
//				aDataText.contains(aListEditSent.getElement().toString());
		
		// OT might change the index
		return //aMessage.contains("" + aListEditReceived.getIndex() ) &&
				aMessage.contains(aListEditReceived.getElement().toString());
		
	}
	
	public static boolean isNextSequenceOfSendEventsCompatible(List<Traceable> aTraceableList, List<Integer> anIndexList) {
		// list edit is "sent" before the message is sent
		ListEditSent aListEditSent = (ListEditSent) aTraceableList.get(anIndexList.get(0));
//		MessageSent aMessageSent = (MessageSent) aTraceableList.get(anIndexList.get(1));
		SendDataRequest aMessageSent = (SendDataRequest) aTraceableList.get(anIndexList.get(1));

//		String aDataText = aMessageSent.getData().toString();
		String aMessage= aMessageSent.getMessage();
//		return aDataText.contains("" + aListEditSent.getIndex() ) &&
//				aDataText.contains(aListEditSent.getElement().toString());
		
		// OT will not change the index sent
		return aMessage.contains("" + aListEditSent.getIndex() ) &&
				aMessage.contains(aListEditSent.getElement().toString());
		
	}
	public static boolean intraSequenceSendCheckIM(List<Traceable> aTraceableList) {
		int startIndex = 0;
		boolean retVal = true;
		boolean foundASequence = false; //if found no instance of events, return false
		System.out.println("Intra Sequence Send Test");
		while (true) {
		// get the first sequence of four events in modularExpected classes
		List<Integer> anIndexList = getNextSequenceOfExpectedSendEvents(aTraceableList, startIndex);
		if (anIndexList == null || anIndexList.size() == 0)
			return foundASequence && retVal;
		foundASequence = true;
		// found events, now test their equality
		boolean thisCheck = isNextSequenceOfSendEventsCompatible(aTraceableList, anIndexList);
		if (!thisCheck) {
			System.out.println("Send check failed:" + anIndexList);
		}
		retVal = retVal & thisCheck;
		// move past the last matched index
		startIndex = anIndexList.get(anIndexList.size() -1) + 1;
		}
		
	}
	
	public static boolean intraSequenceReceiveCheckIM(List<Traceable> aTraceableList) {
		int startIndex = 0;
		boolean retVal = true;
		boolean foundASequence = false; //if found no instance of events, return false
		System.out.println("Intra Sequence Receive Test");
		while (true) {
		// get the first sequence of four events in modularExpected classes
		List<Integer> anIndexList = getNextSequenceOfExpectedReceiveEvents(aTraceableList, startIndex);
		if (anIndexList == null || anIndexList.size() == 0)
			return foundASequence && retVal;
		foundASequence = true;
		// found events, now test their equality
		boolean thisCheck = isNextSequenceOfReceiveEventsCompatible(aTraceableList, anIndexList);
		retVal = retVal && thisCheck;
		if (!thisCheck) {
			System.out.println("Not compatible:" + anIndexList);
		}
		// move past the last matched index
		startIndex = anIndexList.get(anIndexList.size() -1) + 1;
		}
		
	}
	
	public static boolean intraSequenceCheckIM(List<Traceable> aTraceableList) {
		boolean  aReceiveCheck = intraSequenceReceiveCheckIM(aTraceableList);
		boolean aSendCheck = intraSequenceSendCheckIM(aTraceableList);
		return aReceiveCheck & aSendCheck;
	}


	public static boolean compareIMSequences(
			List<Traceable> aTestTraceableList, 
			List<Traceable> aCorrectTraceableList) {
		System.out.println("Comparing IM Sequences");
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
