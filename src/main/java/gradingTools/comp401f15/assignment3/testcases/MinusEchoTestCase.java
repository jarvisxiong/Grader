package gradingTools.comp401f15.assignment3.testcases;


public class MinusEchoTestCase extends TokenScanningTestCase{

	public MinusEchoTestCase() {
        super("Minus echo test case");
    }
	
//	static String[] emptyArgs = {};
//	
//	public Object createScannerBean (Class aClass) {
//		Constructor aConstructor = null;
//		try {
//			aConstructor = aClass.getConstructor();
////			return aConstructor.newInstance();
//			Object aResult = ExecutionUtil.timedInvoke(aConstructor, emptyArgs, 300);
//			return aResult;
//		} catch (Exception e) {
//			try {
//				aConstructor = aClass.getConstructor(String.class);
//				Object aResult = ExecutionUtil.timedInvoke(aConstructor, new String[] {""}, 300);
//
//				return aResult;
//			} catch (Exception e1) {
//				return null;
//			}			
//		}
//		
//		
//		
//	}
//	String[] beanDescriptions =  {null, "ScannerBean", ".*Bean.*", ".*Bean.*"};
//    Class[] constructorArgTypes2 = {String.class};
//    Class[] constructorArgTypes1 = {};
//    String[] constructorArgs2 = {""};
//    String[] constructorArgs1 = {};
    protected String inputEndingSpaces() { return  "- - ";}
    protected String input() { return "- -";}
    
   protected String[] expectedOutputs() { return new String[] {".*-.*", ".*-.*" };};

    
   
	
//    public TestCaseResult test(Project aProject, Class[] aConstructorArgTypes, Object[] aConstructorArgs, String aScannedString) throws NotAutomatableException, NotGradableException {
//    	Map<String, Object> anInputs = new HashMap();
//        anInputs.put("ScannedString", aScannedString);
//        Map<String, Object> anActualOutputs = ExecutionUtil.testBean(getCheckable().getName(), aProject, beanDescriptions, aConstructorArgTypes, aConstructorArgs, anInputs, outputPropertyNames);
////        String aTranscript = (String) anActualOutputs.get("System.out");
////        if (aTranscript != null && !aTranscript.isEmpty()) {
////        	RunningProject.appendToTranscriptFile(aProject, getCheckable().getName(), aTranscript);
////        }
//        if (anActualOutputs.get(ExecutionUtil.MISSING_CLASS) != null) { // only output, no object
//        	return fail ("Could not find scanner bean");
//        }
//        Boolean getsReturnedSets = (Boolean) anActualOutputs.get(ExecutionUtil.GETS_EQUAL_SETS);
//        String anOutput = (String) anActualOutputs.get(ExecutionUtil.PRINTS);
//        String[] anOutputLines =anOutput.split("\n");
//        List<String> anOutputLinesList = Common.arrayToArrayList(anOutputLines);
//        int i = 0;
//        boolean correctTokensPrinted = OutputAndErrorCheckingTestCase.isValidOutputInDifferentLines(anOutputLinesList, expectedOutputs());
//        if (getsReturnedSets && correctTokensPrinted) {
//        	return pass();
//        }
//        if (!getsReturnedSets && !correctTokensPrinted)
//        	return fail("Gets do not returns sets and incorrect tokens printed");
//        if (!getsReturnedSets)	{
//        	return partialPass (0.7, "Gets do not returns sets ");
//        }
//        return partialPass (0.3, "Incorrect tokens printed");
//    }
//
//    @Override
//    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
//        if (project.getClassesManager().isEmpty())
//            throw new NotGradableException();
//        
//      TestCaseResult result = test(project, constructorArgTypes1, constructorArgs1, inputEndingSpaces);
//      if (result.getNotes().contains("Could not find scanner bean"))
//      	return result;
//      if (result.getPercentage() < 0.7) {
//    	  result = test(project, constructorArgTypes2, constructorArgs2, inputEndingSpaces);
//    	  if (result.getPercentage() < 0.7) {
//    		  result = test(project, constructorArgTypes1, constructorArgs1, input);
//    		  if (result.getPercentage() < 0.7)
//    			  result = test(project, constructorArgTypes2, constructorArgs2, input);
//    	  }
//      }
//      return result;
//    }
}
