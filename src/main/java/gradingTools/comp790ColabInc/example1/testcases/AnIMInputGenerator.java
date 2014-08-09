package gradingTools.comp790ColabInc.example1.testcases;

import static gradingTools.comp790ColabInc.example1.testcases.IMUtililties.*;
import static util.trace.session.SessionTraceUtility.clientJoined;

import java.util.ArrayList;
import java.util.List;

import util.misc.ThreadSupport;
import util.pipe.AConsoleModel;
import util.trace.Traceable;
import util.trace.Tracer;
import bus.uigen.trace.TraceUtility;

public class AnIMInputGenerator extends AModularEchoInputGenerator 
	implements IMInputGenerator {
	protected boolean causalPhaseStarted;
	protected String aliceInput, bobInput, cathyInput;
	protected boolean aliceJoined, bobJoined, cathyJoined;
	// protected boolean causalPoemEntered;
	protected boolean aliceInputOver, bobInputOver, cathyInputOver;
	protected boolean aliceFinalOutputOver, bobFinalOutputOver,
			cathyFinalOutputOver;
	protected boolean aliceReceivedCathyCausalInput,
			bobReceivedCathyCausalInput, cathyReceivedCathyCausalOutput;
	// not necessary but adding for debugging delays
	protected boolean aliceReceivedBobCausalInput, bobReceivedAliceCausalInput,
			cathyReceivedAliceCausalInput, cathyReceivedBobCausalInput;

	// protected boolean aliceCorrect, bobCorrect, cathyCorrect;
//	protected ConsoleModel aliceConsole, bobConsole, cathyConsole,
//			sessionManagerConsole;
	protected String aliceProcess, bobProcess, cathyProcess; //, sessionManagerProcess;

	protected boolean joinPhaseTerminated;
	protected boolean causalPhaseTerminated;
	protected boolean causalPhasesTerminated;
	protected boolean viewOutputPhaseStarted;

	// protected boolean inputOver;
	protected String finalOutput;

	protected String aliceFinalOutput, bobFinalOutput, cathyFinalOutput;

//	History history = new AHistory();
	List<String> history = new ArrayList();

	int numCausalPhases = 2;
	protected boolean enterDummyStrings = true;
	protected boolean waitBeforeDummy = true;

//	protected boolean aliceEqualsBobOutput;
//	protected boolean aliceEqualsCathyOutput;
//	protected boolean bobEqualsCathyOutput;
	
	
	public void initCausalPhase() {
		System.out.println("Causal initialized");
		// causalPhaseStarted = false;
		// causalPoemEntered = false;
		causalPhaseTerminated = false;
		aliceInputOver = false;
		bobInputOver = false;
		cathyInputOver = false;
		aliceReceivedCathyCausalInput = false;
		bobReceivedCathyCausalInput = false;
		cathyReceivedCathyCausalOutput = false;
		aliceReceivedBobCausalInput = false;
		bobReceivedAliceCausalInput = false;
		cathyReceivedAliceCausalInput = false;
		cathyReceivedBobCausalInput = false;

	}

	

	void computeFinalHistory() {
		// History aHistory = new AHistory();
		// aHistory.add(0, poem[0]);
		// aHistory.add(1, poem[1]);
		// aHistory.add(2, poem[2]);
		// finalOutput = AnEchoerInteractor.toString(aHistory);

	}

	protected boolean causalPoemEntered() {
		return aliceReceivedCathyCausalInput && bobReceivedCathyCausalInput
				&& cathyReceivedCathyCausalOutput;
	}
	// is too late to initialize them here
	@Override
	public void executionStarted() {
//		aliceProcess = processNames.get(1);
//		bobProcess = processNames.get(2);
//		cathyProcess = processNames.get(3);
	}
	
	@Override
	public void processNamesAdded() {
		aliceProcess = processNames.get(1);
		bobProcess = processNames.get(2);
		cathyProcess = processNames.get(3);
	}

	
//
//	protected Class[] composeMainClasses() {
//		return new Class[] { sessionManagerClass(), aliceClass(), bobClass(),
//				cathyClass() };
//	}
//
//	protected Class sessionManagerClass() {
//		return SessionManagerServerStarter.class;
//		// return OTSessionManagerServerStarter.class;
//
//	}
//
//	protected Class aliceClass() {
//		return AliceIM.class;
//		// return AliceOTIM.class;
//	}
//
//	protected Class bobClass() {
//		return BobIM.class;
//		// return BobOTIM.class;
//	}
//
//	protected Class cathyClass() {
//		return CathyIM.class;
//		// return CathyOTIM.class;
//	}

	protected boolean allJoined() {
		return aliceJoined && bobJoined && cathyJoined;
	}

	// protected boolean allCorrect() {
	// return aliceOutptCorrect && bobCorrect && cathyCorrect;
	// }

	protected void processAllJoined() {
		startCausalPhase();

	}

	protected void startCausalPhase() {
		System.out.println("causal phase started");
		causalPhaseStarted = true;
		enterAlicePart();

	}

	protected void enterAlicePart() {
		if (enterDummyStrings)
		enterAliceDummyString();
		aliceInput = POEM[0];
		notifyNewInputLine(aliceProcess, aliceInput);
		history.add(aliceInput);
	}

	int nextDummy = 0;

	protected void enterAliceDummyString() {
		String aDummyString = processNames.get(1) + nextDummy;
		history.add(aDummyString);
		System.out.println("Alice process:" + aliceProcess);
		notifyNewInputLine(aliceProcess, aDummyString);
		nextDummy++;
		if (waitBeforeDummy)
		ThreadSupport.sleep(100);
	}

	protected void enterBobDummyString() {
		String aDummyString = processNames.get(2) + nextDummy;
		history.add(aDummyString);
		notifyNewInputLine(bobProcess, aDummyString);
		nextDummy++;
		if (waitBeforeDummy)

		ThreadSupport.sleep(100);

	}

	protected void enterCathyDummyString() {
		String aDummyString = processNames.get(3) + nextDummy;
		history.add(aDummyString);
		notifyNewInputLine(cathyProcess, aDummyString);
		nextDummy++;
		if (waitBeforeDummy)

		ThreadSupport.sleep(100);

	}

	protected void enterBobPart() {
		if (enterDummyStrings)

		enterBobDummyString();
		bobInput = POEM[1];
		notifyNewInputLine(bobProcess, bobInput);
		history.add(bobInput);
	}

	protected void enterCathyPart() {
		if (enterDummyStrings)

		enterCathyDummyString();
		cathyInput = POEM[2];
		notifyNewInputLine(cathyProcess, cathyInput);
		history.add(cathyInput);

	}

	protected boolean maybeProcessJoinPhase(
			String aProcessName, String anOutputLine) {
		if (allJoined())
			return false; // go to next phase
		System.out.println("Process name:" + aProcessName + " Output line:" + anOutputLine);
//		if (!isOutputLine(aProcessName, anOutputLine))
//			return true;
		// we are only processing server events
		if (!(processNames.get(0).equals(aProcessName))
		// || !isOutputLine(aProcessName, anOutputLine)
				|| !Tracer.isInfo(anOutputLine))
			return true; // irrelevant event, but join phase is not over
		Traceable traceable = TraceUtility
				.toTraceable(anOutputLine);
		if (traceable != null) {
			if (clientJoined(traceable, processNames.get(1)))
				aliceJoined = true;
			else if (clientJoined(traceable, processNames.get(2)))
				bobJoined = true;
			else if (clientJoined(traceable, processNames.get(3)))
				cathyJoined = true;

			// move this to main loop
			if (allJoined()) {
				// the main loop has to be told about this

				joinPhaseTerminated = true;
				// this phase is over, trigger the next phase by
				// making alice start the poem
				// aliceConsole.setInput(poem[0]);
				return false;

				// processAllJoined();
			}
		}
		return true;
	}

	protected boolean inputOver() {
		return aliceInputOver && bobInputOver && cathyInputOver;
	}

	protected boolean outputOver() {
		return aliceFinalOutputOver && bobFinalOutputOver
				&& cathyFinalOutputOver;
	}

	public static Traceable toTraceable(String aProcessName, String anOutputLine) {
		return Traceable.toTraceable(anOutputLine);
	}

	
	protected boolean causalInteractiveEnterPoem(
			String aProcessName, String anOutputLine) {

		// we will get output echoed
		// as info, so let us ignore
		// the info part
		if (Tracer.isInfo(anOutputLine))
			return true; // keep going
		if (receivedRemoteEcho(aProcessName,  anOutputLine, processNames.get(2), aliceInput,
				processNames.get(1))) {
			// if (isConsole(aConsoleModelEvent, bobClass())
			// && !Tracer.isInfo(anOutputLine) && // we will get output echoed
			// // as info, so let us ignore
			// // the info part
			// remoteEchoOf(aConsoleModelEvent, poem[0], DistributedTags.ALICE)) {
			enterBobPart();
			bobReceivedAliceCausalInput = true;
			System.out.println(numCausalPhases
					+ "Bob received alice causal input:" + aliceInput);

			// when bob hears remote echo of
			// alice's first line, he utters
			// next line

		} else if (receivedRemoteEcho(aProcessName,  anOutputLine, processNames.get(3),
				bobInput, processNames.get(2))) {

			// isConsole(aConsoleModelEvent, cathyClass())
			// && !Tracer.isInfo(anOutputLine) && //
			// remoteEchoOf(aConsoleModelEvent, poem[1], DistributedTags.BOB)) {
			// // similarly cathy follows bob
			System.out.println(numCausalPhases
					+ "Cathy received bob causal input:" + bobInput);

			cathyReceivedBobCausalInput = true;

			enterCathyPart();
			// causalPoemEntered = true;
			// finalOutput = AnEchoInteractor.toString(history);

			// inputOver = true; // for some reason this was deleted
		} else if (receivedRemoteEcho(aProcessName,  anOutputLine, processNames.get(1),
				cathyInput, processNames.get(3))) {
			aliceReceivedCathyCausalInput = true;
			System.out.println(numCausalPhases
					+ "Alice received cathy causal input:" + cathyInput);

		} else if (receivedRemoteEcho(aProcessName,  anOutputLine, processNames.get(2),
				cathyInput, processNames.get(3))) {
			bobReceivedCathyCausalInput = true;
			System.out.println(numCausalPhases
					+ "Bob received cathy causal input:" + cathyInput);

		} else if (receivedLocalEcho(aProcessName,  anOutputLine, processNames.get(3),
				cathyInput)) {
			cathyReceivedCathyCausalOutput = true;
		} else if (receivedRemoteEcho(aProcessName,  anOutputLine, processNames.get(2),
				aliceInput, processNames.get(1))) {
			bobReceivedAliceCausalInput = true;
			System.out.println(numCausalPhases
					+ "Bob received alice causal input:" + aliceInput);
		} else if (receivedRemoteEcho(aProcessName,  anOutputLine, processNames.get(1),
				bobInput, processNames.get(2))) {
			aliceReceivedBobCausalInput = true;
			System.out.println(numCausalPhases
					+ "Alice received bob causal input:" + bobInput);
		}
		return causalPoemEntered();
		// return true;

	}

	public static boolean receivedRemoteEcho(
			String aProcessName, String anOutputLine, Class aReceiver,
			String anEntry, String aSender) {
		// System.out.println("Checking for remote Entry:" +
		// aConsoleModelEvent.getSource() + " " +
		// aConsoleModelEvent.getNewValue());
		return aProcessName.equals(aReceiver) &&
		// !Tracer.isInfo(anOutputLine) && //
				remoteEchoOf(aProcessName,  anOutputLine, anEntry, aSender);
	}
	public static boolean receivedRemoteEcho(
			String aProcessName, String anOutputLine, String aReceiver,
			String anEntry, String aSender) {
		// System.out.println("Checking for remote Entry:" +
		// aConsoleModelEvent.getSource() + " " +
		// aConsoleModelEvent.getNewValue());
		return aProcessName.equals(aReceiver) &&
		// !Tracer.isInfo(anOutputLine) && //
				remoteEchoOf(aProcessName,  anOutputLine, anEntry, aSender);
	}

	public static boolean receivedLocalEcho(
			String aProcessName, String anOutputLine, Class aReceiver,
			String anEntry) {
		return aProcessName.equals(aReceiver) &&
		// !Tracer.isInfo(anOutputLine) && //
				EchoUtilities.echoOf(aProcessName,  anOutputLine, anEntry);
	}
	
	public static boolean receivedLocalEcho(
			String aProcessName, String anOutputLine, String aReceiver,
			String anEntry) {
		return aProcessName.equals(aReceiver) &&
		// !Tracer.isInfo(anOutputLine) && //
				EchoUtilities.echoOf(aProcessName,  anOutputLine, anEntry);
	}

	/*
	 * output for one user may be over before input for another starts, so the
	 * history command input and output are mixed
	 */
	

	void enterAliceHistory() {
		aliceInputOver = true; // set it before property changed
		notifyNewInputLine(aliceProcess, EchoUtilities.HISTORY);
	}

	void enterBobHistory() {
		bobInputOver = true;
		notifyNewInputLine(bobProcess, EchoUtilities.HISTORY);
	}

	void enterCathyHistory() {
		cathyInputOver = true;
		notifyNewInputLine(cathyProcess, EchoUtilities.HISTORY);
	}

	/*
	 * output for one user may be over before input for another starts, so the
	 * history command input and output are mixed
	 */
	
	
	static final String aPromptWithNewLine = EchoUtilities.PROMPT + "\n";

	public static boolean isApplicationOutput(String aStringWithNewLine) {
		return !aStringWithNewLine.equals(aPromptWithNewLine)
				&& !aStringWithNewLine
						.startsWith(AConsoleModel.DEFAULT_INPUT_PROMPT)
				&& !aStringWithNewLine.equals("\n");
	}

	protected boolean viewHistoryOutput(String aProcessName, String anOutputLine) {
		if (Tracer.isInfo(anOutputLine))
			return true;
		String anOutputWithNewLine = anOutputLine
				+ "\n";

		if (aliceInputOver && aProcessName.equals(processNames.get(1))
		// && anOutputWithNewLine.equals(finalOutput))
				&& isApplicationOutput(anOutputWithNewLine)

		) {

			aliceFinalOutputOver = true;
			aliceFinalOutput = anOutputWithNewLine;

			// inputOver = true; // for some reason this was deleted
		} else if (bobInputOver && aProcessName.equals(processNames.get(2))
		// && anOutputWithNewLine.equals(finalOutput))
				&& isApplicationOutput(anOutputWithNewLine))

		{

			bobFinalOutputOver = true;
			bobFinalOutput = anOutputWithNewLine;
			// inputOver = true; // for some reason this was deleted
		} else if (cathyInputOver
				&& aProcessName.equals(processNames.get(3))
				// && anOutputWithNewLine.equals(finalOutput))
				&& isApplicationOutput(anOutputWithNewLine))

		{

			cathyFinalOutputOver = true;
			cathyFinalOutput = anOutputWithNewLine;
			// inputOver = true; // for some reason this was deleted
		}
		// return true;
		return !outputOver(); // return false is output is over so next phase can go

	}

	
	
	

	protected void processCausalPhasesFinished(
			String aProcessName, String anOutputLine) {
		// finalOutput = AnEchoInteractor.toString(history);
		// enterAliceHistory();
		// enterBobHistory();
		// enterCathyHistory();
		causalPhasesTerminated = true;
		enterHistoryCommands(aProcessName, anOutputLine);

//		processAllPhasesFinished(aProcessName, anOutputLine);
	}
	// not called currently
	protected void processAllPhasesFinished(
			String aProcessName, String anOutputLine) {
		System.out.println("All phases finished");
//		System.out.println("causal poem entered" + causalPoemEntered());
//		finalOutput = AnEchoInteractor.toString(history);
		finalOutput = EchoUtilities.toString(history);

		enterAliceHistory();
		enterBobHistory();
		enterCathyHistory();
	}
	
	protected void enterHistoryCommands(String aProcessName, String anOutputLine) {
//		finalOutput = AnEchoInteractor.toString(history);
		finalOutput = EchoUtilities.toString(history);

		enterAliceHistory();
		enterBobHistory();
		enterCathyHistory();
	}

	protected void restartCausalPhase(String aProcessName, String anOutputLine) {
		initCausalPhase();
		startCausalPhase();
		// enterAlicePart();
	}

	protected void startPhaseAfterCausalPhase(
			String aProcessName, String anOutputLine) {
		restartCausalPhase(aProcessName, anOutputLine);

	}

	protected void processCausalPhaseFinished(
			String aProcessName, String anOutputLine) {
		// causalInteractiveEnterPoem(aProcessName, anOutputLine);
		if (causalPoemEntered()) {
			numCausalPhases--;
			if (numCausalPhases > 0) {
				// causalPoemEntered = false;
				// initCausalPhase();
				// enterAlicePart();
				// restartCausalPhase(aProcessName, anOutputLine);
				startPhaseAfterCausalPhase(aProcessName, anOutputLine);
			} else {
				causalPhasesTerminated = true;
				processCausalPhasesFinished(aProcessName, anOutputLine);

			}

		}

	}

	protected boolean maybeProcessCausalIOPhase(
			String aProcessName, String anOutputLine) {
		// do we need this?
		// if (!causalPhaseStarted) // continue phase, but it is not over
		//
		// return true;
		// if (outputOver()) {
		// return false;
		// }
		

		if (causalPoemEntered()) {
//			viewHistoryOutput(aProcessName, anOutputLine);
			return false;
		} else {
			causalInteractiveEnterPoem(aProcessName, anOutputLine);
			// causalInteractiveEnterPoem(aProcessName, anOutputLine);
			if (causalPoemEntered()) {
				causalPhaseTerminated = true;
				// processCausalPhaseFinished(aProcessName, anOutputLine);
				return false;

			}

			// numCausalPhases--;
			// if (numCausalPhases > 0) {
			// // causalPoemEntered = false;
			// initCausalPhase();
			// enterAlicePart();
			// } else {
			// finalOutput = AnEchoInteractor.toString(history);
			// enterAliceHistory();
			// enterBobHistory();
			// enterCathyHistory();
			//
			// }
			// }
		}
		return true;

	}

	// should make sure property changes are serialized

	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
			
	
		if (maybeProcessJoinPhase(aProcessName, anOutputLine))
			return;
		else if (joinPhaseTerminated) {
			joinPhaseTerminated = false;
			processAllJoined(); // trigger more events, and these events and state changes made by them will not be consumed by previous phase
			return;
			// this will consume join phase event but it will be ignored
			// as it is of different type
			// should have transition here also

		} else if (maybeProcessCausalIOPhase(aProcessName, anOutputLine))
			return;
		else if (causalPhaseTerminated) {
			causalPhaseTerminated = false;
			// we do not really need the event do we?
			processCausalPhaseFinished(aProcessName, anOutputLine);
			return;
//		} 
//		else if (causalPhasesTerminated) {
//			causalPhasesTerminated = false; // do not consume event
////			processCausalPhaseFinished(aProcessName, anOutputLine);
//
//			return;
//		} 
//		else if (!viewOutputPhaseStarted) {
//			enterHistoryCommands(aProcessName, anOutputLine);
		} else if (viewHistoryOutput(aProcessName, anOutputLine))
			return;
		// if (maybeProcessTestPhase(aProcessName, anOutputLine)) return; // for some
		// reason
		// launcher.terminateAll();
		else if (!isTerminated())
			notifyInteractionTermination();

	}



	public String getAliceFinalOutput() {
		return aliceFinalOutput;
	}





	public String getBobFinalOutput() {
		return bobFinalOutput;
	}



	



	public String getCathyFinalOutput() {
		return cathyFinalOutput;
	}



	


//	public boolean isAliceEqualsBobOutput() {
//		return aliceEqualsBobOutput;
//	}
//
//
//
//	
//
//
//
//	public boolean isAliceEqualsCathyOutput() {
//		return aliceEqualsCathyOutput;
//	}
//
//
//
//
//
//	public boolean isBobEqualsCathyOutput() {
//		return bobEqualsCathyOutput;
//	}






}
