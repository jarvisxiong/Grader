package gradingTools.comp410f15.assignment4.testcases;

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

public class InsertAndRemoveTestCase extends BasicTestCase {

	public InsertAndRemoveTestCase() {
		super("Insert and Remove Test Case");
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
			
			
			// 
			try {
	            //Get methods
				Method insert = AVLTree.getMethod("insert", int.class);
				Method remove = AVLTree.getMethod("remove", int.class);
				Method getSize = AVLTree.getMethod("getSize");
				
/* === Test Case 1 ===
				 * insert 10
				 */
				message += "===Test Case 1===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 1 passed.\n";
				}
				else{
					score -= 1/35.0;
					message += "Test Case 1 failed.\n";
				}
				
/* === Test Case 2 ===
				 * getSize
				 */
				message += "===Test Case 2===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				//Get size
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
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
					score -= 1/35.0;
					message += "Test Case 2 failed.\n";
				}
				
/* === Test Case 3 ===
				 * insert 10
				 * getSize
				 */
				message += "===Test Case 3===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 1){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 3 passed.\n";
				}
				else{
					score -= 3/35.0;
					message += "Test Case 3 failed.\n";
				}	
				
/* === Test Case 4 ===
				 * insert 10
				 * getSize
				 * insert 5
				 * getSize
				 */
				message += "===Test Case 4===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 1){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(AVLTreeInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 2){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 4 passed.\n";
				}
				else{
					score -= 3/35.0;
					message += "Test Case 4 failed.\n";
				}
				
/* === Test Case 5 ===
				 * insert 10
				 * getSize
				 * insert 5
				 * getSize
				 * insert 2
				 * getSize
				 */
				message += "===Test Case 5===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 1){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(AVLTreeInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 2){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2)\n";
					insert.invoke(AVLTreeInstance, 2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 3){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 5 passed.\n";
				}
				else{
					score -= 3/35.0;
					message += "Test Case 5 failed.\n";
				}


/* === Test Case 6 ===
				 * insert 10
				 * getSize
				 * insert 5
				 * getSize
				 * insert 2
				 * getSize
				 * insert 13
				 * getSize
				 * insert 12
				 * getSize
				 * insert 11
				 * getSize
				 * insert 20
				 * getSize
				 */
				message += "===Test Case 6===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 1){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(AVLTreeInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 2){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2)\n";
					insert.invoke(AVLTreeInstance, 2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 3){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(13)\n";
					insert.invoke(AVLTreeInstance, 13);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(13) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 4){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(12)\n";
					insert.invoke(AVLTreeInstance, 12);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(12) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 5){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(11)\n";
					insert.invoke(AVLTreeInstance, 11);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(11) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 6){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(20)\n";
					insert.invoke(AVLTreeInstance, 20);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(20) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 7){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 6 passed.\n";
				}
				else{
					score -= 3/35.0;
					message += "Test Case 6 failed.\n";
				}
				
/* === Test Case 7 ===
				 * insert 10
				 * remove 10
				 */
				message += "===Test Case 7===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(10)\n";
					remove.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(10) throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 7 passed.\n";
				}
				else{
					score -= 3/35.0;
					message += "Test Case 7 failed.\n";
				}

/* === Test Case 8 ===
				 * insert 10
				 * remove 10
				 * getSize
				 */
				message += "===Test Case 8===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(10)\n";
					remove.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(10) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 0){
						message += "\tgetSize doesn't work\n";
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
					score -= 3/35.0;
					message += "Test Case 8 failed.\n";
				}

/* === Test Case 9 ===
				 * insert 10
				 * getSize
				 * insert 5
				 * getSize
				 * remove 5
				 * getSize
				 * remove 10
				 * getSize
				 */
				message += "===Test Case 9===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 1){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(AVLTreeInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 2){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(5)\n";
					remove.invoke(AVLTreeInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(5) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 1){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(10)\n";
					remove.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(10) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 0){
						message += "\tgetSize doesn't work\n";
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
					score -= 3/35.0;
					message += "Test Case 9 failed.\n";
				}
/* === Test Case 10 ===
				 * insert 10
				 * getSize
				 * insert 5
				 * getSize
				 * remove 10
				 * getSize
				 * remove 5
				 * getSize
				 */
				message += "===Test Case 10===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 1){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(AVLTreeInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 2){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(10)\n";
					remove.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(10) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 1){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(5)\n";
					remove.invoke(AVLTreeInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(5) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 0){
						message += "\tgetSize doesn't work\n";
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
					score -= 3/35.0;
					message += "Test Case 10 failed.\n";
				}
/* === Test Case 11 ===
				 * insert 10
				 * getSize
				 * insert 5
				 * getSize
				 * insert 2
				 * getSize
				 * insert 13
				 * getSize
				 * insert 12
				 * getSize
				 * insert 11
				 * getSize
				 * insert 20
				 * getSize
				 * remove 11
				 * getSize
				 * remove 2
				 * getSize
				 * remove 5
				 * getSize
				 * remove 13
				 * getSize
				 * remove 10
				 * getSize
				 * remove 12
				 * getSize
				 * remove 20
				 * getSize
				 */
				message += "===Test Case 11===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 1){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(AVLTreeInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 2){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2)\n";
					insert.invoke(AVLTreeInstance, 2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 3){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(13)\n";
					insert.invoke(AVLTreeInstance, 13);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(13) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 4){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(12)\n";
					insert.invoke(AVLTreeInstance, 12);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(12) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 5){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(11)\n";
					insert.invoke(AVLTreeInstance, 11);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(11) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 6){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(20)\n";
					insert.invoke(AVLTreeInstance, 20);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(20) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 7){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(11)\n";
					remove.invoke(AVLTreeInstance, 11);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(11) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 6){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(2)\n";
					remove.invoke(AVLTreeInstance, 2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(2) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 5){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(5)\n";
					remove.invoke(AVLTreeInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(5) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 4){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(13)\n";
					remove.invoke(AVLTreeInstance, 13);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(13) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 3){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(10)\n";
					remove.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(10) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 2){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(12)\n";
					remove.invoke(AVLTreeInstance, 12);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(12) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 1){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(20)\n";
					remove.invoke(AVLTreeInstance, 20);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(20) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 0){
						message += "\tgetSize doesn't work\n";
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
					score -= 3/35.0;
					message += "Test Case 11 failed.\n";
				}
				
/* === Test Case 12 ===
				 * insert 10
				 * getSize
				 * insert 5
				 * getSize
				 * insert 2
				 * getSize
				 * insert 13
				 * getSize
				 * insert 12
				 * getSize
				 * insert 11
				 * getSize
				 * insert 20
				 * getSize
				 * remove 13
				 * getSize
				 * remove 2
				 * getSize
				 * remove 10
				 * getSize
				 * remove 20
				 * getSize
				 * remove 12
				 * getSize
				 * remove 11
				 * getSize
				 * remove 5
				 * getSize
				 */
				message += "===Test Case 12===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 1){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(AVLTreeInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 2){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2)\n";
					insert.invoke(AVLTreeInstance, 2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 3){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(13)\n";
					insert.invoke(AVLTreeInstance, 13);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(13) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 4){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(12)\n";
					insert.invoke(AVLTreeInstance, 12);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(12) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 5){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(11)\n";
					insert.invoke(AVLTreeInstance, 11);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(11) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 6){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(20)\n";
					insert.invoke(AVLTreeInstance, 20);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(20) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 7){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(13)\n";
					remove.invoke(AVLTreeInstance, 13);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(13) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 6){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(2)\n";
					remove.invoke(AVLTreeInstance, 2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(2) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 5){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(10)\n";
					remove.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(10) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 4){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(20)\n";
					remove.invoke(AVLTreeInstance, 20);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(20) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 3){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(12)\n";
					remove.invoke(AVLTreeInstance, 12);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(12) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 2){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(11)\n";
					remove.invoke(AVLTreeInstance, 11);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(11) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 1){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(5)\n";
					remove.invoke(AVLTreeInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(5) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 0){
						message += "\tgetSize doesn't work\n";
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
					score -= 3/35.0;
					message += "Test Case 12 failed.\n";
				}
/* === Test Case 13===
				 * insert 10
				 * getSize
				 * insert 5
				 * getSize
				 * remove 10
				 * getSize
				 * remove 5
				 * getSize
				 * insert 2
				 * getSize
				 */
				message += "===Test Case 13===\n";
			
				//create instance
				AVLTreeInstance = AVLTree.newInstance();
				testPassed = true;
				//Insert
				try {
					message += "\tinsert(10)\n";
					insert.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(10) throws error \n";
					testPassed = false;
				}				
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 1){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(5)\n";
					insert.invoke(AVLTreeInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(5) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 2){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(10)\n";
					remove.invoke(AVLTreeInstance, 10);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(10) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 1){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Remove
				try {
					message += "\tremove(5)\n";
					remove.invoke(AVLTreeInstance, 5);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tremove(5) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 0){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				//Insert
				try {
					message += "\tinsert(2)\n";
					insert.invoke(AVLTreeInstance, 2);
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tinsert(2) throws error \n";
					testPassed = false;
				}
				//getSize
				try {
					message += "\tgetSize()\n";
					int i = (int) getSize.invoke(AVLTreeInstance);
					if(i != 1){
						message += "\tgetSize doesn't work\n";
						testPassed = false;
					}
				} catch (InvocationTargetException | NullPointerException e) {
					message += "\tgetSize() throws error \n";
					testPassed = false;
				}
				if(testPassed){
					message += "Test Case 13 passed.\n";
				}
				else{
					score -= 3/35.0;
					message += "Test Case 13 failed.\n";
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
