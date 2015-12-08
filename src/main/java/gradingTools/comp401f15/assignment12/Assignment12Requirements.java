package gradingTools.comp401f15.assignment12;

import bus.uigen.widgets.TextFieldFactory;
import edu.emory.mathcs.backport.java.util.Arrays;
import gradingTools.comp401f15.assignment10.testCases.CommandListObjectTestCase;
import gradingTools.comp401f15.assignment10.testCases.CommandObjectExistsTestCase;
import gradingTools.comp401f15.assignment10.testCases.InterfaceImplementedTestCase;
import gradingTools.comp401f15.assignment10.testCases.RepeatCommandObjectTestCase;
import gradingTools.comp401f15.assignment11.testcases.*;
import gradingTools.comp401f15.assignment12.testcases.FactoryMethodTestCase;
import gradingTools.comp401f15.assignment12.testcases.GenerictClassCheckStyleTestCase;
import gradingTools.comp401f15.assignment12.testcases.LockstepAnimationTestCase;
import gradingTools.comp401f15.assignment12.testcases.WaitingAnimationTestCase;
import gradingTools.comp401f15.assignment4.testcases.ScannerBeanReturnsTokenInterfaceArrayTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.CallCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.DefineCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.FailCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.MoveCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.PassCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.ProceedAllCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.RedoCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.RepeatCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.RotateLeftArmCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.RotateRightArmCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.SayCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.SleepCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.ThreadCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.UndoCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.WaitCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.CallCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.DefineCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.FailCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.MoveCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.PassCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.ProceedAllCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.RedoCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.RepeatCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.RotateLeftArmCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.RotateRightArmCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.SayCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.SleepCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.ThreadCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.UndoCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.WaitCommandBeanTestCase;
import gradingTools.sharedTestCase.CheckstyleConstructorDefinedTestCase;
import gradingTools.sharedTestCase.CheckstyleMethodCalledTestCase;
import gradingTools.sharedTestCase.CheckstyleMethodDefinedTestCase;
import wrappers.framework.grading.ExtendedProjectRequirements;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/19/13
 * Time: 9:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class Assignment12Requirements extends ExtendedProjectRequirements {
	Class[] emptyParameterTypes;

    public Assignment12Requirements() {
        addDueDate("12/03/2015 00:30:00", 1.05);
        addDueDate("12/08/2015 00:30:00", 1.0);  
        
        addFeature("Previous Classes Exist (No pts)", 0, 
        		new InterfaceImplementedTestCase("Parser", Object.class),
        		new InterfaceImplementedTestCase("Table", Object.class),
        		new InterfaceImplementedTestCase("CommandInterpreter", Object.class)

        		);
        addFeature("Generic classes", 15,
                new GenerictClassCheckStyleTestCase("Table"));
        
        addFeature("Coordinated animation starts", 10, 
           		new WaitingAnimationTestCase("startAnimation", "waitingArthur", "waitingLancelot")
           		
           		);
      
        
        		// make sure teh right parser is found);

     // Command Objects (21 pts)
        addFeature("Extra Command Classes Structure ", 38, true,
        		new CommandObjectExistsTestCase("RotateLeftArmCommand"),
        		new CommandObjectExistsTestCase("RotateRightArmCommand"),
        		new CommandObjectExistsTestCase("SleepCommand"),
        		new CommandObjectExistsTestCase("DefineCommand"),
        		new CommandObjectExistsTestCase("CallCommand"),
        		new CommandObjectExistsTestCase("ThreadCommand"),
        		new CommandObjectExistsTestCase("ProceedAllCommand"),        		

        		new CheckstyleConstructorDefinedTestCase("RotateLeftArmCommand", ":@Avatar; int"),
        		new CheckstyleConstructorDefinedTestCase("RotateRightArmCommand", ":@Avatar; int"),
        		new CheckstyleConstructorDefinedTestCase("SleepCommand", ":long"),
        		new CheckstyleConstructorDefinedTestCase("DefineCommand", ":String;@Table"),
        		new CheckstyleConstructorDefinedTestCase("CallCommand", ":String;@Table"),
        		new CheckstyleConstructorDefinedTestCase("ThreadCommand", ":@String;@Table"),
        		
    	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseRotateLeftArm:\\*->Runnable"),
    	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseRotateRightArm:\\*->Runnable"),
    	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseSleep:\\*->Runnable"),
    	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseDefine:\\*->Runnable"),
    	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseCall:\\*->Runnable"),
    	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseThread:\\*->Runnable")   
        		
        		);
//        addFeature("Factory Classes Structure ", 4, true,
//        		
//        		new InterfaceImplementedTestCase("SingletonsCreator", null),
//        		new InterfaceImplementedTestCase("CustomSwingTextFieldFactory", TextFieldFactory.class)
//        		
//        		);
        
//        
//        addFeature("Extra Command Constructors", 12, true,       		
//        		new CheckstyleConstructorDefinedTestCase("RotateLeftArmCommand", ":@Avatar; int"),
//        		new CheckstyleConstructorDefinedTestCase("RotateRightArmCommand", ":@Avatar; int"),
//        		new CheckstyleConstructorDefinedTestCase("SleepCommand", ":long"),
//        		new CheckstyleConstructorDefinedTestCase("DefineCommand", ":String;@Table"),
//        		new CheckstyleConstructorDefinedTestCase("CallCommand", ":String;@Table"),
//        		new CheckstyleConstructorDefinedTestCase("ThreadCommand", ":@String;@Table")
//        );
//        addFeature("Extra Command Signatures", 12, true,
//        		new InterfaceImplementedTestCase("Parser", Object.class),// make sure teh right parser is found
//    	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseRotateLeftArm:\\*->Runnable"),
//    	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseRotateRightArm:\\*->Runnable"),
//    	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseSleep:\\*->Runnable"),
//    	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseDefine:\\*->Runnable"),
//    	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseCall:\\*->Runnable"),
//    	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseThread:\\*->Runnable")    	   		
//    	   		
//        		);
//        @RotateLeftArmCommand = @Avatar!@rotateLeftArm:int->void,
//				 @RotateRightArmCommand = @Avatar!@rotateRightArm:int->void,
//				 @SleepCommand = @ThreadSupport!sleep:long->void,
//				 @DefineCommand = @Table!put:Object;Object->*,
//				 @CallCommand = @Table!get:Object->Object,				 
//				 @ThreadCommand = @Table!get:Object->Object | Thread!start:->void,
//				 @CustomSwingTextFieldFactory = JTextField!setForeground:*->void | JTextField!setForeground:*->void,
//				 main.Assignment(.*) = ObjectEditor.initialize:->void
//        addFeature("Extra Command Calls", 12, true,
//    	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseRotateLeftArm:\\*->Runnable"),
//    	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseRotateRightArm:\\*->Runnable"),
//    	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseSleep:\\*->Runnable"),
//    	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseDefine:\\*->Runnable"),
//    	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseCall:\\*->Runnable"),
//    	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseThread:\\*->Runnable")    	   		
//    	   		
//    	   		
//        		);
        addFeature("Factory Classes Structure", 13, true,
        	
        		new InterfaceImplementedTestCase("SingletonsCreator", null),
        		new InterfaceImplementedTestCase("CustomSwingTextFieldFactory", TextFieldFactory.class),
        		
        		new CheckstyleMethodDefinedTestCase("SingletonsCreator", 	"@scannerFactoryMethod:->@ScannerBean"),
    	   		new CheckstyleMethodDefinedTestCase("SingletonsCreator", 	"@parserFactoryMethod:->@Parser"),
    	   		new CheckstyleMethodDefinedTestCase("SingletonsCreator", 	"@bridgeSceneFactoryMethod:->@BridgeScene"),
    	   		new CheckstyleMethodDefinedTestCase("SingletonsCreator", 	"@avatarTableFactoryMethod:->@Table"),
    	   		new CheckstyleMethodDefinedTestCase("SingletonsCreator", 	"@commandInterpreterFactoryMethod:->@CommandInterpreter"),
    	   		new CheckstyleMethodDefinedTestCase("SingletonsCreator", 	"@scannerFactoryMethod:->@ScannerBean"),
    	   		new CheckstyleMethodDefinedTestCase("SingletonsCreator", 	"@broadcastingClearanceManagerMethod:->BroadcastingClearanceManager")
//  can get false positives with calls   	   		
//    	   		new CheckstyleMethodCalledTestCase("Parser", 	"@SingletonsCreator!@scannerFactoryMethod:->@ScannerBean"),
//    	   		new CheckstyleMethodCalledTestCase("CommandInterpreter", 	"@parserFactoryMethod:->@Parser"),
//    	   		new CheckstyleMethodCalledTestCase("CommandInterpreter", 	"@SingletonsCreator!@bridgeSceneFactoryMethod:->@BridgeScene"),
//    	   		new CheckstyleMethodCalledTestCase("CommandInterpreter", 	"@SingletonsCreator!@avatarTableFactoryMethod:->@Table"),    	   		
//    	   		new CheckstyleMethodCalledTestCase("CommandInterpreter", 	"@BroadcastingClearanceManager!proceedAll:->void")

        
        		
        		);
        
//        @RotateLeftArmCommand = :@Avatar; int,
//		@RotateRightArmCommand = :@Avatar; int,
//		@SleepCommand = :long,
//		@DefineCommand = :String;@Runnable;@Table,	
//		@CallCommand = :String;@Runnable;@Table,
//		@ThreadCommand = :String;@Table,
        
//        addFeature("A12 Constructors", 12,  true,      		
//        		new CheckstyleConstructorDefinedTestCase("@RotateLeftArmCommand", ":*; int"),
//        		new CheckstyleConstructorDefinedTestCase("@RotateRightArmCommand", ":*; int"),
//        		new CheckstyleConstructorDefinedTestCase("@SleepCommand", ":long"),
//        		new CheckstyleConstructorDefinedTestCase("@DefineCommand", ":String;*"),
//        		new CheckstyleConstructorDefinedTestCase("@CallCommand", ":String;*"),
//        		new CheckstyleConstructorDefinedTestCase("@ThreadCommand", ":String;@Table")
//        );
// @Parser
//        @parseRotateLeftArm:*->Runnable |
//	 	@parseRotateRightArm:*->Runnable |
//	 	@parseSleep:*->Runnable |
//		@parseDefine:*->Runnable |
//		@parseCall:*->Runnable |
//		@parseThread:*->Runnable,
//        @scannerFactoryMethod:->@ScannerBean |
//		@parserFactoryMethod:->@Parser |
//		@bridgeSceneFactoryMethod:->@BridgeScene |
//		@avatarTableFactoryMethod:->@Table |
//		@commandInterpreterFactoryMethod:->@CommandInterpreter |
//		@broadcastingClearanceManagerMethod:->BroadcastingClearanceManager,
//		@SingletonsCreator =

//        @scannerFactoryMethod:->@ScannerBean |
//		@parserFactoryMethod:->@Parser |
//		@bridgeSceneFactoryMethod:->@BridgeScene |
//		@avatarTableFactoryMethod:->@Table |
//		@commandInterpreterFactoryMethod:->@CommandInterpreter |
//		@broadcastingClearanceManagerMethod:->BroadcastingClearanceManager,
        
//		@parserFactoryMethod:->@Parser

      
//      
//        addFeature("Factory Signatures", 14, true,    	     	   		
//    	   		new CheckstyleMethodDefinedTestCase("SingletonsCreator", 	"@scannerFactoryMethod:->@ScannerBean"),
//    	   		new CheckstyleMethodDefinedTestCase("SingletonsCreator", 	"@parserFactoryMethod:->@Parser"),
//    	   		new CheckstyleMethodDefinedTestCase("SingletonsCreator", 	"@bridgeSceneFactoryMethod:->@BridgeScene"),
//    	   		new CheckstyleMethodDefinedTestCase("SingletonsCreator", 	"@avatarTableFactoryMethod:->@Table"),
//    	   		new CheckstyleMethodDefinedTestCase("SingletonsCreator", 	"@commandInterpreterFactoryMethod:->@CommandInterpreter"),
//    	   		new CheckstyleMethodDefinedTestCase("SingletonsCreator", 	"@scannerFactoryMethod:->@ScannerBean"),
//    	   		new CheckstyleMethodDefinedTestCase("SingletonsCreator", 	"@broadcastingClearanceManagerMethod:->BroadcastingClearanceManager")
//
//        		);
//     
//        addFeature("Factory Calls", 10, true,    	   		    	   		
//    	   		new CheckstyleMethodCalledTestCase("Parser", 	"@SingletonsCreator!@scannerFactoryMethod:->@ScannerBean"),
//    	   		new CheckstyleMethodCalledTestCase("CommandInterpreter", 	"@parserFactoryMethod:->@Parser"),
//    	   		new CheckstyleMethodCalledTestCase("CommandInterpreter", 	"@SingletonsCreator!@bridgeSceneFactoryMethod:->@BridgeScene"),
//    	   		new CheckstyleMethodCalledTestCase("CommandInterpreter", 	"@SingletonsCreator!@avatarTableFactoryMethod:->@Table"),    	   		
//    	   		new CheckstyleMethodCalledTestCase("CommandInterpreter", 	"@BroadcastingClearanceManager!proceedAll:->void")
//
//    	   		
//        		);
     // do this early so following tests can use the instances
        // actuall do this late because the singleton is locked
        addFeature("Factory method execution", 12,
                new FactoryMethodTestCase("SingletonsCreator", "scannerFactoryMethod", "ScannerBean" ),
                new FactoryMethodTestCase("SingletonsCreator", "bridgeSceneFactoryMethod", "BridgeScene" ),
                new FactoryMethodTestCase("SingletonsCreator", "bridgeSceneFactoryMethod", "BridgeScene" ),
                new FactoryMethodTestCase("SingletonsCreator", "avatarTableFactoryMethod", "Table" ),
                new FactoryMethodTestCase("SingletonsCreator", "broadcastingClearanceManagerMethod", "BroadcastingClearanceManager" ),
                new FactoryMethodTestCase("SingletonsCreator", "commandInterpreterFactoryMethod", "CommandInterpreter" )
                ); 
        addFeature("Exception Classes Structure", 4, true,
            	
        		new InterfaceImplementedTestCase("ScanningException", Object.class),
        		new InterfaceImplementedTestCase("ParsingException", Object.class)       		
        		
        		);
        addFeature("Undo Classes Structure", 2, true,
        		new CommandObjectExistsTestCase("UndoCommand"),
        		new CommandObjectExistsTestCase("RedoCommand"));
     addFeature("Lockstep Animation", 10, true,
           		new LockstepAnimationTestCase("lockstepGuard", "lockstepArthur")
           		
           		);
      addManualFeature("Proceed all demo", 10, true);
      addManualFeature("Extended grammar lockstep demo", 20, true);
      addManualFeature("Lockstep method demo ", 7, true);
      addManualFeature("Undo demo ", 6, true);
      addManualFeature("Exceptions thrown and caught in source code ", 7, true);
      addManualFeature("OE Factory Demo ", 6, true);





//    	addFeature("Pass/Fail Token Classes Implemented", 4,
//    		
////    			new CallCommandBeanTestCase(),
//    			new PassCommandBeanTestCase(),
//    			new FailCommandBeanTestCase()
////    			new RepeatCommandBeanTestCase()
//    			);
//
//        
//    	// for some  reason the following do not work
//    	// initialize token array
////    	addFeature("Scanner Initialization", 0,
////        		new ScannerBeanReturnsTokenInterfaceArrayTestCase()    			
////    			);
////    	addFeature("Scanner Makes Pass and Fail", 4,
////    			new CallCommandCreatedTestCase(),
////    			new PassCommandCreatedTestCase(),
////    			new FailCommandCreatedTestCase()
////    			
////    			);
//        addFeature("Passed Failed Approach Command Objects", 6, 
//        		new CommandObjectExistsTestCase("PassCommand"),
//        		new CommandObjectExistsTestCase("FailCommand"),
//        		new CommandObjectExistsTestCase("ApproachCommand")
////        		new CheckstyleConstructorDefinedTestCase("PassCommand", ":@BridgeScene"),
////        		new CheckstyleConstructorDefinedTestCase("FailCommand", ":@BridgeScene"),
////        		new CheckstyleConstructorDefinedTestCase("ApproachCommand", ":@BridgeScene;@Avatar")
//        				);
//        addFeature("A11 Constructors", 4,
//        		
//        		new CheckstyleConstructorDefinedTestCase("PassCommand", ":@BridgeScene"),
//        		new CheckstyleConstructorDefinedTestCase("FailCommand", ":@BridgeScene"),
//        		new CheckstyleConstructorDefinedTestCase("ApproachCommand", ":@BridgeScene;@Avatar"),
////        		new CheckstyleConstructorDefinedTestCase("RepeatCommand", ":int;@Runnable"),
//        		new CheckstyleConstructorDefinedTestCase("Parser", ":@BridgeScene")
//        		
//        );
//   	 addFeature("CommandList Structure", 5, new CommandListObjectTestCase());
////   addFeature("Repeat command", 5, new RepeatCommandObjectChecker());
//   addFeature("RepeatCommand Structure", 5, new RepeatCommandObjectTestCase());
//   addFeature("Parser Properties", 6, new BeanTestCase("Parser",
//		   Arrays.asList(new String[] {"CommandObject"}),
//		   Arrays.asList(new String[] {"CommandText"})
//		   ));
//
////   addFeature("Parser Structure", 6, 
////   		new CheckstyleConstructorDefinedTestCase("PassCommand", ":@BridgeScene"),
////   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseNumber:\\*->\\*"),
////   		new CheckstyleMethodCalledTestCase("SayCommand", "@BridgeScene!@say:String->\\*")
////   		
////   	);
//   addFeature("Basic Parser Structure", 16, 
////	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseNumber:\\*->\\*"),
//	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseApproach:*->Runnable"),
//	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseCommand:*->Runnable"),
//	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parsePass:*->Runnable"),
//	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseFail:*->Runnable"),	   		
//	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseRepeat:*->Runnable"),
//	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseCommandList:*->Runnable"),
//	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseMove:*->Runnable"),
//	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseSay:*->Runnable")
//// need to fix the call check as it returns null sometimes	   		
////	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseApproach:*->Runnable"),
////	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseCommand:*->Runnable"),
////	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parsePass:*->Runnable"),
////	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseFail:*->Runnable"),	   		
////	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseRepeat:*->Runnable"),
////	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseCommandList:*->Runnable")
//	   		
//	   	);
//   addFeature("Synchronized Methods", 10, 
//   		new SynchronizedAnimationTestCase("asynchronousArthur"),
//   		new SynchronizedAnimationTestCase("asynchronousGalahad"),
//   		new SynchronizedAnimationTestCase("asynchronousLancelot"),
//   		new SynchronizedAnimationTestCase("asynchronousRobin")
//   		);
//   addFeature("Signed Number Structure", 2, true,
//	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseNumber:\\*->\\*")
//	   		
////	   		new CheckstyleMethodCalledTestCase("Parser", 	"@parseNumber:")
//
//	   	);
//   addManualFeature("Basic:approach Lancelot ", 4);
//   addManualFeature("Basic: passed ", 4);
//   addManualFeature("1-Level List:{ move Lancelot  4 3 say “Name?” } " , 8);
//   addManualFeature("Recursive List:{{{ move Lancelot  4 3 say “Name?” }}} " , 8);
//   addManualFeature("1-Level Repeat: repeat 3  move Robin  2 3 " , 6);
//   addManualFeature("Mixed List Repeat: repeat 3 { move Lancelot  2 3 move Arthur 5 6 } " , 4);
//   addManualFeature("Recursive Repeat: repeat 3 repeat 2 { move Lancelot  2 3 move Arthur 5 6 } " , 4);
//   addManualFeature("Recursive Repeat and CL: repeat 3 repeat 2 {{{ move Lancelot  2 3 move Arthur 5 6 }}} " , 4);
//   addManualFeature("demo of synchronized", 10);
//   addManualFeature("demo of refreshing commands", 10);
//   addManualFeature("All Extra: repeat -3 repeat +2 {{{ move Lancelot  -2 +3 move Arthur 5 6 }}}" , 8, true);

   
   
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
