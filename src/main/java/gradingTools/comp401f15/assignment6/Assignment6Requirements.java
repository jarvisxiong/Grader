package gradingTools.comp401f15.assignment6;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment6.testCases.*;
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
import gradingTools.sharedTestCase.MethodExecutionTestCase;
import static gradingTools.sharedTestCase.MethodExecutionTestCase.DNC;
import gradingTools.sharedTestCase.MethodExecutionTestCase.MethodEnvironment;
import gradingTools.sharedTestCase.MethodExecutionTestCase.MethodParameterReference;
import gradingTools.sharedTestCase.PropertyDefinedTestCase;
import gradingTools.sharedTestCase.ReflectiveClassDefinedTestCase;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/4/13
 * Time: 3:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class Assignment6Requirements extends FrameworkProjectRequirements {
    public Assignment6Requirements() {
        
        addFeature("Gorge and Bridge Properites", 10,
                new PropertyDefinedTestCase("BridgeScene", "Gorge",  Object.class)
                );
        
        addFeature("Standing Areas", 15,
                new PropertyDefinedTestCase("BridgeScene", "KnightArea",  Object.class),
                new PropertyDefinedTestCase("BridgeScene", "GuardArea",  Object.class),
                new StandingAreaPlacementTestCase()
                );
 
        addFeature("Avatar init in constructor", 10, new ConstructorInitTestCase());
        
        addFeature("Approach method", 10, 
                new MethodDefinedTestCase("BridgeScene", "approach", void.class, "Avatar"),
                new ApproachMethodFunctionTestCase()
        
        );
        addFeature("Passed method", 15,
                new MethodDefinedTestCase("BridgeScene", "passed", void.class),
                new PassedMethodFunctionTestCase()
        );
        
        addFeature("Failed method", 15,
                new MethodDefinedTestCase("BridgeScene", "failed", void.class),
                new FailedMethodFunctionTestCase()
        );
        
        addFeature("Say method", 10,
                new MethodDefinedTestCase("BridgeScene", "say", void.class, String.class),
                new SayMethodFunctionTestCase()
        );
        
        addFeature("Token refactoring", 10,
                new SingleInterfaceTestCase("Single interface test case")
        );
        addFeature("Approach Command", 10,
                new ApproachCommandBeanTestCase(),
                new ApproachCommandCreatedTestCase());

        // Define extra credit

        addManualFeature("Are there 3D effects for the gorge/bridge?", 3);
        addManualFeature("Is there animated falling?", 3);
        addManualFeature("Is there animated crossing?", 3);

        addFeature("Scroll method", 8, true,
                new MethodDefinedTestCase("BridgeScene", "scroll", "scroll", ".*[sS]croll.*", void.class, int.class, int.class),
                new ScrollMethodFunctionTestCase());
        addFeature("Clearable History", 7, true,
                new ReflectiveClassDefinedTestCase("ClearableHistory", ".*[cC]learable[hH]istory.*", "[cC]learable[hH]istory"),
                new MethodDefinedTestCase("ClearableHistory", "clear", "clear", ".*[cC]lear.*", void.class),
                new ClearableHistoryFunctionTestCase()
        );

        // Define the restrictions

        addRestriction("No public variables.", -5, new EncapsulationTestCase("Encapsulation test case"));
        addRestriction("Interface object assignments.", -5, new InterfaceTypeTestCase("Interface type test case"));
        addRestriction("At least three packages.", -5, new ThreePackageTestCase("Three package test case"));
        // TODO: Method/interface check

        addRestriction("Single main.Assignment", 10, new MainClassDefinedTestCase("main.Assignment(.*)"));
        addRestriction("No System.exit()", -5, new SystemExitTestCase("System.exit test case"));
    }
}
