package gradingTools.comp401f15.assignment3.testcases;


public class QuoteClassTestCase extends TokenScanningTestCase{

	public QuoteClassTestCase() {
        super("Quote class test case");
    }
	

//	String[] beanDescriptions =  {null, "ScannerBean", ".*Bean.*", ".*Bean.*"};
//    Class[] constructorArgTypes2 = {String.class};
//    Class[] constructorArgTypes1 = {};
//    String[] constructorArgs2 = {""};
//    String[] constructorArgs1 = {};
    protected String inputEndingSpaces() { return  "\"CamelCase !@# \" \"Word wordWord\" ";}
    protected String input() { return "\"CamelCase !@#  \" \"Word wordWord\"";}
    
   protected String[] expectedOutputs() {
	   Class aClass =  (Class) this.getCheckable().getRequirements().getUserObject(QuoteTokenBeanTestCase.TAG);
	   String aPattern = ".*" + aClass.getCanonicalName() + ".*";
	   return new String[] {aPattern, aPattern};
	   
   };

    
}
