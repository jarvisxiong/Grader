package gradingTools.comp401f15.assignment5;

import util.annotations.StructurePatternNames;
import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment6.testCases.EncapsulationTestCase;
import gradingTools.assignment6.testCases.SystemExitTestCase;
import gradingTools.comp401f15.assignment1.testcases.MainClassDefinedTestCase;
import gradingTools.comp401f15.assignment3.testcases.HasInterfaceTestCase;
import gradingTools.sharedTestCase.ClassDefinedTestCase;
import gradingTools.sharedTestCase.MethodDefinedTestCase;
import gradingTools.sharedTestCase.NoWarningOrErrorTestCase;
import gradingTools.sharedTestCase.PropertyDefinedTestCase;
import gradingTools.sharedTestCase.VariableHasClassTypeTestCase;


public class Assignment5Requirements extends FrameworkProjectRequirements {

    public Assignment5Requirements() {
    	addDueDate("09/23/2015 23:59:00", 1.05);
    	addDueDate("09/25/2015 23:59:00", 1);
    	addDueDate("09/30/2015 23:59:00", 0.9);
    	addDueDate("10/02/2015 23:59:00", 0.75);
//    	 addFeature("Properties Defined ", 9, 
//         		new CheckstylePropertyDefinedTestCase("LeftLine")
//         		
//         	);
//    	 addFeature("Methods Defined ", 9, new MethodDefinedTestCase("Angle", "move", void.class, int.class, int.class));
    	      		
//    	 addFeature("Properties Defined ", 9, 
//
//    			 new PropertyDefinedTestCase("Angle", "LeftLine",  StructurePatternNames.LINE_PATTERN));
    			 
   	 	addFeature("Angle Bean", 6,
   	 		new PropertyDefinedTestCase("Angle", "LeftLine",  StructurePatternNames.LINE_PATTERN),
   	 		new PropertyDefinedTestCase("Angle", "RightLine",  StructurePatternNames.LINE_PATTERN),
   	 		new MethodDefinedTestCase("Angle", "move", void.class, int.class, int.class));
   	 
   	 	
   	 	addFeature("Avatar Bean", 15,
    	 		new PropertyDefinedTestCase("Avatar", "Head",  StructurePatternNames.IMAGE_PATTERN),
//    	 		new PropertyDefinedTestCase("Avatar", "Text",  StructurePatternNames.STRING_PATTERN),
    	 		new PropertyDefinedTestCase("Avatar", "Text",  Object.class),

    	 		new PropertyDefinedTestCase("Avatar", "Arms", "Angle"),
    	 		new PropertyDefinedTestCase("Avatar", "Legs", "Angle"),
    	 		new PropertyDefinedTestCase("Avatar", "Body", Object.class),
    	 		new MethodDefinedTestCase("Avatar", "move", void.class, int.class, int.class)
   	 	);
   	 	 	 	

    	 
    	 
    	 
    	 
    		addFeature("Bridge Scene Bean", 10,
        	 		new PropertyDefinedTestCase("BridgeScene", "Arthur",  "Avatar"),
        	 		new PropertyDefinedTestCase("BridgeScene", "Lancelot",  "Avatar"),
        	 		new PropertyDefinedTestCase("BridgeScene", "Robin",  "Avatar"),
        	 		new PropertyDefinedTestCase("BridgeScene", "Galahad",  "Avatar"),
        	 		new PropertyDefinedTestCase("BridgeScene", "Guard",  "Avatar"));
    		
    	addManualFeature("Angle connection", 7);
       	 addManualFeature("Angle move", 7);
       	addManualFeature("Avatar connection", 15);
       	addManualFeature("Avatar move", 15);       	 
    	addManualFeature("Bridge Scene Display Constraints", 5); 

       	 addManualFeature("Scene  Demo", 10);

       	 addManualFeature("Avatar Move Demo", 10);
       	addFeature("Avatar Scale Method", 2, true,
	 			
     	 		new MethodDefinedTestCase("Avatar", "scale", void.class, double.class, double.class));
    	 addManualFeature("Avatar scale demo", 6, true);
    	 
    	 addManualFeature("Rotate arm demo", 7, true);

        
    	addRestriction("Class has interface check", 5, new HasInterfaceTestCase("Has interface"));
    	addRestriction("Variable has class type", 5, new VariableHasClassTypeTestCase("Variable has class type"));
        addRestriction("Single main.Assignment", 10, new MainClassDefinedTestCase("main.Assignment(.*)"));
        addRestriction("Classes Tagged ", 9, 
        		new ClassDefinedTestCase("@Angle"),
        		new ClassDefinedTestCase("@Avatar"),
        		new ClassDefinedTestCase("@BridgeScene")
        	);
        addManualRestriction("No image files.", 5);
        addRestriction("No public variables.", 5, new EncapsulationTestCase("Encapsulation test case"));
//        addRestriction("At least three packages.", -5, new ThreePackageTestCase("Three package test case"));
        // TODO: Method/interface check
        addRestriction("No System.exit()", 5, new SystemExitTestCase("System.exit test case"));
//        addManualRestriction(INTERACTIVE_RUN, 10, new NoWarningOrErrorTestCase("OE Warnings", ".*efresh.* | .*not in range.*", 0.3));
        addManualRestriction(INTERACTIVE_RUN, 5, new NoWarningOrErrorTestCase("OE Warnings", ".*(efresh|not in range).*", null, 0.3));

    }
}
