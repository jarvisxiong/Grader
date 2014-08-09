package gradingTools.comp790ColabInc.example1.testcases;

import util.annotations.Tags;
import util.misc.ThreadSupport;
import util.tags.ApplicationTags;
import bus.uigen.pipe.ADemoer;
@Tags(ApplicationTags.ECHOER)
public class AMonolithicEchoInputGenerator extends ADemoer {
	protected String echoProcess;
//	protected boolean echoTestSucceeded;
//	protected boolean echoTranscriptTestSucceeded;

	public static long DEFAULT_TIME_OUT = 5000;
//	
//	protected MainClassListLauncher launcher;
	public static final String[] POEM = {
			"The woods are lovely dark and deep",			 
			"But I have promises to keep",  			 
			"And miles to go before I sleep"
	};
	
	public AMonolithicEchoInputGenerator() {
//		computeFinalHistory();
	}
//	public AMonolithicConsoleModelBasedEchoDemoer(boolean anInteractive) {
//		super(anInteractive);
//	}
//	
	
	
	
	@Override
	public void executionStarted() {
		echoProcess = processNames.get(0);
		
		notifyNewInputLine(echoProcess, POEM[0]);
		// replace these with wait for clearance etc
		ThreadSupport.sleep(1000);
		notifyNewInputLine(echoProcess, POEM[1]);
		ThreadSupport.sleep(1000);
		notifyNewInputLine(echoProcess, POEM[2]);
//		ThreadSupport.sleep(1000);
		ThreadSupport.sleep(1000);
		notifyInteractionTermination();

	
	}
//	public static long getTimeOut() {
//		return DEFAULT_TIME_OUT;
//	}
//	
//	
//	protected Class echoClass() {
//		return 	MonolithicEchoer.class;
//	}
//	
//	protected Class[] composeMainClasses() {
//		return new Class[] {
//			echoClass()			
//		};
//	}
	
//	public Boolean executeLoadAndTest(Boolean aGenerateCorrectTranscripts, Boolean aTestAgainstCorrectTranscripts) {
//		super.executeLoadAndTest(aGenerateCorrectTranscripts, aTestAgainstCorrectTranscripts);
////		return EchoTraceChecker.checkEchoer(getGlobalTraceableList());
//		boolean retVal = EchoTraceChecker.checkInputOutSequences(getGlobalTraceableList());
//		return retVal;
//	}
	
//	public boolean intraSequenceTest(List<Traceable> aTraceableList) {
////		System.out.println("Intra Sequence Check: Echoer");
//		echoTestSucceeded =  EchoTraceChecker.matchInputOutSequences(aTraceableList);
//		System.out.println("Intra Sequence Check: Echoer:" + echoTestSucceeded);
//		return echoTestSucceeded;
//	}
//	@Override
//	public Boolean test() {
////		echoTestSucceeded = EchoTraceChecker.checkInputOutSequences(getGlobalTraceableList());
////		return echoTestSucceeded;
//		return intraSequenceTest(getGlobalTraceableList());
//	}
//	protected   Boolean testAgainstCorrectTranscripts(List<Traceable> aTestTraceableList, 
//			List<Traceable> aCorrectTraceableList) {
//		echoTranscriptTestSucceeded = EchoTraceChecker.compareInputOutSequences(aTestTraceableList, aCorrectTraceableList);
//		System.out.println("Inter sequence echo:" + echoTranscriptTestSucceeded);
//		return echoTranscriptTestSucceeded;
//				
//	}
//	@Override
//	public Boolean testAgainstCorrectTranscripts() {
//		return testAgainstCorrectTranscripts(getGlobalTraceableList(), getCorrectGlobalTraceableList());
////		System.out.println("Comparing echo sequences");
////		echoTranscriptTestSucceeded = EchoTraceChecker.compareInputOutSequences(getGlobalTraceableList(), getCorrectGlobalTraceableList());
////		System.out.println("Inter sequence echo:" + echoTranscriptTestSucceeded);
////
////		return echoTranscriptTestSucceeded;
//	}
//	
////	public Boolean test(Boolean aTestAgainstCorrectTranscripts) {
//////		return EchoTraceChecker.checkEchoer(getGlobalTraceableList());
////		boolean retVal1 = EchoTraceChecker.checkInputOutSequences(getGlobalTraceableList());
////		if (aTestAgainstCorrectTranscripts)
////			return retVal1 & EchoTraceChecker.compareInputOutSequences(getGlobalTraceableList(), getCorrectGlobalTraceableList());
////		else
////			return retVal1;
////	}
//	
//	public static String echoOf(String anInput) {
//		return MonolithicEchoer.echo(anInput);
//	}
//	
//	
//	public static boolean echoOf(PropertyChangeEvent aConsoleModelEvent, String anInput) {
//		if (!isOutputLine(aConsoleModelEvent)) return false;
//		String aText = aConsoleModelEvent.getNewValue().toString();
////		return aText.contains(MonolithicEchoer.echo(anInput));
//		return aText.contains(echoOf(anInput));
//
//		
//	}
//	@Override
//	public void newOutputLine(String aProcessName, String anOutputLine) {
//		// TODO Auto-generated method stub
//		
//	}
	





	


}
