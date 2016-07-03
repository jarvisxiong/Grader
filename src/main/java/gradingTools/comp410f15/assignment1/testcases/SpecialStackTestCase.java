package gradingTools.comp410f15.assignment1.testcases;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.ClassesManager;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.util.Option;
import gradingTools.utils.RunningProjectUtils;

public class SpecialStackTestCase extends BasicTestCase {

	public SpecialStackTestCase() {
		super("SpecialStack Implementation Test Case");
	}
 
	public String getOutput(Project project, String input1, String input2)
			throws NotRunnableException {

		// Get the output when we have integer input from the user
		RunningProject oneInputRunningProject = RunningProjectUtils.runProject(project, 1, input1);
		String oneInputOutput = oneInputRunningProject.await();

		// Get the output when we have double input from the user
		// changed the timeout as a test case failed in the distriuted case
		RunningProject twoInputsRunningProject = RunningProjectUtils.runProject(project, 6, input1,
				input2);
		String twoInputsOutput = twoInputsRunningProject.await();
		twoInputsOutput = twoInputsOutput.substring(oneInputOutput.length());

		if (twoInputsOutput.length() == 0
				&& (oneInputRunningProject.getErrorOutput().contains("InputMismatchException") || twoInputsRunningProject
						.getErrorOutput().contains("InputMismatchException"))) {
			return null;
		}
		return twoInputsOutput;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		try {
			Option<ClassesManager> classesManager = project.getClassesManager();
			
			// Initialize score and feedback message
			double score = 1;
			String message = "";
			int totalPush = 0;
			int totalPeek = 0;
			int totalPop = 0;
			int totalIsEmpty = 0;
			int totalSpecialPush = 0;
			int wrongPush = 0;
			int wrongPeek = 0;
			int wrongPop = 0;
			int wrongIsEmpty = 0;
			int wrongSpecialPush = 0;
			
			//*****************String Test************************/
			/*
			 * push "aaa"
			 * peek
			 * isEmpty
			 * pop
			 * isEmpty
			 * specialPush "aaaaaa"
			 * peek
			 * isEmpty
			 * pop
			 * isEmpty
			 * push "bbb" 
			 * specialPush "bbbbbb" 
			 * push "ccc"
			 * specialPush "cccccc"
			 * push "ddd"
			 * specialPush "dddddd"
			 * push "eee"
			 * specialPush"eeeeee"
			 * pop
			 * pop
			 * pop
			 * pop
			 * pop
			 * pop
			 * pop
			 * pop
			 * isEmpty
			*/
			// Inputs
			String StackClassName = "specialstack";
			String[] input = new String[10];
			input[0] = "aaa";
			input[1] = "aaaaaa";
			input[2] = "bbb";
			input[3] = "bbbbbb";
			input[4] = "ccc";
			input[5] = "cccccc";
			input[6] = "ddd";
			input[7] = "dddddd";
			input[8] = "eee";
			input[9] = "eeeeee";
			
			//Get Stack Class
			Option<ClassDescription> cd = (Option<ClassDescription>) classesManager.get().findByClassOrInterfaceName(StackClassName);			
			Class<String> stack = (Class<String>) cd.get().getJavaClass();
			Object stackInstance = null;
			try {
				stackInstance = stack.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			Method push = null;
			Method peek = null;
			Method pop = null;
			Method isEmpty = null;
			Method specialPush = null;
			
			//Extract methods
			for (Method method : stack.getDeclaredMethods()) {
				if(method.getName().equals("push")){
					push = method;
				}
				else if(method.getName().equals("peek")){
					peek = method;
				}
				else if(method.getName().equals("pop")){
					pop = method;
				}
				else if(method.getName().equals("isEmpty")){
					isEmpty = method;
				}
				else if(method.getName().equals("specialPush")){
					specialPush = method;
				}
			}
			
			//Push
			totalPush++;
			final Object[] args = new Object[1];
		    args[0] = new String[] {input[0]};
			try{
				push.invoke(stackInstance, args);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Peek
			totalPeek++;
			try {
				String[] i =  (String[])peek.invoke(stackInstance);
				if(!i[0].equals(input[0])){
					message += "Peek doesn't work\n";
					wrongPeek++;
				}
			}catch (InvocationTargetException e) {
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (NullPointerException e){
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//isEmpty	
			totalIsEmpty++;		
			try {
				Boolean i =  (Boolean) isEmpty.invoke(stackInstance);
				if(i){
					message += "isEmpty doesn't work\n";
					wrongIsEmpty++;
				}
			} catch (InvocationTargetException e) {
				message += "isEmpty doesn't work\n";
				wrongIsEmpty++;
			} catch (NullPointerException e){
				message += "IsEmpty throws error \n";
				wrongIsEmpty++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Pop
			totalPop++;
			try {
				String[] i =  (String[])pop.invoke(stackInstance);
				if(!i[0].equals(input[0])){
					message += "Pop returns wrong string\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//isEmpty
			totalIsEmpty++;
			try {
				Boolean i =  (Boolean) isEmpty.invoke(stackInstance);
				if(!i){
					message += "isEmpty doesn't work\n";
					wrongIsEmpty++;
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				message += "isEmpty doesn't work\n";
				wrongIsEmpty++;
			} catch (NullPointerException e){
				message += "IsEmpty throws error \n";
				wrongIsEmpty++;
			}
			
			//SpecialPush
			totalSpecialPush++;
			args[0] = new String[] {input[1]};
			try{
				specialPush.invoke(stackInstance, args);
			} catch (InvocationTargetException e) {
				message += "SpecialPush throws error \n";
				wrongSpecialPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Peek
			totalPeek++;
			try {
				String[] i =  (String[])peek.invoke(stackInstance);
				if(!i[0].equals(input[1])){
					message += "Peek doesn't work\n";
					wrongPeek++;
				}
			}catch (InvocationTargetException e) {
				message += "Peek throws error \n";
				wrongPeek++;
			}catch (NullPointerException e){
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//isEmpty	
			totalIsEmpty++;		
			try {
				Boolean i =  (Boolean) isEmpty.invoke(stackInstance);
				if(i){
					message += "isEmpty doesn't work\n";
					wrongIsEmpty++;
				}
			} catch (InvocationTargetException e) {
				message += "isEmpty doesn't work\n";
				wrongIsEmpty++;
			} catch (NullPointerException e){
				message += "IsEmpty throws error \n";
				wrongIsEmpty++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Pop
			totalPop++;
			try {
				String[] i =  (String[])pop.invoke(stackInstance);
				if(!i[0].equals(input[1])){
					message += "Pop returns wrong string\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//isEmpty
			totalIsEmpty++;
			try {
				Boolean i =  (Boolean) isEmpty.invoke(stackInstance);
				if(!i){
					message += "isEmpty doesn't work\n";
					wrongIsEmpty++;
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				message += "isEmpty doesn't work\n";
				wrongIsEmpty++;
			} catch (NullPointerException e){
				message += "IsEmpty throws error \n";
				wrongIsEmpty++;
			}
			
			//Push
			totalPush++;
		    args[0] = new String[] {input[2]};
			try{
				push.invoke(stackInstance, args);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//SpecialPush
			totalSpecialPush++;
			args[0] = new String[] {input[3]};
			try{
				specialPush.invoke(stackInstance, args);
			} catch (InvocationTargetException e) {
				message += "SpecialPush throws error \n";
				wrongSpecialPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Push
			totalPush++;
		    args[0] = new String[] {input[4]};
			try{
				push.invoke(stackInstance, args);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//SpecialPush
			totalSpecialPush++;
			args[0] = new String[] {input[5]};
			try{
				specialPush.invoke(stackInstance, args);
			} catch (InvocationTargetException e) {
				message += "SpecialPush throws error \n";
				wrongSpecialPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Push
			totalPush++;
		    args[0] = new String[] {input[6]};
			try{
				push.invoke(stackInstance, args);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//SpecialPush
			totalSpecialPush++;
			args[0] = new String[] {input[7]};
			try{
				specialPush.invoke(stackInstance, args);
			} catch (InvocationTargetException e) {
				message += "SpecialPush throws error \n";
				wrongSpecialPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Push
			totalPush++;
		    args[0] = new String[] {input[8]};
			try{
				push.invoke(stackInstance, args);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//SpecialPush
			totalSpecialPush++;
			args[0] = new String[] {input[9]};
			try{
				specialPush.invoke(stackInstance, args);
			} catch (InvocationTargetException e) {
				message += "SpecialPush throws error \n";
				wrongSpecialPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Pop
			totalPop++;
			try {
				String[] i =  (String[])pop.invoke(stackInstance);
				if(!i[0].equals(input[8])){
					message += "Pop returns wrong string\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Pop
			totalPop++;
			try {
				String[] i =  (String[])pop.invoke(stackInstance);
				if(!i[0].equals(input[6])){
					message += "Pop returns wrong string\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//Pop
			totalPop++;
			try {
				String[] i =  (String[])pop.invoke(stackInstance);
				if(!i[0].equals(input[4])){
					message += "Pop returns wrong string\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Pop
			totalPop++;
			try {
				String[] i =  (String[])pop.invoke(stackInstance);
				if(!i[0].equals(input[2])){
					message += "Pop returns wrong string\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Pop
			totalPop++;
			try {
				String[] i =  (String[])pop.invoke(stackInstance);
				if(!i[0].equals(input[3])){
					message += "Pop returns wrong string\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Pop
			totalPop++;
			try {
				String[] i =  (String[])pop.invoke(stackInstance);
				if(!i[0].equals(input[5])){
					message += "Pop returns wrong string\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Pop
			totalPop++;
			try {
				String[] i =  (String[])pop.invoke(stackInstance);
				if(!i[0].equals(input[7])){
					message += "Pop returns wrong string\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Pop
			totalPop++;
			try {
				String[] i =  (String[])pop.invoke(stackInstance);
				if(!i[0].equals(input[9])){
					message += "Pop returns wrong string\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//isEmpty
			totalIsEmpty++;
			try {
				Boolean i =  (Boolean) isEmpty.invoke(stackInstance);
				if(!i){
					message += "isEmpty doesn't work\n";
					wrongIsEmpty++;
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				message += "isEmpty doesn't work\n";
				wrongIsEmpty++;
			} catch (NullPointerException e){
				message += "IsEmpty throws error \n";
				wrongIsEmpty++;
			}
			
			
			//*****************Double Test************************/
			/*
			 * push 1.5
			 * peek
			 * isEmpty
			 * pop
			 * isEmpty
			 * specialPush 2.7
			 * peek
			 * isEmpty
			 * pop
			 * isEmpty
			 * push 4.9 
			 * specialPush 5.2
			 * push 6.0
			 * specialPush 9.0
			 * push 1000000000.0
			 * specialPush -18
			 * push 0
			 * specialPush 12.9
			 * pop
			 * pop
			 * pop
			 * pop
			 * pop
			 * pop
			 * pop
			 * pop
			 * isEmpty
			*/
			// Inputs
			double[] input2 = new double[10];
			input2[0] = 1.5;
			input2[1] = 2.7;
			input2[2] = 4.9;
			input2[3] = 5.2;
			input2[4] = 6.0;
			input2[5] = 9.0;
			input2[6] = 1000000000.0;
			input2[7] = -18;
			input2[8] = 0;
			input2[9] = 12.9;
			
			//Get Stack Class
			Class<Double> stack2 = (Class<Double>) cd.get().getJavaClass();
			Object stack2Instance = null;
			try {
				stack2Instance = stack2.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			
			//Extract methods
			for (Method method : stack2.getDeclaredMethods()) {
				if(method.getName().equals("push")){
					push = method;
				}
				else if(method.getName().equals("peek")){
					peek = method;
				}
				else if(method.getName().equals("pop")){
					pop = method;
				}
				else if(method.getName().equals("isEmpty")){
					isEmpty = method;
				}
				else if(method.getName().equals("specialPush")){
					specialPush = method;
				}
			}
			
			//Push
			totalPush++;
		    args[0] = new Double[] {input2[0]};
			try{
				push.invoke(stack2Instance, args);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Peek
			totalPeek++;
			try {
				Double[] i =  (Double[])peek.invoke(stack2Instance);
				if(!i[0].equals(input2[0])){
					message += "Peek doesn't work\n";
					wrongPeek++;
				}
			}catch (InvocationTargetException e) {
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (NullPointerException e){
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//isEmpty	
			totalIsEmpty++;		
			try {
				Boolean i =  (Boolean) isEmpty.invoke(stack2Instance);
				if(i){
					message += "isEmpty doesn't work\n";
					wrongIsEmpty++;
				}
			} catch (InvocationTargetException e) {
				message += "isEmpty doesn't work\n";
				wrongIsEmpty++;
			} catch (NullPointerException e){
				message += "IsEmpty throws error \n";
				wrongIsEmpty++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack2Instance);
				if(!i[0].equals(input2[0])){
					message += "Pop returns wrong Double\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//isEmpty
			totalIsEmpty++;
			try {
				Boolean i =  (Boolean) isEmpty.invoke(stack2Instance);
				if(!i){
					message += "isEmpty doesn't work\n";
					wrongIsEmpty++;
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				message += "isEmpty doesn't work\n";
				wrongIsEmpty++;
			} catch (NullPointerException e){
				message += "IsEmpty throws error \n";
				wrongIsEmpty++;
			}
			
			//SpecialPush
			totalSpecialPush++;
			args[0] = new Double[] {input2[1]};
			try{
				specialPush.invoke(stack2Instance, args);
			} catch (InvocationTargetException e) {
				message += "SpecialPush throws error \n";
				wrongSpecialPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Peek
			totalPeek++;
			try {
				Double[] i =  (Double[])peek.invoke(stack2Instance);
				if(!i[0].equals(input2[1])){
					message += "Peek doesn't work\n";
					wrongPeek++;
				}
			}catch (InvocationTargetException e) {
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (NullPointerException e){
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//isEmpty
			totalIsEmpty++;
			try {
				Boolean i =  (Boolean) isEmpty.invoke(stack2Instance);
				if(i){
					message += "isEmpty doesn't work\n";
					wrongIsEmpty++;
				}
			} catch (InvocationTargetException e) {
				message += "isEmpty doesn't work\n";
				wrongIsEmpty++;
			} catch (NullPointerException e){
				message += "IsEmpty throws error \n";
				wrongIsEmpty++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack2Instance);
				if(!i[0].equals(input2[1])){
					message += "Pop returns wrong Double\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//isEmpty
			totalIsEmpty++;
			try {
				Boolean i =  (Boolean) isEmpty.invoke(stack2Instance);
				if(!i){
					message += "isEmpty doesn't work\n";
					wrongIsEmpty++;
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				message += "isEmpty doesn't work\n";
				wrongIsEmpty++;
			} catch (NullPointerException e){
				message += "IsEmpty throws error \n";
				wrongIsEmpty++;
			}
			
			//Push
			totalPush++;
		    args[0] = new Double[] {input2[2]};
			try{
				push.invoke(stack2Instance, args);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//SpecialPush
			totalSpecialPush++;
			args[0] = new Double[] {input2[3]};
			try{
				specialPush.invoke(stack2Instance, args);
			} catch (InvocationTargetException e) {
				message += "SpecialPush throws error \n";
				wrongSpecialPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Push
			totalPush++;
		    args[0] = new Double[] {input2[4]};
			try{
				push.invoke(stack2Instance, args);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//SpecialPush
			totalSpecialPush++;
			args[0] = new Double[] {input2[5]};
			try{
				specialPush.invoke(stack2Instance, args);
			} catch (InvocationTargetException e) {
				message += "SpecialPush throws error \n";
				wrongSpecialPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Push
			totalPush++;
		    args[0] = new Double[] {input2[6]};
			try{
				push.invoke(stack2Instance, args);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//SpecialPush
			totalSpecialPush++;
			args[0] = new Double[] {input2[7]};
			try{
				specialPush.invoke(stack2Instance, args);
			} catch (InvocationTargetException e) {
				message += "SpecialPush throws error \n";
				wrongSpecialPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Push
			totalPush++;
		    args[0] = new Double[] {input2[8]};
			try{
				push.invoke(stack2Instance, args);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//SpecialPush
			totalSpecialPush++;
			args[0] = new Double[] {input2[9]};
			try{
				specialPush.invoke(stack2Instance, args);
			} catch (InvocationTargetException e) {
				message += "SpecialPush throws error \n";
				wrongSpecialPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack2Instance);
				if(!i[0].equals(input2[8])){
					message += "Pop returns wrong Double\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack2Instance);
				if(!i[0].equals(input2[6])){
					message += "Pop returns wrong Double\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack2Instance);
				if(!i[0].equals(input2[4])){
					message += "Pop returns wrong Double\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack2Instance);
				if(!i[0].equals(input2[2])){
					message += "Pop returns wrong Double\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack2Instance);
				if(!i[0].equals(input2[3])){
					message += "Pop returns wrong Double\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack2Instance);
				if(!i[0].equals(input2[5])){
					message += "Pop returns wrong Double\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack2Instance);
				if(!i[0].equals(input2[7])){
					message += "Pop returns wrong Double\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack2Instance);
				if(!i[0].equals(input2[9])){
					message += "Pop returns wrong Double\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//isEmpty
			totalIsEmpty++;
			try {
				Boolean i =  (Boolean) isEmpty.invoke(stack2Instance);
				if(!i){
					message += "isEmpty doesn't work\n";
					wrongIsEmpty++;
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				message += "isEmpty doesn't work\n";
				wrongIsEmpty++;
			} catch (NullPointerException e){
				message += "IsEmpty throws error \n";
				wrongIsEmpty++;
			}
			
			//*****************Push Test************************/
			/*
			 * Push 0.7
			 * Peek
			 * Pop
			 */
			
			Double[] input3 = new Double[1];
			input3[0] = 0.7;
			
			Class<Double> stack3 = (Class<Double>) cd.get().getJavaClass();
			Object stack3Instance = null;
			try {
				stack3Instance = stack3.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			
			//Extract methods
			for (Method method : stack3.getDeclaredMethods()) {
				if(method.getName().equals("push")){
					push = method;
				}
				else if(method.getName().equals("peek")){
					peek = method;
				}
				else if(method.getName().equals("pop")){
					pop = method;
				}
				else if(method.getName().equals("isEmpty")){
					isEmpty = method;
				}
			}
			
			//Push
			totalPush++;
			final Object[] args2 = new Object[1];
		    args2[0] = new Double[] {input3[0]};
			try{
				push.invoke(stack3Instance, args2);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Peek
			totalPeek++;
			try {
				Double[] i =  (Double[])peek.invoke(stack3Instance);
				if(!i[0].equals(input3[0])){
					message += "Peek doesn't work\n";
					wrongPeek++;
				}
			}catch (InvocationTargetException e) {
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (NullPointerException e){
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack3Instance);
				if(!i[0].equals(input3[0])){
					message += "Pop returns wrong double\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//*****************IsEmpty Test************************/
			Class<Integer> stack4 = (Class<Integer>) cd.get().getJavaClass();
			Object stack4Instance = null;
			try {
				stack4Instance = stack4.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			
			//Extract methods
			for (Method method : stack4.getDeclaredMethods()) {
				if(method.getName().equals("push")){
					push = method;
				}
				else if(method.getName().equals("peek")){
					peek = method;
				}
				else if(method.getName().equals("pop")){
					pop = method;
				}
				else if(method.getName().equals("isEmpty")){
					isEmpty = method;
				}
			}
			
			//isEmpty
			totalIsEmpty++;
			try {
				Boolean i =  (Boolean) isEmpty.invoke(stack4Instance);
				if(!i){
					message += "isEmpty doesn't work\n";
					wrongIsEmpty++;
				}
			} catch (InvocationTargetException e) {
				message += "isEmpty doesn't work\n";
				wrongIsEmpty++;
			} catch (NullPointerException e){
				message += "IsEmpty throws error \n";
				wrongIsEmpty++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//*****************Special Push Test************************/
			/*
			 * SpecialPush 0.7
			 * Peek
			 * Pop
			 */
			
			Double[] input4 = new Double[1];
			input4[0] = 0.7;
			
			Class<Double> stack5 = (Class<Double>) cd.get().getJavaClass();
			Object stack5Instance = null;
			try {
				stack5Instance = stack5.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			
			//Extract methods
			for (Method method : stack5.getDeclaredMethods()) {
				if(method.getName().equals("push")){
					push = method;
				}
				else if(method.getName().equals("peek")){
					peek = method;
				}
				else if(method.getName().equals("pop")){
					pop = method;
				}
				else if(method.getName().equals("isEmpty")){
					isEmpty = method;
				}
			}
			
			//Push
			totalSpecialPush++;
			final Object[] args3 = new Object[1];
		    args3[0] = new Double[] {input4[0]};
			try{
				specialPush.invoke(stack5Instance, args3);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Peek
			totalPeek++;
			try {
				Double[] i =  (Double[])peek.invoke(stack5Instance);
				if(!i[0].equals(input4[0])){
					message += "Peek doesn't work\n";
					wrongPeek++;
				}
			}catch (InvocationTargetException e) {
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (NullPointerException e){
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack5Instance);
				if(!i[0].equals(input4[0])){
					message += "Pop returns wrong double\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//*****************Push, Special Push Test************************/
			/*
			 * Push -8.9
			 * SpecialPush 0.7
			 * Peek
			 * Pop
			 */
			
			Double[] input5 = new Double[2];
			input5[0] = -8.9;
			input5[1] = 0.7;
			
			Class<Double> stack6 = (Class<Double>) cd.get().getJavaClass();
			Object stack6Instance = null;
			try {
				stack6Instance = stack6.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			
			//Extract methods
			for (Method method : stack6.getDeclaredMethods()) {
				if(method.getName().equals("push")){
					push = method;
				}
				else if(method.getName().equals("peek")){
					peek = method;
				}
				else if(method.getName().equals("pop")){
					pop = method;
				}
				else if(method.getName().equals("isEmpty")){
					isEmpty = method;
				}
			}
			
			//Push
			totalPush++;
			final Object[] args4 = new Object[1];
		    args4[0] = new Double[] {input5[0]};
			try{
				push.invoke(stack6Instance, args4);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//SpecialPush
			totalSpecialPush++;
		    args4[0] = new Double[] {input5[1]};
			try{
				specialPush.invoke(stack6Instance, args4);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Peek
			totalPeek++;
			try {
				Double[] i =  (Double[])peek.invoke(stack6Instance);
				if(!i[0].equals(input5[0])){
					message += "Peek doesn't work\n";
					wrongPeek++;
				}
			}catch (InvocationTargetException e) {
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (NullPointerException e){
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack6Instance);
				if(!i[0].equals(input5[0])){
					message += "Pop returns wrong double\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Peek
			totalPeek++;
			try {
				Double[] i =  (Double[])peek.invoke(stack6Instance);
				if(!i[0].equals(input5[1])){
					message += "Peek doesn't work\n";
					wrongPeek++;
				}
			}catch (InvocationTargetException e) {
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (NullPointerException e){
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack6Instance);
				if(!i[0].equals(input5[1])){
					message += "Pop returns wrong double\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//***************** Special Push, Push Test************************/
			/*
			 * SpecialPush -8.9
			 * Push 0.7
			 * Peek
			 * Pop
			 */
			
			Double[] input6 = new Double[2];
			input6[0] = -8.9;
			input6[1] = 0.7;
			
			Class<Double> stack7 = (Class<Double>) cd.get().getJavaClass();
			Object stack7Instance = null;
			try {
				stack7Instance = stack7.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			
			//Extract methods
			for (Method method : stack7.getDeclaredMethods()) {
				if(method.getName().equals("push")){
					push = method;
				}
				else if(method.getName().equals("peek")){
					peek = method;
				}
				else if(method.getName().equals("pop")){
					pop = method;
				}
				else if(method.getName().equals("isEmpty")){
					isEmpty = method;
				}
			}
			
			//SpecialPush
			final Object[] args5 = new Object[1];
			totalSpecialPush++;
		    args5[0] = new Double[] {input6[1]};
			try{
				specialPush.invoke(stack7Instance, args5);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Push
			totalPush++;
		    args5[0] = new Double[] {input6[0]};
			try{
				push.invoke(stack7Instance, args5);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongPush++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			//Peek
			totalPeek++;
			try {
				Double[] i =  (Double[])peek.invoke(stack7Instance);
				if(!i[0].equals(input6[0])){
					message += "Peek doesn't work\n";
					wrongPeek++;
				}
			}catch (InvocationTargetException e) {
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (NullPointerException e){
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack7Instance);
				if(!i[0].equals(input6[0])){
					message += "Pop returns wrong double\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Peek
			totalPeek++;
			try {
				Double[] i =  (Double[])peek.invoke(stack7Instance);
				if(!i[0].equals(input6[1])){
					message += "Peek doesn't work\n";
					wrongPeek++;
				}
			}catch (InvocationTargetException e) {
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (NullPointerException e){
				message += "Peek throws error \n";
				wrongPeek++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack7Instance);
				if(!i[0].equals(input6[1])){
					message += "Pop returns wrong double\n";
					wrongPop++;
				}
			} catch (InvocationTargetException e) {
				message += "Pop throws error \n";
				wrongPop++;
			} catch (NullPointerException e){
				message += "Pop throws error \n";
				wrongPop++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Grade
			score -= 0.3*((double)wrongPush/(double)totalPush);
			score -= 0.3*((double)wrongSpecialPush/(double)totalSpecialPush);
			score -= 0.15*((double)wrongPop/(double)totalPop);
			score -= 0.15*((double)wrongPeek/(double)totalPeek);
			score -= 0.1*((double)wrongIsEmpty/(double)totalIsEmpty);

			// Grade
			if(score == 1){
				return pass();
			}
			else{
				return partialPass(score, message);
			}
			
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
}
