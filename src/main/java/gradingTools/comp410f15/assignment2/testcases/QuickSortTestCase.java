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
import java.util.Set;

import framework.execution.NotRunnableException;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.ClassesManager;
import framework.project.Project;

public class QuickSortTestCase extends BasicTestCase {

	public QuickSortTestCase() {
		super("Quick Sort Test Case");
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
			input[0] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\doubleTenElements.csv";
			input[1] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\doubleTenElementsAscending.csv";
			input[2] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\doubleTenElementsDescending.csv";
			input[3] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\doubleOneElement.csv";
			input[4] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\doubleEmpty.csv";
			
			//Create output fields
			String expectedOutput[] = new String[24];
			String output;
			boolean testPassed;
			
			// Redirect output to baos
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    System.setOut(new PrintStream(baos));
		    
			//Get MovieDatabase Class
			Set<ClassDescription> MovieDatabaseDescription = classesManager.findByClassOrInterfaceName(MovieDatabaseClassName);
			Class<String> MovieDatabase = (Class<String>) MovieDatabaseDescription.iterator().next().getJavaClass();
			Object MovieDatabaseInstance = null;
			
			//Get Movie Class
			Set<ClassDescription> MovieDescription = classesManager.findByClassOrInterfaceName(MovieClassName);
			Class<String> Movie = (Class<String>) MovieDescription.iterator().next().getJavaClass();
			
			// Sort 10 elements - 5 points
			try {
				//Create output
				expectedOutput[0] = "ff,5.0\r\ndd,4.7\r\njj,4.4\r\ngg,3.3\r\ncc,3.3\r\nbb,2.2\r\nhh,1.1\r\naa,1.1\r\nee,1.1\r\nii,0.0\r\n";
				expectedOutput[1] = "ff,5.0\r\ndd,4.7\r\njj,4.4\r\ngg,3.3\r\ncc,3.3\r\nbb,2.2\r\nhh,1.1\r\nee,1.1\r\naa,1.1\r\nii,0.0\r\n";
				expectedOutput[2] = "ff,5.0\r\ndd,4.7\r\njj,4.4\r\ngg,3.3\r\ncc,3.3\r\nbb,2.2\r\naa,1.1\r\nee,1.1\r\nhh,1.1\r\nii,0.0\r\n";
				expectedOutput[3] = "ff,5.0\r\ndd,4.7\r\njj,4.4\r\ngg,3.3\r\ncc,3.3\r\nbb,2.2\r\naa,1.1\r\nhh,1.1\r\nee,1.1\r\nii,0.0\r\n";
				expectedOutput[4] = "ff,5.0\r\ndd,4.7\r\njj,4.4\r\ngg,3.3\r\ncc,3.3\r\nbb,2.2\r\nee,1.1\r\nhh,1.1\r\naa,1.1\r\nii,0.0\r\n";
				expectedOutput[5] = "ff,5.0\r\ndd,4.7\r\njj,4.4\r\ngg,3.3\r\ncc,3.3\r\nbb,2.2\r\nee,1.1\r\naa,1.1\r\nhh,1.1\r\nii,0.0\r\n";
				
				expectedOutput[6] = "ff,5.0\r\ndd,4.7\r\njj,4.4\r\ncc,3.3\r\ngg,3.3\r\nbb,2.2\r\nhh,1.1\r\naa,1.1\r\nee,1.1\r\nii,0.0\r\n";
				expectedOutput[7] = "ff,5.0\r\ndd,4.7\r\njj,4.4\r\ncc,3.3\r\ngg,3.3\r\nbb,2.2\r\nhh,1.1\r\nee,1.1\r\naa,1.1\r\nii,0.0\r\n";
				expectedOutput[8] = "ff,5.0\r\ndd,4.7\r\njj,4.4\r\ncc,3.3\r\ngg,3.3\r\nbb,2.2\r\naa,1.1\r\nee,1.1\r\nhh,1.1\r\nii,0.0\r\n";
				expectedOutput[9] = "ff,5.0\r\ndd,4.7\r\njj,4.4\r\ncc,3.3\r\ngg,3.3\r\nbb,2.2\r\naa,1.1\r\nhh,1.1\r\nee,1.1\r\nii,0.0\r\n";
				expectedOutput[10] = "ff,5.0\r\ndd,4.7\r\njj,4.4\r\ncc,3.3\r\ngg,3.3\r\nbb,2.2\r\nee,1.1\r\nhh,1.1\r\naa,1.1\r\nii,0.0\r\n";
				expectedOutput[11] = "ff,5.0\r\ndd,4.7\r\njj,4.4\r\ncc,3.3\r\ngg,3.3\r\nbb,2.2\r\nee,1.1\r\naa,1.1\r\nhh,1.1\r\nii,0.0\r\n";
				
				expectedOutput[12] = "ii,0.0\r\nee,1.1\r\nhh,1.1\r\naa,1.1\r\nbb,2.2\r\ngg,3.3\r\ncc,3.3\r\njj,4.4\r\ndd,4.7\r\nff,5.0\r\n";
				expectedOutput[13] = "ii,0.0\r\nhh,1.1\r\nee,1.1\r\naa,1.1\r\nbb,2.2\r\ngg,3.3\r\ncc,3.3\r\njj,4.4\r\ndd,4.7\r\nff,5.0\r\n";
				expectedOutput[14] = "ii,0.0\r\nhh,1.1\r\naa,1.1\r\nee,1.1\r\nbb,2.2\r\ngg,3.3\r\ncc,3.3\r\njj,4.4\r\ndd,4.7\r\nff,5.0\r\n";
				expectedOutput[15] = "ii,0.0\r\naa,1.1\r\nhh,1.1\r\nee,1.1\r\nbb,2.2\r\ngg,3.3\r\ncc,3.3\r\njj,4.4\r\ndd,4.7\r\nff,5.0\r\n";
				expectedOutput[16] = "ii,0.0\r\nee,1.1\r\naa,1.1\r\nhh,1.1\r\nbb,2.2\r\ngg,3.3\r\ncc,3.3\r\njj,4.4\r\ndd,4.7\r\nff,5.0\r\n";
				expectedOutput[17] = "ii,0.0\r\naa,1.1\r\nee,1.1\r\nhh,1.1\r\nbb,2.2\r\ngg,3.3\r\ncc,3.3\r\njj,4.4\r\ndd,4.7\r\nff,5.0\r\n";
				
				expectedOutput[18] = "ii,0.0\r\nee,1.1\r\nhh,1.1\r\naa,1.1\r\nbb,2.2\r\ncc,3.3\r\ngg,3.3\r\njj,4.4\r\ndd,4.7\r\nff,5.0\r\n";
				expectedOutput[19] = "ii,0.0\r\nhh,1.1\r\nee,1.1\r\naa,1.1\r\nbb,2.2\r\ncc,3.3\r\ngg,3.3\r\njj,4.4\r\ndd,4.7\r\nff,5.0\r\n";
				expectedOutput[20] = "ii,0.0\r\nhh,1.1\r\naa,1.1\r\nee,1.1\r\nbb,2.2\r\ncc,3.3\r\ngg,3.3\r\njj,4.4\r\ndd,4.7\r\nff,5.0\r\n";
				expectedOutput[21] = "ii,0.0\r\naa,1.1\r\nhh,1.1\r\nee,1.1\r\nbb,2.2\r\ncc,3.3\r\ngg,3.3\r\njj,4.4\r\ndd,4.7\r\nff,5.0\r\n";
				expectedOutput[22] = "ii,0.0\r\nee,1.1\r\naa,1.1\r\nhh,1.1\r\nbb,2.2\r\ncc,3.3\r\ngg,3.3\r\njj,4.4\r\ndd,4.7\r\nff,5.0\r\n";
				expectedOutput[23] = "ii,0.0\r\naa,1.1\r\nee,1.1\r\nhh,1.1\r\nbb,2.2\r\ncc,3.3\r\ngg,3.3\r\njj,4.4\r\ndd,4.7\r\nff,5.0\r\n";
				testPassed = false;
				baos.reset();
				
				//Create and initialize database		
				FileReader fileReader = new FileReader(input[0]);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            int doubleDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
	            MovieDatabaseInstance = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(doubleDatabaseCapacity, "double");
	            
	            String line;
				Field field = MovieDatabase.getDeclaredField("movies");					
				Object MovieArray[] = (Object[])field.get(MovieDatabaseInstance);
				for(int i = 0; i < doubleDatabaseCapacity; i++){
					line = bufferedReader.readLine();
					String movie[] = line.split(",");		

					MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

				}
				field.set(MovieDatabaseInstance, MovieArray);
	            bufferedReader.close();
	            
	            //Get methods
	            Method quickSort = MovieDatabase.getMethod("quicksort");
				Method print = MovieDatabase.getMethod("print");				    
				
				try {
					quickSort.invoke(MovieDatabaseInstance);
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
					for(int i = 0; i < 24; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}					
				}
				if(!testPassed){
					score -= 0.2;
					message += "Quick sort doesn't work in case of 10 elements\n";
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
				expectedOutput[0] = "ii,5.0\r\naa,4.9\r\nbb,4.2\r\ncc,3.8\r\nee,3.2\r\nff,2.5\r\ndd,1.6\r\ngg,1.5\r\nhh,0.9\r\njj,0.0\r\n";
				expectedOutput[1] = "jj,0.0\r\nhh,0.9\r\ngg,1.5\r\ndd,1.6\r\nff,2.5\r\nee,3.2\r\ncc,3.8\r\nbb,4.2\r\naa,4.9\r\nii,5.0\r\n";
				testPassed = false;
				baos.reset();
				
				//Create and initialize database
				FileReader fileReader = new FileReader(input[1]);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            int doubleDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
	            MovieDatabaseInstance = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(doubleDatabaseCapacity, "double");
	            
	            String line;
				Field field = MovieDatabase.getDeclaredField("movies");					
				Object MovieArray[] = (Object[])field.get(MovieDatabaseInstance);
				for(int i = 0; i < doubleDatabaseCapacity; i++){
					line = bufferedReader.readLine();
					String movie[] = line.split(",");
					

					MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

				}
				field.set(MovieDatabaseInstance, MovieArray);
	            bufferedReader.close();
	            
	            //Get methods
	            Method quickSort = MovieDatabase.getMethod("quicksort");
				Method print = MovieDatabase.getMethod("print");				    
				
				try {
					quickSort.invoke(MovieDatabaseInstance);
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
					for(int i = 0; i < 2; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}		
				}
				if(!testPassed){
					score -= 0.2;
					message += "Quick sort doesn't work in case of pre-sorted in ascending order.\n";
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
				expectedOutput[0] = "ii,5.0\r\naa,4.9\r\nbb,4.2\r\ncc,3.8\r\nee,3.2\r\nff,2.5\r\ndd,1.6\r\ngg,1.5\r\nhh,0.9\r\njj,0.0\r\n";
				expectedOutput[1] = "jj,0.0\r\nhh,0.9\r\ngg,1.5\r\ndd,1.6\r\nff,2.5\r\nee,3.2\r\ncc,3.8\r\nbb,4.2\r\naa,4.9\r\nii,5.0\r\n";
				
				testPassed = false;
				baos.reset();
				
				//Create and initialize database
				FileReader fileReader = new FileReader(input[2]);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            int doubleDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
	            MovieDatabaseInstance = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(doubleDatabaseCapacity, "double");
	            
	            String line;
				Field field = MovieDatabase.getDeclaredField("movies");					
				Object MovieArray[] = (Object[])field.get(MovieDatabaseInstance);
				for(int i = 0; i < doubleDatabaseCapacity; i++){
					line = bufferedReader.readLine();
					String movie[] = line.split(",");
					

					MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

				}
				field.set(MovieDatabaseInstance, MovieArray);
	            bufferedReader.close();
	            
	            //Get methods
	            Method quickSort = MovieDatabase.getMethod("quicksort");
				Method print = MovieDatabase.getMethod("print");				    
				
				try {
					quickSort.invoke(MovieDatabaseInstance);
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
					for(int i = 0; i < 2; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
				if(!testPassed){
					score -= 0.2;
					message += "Quick sort doesn't work in case of pre-sorted in descending order.\n";
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
				expectedOutput[0] = "aa,3.9\r\n";
				testPassed = false;
				baos.reset();
				
				//Create and initialize database
				FileReader fileReader = new FileReader(input[3]);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            int doubleDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
	            MovieDatabaseInstance = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(doubleDatabaseCapacity, "double");
	            
	            String line;
				Field field = MovieDatabase.getDeclaredField("movies");					
				Object MovieArray[] = (Object[])field.get(MovieDatabaseInstance);
				for(int i = 0; i < doubleDatabaseCapacity; i++){
					line = bufferedReader.readLine();
					String movie[] = line.split(",");
					

					MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

				}
				field.set(MovieDatabaseInstance, MovieArray);
	            bufferedReader.close();
	            
	            //Get methods
	            Method quickSort = MovieDatabase.getMethod("quicksort");
				Method print = MovieDatabase.getMethod("print");				    
				
				try {
					quickSort.invoke(MovieDatabaseInstance);
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
					message += "Quick sort doesn't work in case of size one array.\n";
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
	            int doubleDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
	            MovieDatabaseInstance = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(doubleDatabaseCapacity, "double");
	            
	            String line;
				Field field = MovieDatabase.getDeclaredField("movies");					
				Object MovieArray[] = (Object[])field.get(MovieDatabaseInstance);
				for(int i = 0; i < doubleDatabaseCapacity; i++){
					line = bufferedReader.readLine();
					String movie[] = line.split(",");
					

					MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

				}
				field.set(MovieDatabaseInstance, MovieArray);
	            bufferedReader.close();
	            
	            //Get methods
	            Method quickSort = MovieDatabase.getMethod("quicksort");
				Method print = MovieDatabase.getMethod("print");				    
				
				try {
					quickSort.invoke(MovieDatabaseInstance);
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
					message += "Quick sort doesn't work in case of empty array.\n";
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
