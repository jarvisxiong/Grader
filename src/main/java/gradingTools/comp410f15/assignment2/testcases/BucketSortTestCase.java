package gradingTools.comp410f15.assignment2.testcases;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
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

public class BucketSortTestCase extends BasicTestCase {

	public BucketSortTestCase() {
		super("Bucket Sort Test Case");
	}
 
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		try {
			ClassesManager classesManager = project.getClassesManager().get();
			String MovieDatabaseClassName = "src.MovieDatabase";
			String MovieClassName = "src.Movie";
			
			// Initialize score and feedback message
			double score = 1;
			String message = "";
			
			//Create input filepaths
			String input[] = new String[5];
			input[0] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\intTenElements.csv";
			input[1] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\intTenElementsAscending.csv";
			input[2] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\intTenElementsDescending.csv";
			input[3] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\intOneElement.csv";
			input[4] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\intEmpty.csv";
			
			//Create output fields
			String expectedOutput[] = new String[48];
			String output;
			boolean testPassed;
			
			// Redirect output to baos
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    System.setOut(new PrintStream(baos));
		    
			//Get MovieDatabase Class
			List<ClassDescription> MovieDatabaseDescription = classesManager.findByClassName(MovieDatabaseClassName);
			Class<String> MovieDatabase = (Class<String>) MovieDatabaseDescription.get(0).getJavaClass();
			Object MovieDatabaseInstance = null;
			
			//Get Movie Class
			List<ClassDescription> MovieDescription = classesManager.findByClassName(MovieClassName);
			Class<String> Movie = (Class<String>) MovieDescription.get(0).getJavaClass();
			
			// Sort 10 elements - 5 points
			try {
				//Create output
				expectedOutput[0] = "f,5.0\r\nd,4.0\r\ng,3.0\r\nc,3.0\r\nb,2.0\r\nh,1.0\r\ne,1.0\r\na,1.0\r\nj,0.0\r\ni,0.0\r\n";
				expectedOutput[1] = "f,5.0\r\nd,4.0\r\ng,3.0\r\nc,3.0\r\nb,2.0\r\nh,1.0\r\ne,1.0\r\na,1.0\r\ni,0.0\r\nj,0.0\r\n";				
				expectedOutput[2] = "f,5.0\r\nd,4.0\r\ng,3.0\r\nc,3.0\r\nb,2.0\r\ne,1.0\r\nh,1.0\r\na,1.0\r\nj,0.0\r\ni,0.0\r\n";
				expectedOutput[3] = "f,5.0\r\nd,4.0\r\ng,3.0\r\nc,3.0\r\nb,2.0\r\ne,1.0\r\nh,1.0\r\na,1.0\r\ni,0.0\r\nj,0.0\r\n";				
				expectedOutput[4] = "f,5.0\r\nd,4.0\r\ng,3.0\r\nc,3.0\r\nb,2.0\r\na,1.0\r\ne,1.0\r\nh,1.0\r\nj,0.0\r\ni,0.0\r\n";
				expectedOutput[5] = "f,5.0\r\nd,4.0\r\ng,3.0\r\nc,3.0\r\nb,2.0\r\na,1.0\r\ne,1.0\r\nh,1.0\r\ni,0.0\r\nj,0.0\r\n";				
				expectedOutput[6] = "f,5.0\r\nd,4.0\r\ng,3.0\r\nc,3.0\r\nb,2.0\r\ne,1.0\r\na,1.0\r\nh,1.0\r\nj,0.0\r\ni,0.0\r\n";
				expectedOutput[7] = "f,5.0\r\nd,4.0\r\ng,3.0\r\nc,3.0\r\nb,2.0\r\ne,1.0\r\na,1.0\r\nh,1.0\r\ni,0.0\r\nj,0.0\r\n";				
				expectedOutput[8] = "f,5.0\r\nd,4.0\r\ng,3.0\r\nc,3.0\r\nb,2.0\r\na,1.0\r\nh,1.0\r\ne,1.0\r\nj,0.0\r\ni,0.0\r\n";
				expectedOutput[9] = "f,5.0\r\nd,4.0\r\ng,3.0\r\nc,3.0\r\nb,2.0\r\na,1.0\r\nh,1.0\r\ne,1.0\r\ni,0.0\r\nj,0.0\r\n";				
				expectedOutput[10] = "f,5.0\r\nd,4.0\r\ng,3.0\r\nc,3.0\r\nb,2.0\r\nh,1.0\r\na,1.0\r\ne,1.0\r\nj,0.0\r\ni,0.0\r\n";
				expectedOutput[11] = "f,5.0\r\nd,4.0\r\ng,3.0\r\nc,3.0\r\nb,2.0\r\nh,1.0\r\na,1.0\r\ne,1.0\r\ni,0.0\r\nj,0.0\r\n";
				
				expectedOutput[12] = "f,5.0\r\nd,4.0\r\nc,3.0\r\ng,3.0\r\nb,2.0\r\nh,1.0\r\ne,1.0\r\na,1.0\r\nj,0.0\r\ni,0.0\r\n";
				expectedOutput[13] = "f,5.0\r\nd,4.0\r\nc,3.0\r\ng,3.0\r\nb,2.0\r\nh,1.0\r\ne,1.0\r\na,1.0\r\ni,0.0\r\nj,0.0\r\n";				
				expectedOutput[14] = "f,5.0\r\nd,4.0\r\nc,3.0\r\ng,3.0\r\nb,2.0\r\ne,1.0\r\nh,1.0\r\na,1.0\r\nj,0.0\r\ni,0.0\r\n";
				expectedOutput[15] = "f,5.0\r\nd,4.0\r\nc,3.0\r\ng,3.0\r\nb,2.0\r\ne,1.0\r\nh,1.0\r\na,1.0\r\ni,0.0\r\nj,0.0\r\n";				
				expectedOutput[16] = "f,5.0\r\nd,4.0\r\nc,3.0\r\ng,3.0\r\nb,2.0\r\na,1.0\r\ne,1.0\r\nh,1.0\r\nj,0.0\r\ni,0.0\r\n";
				expectedOutput[17] = "f,5.0\r\nd,4.0\r\nc,3.0\r\ng,3.0\r\nb,2.0\r\na,1.0\r\ne,1.0\r\nh,1.0\r\ni,0.0\r\nj,0.0\r\n";				
				expectedOutput[18] = "f,5.0\r\nd,4.0\r\nc,3.0\r\ng,3.0\r\nb,2.0\r\ne,1.0\r\na,1.0\r\nh,1.0\r\nj,0.0\r\ni,0.0\r\n";
				expectedOutput[19] = "f,5.0\r\nd,4.0\r\nc,3.0\r\ng,3.0\r\nb,2.0\r\ne,1.0\r\na,1.0\r\nh,1.0\r\ni,0.0\r\nj,0.0\r\n";				
				expectedOutput[20] = "f,5.0\r\nd,4.0\r\nc,3.0\r\ng,3.0\r\nb,2.0\r\na,1.0\r\nh,1.0\r\ne,1.0\r\nj,0.0\r\ni,0.0\r\n";
				expectedOutput[21] = "f,5.0\r\nd,4.0\r\nc,3.0\r\ng,3.0\r\nb,2.0\r\na,1.0\r\nh,1.0\r\ne,1.0\r\ni,0.0\r\nj,0.0\r\n";				
				expectedOutput[22] = "f,5.0\r\nd,4.0\r\nc,3.0\r\ng,3.0\r\nb,2.0\r\nh,1.0\r\na,1.0\r\ne,1.0\r\nj,0.0\r\ni,0.0\r\n";
				expectedOutput[23] = "f,5.0\r\nd,4.0\r\nc,3.0\r\ng,3.0\r\nb,2.0\r\nh,1.0\r\na,1.0\r\ne,1.0\r\ni,0.0\r\nj,0.0\r\n";
				
				expectedOutput[24] = "i,0.0\r\nj,0.0\r\na,1.0\r\ne,1.0\r\nh,1.0\r\nb,2.0\r\nc,3.0\r\ng,3.0\r\nd,4.0\r\nf,5.0\r\n";
				expectedOutput[25] = "j,0.0\r\ni,0.0\r\na,1.0\r\ne,1.0\r\nh,1.0\r\nb,2.0\r\nc,3.0\r\ng,3.0\r\nd,4.0\r\nf,5.0\r\n";				
				expectedOutput[26] = "i,0.0\r\nj,0.0\r\na,1.0\r\nh,1.0\r\ne,1.0\r\nb,2.0\r\nc,3.0\r\ng,3.0\r\nd,4.0\r\nf,5.0\r\n";
				expectedOutput[27] = "j,0.0\r\ni,0.0\r\na,1.0\r\nh,1.0\r\ne,1.0\r\nb,2.0\r\nc,3.0\r\ng,3.0\r\nd,4.0\r\nf,5.0\r\n";				
				expectedOutput[28] = "i,0.0\r\nj,0.0\r\ne,1.0\r\na,1.0\r\nh,1.0\r\nb,2.0\r\nc,3.0\r\ng,3.0\r\nd,4.0\r\nf,5.0\r\n";
				expectedOutput[29] = "j,0.0\r\ni,0.0\r\ne,1.0\r\na,1.0\r\nh,1.0\r\nb,2.0\r\nc,3.0\r\ng,3.0\r\nd,4.0\r\nf,5.0\r\n";				
				expectedOutput[30] = "i,0.0\r\nj,0.0\r\ne,1.0\r\nh,1.0\r\na,1.0\r\nb,2.0\r\nc,3.0\r\ng,3.0\r\nd,4.0\r\nf,5.0\r\n";
				expectedOutput[31] = "j,0.0\r\ni,0.0\r\ne,1.0\r\nh,1.0\r\na,1.0\r\nb,2.0\r\nc,3.0\r\ng,3.0\r\nd,4.0\r\nf,5.0\r\n";				
				expectedOutput[32] = "i,0.0\r\nj,0.0\r\nh,1.0\r\ne,1.0\r\na,1.0\r\nb,2.0\r\nc,3.0\r\ng,3.0\r\nd,4.0\r\nf,5.0\r\n";
				expectedOutput[33] = "j,0.0\r\ni,0.0\r\nh,1.0\r\ne,1.0\r\na,1.0\r\nb,2.0\r\nc,3.0\r\ng,3.0\r\nd,4.0\r\nf,5.0\r\n";				
				expectedOutput[34] = "i,0.0\r\nj,0.0\r\nh,1.0\r\na,1.0\r\ne,1.0\r\nb,2.0\r\nc,3.0\r\ng,3.0\r\nd,4.0\r\nf,5.0\r\n";
				expectedOutput[35] = "j,0.0\r\ni,0.0\r\nh,1.0\r\na,1.0\r\ne,1.0\r\nb,2.0\r\nc,3.0\r\ng,3.0\r\nd,4.0\r\nf,5.0\r\n";
				
				expectedOutput[36] = "i,0.0\r\nj,0.0\r\na,1.0\r\ne,1.0\r\nh,1.0\r\nb,2.0\r\ng,3.0\r\nc,3.0\r\nd,4.0\r\nf,5.0\r\n";
				expectedOutput[37] = "j,0.0\r\ni,0.0\r\na,1.0\r\ne,1.0\r\nh,1.0\r\nb,2.0\r\ng,3.0\r\nc,3.0\r\nd,4.0\r\nf,5.0\r\n";				
				expectedOutput[38] = "i,0.0\r\nj,0.0\r\na,1.0\r\nh,1.0\r\ne,1.0\r\nb,2.0\r\ng,3.0\r\nc,3.0\r\nd,4.0\r\nf,5.0\r\n";
				expectedOutput[39] = "j,0.0\r\ni,0.0\r\na,1.0\r\nh,1.0\r\ne,1.0\r\nb,2.0\r\ng,3.0\r\nc,3.0\r\nd,4.0\r\nf,5.0\r\n";				
				expectedOutput[40] = "i,0.0\r\nj,0.0\r\ne,1.0\r\na,1.0\r\nh,1.0\r\nb,2.0\r\ng,3.0\r\nc,3.0\r\nd,4.0\r\nf,5.0\r\n";
				expectedOutput[41] = "j,0.0\r\ni,0.0\r\ne,1.0\r\na,1.0\r\nh,1.0\r\nb,2.0\r\ng,3.0\r\nc,3.0\r\nd,4.0\r\nf,5.0\r\n";				
				expectedOutput[42] = "i,0.0\r\nj,0.0\r\ne,1.0\r\nh,1.0\r\na,1.0\r\nb,2.0\r\ng,3.0\r\nc,3.0\r\nd,4.0\r\nf,5.0\r\n";
				expectedOutput[43] = "j,0.0\r\ni,0.0\r\ne,1.0\r\nh,1.0\r\na,1.0\r\nb,2.0\r\ng,3.0\r\nc,3.0\r\nd,4.0\r\nf,5.0\r\n";				
				expectedOutput[44] = "i,0.0\r\nj,0.0\r\nh,1.0\r\ne,1.0\r\na,1.0\r\nb,2.0\r\ng,3.0\r\nc,3.0\r\nd,4.0\r\nf,5.0\r\n";
				expectedOutput[45] = "j,0.0\r\ni,0.0\r\nh,1.0\r\ne,1.0\r\na,1.0\r\nb,2.0\r\ng,3.0\r\nc,3.0\r\nd,4.0\r\nf,5.0\r\n";				
				expectedOutput[46] = "i,0.0\r\nj,0.0\r\nh,1.0\r\na,1.0\r\ne,1.0\r\nb,2.0\r\ng,3.0\r\nc,3.0\r\nd,4.0\r\nf,5.0\r\n";
				expectedOutput[47] = "j,0.0\r\ni,0.0\r\nh,1.0\r\na,1.0\r\ne,1.0\r\nb,2.0\r\ng,3.0\r\nc,3.0\r\nd,4.0\r\nf,5.0\r\n";
				testPassed = false;
				baos.reset();
				
				//Create and initialize database
				FileReader fileReader = new FileReader(input[0]);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            int intDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
	            MovieDatabaseInstance = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(intDatabaseCapacity, "int");
	            
	            String line;
				Field field = MovieDatabase.getDeclaredField("movies");					
				Object MovieArray[] = (Object[])field.get(MovieDatabaseInstance);
				for(int i = 0; i < intDatabaseCapacity; i++){
					line = bufferedReader.readLine();
					String movie[] = line.split(",");
					

					MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

				}
				field.set(MovieDatabaseInstance, MovieArray);
	            bufferedReader.close();
	            
	            //Get methods
	            Method bucketSort = MovieDatabase.getMethod("bucketsort");
				Method print = MovieDatabase.getMethod("print");				    
				
				try {
					bucketSort.invoke(MovieDatabaseInstance);
					print.invoke(MovieDatabaseInstance);
					testPassed = true;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					testPassed = false;
				}
				// Match output only if test passed
				if(testPassed){
					output = baos.toString();
					testPassed = false;					
					for(int i = 0; i < 48; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
				if(!testPassed){
					score -= 0.2;
					message += "Bucket sort doesn't work in case of 10 elements\n";
				}
				
				
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
			
			
			// Array pre-sorted in ascending order - 5 points
			try {
				//Create output
				expectedOutput[0] = "a,5.0\r\nb,5.0\r\nc,4.0\r\ne,4.0\r\nf,3.0\r\nd,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";
				expectedOutput[1] = "b,5.0\r\na,5.0\r\nc,4.0\r\ne,4.0\r\nf,3.0\r\nd,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";				
				expectedOutput[2] = "a,5.0\r\nb,5.0\r\ne,4.0\r\nc,4.0\r\nf,3.0\r\nd,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";
				expectedOutput[3] = "b,5.0\r\na,5.0\r\ne,4.0\r\nc,4.0\r\nf,3.0\r\nd,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";				
				expectedOutput[4] = "a,5.0\r\nb,5.0\r\nc,4.0\r\ne,4.0\r\nd,3.0\r\nf,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";
				expectedOutput[5] = "b,5.0\r\na,5.0\r\nc,4.0\r\ne,4.0\r\nd,3.0\r\nf,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";				
				expectedOutput[6] = "a,5.0\r\nb,5.0\r\ne,4.0\r\nc,4.0\r\nd,3.0\r\nf,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";
				expectedOutput[7] = "b,5.0\r\na,5.0\r\ne,4.0\r\nc,4.0\r\nd,3.0\r\nf,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";				
				expectedOutput[8] = "a,5.0\r\nb,5.0\r\nc,4.0\r\ne,4.0\r\nf,3.0\r\nd,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";
				expectedOutput[9] = "b,5.0\r\na,5.0\r\nc,4.0\r\ne,4.0\r\nf,3.0\r\nd,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";				
				expectedOutput[10] = "a,5.0\r\nb,5.0\r\ne,4.0\r\nc,4.0\r\nf,3.0\r\nd,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";
				expectedOutput[11] = "b,5.0\r\na,5.0\r\ne,4.0\r\nc,4.0\r\nf,3.0\r\nd,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";				
				expectedOutput[12] = "a,5.0\r\nb,5.0\r\nc,4.0\r\ne,4.0\r\nd,3.0\r\nf,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";
				expectedOutput[13] = "b,5.0\r\na,5.0\r\nc,4.0\r\ne,4.0\r\nd,3.0\r\nf,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";				
				expectedOutput[14] = "a,5.0\r\nb,5.0\r\ne,4.0\r\nc,4.0\r\nd,3.0\r\nf,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";
				expectedOutput[15] = "b,5.0\r\na,5.0\r\ne,4.0\r\nc,4.0\r\nd,3.0\r\nf,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";
				
				expectedOutput[16] = "j,0.0\r\ni,1.0\r\ng,2.0\r\nh,2.0\r\nf,3.0\r\nd,3.0\r\nc,4.0\r\ne,4.0\r\na,5.0\r\nb,5.0\r\n";
				expectedOutput[17] = "j,0.0\r\ni,1.0\r\ng,2.0\r\nh,2.0\r\nf,3.0\r\nd,3.0\r\nc,4.0\r\ne,4.0\r\nb,5.0\r\na,5.0\r\n";				
				expectedOutput[18] = "j,0.0\r\ni,1.0\r\ng,2.0\r\nh,2.0\r\nf,3.0\r\nd,3.0\r\ne,4.0\r\nc,4.0\r\na,5.0\r\nb,5.0\r\n";
				expectedOutput[19] = "j,0.0\r\ni,1.0\r\ng,2.0\r\nh,2.0\r\nf,3.0\r\nd,3.0\r\ne,4.0\r\nc,4.0\r\nb,5.0\r\na,5.0\r\n";				
				expectedOutput[20] = "j,0.0\r\ni,1.0\r\ng,2.0\r\nh,2.0\r\nd,3.0\r\nf,3.0\r\nc,4.0\r\ne,4.0\r\na,5.0\r\nb,5.0\r\n";
				expectedOutput[21] = "j,0.0\r\ni,1.0\r\ng,2.0\r\nh,2.0\r\nd,3.0\r\nf,3.0\r\nc,4.0\r\ne,4.0\r\nb,5.0\r\na,5.0\r\n";				
				expectedOutput[22] = "j,0.0\r\ni,1.0\r\ng,2.0\r\nh,2.0\r\nd,3.0\r\nf,3.0\r\ne,4.0\r\nc,4.0\r\na,5.0\r\nb,5.0\r\n";
				expectedOutput[23] = "j,0.0\r\ni,1.0\r\ng,2.0\r\nh,2.0\r\nd,3.0\r\nf,3.0\r\ne,4.0\r\nc,4.0\r\nb,5.0\r\na,5.0\r\n";				
				expectedOutput[24] = "j,0.0\r\ni,1.0\r\nh,2.0\r\ng,2.0\r\nf,3.0\r\nd,3.0\r\nc,4.0\r\ne,4.0\r\na,5.0\r\nb,5.0\r\n";
				expectedOutput[25] = "j,0.0\r\ni,1.0\r\nh,2.0\r\ng,2.0\r\nf,3.0\r\nd,3.0\r\nc,4.0\r\ne,4.0\r\nb,5.0\r\na,5.0\r\n";				
				expectedOutput[26] = "j,0.0\r\ni,1.0\r\nh,2.0\r\ng,2.0\r\nf,3.0\r\nd,3.0\r\ne,4.0\r\nc,4.0\r\na,5.0\r\nb,5.0\r\n";
				expectedOutput[27] = "j,0.0\r\ni,1.0\r\nh,2.0\r\ng,2.0\r\nf,3.0\r\nd,3.0\r\ne,4.0\r\nc,4.0\r\nb,5.0\r\na,5.0\r\n";				
				expectedOutput[28] = "j,0.0\r\ni,1.0\r\nh,2.0\r\ng,2.0\r\nd,3.0\r\nf,3.0\r\nc,4.0\r\ne,4.0\r\na,5.0\r\nb,5.0\r\n";
				expectedOutput[29] = "j,0.0\r\ni,1.0\r\nh,2.0\r\ng,2.0\r\nd,3.0\r\nf,3.0\r\nc,4.0\r\ne,4.0\r\nb,5.0\r\na,5.0\r\n";				
				expectedOutput[30] = "j,0.0\r\ni,1.0\r\nh,2.0\r\ng,2.0\r\nd,3.0\r\nf,3.0\r\ne,4.0\r\nc,4.0\r\na,5.0\r\nb,5.0\r\n";
				expectedOutput[31] = "j,0.0\r\ni,1.0\r\nh,2.0\r\ng,2.0\r\nd,3.0\r\nf,3.0\r\ne,4.0\r\nc,4.0\r\nb,5.0\r\na,5.0\r\n";
				testPassed = false;
				baos.reset();
				
				//Create and initialize database
				FileReader fileReader = new FileReader(input[1]);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            int intDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
	            MovieDatabaseInstance = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(intDatabaseCapacity, "int");
	            
	            String line;
				Field field = MovieDatabase.getDeclaredField("movies");					
				Object MovieArray[] = (Object[])field.get(MovieDatabaseInstance);
				for(int i = 0; i < intDatabaseCapacity; i++){
					line = bufferedReader.readLine();
					String movie[] = line.split(",");
					

					MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

				}
				field.set(MovieDatabaseInstance, MovieArray);
	            bufferedReader.close();
	            
	            //Get methods
	            Method bucketSort = MovieDatabase.getMethod("bucketsort");
				Method print = MovieDatabase.getMethod("print");				    
				
				try {
					bucketSort.invoke(MovieDatabaseInstance);
					print.invoke(MovieDatabaseInstance);
					testPassed = true;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					testPassed = false;
				}
				// Match output only if test passed
				if(testPassed){
					output = baos.toString();
					testPassed = false;					
					for(int i = 0; i < 32; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
				if(!testPassed){
					score -= 0.2;
					message += "Bucket sort doesn't work in case of pre-sorted in ascending order.\n";
				}
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
			
			// Array pre-sorted in descending order - 5 points
			try {
				//Create output
				expectedOutput[0] = "a,5.0\r\nb,5.0\r\nc,4.0\r\ne,4.0\r\nf,3.0\r\nd,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";
				expectedOutput[1] = "b,5.0\r\na,5.0\r\nc,4.0\r\ne,4.0\r\nf,3.0\r\nd,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";				
				expectedOutput[2] = "a,5.0\r\nb,5.0\r\ne,4.0\r\nc,4.0\r\nf,3.0\r\nd,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";
				expectedOutput[3] = "b,5.0\r\na,5.0\r\ne,4.0\r\nc,4.0\r\nf,3.0\r\nd,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";				
				expectedOutput[4] = "a,5.0\r\nb,5.0\r\nc,4.0\r\ne,4.0\r\nd,3.0\r\nf,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";
				expectedOutput[5] = "b,5.0\r\na,5.0\r\nc,4.0\r\ne,4.0\r\nd,3.0\r\nf,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";				
				expectedOutput[6] = "a,5.0\r\nb,5.0\r\ne,4.0\r\nc,4.0\r\nd,3.0\r\nf,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";
				expectedOutput[7] = "b,5.0\r\na,5.0\r\ne,4.0\r\nc,4.0\r\nd,3.0\r\nf,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";				
				expectedOutput[8] = "a,5.0\r\nb,5.0\r\nc,4.0\r\ne,4.0\r\nf,3.0\r\nd,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";
				expectedOutput[9] = "b,5.0\r\na,5.0\r\nc,4.0\r\ne,4.0\r\nf,3.0\r\nd,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";				
				expectedOutput[10] = "a,5.0\r\nb,5.0\r\ne,4.0\r\nc,4.0\r\nf,3.0\r\nd,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";
				expectedOutput[11] = "b,5.0\r\na,5.0\r\ne,4.0\r\nc,4.0\r\nf,3.0\r\nd,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";				
				expectedOutput[12] = "a,5.0\r\nb,5.0\r\nc,4.0\r\ne,4.0\r\nd,3.0\r\nf,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";
				expectedOutput[13] = "b,5.0\r\na,5.0\r\nc,4.0\r\ne,4.0\r\nd,3.0\r\nf,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";				
				expectedOutput[14] = "a,5.0\r\nb,5.0\r\ne,4.0\r\nc,4.0\r\nd,3.0\r\nf,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";
				expectedOutput[15] = "b,5.0\r\na,5.0\r\ne,4.0\r\nc,4.0\r\nd,3.0\r\nf,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";
				
				expectedOutput[16] = "j,0.0\r\ni,1.0\r\ng,2.0\r\nh,2.0\r\nf,3.0\r\nd,3.0\r\nc,4.0\r\ne,4.0\r\na,5.0\r\nb,5.0\r\n";
				expectedOutput[17] = "j,0.0\r\ni,1.0\r\ng,2.0\r\nh,2.0\r\nf,3.0\r\nd,3.0\r\nc,4.0\r\ne,4.0\r\nb,5.0\r\na,5.0\r\n";				
				expectedOutput[18] = "j,0.0\r\ni,1.0\r\ng,2.0\r\nh,2.0\r\nf,3.0\r\nd,3.0\r\ne,4.0\r\nc,4.0\r\na,5.0\r\nb,5.0\r\n";
				expectedOutput[19] = "j,0.0\r\ni,1.0\r\ng,2.0\r\nh,2.0\r\nf,3.0\r\nd,3.0\r\ne,4.0\r\nc,4.0\r\nb,5.0\r\na,5.0\r\n";				
				expectedOutput[20] = "j,0.0\r\ni,1.0\r\ng,2.0\r\nh,2.0\r\nd,3.0\r\nf,3.0\r\nc,4.0\r\ne,4.0\r\na,5.0\r\nb,5.0\r\n";
				expectedOutput[21] = "j,0.0\r\ni,1.0\r\ng,2.0\r\nh,2.0\r\nd,3.0\r\nf,3.0\r\nc,4.0\r\ne,4.0\r\nb,5.0\r\na,5.0\r\n";				
				expectedOutput[22] = "j,0.0\r\ni,1.0\r\ng,2.0\r\nh,2.0\r\nd,3.0\r\nf,3.0\r\ne,4.0\r\nc,4.0\r\na,5.0\r\nb,5.0\r\n";
				expectedOutput[23] = "j,0.0\r\ni,1.0\r\ng,2.0\r\nh,2.0\r\nd,3.0\r\nf,3.0\r\ne,4.0\r\nc,4.0\r\nb,5.0\r\na,5.0\r\n";				
				expectedOutput[24] = "j,0.0\r\ni,1.0\r\nh,2.0\r\ng,2.0\r\nf,3.0\r\nd,3.0\r\nc,4.0\r\ne,4.0\r\na,5.0\r\nb,5.0\r\n";
				expectedOutput[25] = "j,0.0\r\ni,1.0\r\nh,2.0\r\ng,2.0\r\nf,3.0\r\nd,3.0\r\nc,4.0\r\ne,4.0\r\nb,5.0\r\na,5.0\r\n";				
				expectedOutput[26] = "j,0.0\r\ni,1.0\r\nh,2.0\r\ng,2.0\r\nf,3.0\r\nd,3.0\r\ne,4.0\r\nc,4.0\r\na,5.0\r\nb,5.0\r\n";
				expectedOutput[27] = "j,0.0\r\ni,1.0\r\nh,2.0\r\ng,2.0\r\nf,3.0\r\nd,3.0\r\ne,4.0\r\nc,4.0\r\nb,5.0\r\na,5.0\r\n";				
				expectedOutput[28] = "j,0.0\r\ni,1.0\r\nh,2.0\r\ng,2.0\r\nd,3.0\r\nf,3.0\r\nc,4.0\r\ne,4.0\r\na,5.0\r\nb,5.0\r\n";
				expectedOutput[29] = "j,0.0\r\ni,1.0\r\nh,2.0\r\ng,2.0\r\nd,3.0\r\nf,3.0\r\nc,4.0\r\ne,4.0\r\nb,5.0\r\na,5.0\r\n";				
				expectedOutput[30] = "j,0.0\r\ni,1.0\r\nh,2.0\r\ng,2.0\r\nd,3.0\r\nf,3.0\r\ne,4.0\r\nc,4.0\r\na,5.0\r\nb,5.0\r\n";
				expectedOutput[31] = "j,0.0\r\ni,1.0\r\nh,2.0\r\ng,2.0\r\nd,3.0\r\nf,3.0\r\ne,4.0\r\nc,4.0\r\nb,5.0\r\na,5.0\r\n";
				testPassed = false;
				baos.reset();
				
				//Create and initialize database
				FileReader fileReader = new FileReader(input[2]);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            int intDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
	            MovieDatabaseInstance = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(intDatabaseCapacity, "int");
	            
	            String line;
				Field field = MovieDatabase.getDeclaredField("movies");					
				Object MovieArray[] = (Object[])field.get(MovieDatabaseInstance);
				for(int i = 0; i < intDatabaseCapacity; i++){
					line = bufferedReader.readLine();
					String movie[] = line.split(",");
					

					MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

				}
				field.set(MovieDatabaseInstance, MovieArray);
	            bufferedReader.close();
	            
	            //Get methods
	            Method bucketSort = MovieDatabase.getMethod("bucketsort");
				Method print = MovieDatabase.getMethod("print");				    
				
				try {
					bucketSort.invoke(MovieDatabaseInstance);
					print.invoke(MovieDatabaseInstance);
					testPassed = true;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					testPassed = false;
				}
				// Match output only if test passed
				if(testPassed){
					output = baos.toString();
					testPassed = false;					
					for(int i = 0; i < 32; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
				if(!testPassed){
					score -= 0.2;
					message += "Bucket sort doesn't work in case of pre-sorted in descending order.\n";
				}
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
			
			// Array has only one element - 5 points
			try {
				//Create output
				expectedOutput[0] = "a,1.0\r\n";
				testPassed = false;
				baos.reset();
				
				//Create and initialize database
				FileReader fileReader = new FileReader(input[3]);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            int intDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
	            MovieDatabaseInstance = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(intDatabaseCapacity, "int");
	            
	            String line;
				Field field = MovieDatabase.getDeclaredField("movies");					
				Object MovieArray[] = (Object[])field.get(MovieDatabaseInstance);
				for(int i = 0; i < intDatabaseCapacity; i++){
					line = bufferedReader.readLine();
					String movie[] = line.split(",");
					

					MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

				}
				field.set(MovieDatabaseInstance, MovieArray);
	            bufferedReader.close();
	            
	            //Get methods
	            Method bucketSort = MovieDatabase.getMethod("bucketsort");
				Method print = MovieDatabase.getMethod("print");				    
				
				try {
					bucketSort.invoke(MovieDatabaseInstance);
					print.invoke(MovieDatabaseInstance);
					testPassed = true;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					testPassed = false;
				}
				// Match output only if test passed
				if(testPassed){
					output = baos.toString();
					if(output.equals(expectedOutput[0])){
						testPassed = true;
					}
					else{
						testPassed = false;
					}
				}
				if(!testPassed){
					score -= 0.2;
					message += "Bucket sort doesn't work in case of size one array.\n";
				}
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
			
			// Array empty - 5 points
			try {
				//Create output
				expectedOutput[0] = "";
				testPassed = false;
				baos.reset();
				
				//Create and initialize database
				FileReader fileReader = new FileReader(input[4]);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            int intDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
	            MovieDatabaseInstance = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(intDatabaseCapacity, "int");
	            
	            String line;
				Field field = MovieDatabase.getDeclaredField("movies");					
				Object MovieArray[] = (Object[])field.get(MovieDatabaseInstance);
				for(int i = 0; i < intDatabaseCapacity; i++){
					line = bufferedReader.readLine();
					String movie[] = line.split(",");
					

					MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

				}
				field.set(MovieDatabaseInstance, MovieArray);
	            bufferedReader.close();
	            
	            //Get methods
	            Method bucketSort = MovieDatabase.getMethod("bucketsort");
				Method print = MovieDatabase.getMethod("print");				    
				
				try {
					bucketSort.invoke(MovieDatabaseInstance);
					print.invoke(MovieDatabaseInstance);
					testPassed = true;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					testPassed = false;
				}
				// Match output only if test passed
				if(testPassed){
					output = baos.toString();
					if(output.equals(expectedOutput[0])){
						testPassed = true;
					}
					else{
						testPassed = false;
					}
				}
				if(!testPassed){
					score -= 0.2;
					message += "Bucket sort doesn't work in case of empty array.\n";
				}
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
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
