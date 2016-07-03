package gradingTools.comp410f15.assignment1.testcases;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import framework.execution.NotRunnableException;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.execution.RunningProject;
import grader.basics.project.ClassDescription;
import grader.basics.project.ClassesManager;
import grader.basics.project.Option;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class QueueTestCase extends BasicTestCase {

	public QueueTestCase() {
		super("Queue Implementation Test Case");
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
			int totalEnqueue = 0;
			int totalPeek = 0;
			int totalDequeue = 0;
			int totalIsEmpty = 0;
			int wrongEnqueue = 0;
			int wrongPeek = 0;
			int wrongDequeue = 0;
			int wrongIsEmpty = 0;
			/*
			 * Enqueue "aaaa"
			 * Enqueue "bbb"
			 * Enqueue "cc"
			 * Enqueue "d"
			 * Peek
			 * isEmpty
			 * Dequeue
			 * Peek
			 * Dequeue
			 * Dequeue
			 * Dequeue
			 * Peek
			 * isEmpty
			 * Enqueue "aaaa"
			 * Peek
			 * isEmpty
			 * Dequeue
			 */
			// Inputs
			String queueClassName = "LinkedQueue";
			String[] input = new String[4];
			input[0] = "aaaa";
			input[1] = "bbb";
			input[2] = "cc";
			input[3] = "d";
			
			// Initialize score and feedback message
			double score = 1;
			String message = "";
			
			//Get Stack Class
			Option<ClassDescription> cd = (Option<ClassDescription>) classesManager.get().findByClassOrInterfaceName(queueClassName);			
			Class<String> queue = (Class<String>) cd.get().getJavaClass();
			Object queueInstance = null;
			try {
				queueInstance = queue.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			Method enqueue = null;
			Method peek = null;
			Method dequeue = null;
			Method isEmpty = null;
			
			//Extract methods
			for (Method method : queue.getDeclaredMethods()) {
				if(method.getName().equals("enqueue")){
					enqueue = method;
				}
				else if(method.getName().equals("peek")){
					peek = method;
				}
				else if(method.getName().equals("dequeue")){
					dequeue = method;
				}
				else if(method.getName().equals("isEmpty")){
					isEmpty = method;
				}
			}
			
			//Enqueue
			totalEnqueue++;
			final Object[] args = new Object[1];
		    args[0] = new String[] {input[0]};
			try{
				enqueue.invoke(queueInstance, args);
			} catch (InvocationTargetException e) {
				message += "Enqueue throws error \n";
				wrongEnqueue++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			totalEnqueue++;
			args[0] = new String[] {input[1]};
			try{
				enqueue.invoke(queueInstance, args);
			} catch (InvocationTargetException e) {
				message += "Enqueue throws error \n";
				wrongEnqueue++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			totalEnqueue++;
			args[0] = new String[] {input[2]};
			try{
				enqueue.invoke(queueInstance, args);
			} catch (InvocationTargetException e) {
				message += "Enqueue throws error \n";
				wrongEnqueue++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			totalEnqueue++;
			args[0] = new String[] {input[3]};
			try{
				enqueue.invoke(queueInstance, args);
			} catch (InvocationTargetException e) {
				message += "Enqueue throws error \n";
				wrongEnqueue++;
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
				String[] i =  (String[])peek.invoke(queueInstance);
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
				Boolean i =  (Boolean) isEmpty.invoke(queueInstance);
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
			
			//Dequeue
			totalDequeue++;
			try {
				String[] i =  (String[])dequeue.invoke(queueInstance);
				if(!i[0].equals(input[0])){
					message += "Dequeue returns wrong string\n";
					wrongDequeue++;
				}
			} catch (InvocationTargetException e) {
				message += "Dequeue throws error \n";
				wrongDequeue++;
			} catch (NullPointerException e){
				message += "Dequeue throws error \n";
				wrongDequeue++;
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
				String[] i =  (String[])peek.invoke(queueInstance);
				if(!i[0].equals(input[1])){
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
			
			//Dequeue
			totalDequeue++;
			try {
				String[] i =  (String[])dequeue.invoke(queueInstance);
				if(!i[0].equals(input[1])){
					message += "Dequeue returns wrong string\n";
					wrongDequeue++;
				}
			} catch (InvocationTargetException e) {
				message += "Dequeue throws error \n";
				wrongDequeue++;
			} catch (NullPointerException e){
				message += "Dequeue throws error \n";
				wrongDequeue++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Dequeue
			totalDequeue++;
			try {
				String[] i =  (String[])dequeue.invoke(queueInstance);
				if(!i[0].equals(input[2])){
					message += "Dequeue returns wrong string\n";
					wrongDequeue++;
				}
			} catch (InvocationTargetException e) {
				message += "Dequeue throws error \n";
				wrongDequeue++;
			} catch (NullPointerException e){
				message += "Dequeue throws error \n";
				wrongDequeue++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Dequeue
			totalDequeue++;
			try {
				String[] i =  (String[])dequeue.invoke(queueInstance);
				if(!i[0].equals(input[3])){
					message += "Dequeue returns wrong string\n";
					wrongDequeue++;
				}
			} catch (InvocationTargetException e) {
				message += "Dequeue throws error \n";
				wrongDequeue++;
			} catch (NullPointerException e){
				message += "Dequeue throws error \n";
				wrongDequeue++;
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
				String[] i =  (String[])peek.invoke(queueInstance);
				if(i != null){
					message += "Dequeue doesn't delete string \n";
					wrongPeek++;
				}
			} catch (InvocationTargetException e) {
				if (!(e.getTargetException() instanceof NullPointerException)){
					message += "Dequeue doesn't delete string \n";
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
				Boolean i =  (Boolean) isEmpty.invoke(queueInstance);
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
			
			//Enqueue
			totalEnqueue++;
		    args[0] = new String[] {input[0]};
			try{
				enqueue.invoke(queueInstance, args);
			} catch (InvocationTargetException e) {
				message += "Enqueue throws error \n";
				wrongEnqueue++;
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
				String[] i =  (String[])peek.invoke(queueInstance);
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
				Boolean i =  (Boolean) isEmpty.invoke(queueInstance);
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
			
			//Dequeue
			totalDequeue++;
			try {
				String[] i =  (String[])dequeue.invoke(queueInstance);
				if(!i[0].equals(input[0])){
					message += "Dequeue returns wrong string\n";
					wrongDequeue++;
				}
			} catch (InvocationTargetException e) {
				message += "Dequeue throws error \n";
				wrongDequeue++;
			} catch (NullPointerException e){
				message += "Dequeue throws error \n";
				wrongDequeue++;
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
			 * Dequeue
			 */
			
			Double[] input2 = new Double[1];
			input2[0] = 0.7;
			
			Class<Double> queue2 = (Class<Double>) cd.get().getJavaClass();
			Object queue2Instance = null;
			try {
				queue2Instance = queue2.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			
			//Extract methods
			for (Method method : queue2.getDeclaredMethods()) {
				if(method.getName().equals("push")){
					enqueue = method;
				}
				else if(method.getName().equals("peek")){
					peek = method;
				}
				else if(method.getName().equals("dequeue")){
					dequeue = method;
				}
				else if(method.getName().equals("isEmpty")){
					isEmpty = method;
				}
			}
			
			//Enqueue
			totalEnqueue++;
			final Object[] args2 = new Object[1];
		    args2[0] = new Double[] {input2[0]};
			try{
				enqueue.invoke(queue2Instance, args2);
			} catch (InvocationTargetException e) {
				message += "Push throws error \n";
				wrongEnqueue++;
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
				Double[] i =  (Double[])peek.invoke(queue2Instance);
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
 
			//Dequeue
			totalDequeue++;
			try {
				Double[] i =  (Double[])dequeue.invoke(queue2Instance);
				if(!i[0].equals(input2[0])){
					message += "Dequeue returns wrong double\n";
					wrongDequeue++;
				}
			} catch (InvocationTargetException e) {
				message += "Dequeue throws error \n";
				wrongDequeue++;
			} catch (NullPointerException e){
				message += "Dequeue throws error \n";
				wrongDequeue++;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//*****************IsEmpty Test************************/
			Class<Integer> queue3 = (Class<Integer>) cd.get().getJavaClass();
			Object queue3Instance = null;
			try {
				queue3Instance = queue3.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			
			//Extract methods
			for (Method method : queue3.getDeclaredMethods()) {
				if(method.getName().equals("push")){
					enqueue = method;
				}
				else if(method.getName().equals("peek")){
					peek = method;
				}
				else if(method.getName().equals("dequeue")){
					dequeue = method;
				}
				else if(method.getName().equals("isEmpty")){
					isEmpty = method;
				}
			}
			
			//isEmpty
			totalIsEmpty++;
			try {
				Boolean i =  (Boolean) isEmpty.invoke(queue3Instance);
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
			
			// Grade
			score -= 0.3*((double)wrongEnqueue/(double)totalEnqueue);
			score -= 0.3*((double)wrongDequeue/(double)totalDequeue);
			score -= 0.3*((double)wrongPeek/(double)totalPeek);
			score -= 0.1*((double)wrongIsEmpty/(double)totalIsEmpty);
			
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
