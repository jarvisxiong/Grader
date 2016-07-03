package gradingTools.comp410f15.assignment3.testcases;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.ClassesManager;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

public class MedianContainerTestCase extends BasicTestCase {

	public MedianContainerTestCase() {
		super("Median Container Test Case");
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		try {
			ClassesManager classesManager = project.getClassesManager().get();
			String MedianContainerClassName = "MedianContainer";
			
			// Initialize score and feedback message
			double score = 1;
			String message = "";
			boolean testPassed = false;
			
			//Get MedianContainer Class
			Set<ClassDescription> MedianContainerDescription = classesManager.findByClassOrInterfaceName(MedianContainerClassName);
			Class<String> MedianContainer = (Class<String>) MedianContainerDescription.iterator().next().getJavaClass();
			Object MedianContainerInstance = null;
			
			
			// 
			try {
	            //Get methods
				Method insert = MedianContainer.getMethod("insert", Comparable.class);
				Method getSize = MedianContainer.getMethod("getSize");
				Method getMedian = MedianContainer.getMethod("getMedian");
				
/* === Test Case 1 ===
				 * insert 10
				 */
				message += "===Test Case 1===\n";
			
				//create instance
				MedianContainerInstance = MedianContainer.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MedianContainerInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 1 passed.\n";
				}
				else{
					score -= 1/26.0;
					message += "Test Case 1 failed.\n";
				}
				
/* === Test Case 2 ===
				 * getSize
				 */
				message += "===Test Case 2===\n";
			
				//create instance
				MedianContainerInstance = MedianContainer.newInstance();
				testPassed = true;
				//Get size
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(MedianContainerInstance);
					if(i != 0){
						message += "\tgetSize() wrong answer\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 2 passed.\n";
				}
				else{
					score -= 1/26.0;
					message += "Test Case 2 failed.\n";
				}
				
/* === Test Case 3 ===
				 * insert 10
				 * getMedian
				 */
				message += "===Test Case 3===\n";
			
				//create instance
				MedianContainerInstance = MedianContainer.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MedianContainerInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != 10){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 3 passed.\n";
				}
				else{
					score -= 2/26.0;
					message += "Test Case 3 failed.\n";
				}	
				
/* === Test Case 4 ===
				 * insert 10
				 * getMedian
				 * insert 5
				 * getMedian
				 */
				message += "===Test Case 4===\n";
			
				//create instance
				MedianContainerInstance = MedianContainer.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MedianContainerInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != 10){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(MedianContainerInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != 5){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 4 passed.\n";
				}
				else{
					score -= 3/26.0;
					message += "Test Case 4 failed.\n";
				}
				
/* === Test Case 5 ===
				 * insert 10
				 * getMedian
				 * insert 5
				 * getMedian
				 * insert 2
				 * getMedian
				 */
				message += "===Test Case 5===\n";
			
				//create instance
				MedianContainerInstance = MedianContainer.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MedianContainerInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != 10){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(MedianContainerInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != 5){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2)\n";
					insert.invoke(MedianContainerInstance, 2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != 5){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 5 passed.\n";
				}
				else{
					score -= 4/26.0;
					message += "Test Case 5 failed.\n";
				}


/* === Test Case 6 ===
				 * insert 10
				 * getMedian
				 * insert 5
				 * getMedian
				 * insert 2
				 * getMedian
				 * insert 13
				 * getMedian
				 * insert 12
				 * getMedian
				 * insert 11
				 * getMedian
				 * insert 20
				 * getMedian
				 */
				message += "===Test Case 6===\n";
			
				//create instance
				MedianContainerInstance = MedianContainer.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MedianContainerInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != 10){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(MedianContainerInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != 5){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2)\n";
					insert.invoke(MedianContainerInstance, 2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != 5){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(13)\n";
					insert.invoke(MedianContainerInstance, 13);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(13) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != 5){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(12)\n";
					insert.invoke(MedianContainerInstance, 12);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(12) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != 10){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(11)\n";
					insert.invoke(MedianContainerInstance, 11);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(11) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != 10){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(20)\n";
					insert.invoke(MedianContainerInstance, 20);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(20) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != 11){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 6 passed.\n";
				}
				else{
					score -= 5/26.0;
					message += "Test Case 6 failed.\n";
				}
				
/* === Test Case 7 ===
				 * insert 1.0
				 * getMedian
				 * insert 0.5
				 * getMedian
				 * insert 0.2
				 * getMedian
				 * insert 1.3
				 * getMedian
				 * insert 1.2
				 * getMedian
				 * insert 1.1
				 * getMedian
				 * insert 2.0
				 * getMedian
				 */
				message += "===Test Case 7===\n";
			
				//create instance
				MedianContainerInstance = MedianContainer.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(1.0)\n";
					insert.invoke(MedianContainerInstance, 1.0);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(1.0) throws error \n";
					testPassed = false;
				}				
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					Double i = (Double) getMedian.invoke(MedianContainerInstance);
					if(i != 1.0){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(0.5)\n";
					insert.invoke(MedianContainerInstance, 0.5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(0.5) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					Double i = (Double) getMedian.invoke(MedianContainerInstance);
					if(i != 0.5){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(0.2)\n";
					insert.invoke(MedianContainerInstance, 0.2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(0.2) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					Double i = (Double) getMedian.invoke(MedianContainerInstance);
					if(i != 0.5){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(1.3)\n";
					insert.invoke(MedianContainerInstance, 1.3);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(1.3) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					Double i = (Double) getMedian.invoke(MedianContainerInstance);
					if(i != 0.5){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(1.2)\n";
					insert.invoke(MedianContainerInstance, 1.2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(1.2) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					Double i = (Double) getMedian.invoke(MedianContainerInstance);
					if(i != 1.0){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(1.1)\n";
					insert.invoke(MedianContainerInstance, 1.1);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(1.1) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					Double i = (Double) getMedian.invoke(MedianContainerInstance);
					if(i != 1.0){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2.0)\n";
					insert.invoke(MedianContainerInstance, 2.0);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2.0) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					Double i = (Double) getMedian.invoke(MedianContainerInstance);
					if(i != 1.1){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 7 passed.\n";
				}
				else{
					score -= 5/26.0;
					message += "Test Case 7 failed.\n";
				}
/* === Test Case 8 ===
				 * insert 0
				 * getMedian
				 * insert -1
				 * getMedian
				 * insert -2
				 * getMedian
				 * insert -3
				 * getMedian
				 * insert -4
				 * getMedian
				 * insert -5
				 * getMedian
				 * insert -6
				 * getMedian
				 */
				message += "===Test Case 8===\n";
			
				//create instance
				MedianContainerInstance = MedianContainer.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(0)\n";
					insert.invoke(MedianContainerInstance, 0);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(0) throws error \n";
					testPassed = false;
				}				
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != 0){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(-1)\n";
					insert.invoke(MedianContainerInstance, -1);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(-1) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != -1){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(-2)\n";
					insert.invoke(MedianContainerInstance, -2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(-2) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != -1){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(-3)\n";
					insert.invoke(MedianContainerInstance, -3);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(-3) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != -2){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(-4)\n";
					insert.invoke(MedianContainerInstance, -4);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(-4) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != -2){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(-5)\n";
					insert.invoke(MedianContainerInstance, -5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(-5) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != -3){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(-6)\n";
					insert.invoke(MedianContainerInstance, -6);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(-6) throws error \n";
					testPassed = false;
				}
				//GetMedian
				try {
					message += "\tgetMedian()\n";
					int i = (int) getMedian.invoke(MedianContainerInstance);
					if(i != -3){
						message += "\tgetMedian doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMedian() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 8 passed.\n";
				}
				else{
					score -= 5/26.0;
					message += "Test Case 8 failed.\n";
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
