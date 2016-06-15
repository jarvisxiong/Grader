package gradingTools.comp401f15.assignment3.testcases;


public class WordClassTestCase extends TokenScanningTestCase{

	public WordClassTestCase() {
        super("Word class test case");
    }
	

//	String[] beanDescriptions =  {null, "ScannerBean", ".*Bean.*", ".*Bean.*"};
//    Class[] constructorArgTypes2 = {String.class};
//    Class[] constructorArgTypes1 = {};
//    String[] constructorArgs2 = {""};
//    String[] constructorArgs1 = {};
    protected String inputEndingSpaces() { return  "CamelCase anotherWord wordWord ";}
    protected String input() { return "CamelCase anotherWord wordWord";}
    
   protected String[] expectedOutputs() {
	   Class aClassName =  (Class) this.getCheckable().getRequirements().getUserObject(WordTokenBeanTestCase.TAG);
	   String aPattern = ".*" + aClassName.getCanonicalName() + ".*";
	   return new String[] {aPattern, aPattern, aPattern };
	   
   };

    
}
