package gradingTools.comp410f15.assignment3.testcases;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import framework.execution.NotRunnableException;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.ClassesManager;
import framework.project.Project;

public class MaxHeapTestCase extends BasicTestCase {

	public MaxHeapTestCase() {
		super("Max Heap Test Case");
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		try {
			ClassesManager classesManager = project.getClassesManager().get();
			String MaxHeapClassName = "MaxHeap";
			
			// Initialize score and feedback message
			double score = 1;
			String message = "";
			boolean testPassed = false;
			
			//Get MaxHeap Class
			List<ClassDescription> MaxHeapDescription = classesManager.findByClassName(MaxHeapClassName);
			Class<String> MaxHeap = (Class<String>) MaxHeapDescription.get(0).getJavaClass();
			Object MaxHeapInstance = null;
			
			
			// 
			try {
	            //Get methods
	            Method deleteMax = MaxHeap.getMethod("deleteMax");
				Method insert = MaxHeap.getMethod("insert", Comparable.class);
				Method getSize = MaxHeap.getMethod("getSize");
				Method getMax = MaxHeap.getMethod("getMax");
				
/* === Test Case 1 ===
				 * insert 10
				 */
				message += "===Test Case 1===\n";
			
				//create instance
				MaxHeapInstance = MaxHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MaxHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 1 passed.\n";
				}
				else{
					score -= 1/32.0;
					message += "Test Case 1 failed.\n";
				}
				
/* === Test Case 2 ===
				 * getSize
				 */
				message += "===Test Case 2===\n";
			
				//create instance
				MaxHeapInstance = MaxHeap.newInstance();
				testPassed = true;
				//Get size
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(MaxHeapInstance);
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
					score -= 1/32.0;
					message += "Test Case 2 failed.\n";
				}
				
/* === Test Case 3 ===
				 * insert 10
				 * getMax
				 */
				message += "===Test Case 3===\n";
			
				//create instance
				MaxHeapInstance = MaxHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MaxHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 3 passed.\n";
				}
				else{
					score -= 1/32.0;
					message += "Test Case 3 failed.\n";
				}	
				
/* === Test Case 4 ===
				 * insert 10
				 * getMax
				 * deleteMax
				 */
				message += "===Test Case 4===\n";
			
				//create instance
				MaxHeapInstance = MaxHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MaxHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 4 passed.\n";
				}
				else{
					score -= 2/32.0;
					message += "Test Case 4 failed.\n";
				}					
				
				
/* === Test Case 5 ===
				 * insert 10
				 * getMax
				 * insert 5
				 * getMax
				 */
				message += "===Test Case 5===\n";
			
				//create instance
				MaxHeapInstance = MaxHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MaxHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(MaxHeapInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 5 passed.\n";
				}
				else{
					score -= 1/32.0;
					message += "Test Case 5 failed.\n";
				}
				
/* === Test Case 6 ===
				 * insert 10
				 * getMax
				 * insert 5
				 * getMax
				 * insert 2
				 * getMax
				 */
				message += "===Test Case 6===\n";
			
				//create instance
				MaxHeapInstance = MaxHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MaxHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(MaxHeapInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2)\n";
					insert.invoke(MaxHeapInstance, 2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 6 passed.\n";
				}
				else{
					score -= 1/32.0;
					message += "Test Case 6 failed.\n";
				}


/* === Test Case 7 ===
				 * insert 10
				 * getMax
				 * insert 5
				 * getMax
				 * insert 2
				 * getMax
				 * insert 13
				 * getMax
				 * insert 12
				 * getMax
				 * insert 11
				 * getMax
				 * insert 20
				 * getMax
				 */
				message += "===Test Case 7===\n";
			
				//create instance
				MaxHeapInstance = MaxHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MaxHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(MaxHeapInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2)\n";
					insert.invoke(MaxHeapInstance, 2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(13)\n";
					insert.invoke(MaxHeapInstance, 13);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(13) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 13){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(12)\n";
					insert.invoke(MaxHeapInstance, 12);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(12) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 13){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(11)\n";
					insert.invoke(MaxHeapInstance, 11);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(11) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 13){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(20)\n";
					insert.invoke(MaxHeapInstance, 20);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(20) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 20){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 7 passed.\n";
				}
				else{
					score -= 4/32.0;
					message += "Test Case 7 failed.\n";
				}
				
/* === Test Case 8 ===
				 * insert 10
				 * getMax
				 * insert 5
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getSize
				 */
				message += "===Test Case 8===\n";
			
				//create instance
				MaxHeapInstance = MaxHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MaxHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(MaxHeapInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 5){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetSize
				try {
					int i = (int) getSize.invoke(MaxHeapInstance);
					if(i != 0){
						message += "\tgetSize() wrong answer\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 8 passed.\n";
				}
				else{
					score -= 4/32.0;
					message += "Test Case 8 failed.\n";
				}
				
/* === Test Case 9 ===
				 * insert 10
				 * getMax
				 * insert 5
				 * getMax
				 * insert 2
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getSize
				 */
				message += "===Test Case 9===\n";
			
				//create instance
				MaxHeapInstance = MaxHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MaxHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(MaxHeapInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2)\n";
					insert.invoke(MaxHeapInstance, 2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 5){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 2){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetSize
				try {
					int i = (int) getSize.invoke(MaxHeapInstance);
					if(i != 0){
						message += "\tgetSize() wrong answer\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 9 passed.\n";
				}
				else{
					score -= 4/32.0;
					message += "Test Case 9 failed.\n";
				}

/* === Test Case 10 ===
				 * insert 10
				 * getMax
				 * insert 5
				 * getMax
				 * insert 2
				 * getMax
				 * insert 13
				 * getMax
				 * insert 12
				 * getMax
				 * insert 11
				 * getMax
				 * insert 20
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getSize
				 */
				message += "===Test Case 10===\n";
			
				//create instance
				MaxHeapInstance = MaxHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MaxHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(MaxHeapInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2)\n";
					insert.invoke(MaxHeapInstance, 2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(13)\n";
					insert.invoke(MaxHeapInstance, 13);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(13) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 13){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(12)\n";
					insert.invoke(MaxHeapInstance, 12);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(12) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 13){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(11)\n";
					insert.invoke(MaxHeapInstance, 11);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(11) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 13){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(20)\n";
					insert.invoke(MaxHeapInstance, 20);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(20) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 20){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 13){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 12){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 11){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 5){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 2){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetSize
				try {
					int i = (int) getSize.invoke(MaxHeapInstance);
					if(i != 0){
						message += "\tgetSize() wrong answer\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 10 passed.\n";
				}
				else{
					score -= 4/32.0;
					message += "Test Case 10 failed.\n";
				}

/* === Test Case 11 ===
				 * insert 10
				 * getMax
				 * insert 5
				 * getMax
				 * insert 2
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getSize
				 * insert 10
				 * getMax
				 * deleteMax
				 * getSize
				 */
				message += "===Test Case 11===\n";
			
				//create instance
				MaxHeapInstance = MaxHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MaxHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(MaxHeapInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2)\n";
					insert.invoke(MaxHeapInstance, 2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 5){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 2){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetSize
				try {
					int i = (int) getSize.invoke(MaxHeapInstance);
					if(i != 0){
						message += "\tgetSize() wrong answer\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MaxHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMax
				try {
					message += "\tgetMax()\n";
					int i = (int) getMax.invoke(MaxHeapInstance);
					if(i != 10){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetSize
				try {
					int i = (int) getSize.invoke(MaxHeapInstance);
					if(i != 0){
						message += "\tgetSize() wrong answer\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 11 passed.\n";
				}
				else{
					score -= 4/32.0;
					message += "Test Case 11 failed.\n";
				}
				
/* === Test Case 12 ===
				 * insert 1.0
				 * getMax
				 * insert 0.5
				 * getMax
				 * insert 0.2
				 * getMax
				 * insert 1.3
				 * getMax
				 * insert 1.2
				 * getMax
				 * insert 1.1
				 * getMax
				 * insert 2.0
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getMax
				 * deleteMax
				 * getSize
				 */
				message += "===Test Case 12===\n";
			
				//create instance
				MaxHeapInstance = MaxHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(1.0)\n";
					insert.invoke(MaxHeapInstance, 1.0);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(1.0) throws error \n";
					testPassed = false;
				}				
				//GetMax
				try {
					message += "\tgetMax()\n";
					Double i = (Double) getMax.invoke(MaxHeapInstance);
					if(i != 1.0){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(0.5)\n";
					insert.invoke(MaxHeapInstance, 0.5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(0.5) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					Double i = (Double) getMax.invoke(MaxHeapInstance);
					if(i != 1.0){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(0.2)\n";
					insert.invoke(MaxHeapInstance, 0.2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(0.2) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					Double i = (Double) getMax.invoke(MaxHeapInstance);
					if(i != 1.0){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(1.3)\n";
					insert.invoke(MaxHeapInstance, 1.3);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(1.3) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					Double i = (Double) getMax.invoke(MaxHeapInstance);
					if(i != 1.3){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(1.2)\n";
					insert.invoke(MaxHeapInstance, 1.2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(1.2) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					Double i = (Double) getMax.invoke(MaxHeapInstance);
					if(i != 1.3){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(1.1)\n";
					insert.invoke(MaxHeapInstance, 1.1);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(1.1) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					Double i = (Double) getMax.invoke(MaxHeapInstance);
					if(i != 1.3){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2.0)\n";
					insert.invoke(MaxHeapInstance, 2.0);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2.0) throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					Double i = (Double) getMax.invoke(MaxHeapInstance);
					if(i != 2.0){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					Double i = (Double) getMax.invoke(MaxHeapInstance);
					if(i != 1.3){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					Double i = (Double) getMax.invoke(MaxHeapInstance);
					if(i != 1.2){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					Double i = (Double) getMax.invoke(MaxHeapInstance);
					if(i != 1.1){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					Double i = (Double) getMax.invoke(MaxHeapInstance);
					if(i != 1.0){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					Double i = (Double) getMax.invoke(MaxHeapInstance);
					if(i != 0.5){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetMax
				try {
					message += "\tgetMax()\n";
					Double i = (Double) getMax.invoke(MaxHeapInstance);
					if(i != 0.2){
						message += "\tgetMax doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMax() throws error \n";
					testPassed = false;
				}
				//DeleteMax
				try {
					message += "\tdeleteMax()\n";
					deleteMax.invoke(MaxHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMax() throws error \n";
					testPassed = false;
				}
				//GetSize
				try {
					int i = (int) getSize.invoke(MaxHeapInstance);
					if(i != 0){
						message += "\tgetSize() wrong answer\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 12 passed.\n";
				}
				else{
					score -= 5/32.0;
					message += "Test Case 12 failed.\n";
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
