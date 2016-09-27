package gradingTools.comp401f15.assignment11;

import edu.emory.mathcs.backport.java.util.Arrays;
import gradingTools.comp401f15.assignment10.testCases.CommandListObjectTestCase;
import gradingTools.comp401f15.assignment10.testCases.CommandObjectExistsTestCase;
import gradingTools.comp401f15.assignment10.testCases.RepeatCommandObjectTestCase;
import gradingTools.comp401f15.assignment11.testcases.AbstractClassTestCase;
import gradingTools.comp401f15.assignment11.testcases.BeanTestCase;
import gradingTools.comp401f15.assignment11.testcases.SynchronizedAnimationTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.FailCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.PassCommandBeanTestCase;
import gradingTools.sharedTestCase.checkstyle.CheckstyleConstructorDefinedTestCase;
import gradingTools.sharedTestCase.checkstyle.CheckstyleMethodDefinedTestCase;
import wrappers.framework.grading.ExtendedProjectRequirements;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/19/13
 * Time: 9:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class Assignment11Requirements extends ExtendedProjectRequirements {
	Class[] emptyParameterTypes;

    public Assignment11Requirements() {
        addDueDate("11/12/2015 00:30:00", 1.05);
        addDueDate("11/14/2015 00:30:00", 1);
        addDueDate("11/19/2015 00:30:00", 0.9);
        addDueDate("11/21/2015 00:30:00", 0.75);

        // Abstract classes (3)
        addFeature("Abstract classes", 5,
                new AbstractClassTestCase("Locatable"),
                new AbstractClassTestCase("BoundedShape"));
//                new AbstractAncestorTokenTestCase());
//      //Do the command tokens exist and do word stuff
//        addFeature("Generic classes", 5,
//                new GenerictClassCheckStyleTestCase("Table"));
    	addFeature("Pass/Fail Token Classes Implemented", 4,
    		
//    			new CallCommandBeanTestCase(),
    			new PassCommandBeanTestCase(),
    			new FailCommandBeanTestCase()
//    			new RepeatCommandBeanTestCase()
    			);

        
    	// for some  reason the following do not work
    	// initialize token array
//    	addFeature("Scanner Initialization", 0,
//        		new ScannerBeanReturnsTokenInterfaceArrayTestCase()    			
//    			);
//    	addFeature("Scanner Makes Pass and Fail", 4,
//    			new CallCommandCreatedTestCase(),
//    			new PassCommandCreatedTestCase(),
//    			new FailCommandCreatedTestCase()
//    			
//    			);
        addFeature("Passed Failed Approach Command Objects", 6, 
        		new CommandObjectExistsTestCase("PassCommand"),
        		new CommandObjectExistsTestCase("FailCommand"),
        		new CommandObjectExistsTestCase("ApproachCommand")
//        		new CheckstyleConstructorDefinedTestCase("PassCommand", ":@BridgeScene"),
//        		new CheckstyleConstructorDefinedTestCase("FailCommand", ":@BridgeScene"),
//        		new CheckstyleConstructorDefinedTestCase("ApproachCommand", ":@BridgeScene;@Avatar")
        				);
        addFeature("A11 Constructors", 4,
        		
        		new CheckstyleConstructorDefinedTestCase("PassCommand", ":@BridgeScene"),
        		new CheckstyleConstructorDefinedTestCase("FailCommand", ":@BridgeScene"),
        		new CheckstyleConstructorDefinedTestCase("ApproachCommand", ":@BridgeScene;@Avatar"),
//        		new CheckstyleConstructorDefinedTestCase("RepeatCommand", ":int;@Runnable"),
        		new CheckstyleConstructorDefinedTestCase("Parser", ":@BridgeScene")
        		
        );
   	 addFeature("CommandList Structure", 5, new CommandListObjectTestCase());
//   addFeature("Repeat command", 5, new RepeatCommandObjectChecker());
   addFeature("RepeatCommand Structure", 5, new RepeatCommandObjectTestCase());
   addFeature("Parser Properties", 6, new BeanTestCase("Parser",
		   Arrays.asList(new String[] {"CommandObject"}),
		   Arrays.asList(new String[] {"CommandText"})
		   ));

//   addFeature("Parser Structure", 6, 
//   		new CheckstyleConstructorDefinedTestCase("PassCommand", ":@BridgeScene"),
//   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseNumber:\\*->\\*"),
//   		new CheckstyleMethodCalledTestCase("SayCommand", "@BridgeScene!@say:String->\\*")
//   		
//   	);
   addFeature("Basic Parser Structure", 16, 
//	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseNumber:\\*->\\*"),
	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseApproach:\\*->Runnable"),
	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseCommand:\\*->Runnable"),
	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parsePass:\\*->Runnable"),
	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseFail:\\*->Runnable"),	   		
	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseRepeat:\\*->Runnable"),
	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseCommandList:\\*->Runnable"),
	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseMove:\\*->Runnable"),
	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseSay:\\*->Runnable")
// need to fix the call check as it returns null sometimes	   		
//	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseApproach:*->Runnable"),
//	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseCommand:*->Runnable"),
//	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parsePass:*->Runnable"),
//	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseFail:*->Runnable"),	   		
//	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseRepeat:*->Runnable"),
//	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseCommandList:*->Runnable")
	   		
	   	);
   addFeature("Synchronized Methods", 10, 
   		new SynchronizedAnimationTestCase("asynchronousArthur")
//   		new SynchronizedAnimationTestCase("asynchronousGalahad"),
//   		new SynchronizedAnimationTestCase("asynchronousLancelot"),
//   		new SynchronizedAnimationTestCase("asynchronousRobin")
   		);
   addFeature("Signed Number Structure", 2, true,
	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseNumber:\\*->\\*")
	   		
//	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseNumber:")

	   	);
   addManualFeature("Basic:approach Lancelot ", 4);
   addManualFeature("Basic: passed ", 4);
   addManualFeature("1-Level List:{ move Lancelot  4 3 say �Name?� } " , 8);
   addManualFeature("Recursive List:{{{ move Lancelot  4 3 say �Name?� }}} " , 8);
   addManualFeature("1-Level Repeat: repeat 3  move Robin  2 3 " , 6);
   addManualFeature("Mixed List Repeat: repeat 3 { move Lancelot  2 3 move Arthur 5 6 } " , 4);
   addManualFeature("Recursive Repeat: repeat 3 repeat 2 { move Lancelot  2 3 move Arthur 5 6 } " , 4);
   addManualFeature("Recursive Repeat and CL: repeat 3 repeat 2 {{{ move Lancelot  2 3 move Arthur 5 6 }}} " , 4);
   addManualFeature("demo of synchronized", 10);
   addManualFeature("demo of refreshing commands", 10);
   addManualFeature("All Extra: repeat -3 repeat +2 {{{ move Lancelot  -2 +3 move Arthur 5 6 }}}" , 8, true);

   
   
// addFeature("A11 Parser Structure", 24, 
////	new CheckstyleMethodDefinedTestCase("Parser", 	"@parseNumber:\\*->\\*"),
//	new CheckstyleMethodDefinedTestCase("Parser", 	"@parseApproach:*->Runnable"),
//	new CheckstyleMethodDefinedTestCase("Parser", 	"@parseCommand:*->Runnable"),
//	new CheckstyleMethodDefinedTestCase("Parser", 	"@parsePass:*->Runnable"),
//	new CheckstyleMethodDefinedTestCase("Parser", 	"@parseFail:*->Runnable"),	   		
//	new CheckstyleMethodDefinedTestCase("Parser", 	"@parseRepeat:*->Runnable"),
//	new CheckstyleMethodDefinedTestCase("Parser", 	"@parseCommandList:*->Runnable"),
//	
//	new CheckstyleMethodCalledTestCase("Parser", 	"@parseApproach:*->Runnable"),
//	new CheckstyleMethodCalledTestCase("Parser", 	"@parseCommand:*->Runnable"),
//	new CheckstyleMethodCalledTestCase("Parser", 	"@parsePass:*->Runnable"),
//	new CheckstyleMethodCalledTestCase("Parser", 	"@parseFail:*->Runnable"),	   		
//	new CheckstyleMethodCalledTestCase("Parser", 	"@parseRepeat:*->Runnable"),
//	new CheckstyleMethodCalledTestCase("Parser", 	"@parseCommandList:*->Runnable")
//	
//);
   
        
//        addFeature("Parser structure", 6, 
//        		new CheckstyleConstructorDefinedTestCase("PassCommand", ":@BridgeScene"),
//        		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseNumber:\\*->\\*"),
//        		new CheckstyleMethodCalledTestCase("SayCommand", "@BridgeScene!@say:String->\\*")
//        		
//        				);		
      
        
        
        // New commands (17)
////        addFeature("Passed & failed", 2);
//        addFeature("Passed failed approach cmd obj", 5, new AdditionalCommandObjectsChecker());
       

//
//        //Parsing (70)
//        addFeature("Methods for all 8 nonterminals", 10, new ParserMethodChecker());
////        addManualFeature("Recursive cmd list commands", 40,
////                new QuestionTestCase("Does the command list parser recursively parse?", "Recursive command list test case"),
////                new QuestionTestCase("Test the command interpreter. Do command lists work properly?", "Command list functionality test case"));
////        addManualFeature("Recursive repeat commands", 20,
////                new QuestionTestCase("Does the repeat command parser recursively parse?", "Recursive repeat command test case"),
////                new QuestionTestCase("Test the command interpreter. Do repeat commands work properly?", "Command list functionality test case"));
//
//        // Animation (20)
//        addManualFeature("Synchronized avatar methods", 10);
////        addManualFeature("Coordinated animation", 10);
//
//        // Generics (10)
//        addManualFeature("Generic table", 10);
//
//        // Extra Credit
////        addFeature("Lockstep animation", 3, true);
////        addFeature("Signed number parsing", 2, true);
////        addFeature("Extended grammar parsing", 15, true);
////        addFeature("Exceptions", 10, true);
////        addFeature("Undo/redo", 5, true);
//
    }
}
