package gradingTools.comp401f15.assignment3.testcases;



public class StartTokenBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "Start";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
//	protected double missingClassPenalty() {return 1.0;};
//	protected double missingExpectedConstructorPenalty() {return 0.2;};
//	protected double missingNullConstructorPenalty() {return 0.0;}
//	protected double missingPropertyPenalty() {return 0.2;};
//	protected double getsNotSetsPenalty() {return 0.2;}
//	protected double incorrectDependentsPenalty(){return 0.3;}
	
	protected String input(){ return "{";};
	protected Object value() {return null;};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return null;};

    public StartTokenBeanTestCase() {
        super("Start Token Bean Case");
    }

   
}

