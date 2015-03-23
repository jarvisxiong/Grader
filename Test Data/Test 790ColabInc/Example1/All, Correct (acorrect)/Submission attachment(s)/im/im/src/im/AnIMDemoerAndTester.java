package im;

import static im.IMUtililties.remoteEchoOf;
import static echo.monolithic.AMonolithicEchoDemoerAndTester.echoOf;
import static util.models.ConsoleModelUtility.containsText;
import static util.models.ConsoleModelUtility.getText;
import static util.models.ConsoleModelUtility.isConsole;
import static util.models.ConsoleModelUtility.isInfo;
import static util.models.ConsoleModelUtility.isOutput;
import static util.models.ConsoleModelUtility.isOutputLine;
import static util.trace.session.SessionTraceUtility.clientJoined;
import im.ot.AliceOTIM;
import im.ot.BobOTIM;
import im.ot.CathyOTIM;
import im.ot.OTSessionManagerServerStarter;




import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Set;

import echo.modular.AHistory;
import echo.modular.AModularEchoDemoerAndTester;
import echo.modular.AnEchoInteractor;
import echo.modular.EchoerInteractor;
import echo.modular.History;
import trace.echo.modular.ModularEchoTraceChecker;
import trace.im.IMTraceChecker;
import util.misc.ThreadSupport;
import util.models.AConsoleModel;
import util.models.ConsoleModel;
import util.tags.DistributedTags;
import util.trace.Traceable;
import bus.uigen.models.ADemoerAndTester;
import bus.uigen.models.DemoerAndTester;
import bus.uigen.models.MainClassListLauncher;
import bus.uigen.trace.TraceUtility;

public class AnIMDemoerAndTester extends AModularEchoDemoerAndTester {
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
	protected ConsoleModel aliceConsole, bobConsole, cathyConsole,
			sessionManagerConsole;

	protected boolean joinPhaseTerminated;
	protected boolean causalPhaseTerminated;
	protected boolean causalPhasesTerminated;
	protected boolean viewOutputPhaseStarted;

	// protected boolean inputOver;
	protected String finalOutput;

	protected String aliceFinalOutput, bobFinalOutput, cathyFinalOutput;

	History history;

	int numCausalPhases = 2;
	protected boolean enterDummyStrings = true;
	protected boolean waitBeforeDummy = true;

	protected boolean aliceEqualsBobOutput;
	protected boolean aliceEqualsCathyOutput;
	protected boolean bobEqualsCathyOutput;
	
	public AnIMDemoerAndTester(boolean anInteractive) {
		super(anInteractive);
		history = new AHistory();
	}
	
	public AnIMDemoerAndTester() {
		this(true);
	}

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

	@Override
	public void executeAll() {
		consoleModels = launcher.getOrCreateConsoleModels();
		aliceConsole = consoleModels.get(1);
		bobConsole = consoleModels.get(2);
		cathyConsole = consoleModels.get(3);
		launcher.addConsolesPropertyChangeListener(this); // input added in
															// response to
															// events
		launcher.executeAll();
	}

	protected Class[] composeMainClasses() {
		return new Class[] { sessionManagerClass(), aliceClass(), bobClass(),
				cathyClass() };
	}

	protected Class sessionManagerClass() {
		return SessionManagerServerStarter.class;
		// return OTSessionManagerServerStarter.class;

	}

	protected Class aliceClass() {
		return AliceIM.class;
		// return AliceOTIM.class;
	}

	protected Class bobClass() {
		return BobIM.class;
		// return BobOTIM.class;
	}

	protected Class cathyClass() {
		return CathyIM.class;
		// return CathyOTIM.class;
	}

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
		aliceInput = poem[0];
		aliceConsole.setInput(aliceInput);
		history.add(aliceInput);
	}

	int nextDummy = 0;

	protected void enterAliceDummyString() {
		String aDummyString = DistributedTags.CLIENT_1 + nextDummy;
		history.add(aDummyString);
		aliceConsole.setInput(aDummyString);
		nextDummy++;
		if (waitBeforeDummy)
		ThreadSupport.sleep(100);
	}

	protected void enterBobDummyString() {
		String aDummyString = DistributedTags.CLIENT_2 + nextDummy;
		history.add(aDummyString);
		bobConsole.setInput(aDummyString);
		nextDummy++;
		if (waitBeforeDummy)

		ThreadSupport.sleep(100);

	}

	protected void enterCathyDummyString() {
		String aDummyString = DistributedTags.CLIENT_3 + nextDummy;
		history.add(aDummyString);
		cathyConsole.setInput(aDummyString);
		nextDummy++;
		if (waitBeforeDummy)

		ThreadSupport.sleep(100);

	}

	protected void enterBobPart() {
		if (enterDummyStrings)

		enterBobDummyString();
		bobInput = poem[1];
		bobConsole.setInput(bobInput);
		history.add(bobInput);
	}

	protected void enterCathyPart() {
		if (enterDummyStrings)

		enterCathyDummyString();
		cathyInput = poem[2];
		cathyConsole.setInput(cathyInput);
		history.add(cathyInput);

	}

	protected boolean maybeProcessJoinPhase(
			PropertyChangeEvent aConsoleModelEvent) {
		if (allJoined())
			return false; // go to next phase
		if (!isOutputLine(aConsoleModelEvent))
			return true;
		// we are only processing server events
		if (!isConsole(aConsoleModelEvent, DistributedTags.SERVER)
		// || !isOutputLine(aConsoleModelEvent)
				|| !isInfo(aConsoleModelEvent))
			return true; // irrelevant event, but join phase is not over
		Traceable traceable = TraceUtility
				.toTraceable(getText(aConsoleModelEvent));
		if (traceable != null) {
			if (clientJoined(traceable, DistributedTags.CLIENT_1))
				aliceJoined = true;
			else if (clientJoined(traceable, DistributedTags.CLIENT_2))
				bobJoined = true;
			else if (clientJoined(traceable, DistributedTags.CLIENT_3))
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

	public static Traceable toTraceable(PropertyChangeEvent aConsoleModelEvent) {
		return Traceable.toTraceable((String) aConsoleModelEvent.getNewValue());
	}

	// protected void lookForHistoryOutput(PropertyChangeEvent
	// aConsoleModelEvent) {
	// String anOutputLine = (String) aConsoleModelEvent.getNewValue();
	// if (isInfo(aConsoleModelEvent) ) return;
	// if (isConsole(aConsoleModelEvent, aliceClass())
	// && anOutputLine.equals(finalOutput)) {
	//
	// aliceOutputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// } if (isConsole(aConsoleModelEvent, bobClass())
	// && anOutputLine.equals(finalOutput)) {
	//
	// bobOutputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// } if (isConsole(aConsoleModelEvent, cathyClass())
	// && anOutputLine.equals(finalOutput)) {
	//
	// cathyOutputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// }
	//
	//
	//
	//
	// }

	// protected void interactiveEnterPoem(PropertyChangeEvent
	// aConsoleModelEvent) {
	// if (isConsole(aConsoleModelEvent, bobClass())
	// && !isInfo(aConsoleModelEvent) && // we will get output echoed
	// // as info, so let us ignore
	// // the info part
	// remoteEchoOf(aConsoleModelEvent, poem[0], DistributedTags.ALICE)) {
	// enterBobPart();
	// // when bob hears remote echo of
	// // alice's first line, he utters
	// // next line
	// // bobInput = poem[1];
	// // bobConsole.setInput(bobInput);
	// // history.add(bobInput);
	// } else if (isConsole(aConsoleModelEvent, cathyClass())
	// && !isInfo(aConsoleModelEvent) && //
	// remoteEchoOf(aConsoleModelEvent, poem[1], DistributedTags.BOB)) {
	// // similarly cathy follows bob
	// // cathyInput = poem[2];
	// // cathyConsole.setInput(cathyInput);
	// // history.add(cathyInput);
	// enterCathyPart();
	// poemEntered = true;
	// finalOutput = AnEchoInteractor.toString(history);
	//
	// // inputOver = true; // for some reason this was deleted
	// }
	//
	// }
	protected boolean causalInteractiveEnterPoem(
			PropertyChangeEvent aConsoleModelEvent) {

		// we will get output echoed
		// as info, so let us ignore
		// the info part
		if (isInfo(aConsoleModelEvent))
			return true; // keep going
		if (receivedRemoteEcho(aConsoleModelEvent, DistributedTags.CLIENT_2, aliceInput,
				DistributedTags.CLIENT_1)) {
			// if (isConsole(aConsoleModelEvent, bobClass())
			// && !isInfo(aConsoleModelEvent) && // we will get output echoed
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

		} else if (receivedRemoteEcho(aConsoleModelEvent, DistributedTags.CLIENT_3,
				bobInput, DistributedTags.CLIENT_2)) {

			// isConsole(aConsoleModelEvent, cathyClass())
			// && !isInfo(aConsoleModelEvent) && //
			// remoteEchoOf(aConsoleModelEvent, poem[1], DistributedTags.BOB)) {
			// // similarly cathy follows bob
			System.out.println(numCausalPhases
					+ "Cathy received bob causal input:" + bobInput);

			cathyReceivedBobCausalInput = true;

			enterCathyPart();
			// causalPoemEntered = true;
			// finalOutput = AnEchoInteractor.toString(history);

			// inputOver = true; // for some reason this was deleted
		} else if (receivedRemoteEcho(aConsoleModelEvent, DistributedTags.CLIENT_1,
				cathyInput, DistributedTags.CLIENT_3)) {
			aliceReceivedCathyCausalInput = true;
			System.out.println(numCausalPhases
					+ "Alice received cathy causal input:" + cathyInput);

		} else if (receivedRemoteEcho(aConsoleModelEvent, DistributedTags.CLIENT_2,
				cathyInput, DistributedTags.CLIENT_3)) {
			bobReceivedCathyCausalInput = true;
			System.out.println(numCausalPhases
					+ "Bob received cathy causal input:" + cathyInput);

		} else if (receivedLocalEcho(aConsoleModelEvent, DistributedTags.CLIENT_3,
				cathyInput)) {
			cathyReceivedCathyCausalOutput = true;
		} else if (receivedRemoteEcho(aConsoleModelEvent, DistributedTags.CLIENT_2,
				aliceInput, DistributedTags.CLIENT_1)) {
			bobReceivedAliceCausalInput = true;
			System.out.println(numCausalPhases
					+ "Bob received alice causal input:" + aliceInput);
		} else if (receivedRemoteEcho(aConsoleModelEvent, DistributedTags.CLIENT_1,
				bobInput, DistributedTags.CLIENT_2)) {
			aliceReceivedBobCausalInput = true;
			System.out.println(numCausalPhases
					+ "Alice received bob causal input:" + bobInput);
		}
		return causalPoemEntered();
		// return true;

	}

	public static boolean receivedRemoteEcho(
			PropertyChangeEvent aConsoleModelEvent, Class aReceiver,
			String anEntry, String aSender) {
		// System.out.println("Checking for remote Entry:" +
		// aConsoleModelEvent.getSource() + " " +
		// aConsoleModelEvent.getNewValue());
		return isConsole(aConsoleModelEvent, aReceiver) &&
		// !isInfo(aConsoleModelEvent) && //
				remoteEchoOf(aConsoleModelEvent, anEntry, aSender);
	}
	public static boolean receivedRemoteEcho(
			PropertyChangeEvent aConsoleModelEvent, String aReceiver,
			String anEntry, String aSender) {
		// System.out.println("Checking for remote Entry:" +
		// aConsoleModelEvent.getSource() + " " +
		// aConsoleModelEvent.getNewValue());
		return isConsole(aConsoleModelEvent, aReceiver) &&
		// !isInfo(aConsoleModelEvent) && //
				remoteEchoOf(aConsoleModelEvent, anEntry, aSender);
	}

	public static boolean receivedLocalEcho(
			PropertyChangeEvent aConsoleModelEvent, Class aReceiver,
			String anEntry) {
		return isConsole(aConsoleModelEvent, aReceiver) &&
		// !isInfo(aConsoleModelEvent) && //
				echoOf(aConsoleModelEvent, anEntry);
	}
	
	public static boolean receivedLocalEcho(
			PropertyChangeEvent aConsoleModelEvent, String aReceiver,
			String anEntry) {
		return isConsole(aConsoleModelEvent, aReceiver) &&
		// !isInfo(aConsoleModelEvent) && //
				echoOf(aConsoleModelEvent, anEntry);
	}

	/*
	 * output for one user may be over before input for another starts, so the
	 * history command input and output are mixed
	 */
	// protected void enterHistory(PropertyChangeEvent aConsoleModelEvent) {
	// if (isInfo(aConsoleModelEvent) ) return;
	// String anOutputWithNewLine = (String) aConsoleModelEvent.getNewValue() +
	// "\n";
	//
	// if (isConsole(aConsoleModelEvent, aliceClass()) &&
	// // !isInfo(aConsoleModelEvent) && //
	// remoteEchoOf(aConsoleModelEvent, poem[2], DistributedTags.CATHY)) {
	// aliceConsole.setInput(EchoerInteractor.HISTORY);
	// aliceInputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// } else if (isConsole(aConsoleModelEvent, bobClass()) &&
	// // && !isInfo(aConsoleModelEvent) && //
	// remoteEchoOf(aConsoleModelEvent, poem[2], DistributedTags.CATHY)) {
	// bobConsole.setInput(EchoerInteractor.HISTORY);
	// bobInputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// } else if (isConsole(aConsoleModelEvent, cathyClass()) &&
	// // !isInfo(aConsoleModelEvent) && //
	// echoOf(aConsoleModelEvent, poem[2], DistributedTags.CATHY)) {
	// cathyConsole.setInput(EchoerInteractor.HISTORY);
	// cathyInputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// } if (aliceInputOver && isConsole(aConsoleModelEvent, aliceClass())
	// && anOutputWithNewLine.equals(finalOutput)) {
	//
	// aliceCorrectOutputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// } if (bobInputOver && isConsole(aConsoleModelEvent, bobClass())
	// && anOutputWithNewLine.equals(finalOutput)) {
	//
	// bobCorrectOutputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// } if (cathyInputOver && isConsole(aConsoleModelEvent, cathyClass())
	// && anOutputWithNewLine.equals(finalOutput)) {
	//
	// cathyCorrectOutputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// }
	//
	// }

	void enterAliceHistory() {
		aliceInputOver = true; // set it before property changed
		aliceConsole.setInput(EchoerInteractor.HISTORY);
	}

	void enterBobHistory() {
		bobInputOver = true;
		bobConsole.setInput(EchoerInteractor.HISTORY);
	}

	void enterCathyHistory() {
		cathyInputOver = true;
		cathyConsole.setInput(EchoerInteractor.HISTORY);
	}

	/*
	 * output for one user may be over before input for another starts, so the
	 * history command input and output are mixed
	 */
	// protected boolean enterHistoryAndViewOutput(
	// PropertyChangeEvent aConsoleModelEvent) {
	// if (isInfo(aConsoleModelEvent))
	// return true;
	// String anOutputWithNewLine = (String) aConsoleModelEvent.getNewValue()
	// + "\n";
	//
	// if (receivedRemoteEcho(aConsoleModelEvent, aliceClass(), cathyInput,
	// DistributedTags.CATHY)) {
	// aliceConsole.setInput(EchoerInteractor.HISTORY);
	// aliceInputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// } else if (receivedRemoteEcho(aConsoleModelEvent, bobClass(),
	// cathyInput, DistributedTags.CATHY)) {
	// bobConsole.setInput(EchoerInteractor.HISTORY);
	// bobInputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// } else if (receivedLocalEcho(aConsoleModelEvent, cathyClass(),
	// cathyInput)) {
	// cathyConsole.setInput(EchoerInteractor.HISTORY);
	// cathyInputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// }
	// if (aliceInputOver && isConsole(aConsoleModelEvent, aliceClass())
	// && anOutputWithNewLine.equals(finalOutput)) {
	//
	// aliceFinalOutputOver = true;
	// aliceFinalOutput = anOutputWithNewLine;
	// // inputOver = true; // for some reason this was deleted
	// }
	// if (bobInputOver && isConsole(aConsoleModelEvent, bobClass())
	// && anOutputWithNewLine.equals(finalOutput)) {
	//
	// bobFinalOutputOver = true;
	// bobFinalOutput = anOutputWithNewLine;
	// // inputOver = true; // for some reason this was deleted
	// }
	// if (cathyInputOver && isConsole(aConsoleModelEvent, cathyClass())
	// && anOutputWithNewLine.equals(finalOutput)) {
	//
	// cathyFinalOutputOver = true;
	// cathyFinalOutput = anOutputWithNewLine;
	// // inputOver = true; // for some reason this was deleted
	// }
	// return true;
	//
	// }
	static final String aPromptWithNewLine = AnEchoInteractor.PROMPT + "\n";

	public static boolean isApplicationOutput(String aStringWithNewLine) {
		return !aStringWithNewLine.equals(aPromptWithNewLine)
				&& !aStringWithNewLine
						.startsWith(AConsoleModel.DEFAULT_INPUT_PROMPT)
				&& !aStringWithNewLine.equals("\n");
	}

	protected boolean viewHistoryOutput(PropertyChangeEvent aConsoleModelEvent) {
		if (isInfo(aConsoleModelEvent))
			return true;
		String anOutputWithNewLine = (String) aConsoleModelEvent.getNewValue()
				+ "\n";

		if (aliceInputOver && isConsole(aConsoleModelEvent, DistributedTags.CLIENT_1)
		// && anOutputWithNewLine.equals(finalOutput))
				&& isApplicationOutput(anOutputWithNewLine)

		) {

			aliceFinalOutputOver = true;
			aliceFinalOutput = anOutputWithNewLine;

			// inputOver = true; // for some reason this was deleted
		} else if (bobInputOver && isConsole(aConsoleModelEvent, DistributedTags.CLIENT_2)
		// && anOutputWithNewLine.equals(finalOutput))
				&& isApplicationOutput(anOutputWithNewLine))

		{

			bobFinalOutputOver = true;
			bobFinalOutput = anOutputWithNewLine;
			// inputOver = true; // for some reason this was deleted
		} else if (cathyInputOver
				&& isConsole(aConsoleModelEvent, DistributedTags.CLIENT_3)
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

	// protected boolean maybeProcessInputPhase(
	// PropertyChangeEvent aConsoleModelEvent) {
	// if (outputOver())
	// return false;
	// if (!isOutputLine(aConsoleModelEvent)) // continue phase, but it is not
	// // over
	// return true;
	//
	//
	// if (isConsole(aConsoleModelEvent, DistributedTags.BOB)
	// && !isInfo(aConsoleModelEvent) && // we will get output echoed
	// // as info, so let us ignore
	// // it
	// remoteEchoOf(aConsoleModelEvent, poem[0], DistributedTags.ALICE)) {
	// bobConsole.setInput(poem[1]); // when bob hears remote echo of
	// // alice's first line, he utters
	// // next line
	// } else if (isConsole(aConsoleModelEvent, cathyClass())
	// && !isInfo(aConsoleModelEvent) && //
	// remoteEchoOf(aConsoleModelEvent, poem[1], DistributedTags.BOB)) {
	// cathyConsole.setInput(poem[2]); // similarly cathy follows bob
	// // inputOver = true; // for some reason this was deleted
	// } else if (isConsole(aConsoleModelEvent, aliceClass())
	// && !isInfo(aConsoleModelEvent) && //
	// remoteEchoOf(aConsoleModelEvent, poem[2], DistributedTags.CATHY)) {
	// aliceInputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// } else if (isConsole(aConsoleModelEvent, aliceClass())
	// && !isInfo(aConsoleModelEvent) && //
	// remoteEchoOf(aConsoleModelEvent, poem[2], DistributedTags.CATHY)) {
	// aliceConsole.setInput(EchoerInteractor.HISTORY);
	// aliceInputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// } else if (isConsole(aConsoleModelEvent, bobClass())
	// && !isInfo(aConsoleModelEvent) && //
	// remoteEchoOf(aConsoleModelEvent, poem[2], DistributedTags.CATHY)) {
	// bobConsole.setInput(EchoerInteractor.HISTORY);
	// bobInputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// } else if (isConsole(aConsoleModelEvent, cathyClass())
	// && !isInfo(aConsoleModelEvent) && //
	// echoOf(aConsoleModelEvent, poem[2], DistributedTags.CATHY)) {
	// cathyConsole.setInput(EchoerInteractor.HISTORY);
	// cathyInputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// } else if (aliceInputOver &&
	// isConsole(aConsoleModelEvent, aliceClass())
	// && !isInfo(aConsoleModelEvent) && //
	// remoteEchoOf(aConsoleModelEvent, poem[2], DistributedTags.CATHY)) {
	// aliceConsole.setInput(EchoerInteractor.HISTORY);
	// aliceInputOver = true;
	// // inputOver = true; // for some reason this was deleted
	// }
	//
	// return true;
	//
	// }
	public   boolean intraSequenceTest(List<Traceable> aTraceableList) {
		boolean superTest = super.intraSequenceTest(aTraceableList);
		boolean imTest = IMTraceChecker.intraSequenceCheckIM(aTraceableList);
		return superTest && imTest;
				
	}

	protected Boolean intraSequenceTest() {
		Set<String> titles = titleToLocalTraceableList.keySet();
		boolean retVal = true;
		for (String title : titles) {
			if (title.contains(DistributedTags.SERVER))
				continue;
			System.out.println("intra sequence tes for:"
					+ title);
			List<Traceable> aTestTraceableList = titleToLocalTraceableList
					.get(title);
			
			retVal = retVal & intraSequenceTest(aTestTraceableList);
//			modularityTranscriptTestSucceeded = super.testAgainstCorrectTranscripts(testTranscript, correctTranscript);
////			if (!modularityTranscriptTestSucceeded)
////				System.out.println("Modularity test failed");
//			
//			if (!IMTraceChecker.compareIMSequences(testTranscript,
//					correctTranscript)) {
//				retVal = false;
//				System.out.println("IM Sequence Test failed");
//
//			}
		}

		return retVal;

	}
	@Override
	protected   Boolean testAgainstCorrectTranscripts(List<Traceable> aTestTraceableList, 
			List<Traceable> aCorrectTraceableList) {
		return super.testAgainstCorrectTranscripts(aTestTraceableList, aCorrectTraceableList)
		& IMTraceChecker.compareIMSequences(aTestTraceableList,
				aCorrectTraceableList);
				
	}
	

	@Override
	public Boolean testAgainstCorrectTranscripts() {
		Set<String> titles = titleToLocalTraceableList.keySet();
		boolean retVal = true;
		for (String title : titles) {
			if (title.contains(DistributedTags.SERVER))
				continue;
			System.out.println("Comparing test and local transcripts for:"
					+ title);
			List<Traceable> aTestTraceableList = titleToLocalTraceableList
					.get(title);
			List<Traceable> aCorrectTraceableList = titleToCorrectTraceableList
					.get(title);
			testAgainstCorrectTranscripts(aTestTraceableList, aCorrectTraceableList);
//			modularityTranscriptTestSucceeded = super.testAgainstCorrectTranscripts(testTranscript, correctTranscript);
////			if (!modularityTranscriptTestSucceeded)
////				System.out.println("Modularity test failed");
//			
//			if (!IMTraceChecker.compareIMSequences(testTranscript,
//					correctTranscript)) {
//				retVal = false;
//				System.out.println("IM Sequence Test failed");
//
//			}
		}

		return retVal;

	}

	protected void processCausalPhasesFinished(
			PropertyChangeEvent aConsoleModelEvent) {
		// finalOutput = AnEchoInteractor.toString(history);
		// enterAliceHistory();
		// enterBobHistory();
		// enterCathyHistory();
		causalPhasesTerminated = true;
		enterHistoryCommands(aConsoleModelEvent);

//		processAllPhasesFinished(aConsoleModelEvent);
	}
	// not called currently
	protected void processAllPhasesFinished(
			PropertyChangeEvent aConsoleModelEvent) {
		System.out.println("All phases finished");
//		System.out.println("causal poem entered" + causalPoemEntered());
		finalOutput = AnEchoInteractor.toString(history);
		enterAliceHistory();
		enterBobHistory();
		enterCathyHistory();
	}
	
	protected void enterHistoryCommands(PropertyChangeEvent aConsoleModelEvent) {
		finalOutput = AnEchoInteractor.toString(history);
		enterAliceHistory();
		enterBobHistory();
		enterCathyHistory();
	}

	protected void restartCausalPhase(PropertyChangeEvent aConsoleModelEvent) {
		initCausalPhase();
		startCausalPhase();
		// enterAlicePart();
	}

	protected void startPhaseAfterCausalPhase(
			PropertyChangeEvent aConsoleModelEvent) {
		restartCausalPhase(aConsoleModelEvent);

	}

	protected void processCausalPhaseFinished(
			PropertyChangeEvent aConsoleModelEvent) {
		// causalInteractiveEnterPoem(aConsoleModelEvent);
		if (causalPoemEntered()) {
			numCausalPhases--;
			if (numCausalPhases > 0) {
				// causalPoemEntered = false;
				// initCausalPhase();
				// enterAlicePart();
				// restartCausalPhase(aConsoleModelEvent);
				startPhaseAfterCausalPhase(aConsoleModelEvent);
			} else {
				causalPhasesTerminated = true;
				processCausalPhasesFinished(aConsoleModelEvent);

			}

		}

	}

	protected boolean maybeProcessCausalIOPhase(
			PropertyChangeEvent aConsoleModelEvent) {
		// do we need this?
		// if (!causalPhaseStarted) // continue phase, but it is not over
		//
		// return true;
		// if (outputOver()) {
		// return false;
		// }
		if (!isOutputLine(aConsoleModelEvent)) // continue phase, but it is not
												// over
			return true;

		if (causalPoemEntered()) {
//			viewHistoryOutput(aConsoleModelEvent);
			return false;
		} else {
			causalInteractiveEnterPoem(aConsoleModelEvent);
			// causalInteractiveEnterPoem(aConsoleModelEvent);
			if (causalPoemEntered()) {
				causalPhaseTerminated = true;
				// processCausalPhaseFinished(aConsoleModelEvent);
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
	public void propertyChange(PropertyChangeEvent aConsoleModelEvent) {
		// process each of the phases after the preceding ones have finished
		
		// these maybes should return three values: continue, next step, or
		// return;
		if (maybeProcessJoinPhase(aConsoleModelEvent))
			return;
		else if (joinPhaseTerminated) {
			joinPhaseTerminated = false;
			processAllJoined(); // trigger more events, and these events and state changes made by them will not be consumed by previous phase
			return;
			// this will consume join phase event but it will be ignored
			// as it is of different type
			// should have transition here also

		} else if (maybeProcessCausalIOPhase(aConsoleModelEvent))
			return;
		else if (causalPhaseTerminated) {
			causalPhaseTerminated = false;
			// we do not really need the event do we?
			processCausalPhaseFinished(aConsoleModelEvent);
			return;
//		} 
//		else if (causalPhasesTerminated) {
//			causalPhasesTerminated = false; // do not consume event
////			processCausalPhaseFinished(aConsoleModelEvent);
//
//			return;
//		} 
//		else if (!viewOutputPhaseStarted) {
//			enterHistoryCommands(aConsoleModelEvent);
		} else if (viewHistoryOutput(aConsoleModelEvent))
			return;
		// if (maybeProcessTestPhase(aConsoleModelEvent)) return; // for some
		// reason
		// launcher.terminateAll();
		else if (!isTerminated())
			notifyInteractionTermination();

	}

	@Override
	public Boolean test() {
//		boolean superTestSucceeded = super.test();
		aliceEqualsBobOutput = aliceFinalOutput.equals(bobFinalOutput);
		aliceEqualsCathyOutput = aliceFinalOutput.equals(cathyFinalOutput);
		bobEqualsCathyOutput = bobFinalOutput.equals(cathyFinalOutput);
		boolean historiesEqual = aliceFinalOutput != null
				&& aliceEqualsBobOutput &&aliceEqualsCathyOutput
				&& bobEqualsCathyOutput;
		boolean intraSequenceTest = intraSequenceTest();
		System.out.println("AnIMDemoerAndATest result:" + historiesEqual);
		return historiesEqual;

	}

}
