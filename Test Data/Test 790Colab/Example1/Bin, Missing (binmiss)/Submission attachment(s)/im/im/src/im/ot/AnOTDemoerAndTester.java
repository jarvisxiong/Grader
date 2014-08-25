package im.ot;

import static im.IMUtililties.remoteEchoOf;
import static util.models.ConsoleModelUtility.containsText;
import static util.models.ConsoleModelUtility.getText;
import static util.models.ConsoleModelUtility.isConsole;
import static util.models.ConsoleModelUtility.isInfo;
import static util.models.ConsoleModelUtility.isOutput;
import static util.models.ConsoleModelUtility.isOutputLine;
import static util.trace.session.SessionTraceUtility.clientJoined;
import im.AliceIM;
import im.AnIMDemoerAndTester;
import im.BobIM;
import im.CathyIM;

import java.beans.PropertyChangeEvent;
import java.util.List;

import echo.modular.AModularEchoDemoerAndTester;
import echo.modular.AnEchoInteractor;
import trace.im.IMTraceChecker;
import trace.ot.OTTraceChecker;
import util.misc.ThreadSupport;
import util.models.ConsoleModel;
import util.tags.DistributedTags;
import util.trace.Traceable;
import bus.uigen.models.ADemoerAndTester;
import bus.uigen.models.DemoerAndTester;
import bus.uigen.models.MainClassListLauncher;
import bus.uigen.trace.TraceUtility;

public class AnOTDemoerAndTester extends AnIMDemoerAndTester {
	protected boolean concurrentPhaseStarted;
	protected boolean aliceReceivedBobConcurrentInput;
	protected boolean aliceReceivedCathyConcurrentInput;
	protected boolean bobReceivedAliceConcurrentInput;
	protected boolean bobReceivedCathyConcurrentInput;
	protected boolean cathyReceivedAliceConcurrentInput;
	protected boolean cathyReceivedBobConcurrentInput;
	protected boolean concurrentPoemPartEntered;	
	protected int numConcurrentPhases = 2;
//	protected boolean transition = true;
	protected boolean concurrentPhaseTerminated;
	protected boolean concurrentPhasesTerminated;
	public static final int SERVER_SERIALIZATION_WAIT = 1000;

//	String[] otPoem = {
//			"My little horse must think it queer",  			 
//			"To stop without a farmhouse near",  			 
//			"Between the woods and frozen lake"
//	};

	
	public AnOTDemoerAndTester() {
	}
	
	public void initConcurrentPhase() {
		System.out.println("Initializing concurrent phase:" + numConcurrentPhases);
		concurrentPhaseStarted = true;
		aliceReceivedBobConcurrentInput = false;
		aliceReceivedCathyConcurrentInput = false;
		bobReceivedAliceConcurrentInput = false;
		bobReceivedCathyConcurrentInput = false;
		cathyReceivedAliceConcurrentInput = false;
		cathyReceivedBobConcurrentInput = false;
		concurrentPoemPartEntered = false;
	}
	
	
		
	
//	protected Class[] composeMainClasses() {
//		return new Class[] {
//			sessionManagerClass(),
//			aliceClass(),
//			bobClass(),
//			cathyClass()				
//		};
//	}
	
	protected Class sessionManagerClass() {
		return OTSessionManagerServerStarter.class;
	}
	
	protected Class aliceClass() {
		return 	AliceOTIM.class;
	}
	
	protected Class bobClass() {
		return 	BobOTIM.class;
	}
	
	protected Class cathyClass() {
		return 	CathyOTIM.class;
	}
	
	void enterConcurrentPart() {
//		enterAliceDummyString();
//		ThreadSupport.sleep(100); // avoid race conditions
		enterAlicePart();
		ThreadSupport.sleep(SERVER_SERIALIZATION_WAIT); // make it deterministic
//		enterBobDummyString();
//		ThreadSupport.sleep(100); // avoid race conditions
		enterBobPart();
		ThreadSupport.sleep(SERVER_SERIALIZATION_WAIT);

//		ThreadSupport.sleep(100);
//		enterCathyDummyString();
//		ThreadSupport.sleep(100); // avoid race conditions
		enterCathyPart();
		concurrentPoemPartEntered = true;
	}
	
	protected void processAllJoined() {
		enterConcurrentPart();
	}
	
	
	protected   Boolean testAgainstCorrectTranscripts(List<Traceable> aTestTraceableList, 
			List<Traceable> aCorrectTraceableList) {
		return super.testAgainstCorrectTranscripts(aTestTraceableList, aCorrectTraceableList)
		& OTTraceChecker.compareOTSequences(aTestTraceableList,
				aCorrectTraceableList);
				
	}

	

	
//	protected boolean maybeProcessInputPhase(PropertyChangeEvent aConsoleModelEvent) {
//		// 
//		return true;
//		
//	}
//	protected boolean maybeProcessTestPhase(PropertyChangeEvent aConsoleModelEvent) {
//		if (allCorrect())
//			return false;
//		if (!isOutput(aConsoleModelEvent))
//			return true;
//		if (!containsText(aConsoleModelEvent, finalOutput))
//			return true;
//		if (isConsole(aConsoleModelEvent, aliceClass())) {
//				aliceCorrect = true;
//		} else if (isConsole(aConsoleModelEvent, bobClass())) {
//				bobCorrect = true;
//		} else if (isConsole(aConsoleModelEvent, cathyClass())) {
//				cathyCorrect = true;
//		}
//		return true;		
//	}
	protected boolean concurrentInputOver() {
		return aliceReceivedBobConcurrentInput && aliceReceivedCathyConcurrentInput
				&& bobReceivedAliceConcurrentInput && bobReceivedCathyConcurrentInput 
				&& cathyReceivedAliceConcurrentInput && cathyReceivedBobConcurrentInput;
	}
//	protected boolean maybeProcessConcurrentInputPhase(
//			PropertyChangeEvent aConsoleModelEvent) {
//		if (concurrentInputOver())
//			return false;
//		if (!isOutputLine(aConsoleModelEvent)) // continue phase, but it is not
//												// over
//			return true;
//		if (concurrentPoemPartEntered)
//			return 
//		
//		if (causalPoemEntered)
//			enterHistoryAndViewOutput(aConsoleModelEvent);
//		else
//			causalInteractiveEnterPoem(aConsoleModelEvent);
//		return true;
//
//	}
	@Override
	public void propertyChange(PropertyChangeEvent aConsoleModelEvent) {
//		System.out.println("Received property" + aConsoleModelEvent.getSource() + " " + aConsoleModelEvent.getNewValue());

		// process each of the phases after the preceding ones have finished
		if (maybeProcessJoinPhase(aConsoleModelEvent))
			return;
		else if (joinPhaseTerminated) {
			joinPhaseTerminated = false;
			processAllJoined();
			return;

		} else	if (maybeProcessConcurrentIOPhase(aConsoleModelEvent)) return;
		// do  not consume first event after concurrent phase as it was
		// consumed by the concurrent phase
		else if (concurrentPhaseTerminated) {
			// start causal phase here after the event has been consumed
			// and not in processConcurrentPhaseFinished
			concurrentPhaseTerminated = false;
			System.out.println("concurrent phase terminated");
			processConcurrentPhaseFinished(aConsoleModelEvent);
		
//			 do not trigger input here
			return;
//		} else if (!causalPhaseStarted) {
//			processConcurrentPhaseFinished(aConsoleModelEvent);

		} else	if (maybeProcessCausalIOPhase(aConsoleModelEvent)) {
			return;
		} else if (causalPhaseTerminated) {
			causalPhaseTerminated = false;
			// we do not really need the event do we?
			processCausalPhaseFinished(aConsoleModelEvent);
		
	} else if (viewHistoryOutput(aConsoleModelEvent))
		return;
	else if (!isTerminated())
			notifyInteractionTermination();
//		if (maybeProcessConcurrentIOPhase(aConsoleModelEvent)) return;		

//		if (maybeProcessTestPhase(aConsoleModelEvent)) return;
//		launcher.terminateAll();
		
	}
	// be careful about enetring new input as the previous inpt has not been consumed
	// and transition will get confused
	protected void processConcurrentPhasesFinished(PropertyChangeEvent aConsoleModelEvent) {
//		causalPhaseStarted = true;
//		enterAlicePart();
		startCausalPhase();
	}
	
	protected void restartConcurrentPhase(PropertyChangeEvent aConsoleModelEvent) {
		initConcurrentPhase();
		enterConcurrentPart();
	}
	protected void startPhaseAfterConcurrentPhase(PropertyChangeEvent aConsoleModelEvent) {
		restartConcurrentPhase(aConsoleModelEvent);


	}
	
	protected boolean processConcurrentPhaseFinished(PropertyChangeEvent aConsoleModelEvent) {
		numConcurrentPhases --;
		if (numConcurrentPhases > 0)  {
			startPhaseAfterConcurrentPhase(aConsoleModelEvent);
			// let us start another concurrent phase
//			restartConcurrentPhase(aConsoleModelEvent);
//			initConcurrentPhase();
//			enterConcurrentPart();
			return true;
		} else {
			
//		causalPhaseStarted = true;
//		enterAlicePart();
			processConcurrentPhasesFinished(aConsoleModelEvent);
			return false;
		}
	}
	protected boolean maybeProcessConcurrentIOPhase(
			PropertyChangeEvent aConsoleModelEvent) {
		if (!isOutputLine(aConsoleModelEvent)) // continue phase, but it is not
			// over
			return true;

		// do this check so we dont waste time doing the other processing
		if (concurrentInputOver())
			return false;

		// we will get output echoed
		// as info, so let us ignore
		// the info part

		if (isInfo(aConsoleModelEvent))
			return true;
		if (receivedRemoteEcho(aConsoleModelEvent, DistributedTags.CLIENT_1, bobInput,
				BobIM.USER_NAME)) {
			aliceReceivedBobConcurrentInput = true;
			System.out.println( numConcurrentPhases + "aliceReceivedBobConcurrentInput:" + bobInput );


		} else if (receivedRemoteEcho(aConsoleModelEvent, DistributedTags.CLIENT_1,
				cathyInput, CathyIM.USER_NAME)) {
			aliceReceivedCathyConcurrentInput = true;
			System.out.println(numConcurrentPhases + "aliceReceivedCathyConcurrentInput:" + cathyInput);


		} else if (receivedRemoteEcho(aConsoleModelEvent, DistributedTags.CLIENT_2,
				aliceInput, AliceIM.USER_NAME)) {
			bobReceivedAliceConcurrentInput = true;
			System.out.println(numConcurrentPhases + "bobReceivedAliceConcurrentInput:" + aliceInput);

		} else if (receivedRemoteEcho(aConsoleModelEvent, DistributedTags.CLIENT_2,
				cathyInput, CathyIM.USER_NAME)) {
			bobReceivedCathyConcurrentInput = true;
			System.out.println(numConcurrentPhases + "bobReceivedCathyConcurrentInput:" + cathyInput);

		}else if (receivedRemoteEcho(aConsoleModelEvent, DistributedTags.CLIENT_3, bobInput,
				BobIM.USER_NAME)) {
			cathyReceivedBobConcurrentInput = true;
			System.out.println(numConcurrentPhases + "cathyReceivedBobConcurrentInput:" + bobInput );

			// inputOver = true; // for some reason this was deleted
		} else if (receivedRemoteEcho(aConsoleModelEvent, DistributedTags.CLIENT_3,
				aliceInput, AliceIM.USER_NAME)) {
			cathyReceivedAliceConcurrentInput = true;
			System.out.println(numConcurrentPhases + "cathyReceivedAliceConcurrentInput" + aliceInput);

			// inputOver = true; // for some reason this was deleted
		} 

		// do teh check again so we let caller know this phase is over
		// start causal phase
		if (concurrentInputOver()) {
			concurrentPhaseTerminated = true;
			return false;
					
//					processConcurrentPhaseFinished(aConsoleModelEvent);
//			if (numConcurrentPhases > 0)  {
//				// let us start another concurrent phase
//				numConcurrentPhases --;
//				initConcurrentPhase();
//				enterConcurrentPart();
//				return true;
//			} else {
//				
//			causalPhaseStarted = true;
//			enterAlicePart();
//			return false;
//			}
		}
		return true;

	}
	

}
