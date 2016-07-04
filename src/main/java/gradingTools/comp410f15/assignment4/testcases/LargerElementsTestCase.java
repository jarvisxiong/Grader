package gradingTools.comp410f15.assignment4.testcases;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.ClassesManager;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

public class LargerElementsTestCase extends BasicTestCase {

	public LargerElementsTestCase() {
		super("Larger Elements Test Case");
	}
	
	private boolean compareArrays(int[] arr1, int[] arr2){
		int i = 0; 
		int j = 0;
		while(i < arr1.length && j < arr2.length){
			if(arr1[i] == arr2[j]){
				i++;
				j++;
			}
			else{
				return false;
			}
		}
		return true;
	}
	
	private String print(int[] arr){
		String message = "";
		for(int i = 0; i < arr.length; i++){
			message += arr[i] + " ";
		}
		message += "\n";
		return message;
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		try {
			ClassesManager classesManager = project.getClassesManager().get();
			String AVLTreeClassName = "src.AVLTree";
			
			// Initialize score and feedback message
			double score = 1;
			String message = "";
			boolean testPassed;
			
			//Get AVLTree Class
			Set<ClassDescription> AVLTreeDescription = classesManager.findByClassOrInterfaceName(AVLTreeClassName);
			Class<String> AVLTree = (Class<String>) AVLTreeDescription.iterator().next().getJavaClass();
			Object AVLTreeInstance = null;
			
			//Create input arrays
			int[] one = new int[1];
			int[] five = new int[5];
			int[] increasingFive = new int[5];
			int[] decreasingFive = new int[5];
			int[] seven = new int[7];
			int[] increasingSeven = new int[7];
			int[] decreasingSeven = new int[7];
			
			int[] oneOut = new int[1];
			int[] fiveOut = new int[5];
			int[] increasingFiveOut = new int[5];
			int[] decreasingFiveOut = new int[5];
			int[] sevenOut = new int[7];
			int[] increasingSevenOut = new int[7];
			int[] decreasingSevenOut = new int[7];
			
			one[0] = 5;
			oneOut[0] = 0;
			
			five[0] = 10;
			five[1] = 5;
			five[2] = 6;
			five[3] = 13;
			five[4] = 1;
			fiveOut[0] = 1;
			fiveOut[1] = 2;
			fiveOut[2] = 1;
			fiveOut[3] = 0;
			fiveOut[4] = 0;
			
			seven[0] = 7;
			seven[1] = 8;
			seven[2] = 17;
			seven[3] = 3;
			seven[4] = 4;
			seven[5] = 2;
			seven[6] = 19;			
			sevenOut[0] = 3;
			sevenOut[1] = 2;
			sevenOut[2] = 1;
			sevenOut[3] = 2;
			sevenOut[4] = 1;
			sevenOut[5] = 1;
			sevenOut[6] = 0;
			
			for(int i = 0; i < 5; i++){
				increasingFive[i] = i+1;
				increasingFiveOut[i] = 4-i;
				decreasingFive[i] = 5-i;
				decreasingFiveOut[i] = 0; 
			}
			
			for(int i = 0; i < 7; i++){
				increasingSeven[i] = i+1;
				increasingSevenOut[i] = 6-i;
				decreasingSeven[i] = 7-i;
				decreasingSevenOut[i] = 0;
			}
			
			// 
			try {
	            //Get methods
				Method largerElementsOnTheRight = AVLTree.getMethod("largerElementsOnTheRight", int[].class);

/* === Test Case 1 ===
				 * five
				 */
				message += "===Test Case 1===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				
				//Insert
				try {
					message += print(five);
					int[] i = (int[]) largerElementsOnTheRight.invoke(AVLTreeInstance, five);
					if (!compareArrays(fiveOut, i)){
						message += "\tFails\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tThrows error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 1 passed.\n";
				}
				else{
					score -= 3/25.0;
					message += "Test Case 1 failed.\n";
				}

/* === Test Case 2 ===
				 * seven
				 */
				message += "===Test Case 2===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				
				//Insert
				try {
					message += print(seven);
					int[] i = (int[]) largerElementsOnTheRight.invoke(AVLTreeInstance, seven);
					if (!compareArrays(sevenOut, i)){
						message += "\tFails\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tThrows error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 2 passed.\n";
				}
				else{
					score -= 3/25.0;
					message += "Test Case 2 failed.\n";
				}
				
/* === Test Case 3 ===
				 * increasingFive
				 */
				message += "===Test Case 3===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				
				//Insert
				try {
					message += print(increasingFive);
					int[] i = (int[]) largerElementsOnTheRight.invoke(AVLTreeInstance, increasingFive);
					if (!compareArrays(increasingFiveOut, i)){
						message += "\tFails\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tThrows error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 3 passed.\n";
				}
				else{
					score -= 3/25.0;
					message += "Test Case 3 failed.\n";
				}
				
/* === Test Case 4 ===
				 * decreasingFive
				 */
				message += "===Test Case 4===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				
				//Insert
				try {
					message += print(decreasingFive);
					int[] i = (int[]) largerElementsOnTheRight.invoke(AVLTreeInstance, decreasingFive);
					if (!compareArrays(decreasingFiveOut, i)){
						message += "\tFails\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tThrows error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 4 passed.\n";
				}
				else{
					score -= 3/25.0;
					message += "Test Case 4 failed.\n";
				}
				
/* === Test Case 5 ===
				 * increasingSeven
				 */
				message += "===Test Case 5===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				
				//Insert
				try {
					message += print(increasingSeven);
					int[] i = (int[]) largerElementsOnTheRight.invoke(AVLTreeInstance, increasingSeven);
					if (!compareArrays(increasingSevenOut, i)){
						message += "\tFails\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tThrows error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 5 passed.\n";
				}
				else{
					score -= 3/25.0;
					message += "Test Case 5 failed.\n";
				}
				
/* === Test Case 6 ===
				 * decreasingSeven
				 */
				message += "===Test Case 6===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				
				//Insert
				try {
					message += print(decreasingSeven);
					int[] i = (int[]) largerElementsOnTheRight.invoke(AVLTreeInstance, decreasingSeven);
					if (!compareArrays(decreasingSevenOut, i)){
						message += "\tFails\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tThrows error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 6 passed.\n";
				}
				else{
					score -= 3/25.0;
					message += "Test Case 6 failed.\n";
				}
				
/* === Test Case 7 ===
				 * one
				 */
				message += "===Test Case 7===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				
				//Insert
				try {
					message += print(one);
					int[] i = (int[]) largerElementsOnTheRight.invoke(AVLTreeInstance, one);
					if (!compareArrays(oneOut, i)){
						message += "\tFails\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tThrows error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 7 passed.\n";
				}
				else{
					score -= 1/25.0;
					message += "Test Case 7 failed.\n";
				}
				
/* === Test Case 8 ===
				 * one
				 * five
				 */
				message += "===Test Case 8===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				
				//Insert
				try {
					message += print(one);
					int[] i = (int[]) largerElementsOnTheRight.invoke(AVLTreeInstance, one);
					if (!compareArrays(oneOut, i)){
						message += "\tFails\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tThrows error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += print(five);
					int[] i = (int[]) largerElementsOnTheRight.invoke(AVLTreeInstance, five);
					if (!compareArrays(fiveOut, i)){
						message += "\tFails\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tThrows error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 8 passed.\n";
				}
				else{
					score -= 3/25.0;
					message += "Test Case 8 failed.\n";
				}
				
/* === Test Case 9 ===
				 * one
				 * five
				 * seven
				 */
				message += "===Test Case 9===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				
				//Insert
				try {
					message += print(one);
					int[] i = (int[]) largerElementsOnTheRight.invoke(AVLTreeInstance, one);
					if (!compareArrays(oneOut, i)){
						message += "\tFails\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tThrows error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += print(five);
					int[] i = (int[]) largerElementsOnTheRight.invoke(AVLTreeInstance, five);
					if (!compareArrays(fiveOut, i)){
						message += "\tFails\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tThrows error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += print(seven);
					int[] i = (int[]) largerElementsOnTheRight.invoke(AVLTreeInstance, seven);
					if (!compareArrays(sevenOut, i)){
						message += "\tFails\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tThrows error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 9 passed.\n";
				}
				else{
					score -= 3/25.0;
					message += "Test Case 9 failed.\n";
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
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
