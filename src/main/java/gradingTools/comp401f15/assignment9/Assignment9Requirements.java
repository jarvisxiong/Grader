package gradingTools.comp401f15.assignment9;

import framework.grading.FrameworkProjectRequirements;
import static framework.grading.ProjectRequirements.INTERACTIVE_RUN;
import gradingTools.assignment6.testCases.*;
import gradingTools.assignment9.testCases.*;
import gradingTools.comp401f15.assignment1.testcases.MainClassDefinedTestCase;
import gradingTools.comp401f15.assignment3.testcases.HasInterfaceTestCase;
import gradingTools.comp401f15.assignment3.testcases.VariableHasClassTypeTestCase;
import gradingTools.comp401f15.assignment9.testCases.MoveAllAvatarsTestCase;
import gradingTools.comp401f15.assignment9.testCases.MoveSingleAvatarTestCase;
import gradingTools.comp401f15.assignment9.testCases.ResetAllAvatarsTestCase;
import gradingTools.comp401f15.assignment9.testCases.ResetSingleAvatarTestCase;
import gradingTools.sharedTestCase.IllegalImportOrCallTestCase;
import gradingTools.sharedTestCase.NoWarningOrErrorTestCase;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/28/13
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Assignment9Requirements extends FrameworkProjectRequirements {

    public Assignment9Requirements() {

        // Add due date/times with a 30 minute grace period
        addDueDate("10/29/2015 00:59:00", 1.05);
        addDueDate("10/31/2015 00:59:00", 1);
        addDueDate("11/04/2015 00:59:00", 0.9);
        addDueDate("11/07/2015 00:59:00", 0.75);

        // Part 2
        addFeature("(1) Inheriting Scene painter class tagged", 2, new ScenePainterTagTestCase());
        addFeature("(1) View extends component", 5, new ScenePainterExtendsComponentTestCase());
        addFeature("(1) View is listener of shapes", 10, new ScenePainterListenerTestCase());
        addFeature("(1) paint() called when events fire", 10, new ScenePainterPaintOnEventTestCase());
        addManualFeature("(1) Does the paint method draw everything in the view?", 25);

        // Part 2 (EC)
        addFeature("(2) Listener & Observing Painter classes tagged", 2, new ListenerAndPainterTagTestCase());
        addFeature("(2) Observable Bridge Scene Painter extends component", 5, new ObservablePainterExtendsComponentTestCase());
        addFeature("(2) Paint listener paint method", 10, new PaintListenerPaintMethodTestCase());
        addFeature("(2) View classes register as listener", 20, new PaintListenerListenersTestCase());
        addFeature("(2) Listeners notify on fired events", 10, new PaintListenerPaintOnEventTestCase());
        addManualFeature("(2) Do the paint listener views do all the painting/drawing?", 25);
        addManualFeature("(2) Are the avatars drawn on top of the bridge and gorge?", 5);

        // Part 3
        addManualFeature("Is there a demo?", 10);
        
        addFeature("Bridge scene controller tagged", 2, true, new BridgeSceneControllerTagTestCase());
        addFeature("Move Arthur", 2, new MoveSingleAvatarTestCase("Arthur"));
        addFeature("Move Galahad", 2, new MoveSingleAvatarTestCase("Galahad"));
        addFeature("Move Lancelot", 2, new MoveSingleAvatarTestCase("Lancelot"));
        addFeature("Move Robin", 2, new MoveSingleAvatarTestCase("Robin"));
        addFeature("Move All", 2, new MoveAllAvatarsTestCase());
        
        addFeature("Reset Arthur", 1, new ResetSingleAvatarTestCase("Arthur"));
        addFeature("Reset Galahad", 1, new ResetSingleAvatarTestCase("Galahad"));
        addFeature("Reset Lancelot", 1, new ResetSingleAvatarTestCase("Lancelot"));
        addFeature("Reset Robin", 1, new ResetSingleAvatarTestCase("Robin"));
        addFeature("Reset All", 6, new ResetAllAvatarsTestCase());
        
        
        // Extra Credit
        addManualFeature("Custom user-interface to manipulate command interpreter?", 10, true);
        addManualFeature("Command interpreter displays  error property?", 5, true);
        addManualFeature("Progress bar?", 10, true);
        addManualFeature("Interpeter UI has two or more action components?", 10, true);

        // Define the restrictions
        addRestriction("No public variables.", 5, new EncapsulationTestCase("Encapsulation test case"));
        addRestriction("At least three packages.", 5, new ThreePackageTestCase("Three package test case"));
        // TODO: Method/interface check
        addRestriction("No System.exit()", 5, new SystemExitTestCase("System.exit test case"));
    	addRestriction("Class has interface check", 5, new HasInterfaceTestCase("Has interface"));
    	addRestriction("Variable has class type", 5, new VariableHasClassTypeTestCase("Variable has class type"));
        addRestriction("Illegal import or call", 25, new IllegalImportOrCallTestCase());
        addRestriction("Single main.Assignment", 10, new MainClassDefinedTestCase("main.Assignment(.*)"));
        addManualRestriction(INTERACTIVE_RUN, 5, new NoWarningOrErrorTestCase("OE Warnings", ".*(efresh|not in range|Hashtable Pattern).*", ".*Assuming implicit pattern.*", 0.3));

    }
}
