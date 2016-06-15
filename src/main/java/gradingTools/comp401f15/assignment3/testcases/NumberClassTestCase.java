package gradingTools.comp401f15.assignment3.testcases;


public class NumberClassTestCase extends TokenScanningTestCase{

	public NumberClassTestCase() {
        super("Number class test case");
    }
	

	String[] beanDescriptions =  {null, "ScannerBean", ".*Bean.*", ".*Bean.*"};
    Class[] constructorArgTypes2 = {String.class};
    Class[] constructorArgTypes1 = {};
    String[] constructorArgs2 = {""};
    String[] constructorArgs1 = {};
    protected String inputEndingSpaces() { return  "00220 32 45 ";}
    protected String input() { return "00220 32 45";}
    
   protected String[] expectedOutputs() {
	   Class aClass =  (Class) this.getCheckable().getRequirements().getUserObject(NumberTokenBeanTestCase.TAG);
	   String aPattern = ".*" + aClass.getCanonicalName() + ".*";
	   return new String[] {aPattern, aPattern, aPattern };
	   
   };

    
}
