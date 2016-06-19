package gradingTools.comp410f15.assignment1.testcases;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import framework.project.Option;
import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.ClassesManager;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class StackTestCase extends BasicTestCase {

	public StackTestCase() {
		super("Stack Implementation Test Case");
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
			int totalPush = 0;
			int totalPeek = 0;
			int totalPop = 0;
			int totalIsEmpty = 0;
			int wrongPush = 0;
			int wrongPeek = 0;
			int wrongPop = 0;
			int wrongIsEmpty = 0;
			/*
			 * Push "aaaa"
			 * Push "bbb"
			 * Push "cc"
			 * Push "d"
			 * Peek
			 * isEmpty
			 * Pop
			 * Peek
			 * Pop
			 * Pop
			 * Pop
			 * Peek
			 * isEmpty
			 * Push "aaaa"
			 * Peek
			 * isEmpty
			 * Pop
			 */
			// Inputs
			String StackClassName = "LinkedStack";
			String[] input = new String[4];
			input[0] = "aaaa";
			input[1] = "bbb";
			input[2] = "cc";
			input[3] = "d";
			
			// Initialize score and feedback message
			double score = 1;
			String message = "";
			
			//Get Stack Class
			Option<ClassDescription> cd = (Option<ClassDescription>) classesManager.get().findByClassName(StackClassName);			
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
			
			totalPush++;
			args[0] = new String[] {input[1]};
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
			
			totalPush++;
			args[0] = new String[] {input[3]};
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
				if(!i[0].equals(input[3])){
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
			
			//Peek
			totalPeek++;
			try {
				String[] i =  (String[])peek.invoke(stackInstance);
				if(!i[0].equals(input[2])){
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
			
			//Peek
			totalPeek++;
			try {
				String[] i =  (String[])peek.invoke(stackInstance);
				if(i != null){
					message += "Pop doesn't delete string \n";
					wrongPeek++;
				}
			} catch (InvocationTargetException e) {
				if (!(e.getTargetException() instanceof NullPointerException)){
					message += "Pop doesn't delete string \n";
					wrongPeek++;
				}
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
			
			//*****************Double Test************************/
			/*
			 * Push 0.7
			 * Peek
			 * Pop
			 */
			
			Double[] input2 = new Double[1];
			input2[0] = 0.7;
			
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
			}
			
			//Push
			totalPush++;
			final Object[] args2 = new Object[1];
		    args2[0] = new Double[] {input2[0]};
			try{
				push.invoke(stack2Instance, args2);
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

			//Pop
			totalPop++;
			try {
				Double[] i =  (Double[])pop.invoke(stack2Instance);
				if(!i[0].equals(input2[0])){
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
			Class<Integer> stack3 = (Class<Integer>) cd.get().getJavaClass();
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
			
			//isEmpty
			totalIsEmpty++;
			try {
				Boolean i =  (Boolean) isEmpty.invoke(stack3Instance);
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
			
			score -= 0.3*((double)wrongPush/(double)totalPush);
			score -= 0.3*((double)wrongPop/(double)totalPop);
			score -= 0.3*((double)wrongPeek/(double)totalPeek);
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
