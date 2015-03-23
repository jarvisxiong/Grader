package echo.modular;

import java.util.List;

import trace.echo.modular.ModularEchoTraceChecker;
import util.trace.Traceable;
import bus.uigen.models.DemoerAndTester;
import echo.monolithic.AMonolithicEchoDemoerAndTester;

public class AModularEchoDemoerAndTester extends AMonolithicEchoDemoerAndTester implements DemoerAndTester{

	protected boolean modularityTestSucceeded;
	protected boolean modularityTranscriptTestSucceeded;

	
	public AModularEchoDemoerAndTester() {
//		computeFinalHistory();
	}
	public AModularEchoDemoerAndTester(boolean anInteractive) {
		super(anInteractive);
	}
	
	
	 
	
	

	
	
	protected Class echoClass() {
		return 	AnEchoComposerAndLauncher.class;
	}
	protected   Boolean testAgainstCorrectTranscripts(List<Traceable> aTestTraceableList, 
			List<Traceable> aCorrectTraceableList) {
		boolean superTestSucceeded = super.testAgainstCorrectTranscripts(aTestTraceableList, aCorrectTraceableList);
		modularityTranscriptTestSucceeded =  ModularEchoTraceChecker.compareMVCSequences(aTestTraceableList, aCorrectTraceableList);
		return superTestSucceeded && modularityTranscriptTestSucceeded;
	}
//	@Override
//	public Boolean testAgainstCorrectTranscripts() {
//		boolean superTestSucceeded = super.testAgainstCorrectTranscripts();
////		modularityTranscriptTestSucceeded = ModularEchoTraceChecker.compareMVCSequences(getGlobalTraceableList(), getCorrectGlobalTraceableList());
//		modularityTranscriptTestSucceeded = testAgainstCorrectTranscripts(getGlobalTraceableList(), getCorrectGlobalTraceableList());
//
//		return superTestSucceeded && modularityTranscriptTestSucceeded;
//
//	}
//	
	@Override
	public boolean intraSequenceTest(List<Traceable> aTraceableList) {
		boolean superTestSucceeded = super.intraSequenceTest(aTraceableList);
		modularityTestSucceeded =  ModularEchoTraceChecker.intraSequenceCheckModularEchoer(aTraceableList);
		System.out.println( "Intra Sequence Check, Modularity:" + modularityTestSucceeded );
		return superTestSucceeded &
				modularityTestSucceeded;

	}
	
//	@Override
//	public Boolean test() {
//		boolean superTestSucceeded = super.test();
////		modularityTestSucceeded = ModularEchoTraceChecker.intraSequenceCheckModularEchoer(getGlobalTraceableList());
//		modularityTestSucceeded = intraSequenceCheck(getGlobalTraceableList());
//
//		return superTestSucceeded &
//				modularityTestSucceeded;
//
//	}

//	public Boolean executeLoadAndTest(Boolean aCorrectTranscripts, Boolean aTestAgainstCorrectTranscripts) {	
//		return super.executeLoadAndTest(aCorrectTranscripts, aTestAgainstCorrectTranscripts) & // not short-circuit on purpose 
//				ModularEchoTraceChecker.checkModularEchoer(getGlobalTraceableList());
//	}
//	public Boolean test(Boolean aTestAgainstCorrectTranscripts) {	
//		// not short-circuit on purpose 
//		boolean retVal1 = super.test(aTestAgainstCorrectTranscripts);
//		boolean retVal2 = ModularEchoTraceChecker.checkModularEchoer(getGlobalTraceableList());
//		boolean retVal = retVal1 & retVal1; // not short-circuit on purpose 
//		if (aTestAgainstCorrectTranscripts)
//			return retVal & 
//				ModularEchoTraceChecker.compareMVCSequences(getGlobalTraceableList(), getCorrectGlobalTraceableList());
//		else
//			return retVal;
//	}
}
