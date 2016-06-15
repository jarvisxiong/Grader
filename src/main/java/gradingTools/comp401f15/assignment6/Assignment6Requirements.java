package gradingTools.comp401f15.assignment6;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment6.testCases.EncapsulationTestCase;
import gradingTools.assignment6.testCases.InterfaceTypeTestCase;
import gradingTools.assignment6.testCases.SingleInterfaceTestCase;
import gradingTools.assignment6.testCases.SystemExitTestCase;
import gradingTools.assignment6.testCases.ThreePackageTestCase;
import gradingTools.comp401f15.assignment1.testcases.MainClassDefinedTestCase;
import gradingTools.comp401f15.assignment6.testcases.ClearableHistoryFunctionTestCase;
import gradingTools.comp401f15.assignment6.testcases.ConstructorInitTestCase;
import gradingTools.comp401f15.assignment6.testcases.StandingAreaPlacementTestCase;
import gradingTools.comp401f15.assignment6.testcases.commands.ApproachCommandBeanTestCase;
import gradingTools.comp401f15.assignment6.testcases.commands.ApproachCommandCreatedTestCase;
import gradingTools.comp401f15.assignment6.testcases.commands.methods.ApproachMethodFunctionTestCase;
import gradingTools.comp401f15.assignment6.testcases.commands.methods.FailedMethodFunctionTestCase;
import gradingTools.comp401f15.assignment6.testcases.commands.methods.PassedMethodFunctionTestCase;
import gradingTools.comp401f15.assignment6.testcases.commands.methods.SayMethodFunctionTestCase;
import gradingTools.comp401f15.assignment6.testcases.commands.methods.ScrollMethodFunctionTestCase;
import gradingTools.sharedTestCase.MethodDefinedTestCase;
import gradingTools.sharedTestCase.NoWarningOrErrorTestCase;
import gradingTools.sharedTestCase.PropertyDefinedTestCase;
import gradingTools.sharedTestCase.ReflectiveClassDefinedTestCase;
import gradingTools.sharedTestCase.VariableHasClassTypeTestCase;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/4/13
 * Time: 3:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class Assignment6Requirements extends FrameworkProjectRequirements {
    public Assignment6Requirements() {
    	addDueDate("09/30/2015 23:59:00", 1.05);
    	addDueDate("10/02/2015 23:59:00", 1);
    	addDueDate("10/07/2015 23:59:00", 0.9);
    	addDueDate("10/09/2015 23:59:00", 0.75);
        
        addFeature("Gorge and Bridge Properites", 10,
                new PropertyDefinedTestCase("BridgeScene", "Gorge",  Object.class)
                );
        
        addFeature("Standing Areas", 15,
                new PropertyDefinedTestCase("BridgeScene", "KnightArea",  Object.class),
                new PropertyDefinedTestCase("BridgeScene", "GuardArea",  Object.class),
                new StandingAreaPlacementTestCase()
                );
 
        addFeature("Avatar init in constructor", 10, new ConstructorInitTestCase());
        
        addFeature("Helper Properties", 4,
   	 		new PropertyDefinedTestCase("BridgeScene", "Occupied",  boolean.class),
   	 		new PropertyDefinedTestCase("BridgeScene", "KnightTurn",  boolean.class)
        );
        
        addFeature("Approach method", 7, 
                new MethodDefinedTestCase("BridgeScene", "approach", void.class, "Avatar"),
                new ApproachMethodFunctionTestCase()
        );
        
        addManualFeature("Approach method demoed?", 3);
        
        addFeature("Passed method", 12,
                new MethodDefinedTestCase("BridgeScene", "passed", void.class),
                new PassedMethodFunctionTestCase()
        );
        
        addManualFeature("Pass method demoed?", 3);
        
        addFeature("Failed method", 12,
                new MethodDefinedTestCase("BridgeScene", "failed", void.class),
                new FailedMethodFunctionTestCase()
        );
        
        addManualFeature("Fail method demoed?", 3);
        
        addFeature("Say method", 7,
                new MethodDefinedTestCase("BridgeScene", "say", void.class, String.class),
                new SayMethodFunctionTestCase()
        );
        
        addManualFeature("Say method demoed?", 3);
        
        addFeature("Token refactoring", 10,
                new SingleInterfaceTestCase("Single interface test case")
        );
        addFeature("Approach Command", 10,
                new ApproachCommandBeanTestCase(),
                new ApproachCommandCreatedTestCase());

        addFeature("Scroll method", 8, true,
                new MethodDefinedTestCase("BridgeScene", "scroll", "scroll", ".*[sS]croll.*", void.class, int.class, int.class),
                new ScrollMethodFunctionTestCase());
        addFeature("Clearable History", 7, true,
                new ReflectiveClassDefinedTestCase("ClearableHistory", ".*[cC]learable[hH]istory.*", "[cC]learable[hH]istory"),
                new MethodDefinedTestCase("ClearableHistory", "clear", "clear", ".*[cC]lear.*", void.class),
                new ClearableHistoryFunctionTestCase()
        );
        

        // Define the restrictions

        
//        addRestriction("Helper Properties", -4,
//   	 		new PropertyDefinedTestCase("BridgeScene", "Occupied",  boolean.class),
//   	 		new PropertyDefinedTestCase("BridgeScene", "KnightTurn",  boolean.class)
//        );
        
//        addRestriction("No public variables.", -5, new EncapsulationTestCase("Encapsulation test case"));
        addRestriction("Interface object assignments.", 5, new InterfaceTypeTestCase("Interface type test case"));
        addRestriction("At least three packages.", 5, new ThreePackageTestCase("Three package test case"));
        addRestriction("Variable has class type", 5, new VariableHasClassTypeTestCase("Variable has class type"));
        addRestriction("No public variables.", 5, new EncapsulationTestCase("Encapsulation test case"));

        // TODO: Method/interface check
        
        addRestriction("Single main.Assignment", 10, new MainClassDefinedTestCase("main.Assignment(.*)"));
        addRestriction("No System.exit()", -5, new SystemExitTestCase("System.exit test case"));
        
        addManualRestriction(INTERACTIVE_RUN, 5, new NoWarningOrErrorTestCase("OE Warnings", ".*(efresh|not in range).*", null, 0.3));
    }
}
