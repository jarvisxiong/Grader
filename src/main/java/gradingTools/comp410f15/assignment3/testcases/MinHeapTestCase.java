package gradingTools.comp410f15.assignment3.testcases;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import framework.execution.NotRunnableException;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.ClassesManager;
import framework.project.Project;

public class MinHeapTestCase extends BasicTestCase {

	public MinHeapTestCase() {
		super("Min Heap Test Case");
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		try {
			ClassesManager classesManager = project.getClassesManager().get();
			String MinHeapClassName = "MinHeap";
			
			// Initialize score and feedback message
			double score = 1;
			String message = "";
			boolean testPassed = false;
			
			//Get MinHeap Class
			Set<ClassDescription> MinHeapDescription = classesManager.findByClassOrInterfaceName(MinHeapClassName);
			Class<String> MinHeap = (Class<String>) MinHeapDescription.iterator().next().getJavaClass();
			Object MinHeapInstance = null;
			
			
			// 
			try {
	            //Get methods
	            Method deleteMin = MinHeap.getMethod("deleteMin");
				Method insert = MinHeap.getMethod("insert", Comparable.class);
				Method getSize = MinHeap.getMethod("getSize");
				Method getMin = MinHeap.getMethod("getMin");
				
/* === Test Case 1 ===
				 * insert 10
				 */
				message += "===Test Case 1===\n";
			
				//create instance
				MinHeapInstance = MinHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MinHeapInstance, 10);
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
				MinHeapInstance = MinHeap.newInstance();
				testPassed = true;
				//Get size
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(MinHeapInstance);
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
				 * getMin
				 */
				message += "===Test Case 3===\n";
			
				//create instance
				MinHeapInstance = MinHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MinHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
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
				 * getMin
				 * deleteMin
				 */
				message += "===Test Case 4===\n";
			
				//create instance
				MinHeapInstance = MinHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MinHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
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
				 * getMin
				 * insert 15
				 * getMin
				 */
				message += "===Test Case 5===\n";
			
				//create instance
				MinHeapInstance = MinHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MinHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(15)\n";
					insert.invoke(MinHeapInstance, 15);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(15) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
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
				 * getMin
				 * insert 15
				 * getMin
				 * insert 20
				 * getMin
				 */
				message += "===Test Case 6===\n";
			
				//create instance
				MinHeapInstance = MinHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MinHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(15)\n";
					insert.invoke(MinHeapInstance, 15);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(15) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(20)\n";
					insert.invoke(MinHeapInstance, 20);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(20) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
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
				 * getMin
				 * insert 15
				 * getMin
				 * insert 20
				 * getMin
				 * insert 7
				 * getMin
				 * insert 8
				 * getMin
				 * insert 9
				 * getMin
				 * insert 2
				 * getMin
				 */
				message += "===Test Case 7===\n";
			
				//create instance
				MinHeapInstance = MinHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MinHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(15)\n";
					insert.invoke(MinHeapInstance, 15);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(15) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(20)\n";
					insert.invoke(MinHeapInstance, 20);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(20) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(7)\n";
					insert.invoke(MinHeapInstance, 7);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(7) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 7){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(8)\n";
					insert.invoke(MinHeapInstance, 8);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(8) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 7){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(9)\n";
					insert.invoke(MinHeapInstance, 9);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(9) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 7){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2)\n";
					insert.invoke(MinHeapInstance, 2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 2){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
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
				 * getMin
				 * insert 15
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getSize
				 */
				message += "===Test Case 8===\n";
			
				//create instance
				MinHeapInstance = MinHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MinHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(15)\n";
					insert.invoke(MinHeapInstance, 15);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(15) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 15){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetSize
				try {
					int i = (int) getSize.invoke(MinHeapInstance);
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
				 * getMin
				 * insert 15
				 * getMin
				 * insert 20
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getSize
				 */
				message += "===Test Case 9===\n";
			
				//create instance
				MinHeapInstance = MinHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MinHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(15)\n";
					insert.invoke(MinHeapInstance, 15);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(15) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(20)\n";
					insert.invoke(MinHeapInstance, 20);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(20) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 15){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 20){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetSize
				try {
					int i = (int) getSize.invoke(MinHeapInstance);
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
				 * getMin
				 * insert 15
				 * getMin
				 * insert 20
				 * getMin
				 * insert 7
				 * getMin
				 * insert 8
				 * getMin
				 * insert 9
				 * getMin
				 * insert 2
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getSize
				 */
				message += "===Test Case 10===\n";
			
				//create instance
				MinHeapInstance = MinHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MinHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(15)\n";
					insert.invoke(MinHeapInstance, 15);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(15) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(20)\n";
					insert.invoke(MinHeapInstance, 20);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(20) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(7)\n";
					insert.invoke(MinHeapInstance, 7);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(7) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 7){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(8)\n";
					insert.invoke(MinHeapInstance, 8);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(8) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 7){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(9)\n";
					insert.invoke(MinHeapInstance, 9);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(9) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 7){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2)\n";
					insert.invoke(MinHeapInstance, 2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 2){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 7){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 8){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 9){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 15){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 20){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetSize
				try {
					int i = (int) getSize.invoke(MinHeapInstance);
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
				 * getMin
				 * insert 15
				 * getMin
				 * insert 20
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getSize
				 * insert 10
				 * getMin
				 * deleteMin
				 * getSize
				 */
				message += "===Test Case 11===\n";
			
				//create instance
				MinHeapInstance = MinHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(MinHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(15)\n";
					insert.invoke(MinHeapInstance, 15);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(15) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(20)\n";
					insert.invoke(MinHeapInstance, 20);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(20) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 15){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 20){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetSize
				try {
					int i = (int) getSize.invoke(MinHeapInstance);
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
					insert.invoke(MinHeapInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//GetMin
				try {
					message += "\tgetMin()\n";
					int i = (int) getMin.invoke(MinHeapInstance);
					if(i != 10){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetSize
				try {
					int i = (int) getSize.invoke(MinHeapInstance);
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
				 * getMin
				 * insert 1.5
				 * getMin
				 * insert 2.0
				 * getMin
				 * insert 0.7
				 * getMin
				 * insert 0.8
				 * getMin
				 * insert 0.9
				 * getMin
				 * insert 0.2
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getMin
				 * deleteMin
				 * getSize
				 */
				message += "===Test Case 12===\n";
			
				//create instance
				MinHeapInstance = MinHeap.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(1.0)\n";
					insert.invoke(MinHeapInstance, 1.0);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(1.0) throws error \n";
					testPassed = false;
				}				
				//GetMin
				try {
					message += "\tgetMin()\n";
					Double i = (Double) getMin.invoke(MinHeapInstance);
					if(i != 1.0){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(1.5)\n";
					insert.invoke(MinHeapInstance, 1.5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(1.5) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					Double i = (Double) getMin.invoke(MinHeapInstance);
					if(i != 1.0){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2.0)\n";
					insert.invoke(MinHeapInstance, 2.0);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2.0) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					Double i = (Double) getMin.invoke(MinHeapInstance);
					if(i != 1.0){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(0.7)\n";
					insert.invoke(MinHeapInstance, 0.7);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(0.7) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					Double i = (Double) getMin.invoke(MinHeapInstance);
					if(i != 0.7){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(0.8)\n";
					insert.invoke(MinHeapInstance, 0.8);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(0.8) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					Double i = (Double) getMin.invoke(MinHeapInstance);
					if(i != 0.7){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(0.9)\n";
					insert.invoke(MinHeapInstance, 0.9);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(0.9) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					Double i = (Double) getMin.invoke(MinHeapInstance);
					if(i != 0.7){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(0.2)\n";
					insert.invoke(MinHeapInstance, 0.2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(0.2) throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					Double i = (Double) getMin.invoke(MinHeapInstance);
					if(i != 0.2){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					Double i = (Double) getMin.invoke(MinHeapInstance);
					if(i != 0.7){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					Double i = (Double) getMin.invoke(MinHeapInstance);
					if(i != 0.8){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					Double i = (Double) getMin.invoke(MinHeapInstance);
					if(i != 0.9){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					Double i = (Double) getMin.invoke(MinHeapInstance);
					if(i != 1.0){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					Double i = (Double) getMin.invoke(MinHeapInstance);
					if(i != 1.5){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetMin
				try {
					message += "\tgetMin()\n";
					Double i = (Double) getMin.invoke(MinHeapInstance);
					if(i != 2.0){
						message += "\tgetMin doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetMin() throws error \n";
					testPassed = false;
				}
				//DeleteMin
				try {
					message += "\tdeleteMin()\n";
					deleteMin.invoke(MinHeapInstance);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tdeleteMin() throws error \n";
					testPassed = false;
				}
				//GetSize
				try {
					int i = (int) getSize.invoke(MinHeapInstance);
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
