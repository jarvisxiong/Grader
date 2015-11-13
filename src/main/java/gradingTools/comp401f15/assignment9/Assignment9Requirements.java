package gradingTools.comp401f15.assignment9;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment6.testCases.*;
import gradingTools.assignment9.testCases.*;

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
        addDueDate("10/28/2013 12:59:00", 1.05);
        addDueDate("10/30/2013 12:59:00", 1);
        addDueDate("11/03/2013 12:59:00", 0.9);
        addDueDate("11/06/2013 12:59:00", 0.75);

        // Part 1
        addManualFeature("Does the controller call the interpreter setter?", 15);
        addManualFeature("Does the interpreter GUI control the scene?", 15);
        addManualFeature("Does the controller register a listener for the GUI object", 5);

        // Part 2
        addFeature("(1) Inheriting Scene painter class tagged", 5, new ScenePainterTagTestCase());
        addFeature("(1) View extends component", 5, new ScenePainterExtendsComponentTestCase());
        addFeature("(1) View is listener of shapes", 10, new ScenePainterListenerTestCase());
        addFeature("(1) paint() called when events fire", 5, new ScenePainterPaintOnEventTestCase());
        addManualFeature("Does the paint method draw everything in the view?", 25);

        // Part 2 (EC)
        addFeature("(2) Listener & Observing Painter classes tagged", 5, new ListenerAndPainterTagTestCase());
        addFeature("(2) Observable Bridge Scene Painter extends component", 5, new ObservablePainterExtendsComponentTestCase());
        addFeature("(2) Paint listener paint method", 5, new PaintListenerPaintMethodTestCase());
        addFeature("(2) View classes register as listener", 20, new PaintListenerListenersTestCase());
        addFeature("(2) Listeners notify on fired events", 10, new PaintListenerPaintOnEventTestCase());
        addManualFeature("Do the paint listener views do all the painting/drawing?", 25);
        addManualFeature("Are the avatars drawn on top of the bridge and gorge?", 5);

        // Part 3
        addManualFeature("Is there a demo?", 10);

        // Extra Credit
        addManualFeature("Does the command interpreter display the error property?", 5, true);
        addManualFeature("Is there a progress bar?", 5, true);
        addManualFeature("Does the interpreter have two or more action components?", 5, true);
        addFeature("Bridge scene controller tagged", 2, true, new BridgeSceneControllerTagTestCase());
        addManualFeature("Does the bridge scene controller track the locations of mouse clicks?", 4, true);
        addManualFeature("Does the bridge scene controller support keyboard commands?", 4, true);


        // Define the restrictions
        addRestriction("No public variables.", -5, new EncapsulationTestCase("Encapsulation test case"));
        addRestriction("Interface object assignments.", -5, new InterfaceTypeTestCase("Interface type test case"));
        addRestriction("At least three packages.", -5, new ThreePackageTestCase("Three package test case"));
        addRestriction("Main class in correct package.", -5, new MainClassTestCase("Assignment9", "Main method test case"));
        addRestriction("No System.exit()", -5, new SystemExitTestCase("System.exit test case"));
        addRestriction("No OE .refresh()", -5, new RefreshTestCase());

    }
}
