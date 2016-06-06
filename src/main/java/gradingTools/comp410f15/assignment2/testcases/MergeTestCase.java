package gradingTools.comp410f15.assignment2.testcases;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
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

public class MergeTestCase extends BasicTestCase {

	public MergeTestCase() {
		super("Merge Test Case");
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		try {
			ClassesManager classesManager = project.getClassesManager().get();
			String MovieSorterClassName = "src.MovieSorter";
			String MovieDatabaseClassName = "src.MovieDatabase";
			String MovieClassName = "src.Movie";
			
			// Initialize score and feedback message
			double score = 1;
			String message = "";
			
			// Redirect output to baos
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    System.setOut(new PrintStream(baos));
		    
		    //Create output fields
		    String[] expectedOutput = new String[96];
			boolean isIncreasing = false;
			boolean testPassed;
			String output;
			
		 	//Get MovieDatabase Class
			List<ClassDescription> MovieDatabaseDescription = classesManager.findByClassName(MovieDatabaseClassName);
			Class<String> MovieDatabase = (Class<String>) MovieDatabaseDescription.get(0).getJavaClass();
			
			//Get Movie Class
			List<ClassDescription> MovieDescription = classesManager.findByClassName(MovieClassName);
			Class<String> Movie = (Class<String>) MovieDescription.get(0).getJavaClass();
			
			//Get MovieSorter Class
			List<ClassDescription> MovieSorterDescription = classesManager.findByClassName(MovieSorterClassName);
			Class<String> MovieSorter = (Class<String>) MovieSorterDescription.get(0).getJavaClass();
			Object MovieSorterInstance = null;
			
		    //Create int input
		    String intInput[] = new String[8];
		    intInput[0] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\intTenElementsAscending.csv";
		    intInput[1] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\intTenElementsDescending.csv";
		    intInput[2] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\intOneElement.csv";
		    intInput[3] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\intEmpty.csv";
		    intInput[4] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\intLowRatingAscending.csv";
		    intInput[5] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\intLowRatingDescending.csv";
		    intInput[6] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\intHighRatingAscending.csv";
		    intInput[7] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\intHighRatingDescending.csv";
		    
		    Object intDatabaseAscending, intDatabaseDescending, intDatabaseOneElement, intDatabaseEmpty;
		    Object intDatabaseLowRatingAscending, intDatabaseLowRatingDescending;
		    Object intDatabaseHighRatingAscending, intDatabaseHighRatingDescending;
		    
		    FileReader fileReader;
		    BufferedReader bufferedReader;
		    
		    int intDatabaseCapacity, doubleDatabaseCapacity;
		    String line;
		    Field field = MovieDatabase.getDeclaredField("movies");
		    Object MovieArray[];
		    
		    //Ascending intDatabase
            fileReader = new FileReader(intInput[0]);
            bufferedReader = new BufferedReader(fileReader);
            intDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
            intDatabaseAscending = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(intDatabaseCapacity, "int");
            
            MovieArray = (Object[])field.get(intDatabaseAscending);
			for(int i = 0; i < intDatabaseCapacity; i++){
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				

				MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

			}
			field.set(intDatabaseAscending, MovieArray);
            bufferedReader.close();
		    
		    //Descending intDatabase
            fileReader = new FileReader(intInput[1]);
            bufferedReader = new BufferedReader(fileReader);
            intDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
            intDatabaseDescending = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(intDatabaseCapacity, "int");
            

			MovieArray = (Object[])field.get(intDatabaseDescending);
			for(int i = 0; i < intDatabaseCapacity; i++){
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				

				MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

			}
			field.set(intDatabaseDescending, MovieArray);
            bufferedReader.close();
		    
		    
		    //One element intDatabase
            fileReader = new FileReader(intInput[2]);
            bufferedReader = new BufferedReader(fileReader);
            intDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
            intDatabaseOneElement = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(intDatabaseCapacity, "int");
            
            MovieArray = (Object[])field.get(intDatabaseOneElement);
			for(int i = 0; i < intDatabaseCapacity; i++){
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				

				MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

			}
			field.set(intDatabaseOneElement, MovieArray);
            bufferedReader.close();
		    
		    //Empty intDatabase
		    fileReader = new FileReader(intInput[3]);
	        bufferedReader = new BufferedReader(fileReader);
	        intDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
            intDatabaseEmpty = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(intDatabaseCapacity, "int");
            
			MovieArray = (Object[])field.get(intDatabaseEmpty);
			for(int i = 0; i < intDatabaseCapacity; i++){
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				

				MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

			}
			field.set(intDatabaseEmpty, MovieArray);
            bufferedReader.close();
		   	    
		    //intDatabase having ratings lower than 3 in Ascending order
            fileReader = new FileReader(intInput[4]);
            bufferedReader = new BufferedReader(fileReader);
            intDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
            intDatabaseLowRatingAscending = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(intDatabaseCapacity, "int");
            
			MovieArray = (Object[])field.get(intDatabaseLowRatingAscending);
			for(int i = 0; i < intDatabaseCapacity; i++){
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				

				MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

			}
			field.set(intDatabaseLowRatingAscending, MovieArray);
            bufferedReader.close();
	            
		    //intDatabase having ratings lower than 3 in Descending order
	        fileReader = new FileReader(intInput[5]);
            bufferedReader = new BufferedReader(fileReader);
            intDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
            intDatabaseLowRatingDescending = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(intDatabaseCapacity, "int");
            
            MovieArray = (Object[])field.get(intDatabaseLowRatingDescending);
			for(int i = 0; i < intDatabaseCapacity; i++){
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				

				MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

			}
			field.set(intDatabaseLowRatingDescending, MovieArray);
            bufferedReader.close();
		    
            //intDatabase having ratings higher than 3 in Ascending order
            fileReader = new FileReader(intInput[6]);
            bufferedReader = new BufferedReader(fileReader);
            intDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
            intDatabaseHighRatingAscending = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(intDatabaseCapacity, "int");
            
            MovieArray = (Object[])field.get(intDatabaseHighRatingAscending);
			for(int i = 0; i < intDatabaseCapacity; i++){
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				

				MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

			}
			field.set(intDatabaseHighRatingAscending, MovieArray);
            bufferedReader.close();
		    
            //intDatabase having ratings higher than 3 in Descending order
            fileReader = new FileReader(intInput[7]);
            bufferedReader = new BufferedReader(fileReader);
            intDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
            intDatabaseHighRatingDescending = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(intDatabaseCapacity, "int");
            
            MovieArray = (Object[])field.get(intDatabaseHighRatingDescending);
			for(int i = 0; i < intDatabaseCapacity; i++){
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				

				MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

			}
			field.set(intDatabaseHighRatingDescending, MovieArray);
            bufferedReader.close();
		    
            //Create double input
		    String doubleInput[] = new String[8];
		    doubleInput[0] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\doubleTenElementsAscending.csv";
		    doubleInput[1] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\doubleTenElementsDescending.csv";
		    doubleInput[2] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\doubleOneElement.csv";
		    doubleInput[3] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\doubleEmpty.csv";
		    doubleInput[4] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\doubleLowRatingAscending.csv";
		    doubleInput[5] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\doubleLowRatingDescending.csv";
		    doubleInput[6] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\doubleHighRatingAscending.csv";
		    doubleInput[7] = "C:\\Users\\tanmay\\Downloads\\TAWork\\TestCases\\doubleHighRatingDescending.csv";
		    
		    Object doubleDatabaseAscending, doubleDatabaseDescending, doubleDatabaseOneElement, doubleDatabaseEmpty;
		    Object doubleDatabaseLowRatingAscending, doubleDatabaseLowRatingDescending;
		    Object doubleDatabaseHighRatingAscending, doubleDatabaseHighRatingDescending;
		    //Ascending doubleDatabase
		    fileReader = new FileReader(doubleInput[0]);
            bufferedReader = new BufferedReader(fileReader);
            doubleDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
            doubleDatabaseAscending = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(doubleDatabaseCapacity, "double");
            
            MovieArray = (Object[])field.get(doubleDatabaseAscending);
			for(int i = 0; i < doubleDatabaseCapacity; i++){
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				

				MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

			}
			field.set(doubleDatabaseAscending, MovieArray);
            bufferedReader.close();
	        
		    //Descending doubleDatabase
            fileReader = new FileReader(doubleInput[1]);
            bufferedReader = new BufferedReader(fileReader);
            doubleDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
            doubleDatabaseDescending = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(doubleDatabaseCapacity, "double");
            
            MovieArray = (Object[])field.get(doubleDatabaseDescending);
			for(int i = 0; i < doubleDatabaseCapacity; i++){
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				

				MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

			}
			field.set(doubleDatabaseDescending, MovieArray);
            bufferedReader.close();
		    
		    //One element doubleDatabase
		    fileReader = new FileReader(doubleInput[2]);
            bufferedReader = new BufferedReader(fileReader);
            doubleDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
            doubleDatabaseOneElement = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(doubleDatabaseCapacity, "double");
            
            MovieArray = (Object[])field.get(doubleDatabaseOneElement);
			for(int i = 0; i < doubleDatabaseCapacity; i++){
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				

				MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

			}
			field.set(doubleDatabaseOneElement, MovieArray);
            bufferedReader.close();
		    
		    //Empty doubleDatabase
		    fileReader = new FileReader(doubleInput[3]);
            bufferedReader = new BufferedReader(fileReader);
            doubleDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
            doubleDatabaseEmpty = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(doubleDatabaseCapacity, "double");
            
            MovieArray = (Object[])field.get(doubleDatabaseEmpty);
			for(int i = 0; i < doubleDatabaseCapacity; i++){
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				

				MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

			}
			field.set(doubleDatabaseEmpty, MovieArray);
            bufferedReader.close();
		    
		    //doubleDatabase having ratings lower than 3 in Ascending order
		    fileReader = new FileReader(doubleInput[4]);
            bufferedReader = new BufferedReader(fileReader);
            doubleDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
            doubleDatabaseLowRatingAscending = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(doubleDatabaseCapacity, "double");
            
            MovieArray = (Object[])field.get(doubleDatabaseLowRatingAscending);
			for(int i = 0; i < doubleDatabaseCapacity; i++){
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				

				MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

			}
			field.set(doubleDatabaseLowRatingAscending, MovieArray);
            bufferedReader.close();
		    
		    //doubleDatabase having ratings lower than 3 in Descending order
		    fileReader = new FileReader(doubleInput[5]);
            bufferedReader = new BufferedReader(fileReader);
            doubleDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
            doubleDatabaseLowRatingDescending = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(doubleDatabaseCapacity, "double");
            
            MovieArray	 = (Object[])field.get(doubleDatabaseLowRatingDescending);
			for(int i = 0; i < doubleDatabaseCapacity; i++){
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				

				MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

			}
			field.set(doubleDatabaseLowRatingDescending, MovieArray);
            bufferedReader.close();
		    
            //doubleDatabase having ratings higher than 3 in Ascending order
            fileReader = new FileReader(doubleInput[6]);
            bufferedReader = new BufferedReader(fileReader);
            doubleDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
            doubleDatabaseHighRatingAscending = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(doubleDatabaseCapacity, "double");
            
            MovieArray = (Object[])field.get(doubleDatabaseHighRatingAscending);
			for(int i = 0; i < doubleDatabaseCapacity; i++){
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				

				MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

			}
			field.set(doubleDatabaseHighRatingAscending, MovieArray);
            bufferedReader.close();
		    
		    //doubleDatabase having ratings higher than 3 in Descending order
		    fileReader = new FileReader(doubleInput[7]);
            bufferedReader = new BufferedReader(fileReader);
            doubleDatabaseCapacity = Integer.parseInt(bufferedReader.readLine());
            doubleDatabaseHighRatingDescending = MovieDatabase.getDeclaredConstructor(int.class, String.class).newInstance(doubleDatabaseCapacity, "double");
            
            MovieArray = (Object[])field.get(doubleDatabaseHighRatingDescending);
			for(int i = 0; i < doubleDatabaseCapacity; i++){
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				

				MovieArray[i] = (Object) Movie.getDeclaredConstructor(String.class, String.class).newInstance(movie[0], movie[1]); 

			}
			field.set(doubleDatabaseHighRatingDescending, MovieArray);
            bufferedReader.close();
		    
		    //********* TEST CASES START **********//
		    Field fieldCapacity = MovieDatabase.getDeclaredField("capacity");
		    fieldCapacity.setAccessible(true);
		    Field fieldSortedDatabase = MovieSorter.getField("sortedDatabase");
		    final Object[] args = new Object[2];
		    
			//Get methods
		    Method merge = null;
		    for (Method method : MovieSorter.getDeclaredMethods()) {
				if(method.getName().equals("merge")){
					merge = method;
				}
		    }
			Method print = MovieDatabase.getMethod("print");
		    
		    /**** Test case 1 
		     * Both arrays contain 10 elements each *
		     *****/
		    
		    intDatabaseCapacity = fieldCapacity.getInt(intDatabaseAscending);
			doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseAscending);
			
			/**BOTH DESCENDING**/
			//Create and initialize MovieSorter
			MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
			
            //Create input 
			args[0] = intDatabaseDescending;
		    args[1] = doubleDatabaseDescending;			
		    
		    //Create output
			expectedOutput[0] = "ii,5.0\r\na,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[1] = "ii,5.0\r\nb,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[2] = "a,5.0\r\nii,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[3] = "a,5.0\r\nb,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[4] = "b,5.0\r\nii,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[5] = "b,5.0\r\na,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[6] = "ii,5.0\r\na,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[7] = "ii,5.0\r\nb,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[8] = "a,5.0\r\nii,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[9] = "a,5.0\r\nb,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[10] = "b,5.0\r\nii,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[11] = "b,5.0\r\na,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			
			expectedOutput[12] = "ii,5.0\r\na,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[13] = "ii,5.0\r\nb,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[14] = "a,5.0\r\nii,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[15] = "a,5.0\r\nb,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[16] = "b,5.0\r\nii,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[17] = "b,5.0\r\na,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[18] = "ii,5.0\r\na,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[19] = "ii,5.0\r\nb,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[20] = "a,5.0\r\nii,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[21] = "a,5.0\r\nb,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[22] = "b,5.0\r\nii,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[23] = "b,5.0\r\na,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[24] = "ii,5.0\r\na,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[25] = "ii,5.0\r\nb,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[26] = "a,5.0\r\nii,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[27] = "a,5.0\r\nb,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[28] = "b,5.0\r\nii,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[29] = "b,5.0\r\na,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[30] = "ii,5.0\r\na,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[31] = "ii,5.0\r\nb,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[32] = "a,5.0\r\nii,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[33] = "a,5.0\r\nb,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[34] = "b,5.0\r\nii,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[35] = "b,5.0\r\na,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[36] = "ii,5.0\r\na,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[37] = "ii,5.0\r\nb,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[38] = "a,5.0\r\nii,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[39] = "a,5.0\r\nb,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[40] = "b,5.0\r\nii,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[41] = "b,5.0\r\na,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[42] = "ii,5.0\r\na,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[43] = "ii,5.0\r\nb,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[44] = "a,5.0\r\nii,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[45] = "a,5.0\r\nb,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";			
			expectedOutput[46] = "b,5.0\r\nii,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			expectedOutput[47] = "b,5.0\r\na,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\njj,0.0\r\nj,0.0\r\n";
			
			expectedOutput[48] = "ii,5.0\r\na,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[49] = "ii,5.0\r\nb,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[50] = "a,5.0\r\nii,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[51] = "a,5.0\r\nb,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[52] = "b,5.0\r\nii,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[53] = "b,5.0\r\na,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[54] = "ii,5.0\r\na,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[55] = "ii,5.0\r\nb,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[56] = "a,5.0\r\nii,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[57] = "a,5.0\r\nb,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[58] = "b,5.0\r\nii,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[59] = "b,5.0\r\na,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[60] = "ii,5.0\r\na,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[61] = "ii,5.0\r\nb,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[62] = "a,5.0\r\nii,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[63] = "a,5.0\r\nb,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[64] = "b,5.0\r\nii,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[65] = "b,5.0\r\na,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[66] = "ii,5.0\r\na,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[67] = "ii,5.0\r\nb,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[68] = "a,5.0\r\nii,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[69] = "a,5.0\r\nb,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[70] = "b,5.0\r\nii,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[71] = "b,5.0\r\na,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nf,3.0\r\nd,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[72] = "ii,5.0\r\na,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[73] = "ii,5.0\r\nb,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[74] = "a,5.0\r\nii,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[75] = "a,5.0\r\nb,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[76] = "b,5.0\r\nii,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[77] = "b,5.0\r\na,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[78] = "ii,5.0\r\na,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[79] = "ii,5.0\r\nb,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[80] = "a,5.0\r\nii,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[81] = "a,5.0\r\nb,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[82] = "b,5.0\r\nii,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[83] = "b,5.0\r\na,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\nc,4.0\r\ne,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[84] = "ii,5.0\r\na,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[85] = "ii,5.0\r\nb,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[86] = "a,5.0\r\nii,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[87] = "a,5.0\r\nb,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[88] = "b,5.0\r\nii,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[89] = "b,5.0\r\na,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\ng,2.0\r\nh,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[90] = "ii,5.0\r\na,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[91] = "ii,5.0\r\nb,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[92] = "a,5.0\r\nii,5.0\r\nb,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[93] = "a,5.0\r\nb,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";			
			expectedOutput[94] = "b,5.0\r\nii,5.0\r\na,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			expectedOutput[95] = "b,5.0\r\na,5.0\r\nii,5.0\r\naa,4.9\r\nbb,4.2\r\ne,4.0\r\nc,4.0\r\ncc,3.8\r\nee,3.2\r\nd,3.0\r\nf,3.0\r\nff,2.5\r\nh,2.0\r\ng,2.0\r\ndd,1.6\r\ngg,1.5\r\ni,1.0\r\nhh,0.9\r\nj,0.0\r\njj,0.0\r\n";
			
			testPassed = false;
			baos.reset();
			
			try {
		    	merge.invoke(MovieSorterInstance, args);
				Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
				print.invoke(sortedDatabase);				
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
				for(int i = 0; i < 96; i++){
					if(output.equals(expectedOutput[i])){
						testPassed = true;
						break;
					}
				}
			}
			
			/**BOTH ASCENDING**/
			if(!testPassed){
				//Create and initialize MovieSorter
				MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
	            	            
				//Create input
				args[0] = intDatabaseAscending;
			    args[1] = doubleDatabaseAscending;			
			    
			    //Create output
				testPassed = false;
				baos.reset();
				
				try {
			    	merge.invoke(MovieSorterInstance, args);
					Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
					print.invoke(sortedDatabase);				
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
					for(int i = 0; i < 96; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
			}
			
			/**int ASCENDING**/
			if(!testPassed){
				//Create and initialize MovieSorter
				MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
				
				//Create input
				args[0] = intDatabaseAscending;
			    args[1] = doubleDatabaseDescending;			
			    
			    //Create output
				testPassed = false;
				baos.reset();
				
				try {
			    	merge.invoke(MovieSorterInstance, args);
					Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
					print.invoke(sortedDatabase);				
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
					for(int i = 0; i < 96; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
			}
			
			/**double ASCENDING**/
			if(!testPassed){
				//Create and initialize MovieSorter
				MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
				
				//Create input
				args[0] = intDatabaseDescending;
			    args[1] = doubleDatabaseAscending;			
			    
			    //Create output
				testPassed = false;
				baos.reset();
				
				try {
			    	merge.invoke(MovieSorterInstance, args);
					Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
					print.invoke(sortedDatabase);				
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
					for(int i = 0; i < 96; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
			}
			
			if(!testPassed){
				score -= 0.2;
				message += "Merge doesn't work in the basic case.\n";
			}
		    
			/**** Test case 2 
		     * Int database contains 1 element and double database contains 10 elements *
		     *****/
				
			/**Double DESCENDING**/
			//Create and initialize MovieSorter
			intDatabaseCapacity = fieldCapacity.getInt(intDatabaseOneElement);
			doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseDescending);
			MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
			
			//Create input
			args[0] = intDatabaseOneElement;
		    args[1] = doubleDatabaseDescending;			
		    
		    //Create output
		    expectedOutput[0] = "ii,5.0\r\naa,4.9\r\nbb,4.2\r\ncc,3.8\r\nee,3.2\r\nff,2.5\r\ndd,1.6\r\ngg,1.5\r\na,1.0\r\nhh,0.9\r\njj,0.0\r\n";
			
			testPassed = false;
			baos.reset();
			
			try {
		    	merge.invoke(MovieSorterInstance, args);
				Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
				print.invoke(sortedDatabase);				
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
				for(int i = 0; i < 1; i++){
					if(output.equals(expectedOutput[i])){
						testPassed = true;
						break;
					}
				}
			}
			
			/**Double ASCENDING**/
			if(!testPassed){
				//Create and initialize MovieSorter
				intDatabaseCapacity = fieldCapacity.getInt(intDatabaseOneElement);
				doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseAscending);
				MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
				
				//Create input
				args[0] = intDatabaseOneElement;
			    args[1] = doubleDatabaseAscending;			
			    
			    //Create output
				testPassed = false;
				baos.reset();
				
				try {
			    	merge.invoke(MovieSorterInstance, args);
					Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
					print.invoke(sortedDatabase);				
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
					for(int i = 0; i < 1; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
			}
			if(!testPassed){
				score -= 0.1;
				message += "Merge doesn't work if int database has only one element.\n";
			}
			
			/**** Test case 3
			 * Double database contains 1 element and int database contains 10 elements
			 */
			/**Int DESCENDING**/
			//Create and initialize MovieSorter
			intDatabaseCapacity = fieldCapacity.getInt(intDatabaseDescending);
			doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseOneElement);
			MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
			
			//Create input
			args[0] = intDatabaseDescending;
		    args[1] = doubleDatabaseOneElement;			
		    
		    //Create output
		    expectedOutput[0] = "a,5.0\r\nb,5.0\r\nc,4.0\r\ne,4.0\r\naa,3.9\r\nf,3.0\r\nd,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";
			expectedOutput[1] = "b,5.0\r\na,5.0\r\nc,4.0\r\ne,4.0\r\naa,3.9\r\nf,3.0\r\nd,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";			
			expectedOutput[2] = "a,5.0\r\nb,5.0\r\ne,4.0\r\nc,4.0\r\naa,3.9\r\nf,3.0\r\nd,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";
			expectedOutput[3] = "b,5.0\r\na,5.0\r\ne,4.0\r\nc,4.0\r\naa,3.9\r\nf,3.0\r\nd,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";			
			expectedOutput[4] = "a,5.0\r\nb,5.0\r\nc,4.0\r\ne,4.0\r\naa,3.9\r\nd,3.0\r\nf,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";
			expectedOutput[5] = "b,5.0\r\na,5.0\r\nc,4.0\r\ne,4.0\r\naa,3.9\r\nd,3.0\r\nf,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";				
			expectedOutput[6] = "a,5.0\r\nb,5.0\r\ne,4.0\r\nc,4.0\r\naa,3.9\r\nd,3.0\r\nf,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";
			expectedOutput[7] = "b,5.0\r\na,5.0\r\ne,4.0\r\nc,4.0\r\naa,3.9\r\nd,3.0\r\nf,3.0\r\ng,2.0\r\nh,2.0\r\ni,1.0\r\nj,0.0\r\n";			
			expectedOutput[8] = "a,5.0\r\nb,5.0\r\nc,4.0\r\ne,4.0\r\naa,3.9\r\nf,3.0\r\nd,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";
			expectedOutput[9] = "b,5.0\r\na,5.0\r\nc,4.0\r\ne,4.0\r\naa,3.9\r\nf,3.0\r\nd,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";				
			expectedOutput[10] = "a,5.0\r\nb,5.0\r\ne,4.0\r\nc,4.0\r\naa,3.9\r\nf,3.0\r\nd,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";
			expectedOutput[11] = "b,5.0\r\na,5.0\r\ne,4.0\r\nc,4.0\r\naa,3.9\r\nf,3.0\r\nd,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";				
			expectedOutput[12] = "a,5.0\r\nb,5.0\r\nc,4.0\r\ne,4.0\r\naa,3.9\r\nd,3.0\r\nf,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";
			expectedOutput[13] = "b,5.0\r\na,5.0\r\nc,4.0\r\ne,4.0\r\naa,3.9\r\nd,3.0\r\nf,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";				
			expectedOutput[14] = "a,5.0\r\nb,5.0\r\ne,4.0\r\nc,4.0\r\naa,3.9\r\nd,3.0\r\nf,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";
			expectedOutput[15] = "b,5.0\r\na,5.0\r\ne,4.0\r\nc,4.0\r\naa,3.9\r\nd,3.0\r\nf,3.0\r\nh,2.0\r\ng,2.0\r\ni,1.0\r\nj,0.0\r\n";
			
			
			testPassed = false;
			baos.reset();
			
			try {
		    	merge.invoke(MovieSorterInstance, args);
				Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
				print.invoke(sortedDatabase);				
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
				for(int i = 0; i < 16; i++){
					if(output.equals(expectedOutput[i])){
						testPassed = true;
						break;
					}
				}
			}
			
			/**Int ASCENDING**/
			if(!testPassed){
				//Create and initialize MovieSorter
				intDatabaseCapacity = fieldCapacity.getInt(intDatabaseAscending);
				doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseOneElement);
				MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
				
				//Create input
				args[0] = intDatabaseAscending;
			    args[1] = doubleDatabaseOneElement;			
			    
			    //Create output
				testPassed = false;
				baos.reset();
				
				try {
			    	merge.invoke(MovieSorterInstance, args);
					Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
					print.invoke(sortedDatabase);				
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
					for(int i = 0; i < 16; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
			}
			if(!testPassed){
				score -= 0.1;
				message += "Merge doesn't work if int database has only one element.\n";
			}
				
			/**** Test case 4 
		     * Int Database is empty *
		     *****/
			/**Double DESCENDING**/
			//Create and initialize MovieSorter
			intDatabaseCapacity = fieldCapacity.getInt(intDatabaseEmpty);
			doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseDescending);
			MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
			
			//Create input
			args[0] = intDatabaseEmpty;
		    args[1] = doubleDatabaseDescending;			
		    
		    //Create output
		    expectedOutput[0] = "ii,5.0\r\naa,4.9\r\nbb,4.2\r\ncc,3.8\r\nee,3.2\r\nff,2.5\r\ndd,1.6\r\ngg,1.5\r\nhh,0.9\r\njj,0.0\r\n";
			
			testPassed = false;
			baos.reset();
			
			try {
		    	merge.invoke(MovieSorterInstance, args);
				Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
				print.invoke(sortedDatabase);				
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
				for(int i = 0; i < 1; i++){
					if(output.equals(expectedOutput[i])){
						testPassed = true;
						break;
					}
				}
			}
			
			/**Double ASCENDING**/
			if(!testPassed){
				//Create and initialize MovieSorter
				intDatabaseCapacity = fieldCapacity.getInt(intDatabaseEmpty);
				doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseAscending);
				MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
				
				//Create input
				args[0] = intDatabaseEmpty;
			    args[1] = doubleDatabaseAscending;			
			    
			    //Create output
				testPassed = false;
				baos.reset();
				
				try {
			    	merge.invoke(MovieSorterInstance, args);
					Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
					print.invoke(sortedDatabase);				
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
					for(int i = 0; i < 1; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
			}
			if(!testPassed){
				score -= 0.08;
				message += "Merge doesn't work if int datbase is empty.\n";
			}
			
			/**** Test case 5 
		     * Double Database is empty *
		     *****/
			/**Int DESCENDING**/
			//Create and initialize MovieSorter
			intDatabaseCapacity = fieldCapacity.getInt(intDatabaseDescending);
			doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseEmpty);
			MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
			
			//Create input
			args[0] = intDatabaseDescending;
		    args[1] = doubleDatabaseEmpty;			
		    
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
			
			testPassed = false;
			baos.reset();
			
			try {
		    	merge.invoke(MovieSorterInstance, args);
				Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
				print.invoke(sortedDatabase);				
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
				for(int i = 0; i < 16; i++){
					if(output.equals(expectedOutput[i])){
						testPassed = true;
						break;
					}
				}
			}
			
			/**Int ASCENDING**/
			if(!testPassed){
				//Create and initialize MovieSorter
				intDatabaseCapacity = fieldCapacity.getInt(intDatabaseAscending);
				doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseEmpty);
				MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
				
				//Create input
				args[0] = intDatabaseAscending;
			    args[1] = doubleDatabaseEmpty;			
			    
			    //Create output
				testPassed = false;
				baos.reset();
				
				try {
			    	merge.invoke(MovieSorterInstance, args);
					Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
					print.invoke(sortedDatabase);				
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
					for(int i = 0; i < 16; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
			}
			if(!testPassed){
				score -= 0.08;
				message += "Merge doesn't work if double datbase is empty.\n";
			}
			
			/**** Test case 6 
		     * int Database has low ratings *
		     *****/
			/**BOTH DESCENDING**/
			//Create and initialize MovieSorter
			intDatabaseCapacity = fieldCapacity.getInt(intDatabaseLowRatingDescending);
			doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseHighRatingDescending);
			MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
			
            //Create input 
			args[0] = intDatabaseLowRatingDescending;
		    args[1] = doubleDatabaseHighRatingDescending;			
		    
		    //Create output
			expectedOutput[0] = "ii,5.0\r\naa,4.9\r\nbb,4.2\r\ncc,3.8\r\nee,3.2\r\nf,2.0\r\nd,2.0\r\ne,1.0\r\nc,1.0\r\nb,0.0\r\na,0.0\r\n";
			expectedOutput[1] = "ii,5.0\r\naa,4.9\r\nbb,4.2\r\ncc,3.8\r\nee,3.2\r\nf,2.0\r\nd,2.0\r\ne,1.0\r\nc,1.0\r\na,0.0\r\nb,0.0\r\n";		
			expectedOutput[2] = "ii,5.0\r\naa,4.9\r\nbb,4.2\r\ncc,3.8\r\nee,3.2\r\nf,2.0\r\nd,2.0\r\nc,1.0\r\ne,1.0\r\nb,0.0\r\na,0.0\r\n";
			expectedOutput[3] = "ii,5.0\r\naa,4.9\r\nbb,4.2\r\ncc,3.8\r\nee,3.2\r\nf,2.0\r\nd,2.0\r\nc,1.0\r\ne,1.0\r\na,0.0\r\nb,0.0\r\n";		
			expectedOutput[4] = "ii,5.0\r\naa,4.9\r\nbb,4.2\r\ncc,3.8\r\nee,3.2\r\nd,2.0\r\nf,2.0\r\ne,1.0\r\nc,1.0\r\nb,0.0\r\na,0.0\r\n";
			expectedOutput[5] = "ii,5.0\r\naa,4.9\r\nbb,4.2\r\ncc,3.8\r\nee,3.2\r\nd,2.0\r\nf,2.0\r\ne,1.0\r\nc,1.0\r\na,0.0\r\nb,0.0\r\n";		
			expectedOutput[6] = "ii,5.0\r\naa,4.9\r\nbb,4.2\r\ncc,3.8\r\nee,3.2\r\nd,2.0\r\nf,2.0\r\nc,1.0\r\ne,1.0\r\nb,0.0\r\na,0.0\r\n";
			expectedOutput[7] = "ii,5.0\r\naa,4.9\r\nbb,4.2\r\ncc,3.8\r\nee,3.2\r\nd,2.0\r\nf,2.0\r\nc,1.0\r\ne,1.0\r\na,0.0\r\nb,0.0\r\n";		
			
			testPassed = false;
			baos.reset();
			
			try {
		    	merge.invoke(MovieSorterInstance, args);
				Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
				print.invoke(sortedDatabase);				
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
				for(int i = 0; i < 8; i++){
					if(output.equals(expectedOutput[i])){
						testPassed = true;
						break;
					}
				}
			}
			
			/**BOTH ASCENDING**/
			if(!testPassed){
				//Create and initialize MovieSorter
				intDatabaseCapacity = fieldCapacity.getInt(intDatabaseLowRatingAscending);
				doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseHighRatingAscending);
				MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
	            	            
				//Create input
				args[0] = intDatabaseLowRatingAscending;
			    args[1] = doubleDatabaseHighRatingAscending;			
			    
			    //Create output
				testPassed = false;
				baos.reset();
				
				try {
			    	merge.invoke(MovieSorterInstance, args);
					Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
					print.invoke(sortedDatabase);				
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
					for(int i = 0; i < 8; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
			}
			
			/**int ASCENDING**/
			if(!testPassed){
				//Create and initialize MovieSorter
				intDatabaseCapacity = fieldCapacity.getInt(intDatabaseLowRatingAscending);
				doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseHighRatingDescending);
				MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
				
				//Create input
				args[0] = intDatabaseLowRatingAscending;
			    args[1] = doubleDatabaseHighRatingDescending;			
			    
			    //Create output
				testPassed = false;
				baos.reset();
				
				try {
			    	merge.invoke(MovieSorterInstance, args);
					Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
					print.invoke(sortedDatabase);				
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
					for(int i = 0; i < 8; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
			}
			
			/**double ASCENDING**/
			if(!testPassed){
				//Create and initialize MovieSorter
				intDatabaseCapacity = fieldCapacity.getInt(intDatabaseLowRatingDescending);
				doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseHighRatingAscending);
				MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
				
				//Create input
				args[0] = intDatabaseLowRatingDescending;
			    args[1] = doubleDatabaseHighRatingAscending;			
			    
			    //Create output
				testPassed = false;
				baos.reset();
				
				try {
			    	merge.invoke(MovieSorterInstance, args);
					Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
					print.invoke(sortedDatabase);				
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
					for(int i = 0; i < 8; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
			}
			
			if(!testPassed){
				score -= 0.2;
				message += "Merge doesn't work if int database has low ratings.\n";
			}
			
			/**** Test case 7 
		     * int Database has high ratings *
		     *****/
			/**BOTH DESCENDING**/
			//Create and initialize MovieSorter
			intDatabaseCapacity = fieldCapacity.getInt(intDatabaseHighRatingDescending);
			doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseLowRatingDescending);
			MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
			
            //Create input 
			args[0] = intDatabaseHighRatingDescending;
		    args[1] = doubleDatabaseLowRatingDescending;			
		    
		    //Create output
			expectedOutput[0] = "a,5.0\r\nb,5.0\r\nc,4.0\r\ne,4.0\r\nf,3.0\r\nd,3.0\r\nee,2.5\r\ncc,2.3\r\nbb,1.8\r\naa,1.1\r\nii,0.0\r\n";
			expectedOutput[1] = "b,5.0\r\na,5.0\r\nc,4.0\r\ne,4.0\r\nf,3.0\r\nd,3.0\r\nee,2.5\r\ncc,2.3\r\nbb,1.8\r\naa,1.1\r\nii,0.0\r\n";
			expectedOutput[2] = "a,5.0\r\nb,5.0\r\ne,4.0\r\nc,4.0\r\nf,3.0\r\nd,3.0\r\nee,2.5\r\ncc,2.3\r\nbb,1.8\r\naa,1.1\r\nii,0.0\r\n";
			expectedOutput[3] = "b,5.0\r\na,5.0\r\ne,4.0\r\nc,4.0\r\nf,3.0\r\nd,3.0\r\nee,2.5\r\ncc,2.3\r\nbb,1.8\r\naa,1.1\r\nii,0.0\r\n";
			expectedOutput[4] = "a,5.0\r\nb,5.0\r\nc,4.0\r\ne,4.0\r\nd,3.0\r\nf,3.0\r\nee,2.5\r\ncc,2.3\r\nbb,1.8\r\naa,1.1\r\nii,0.0\r\n";
			expectedOutput[5] = "b,5.0\r\na,5.0\r\nc,4.0\r\ne,4.0\r\nd,3.0\r\nf,3.0\r\nee,2.5\r\ncc,2.3\r\nbb,1.8\r\naa,1.1\r\nii,0.0\r\n";
			expectedOutput[6] = "a,5.0\r\nb,5.0\r\ne,4.0\r\nc,4.0\r\nd,3.0\r\nf,3.0\r\nee,2.5\r\ncc,2.3\r\nbb,1.8\r\naa,1.1\r\nii,0.0\r\n";
			expectedOutput[7] = "b,5.0\r\na,5.0\r\ne,4.0\r\nc,4.0\r\nd,3.0\r\nf,3.0\r\nee,2.5\r\ncc,2.3\r\nbb,1.8\r\naa,1.1\r\nii,0.0\r\n";
			
			testPassed = false;
			baos.reset();
			
			try {
		    	merge.invoke(MovieSorterInstance, args);
				Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
				print.invoke(sortedDatabase);				
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
				for(int i = 0; i < 8; i++){
					if(output.equals(expectedOutput[i])){
						testPassed = true;
						break;
					}
				}
			}
			
			/**BOTH ASCENDING**/
			if(!testPassed){
				//Create and initialize MovieSorter
				intDatabaseCapacity = fieldCapacity.getInt(intDatabaseHighRatingAscending);
				doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseLowRatingAscending);
				MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
	            	            
				//Create input
				args[0] = intDatabaseHighRatingAscending;
			    args[1] = doubleDatabaseLowRatingAscending;			
			    
			    //Create output
				testPassed = false;
				baos.reset();
				
				try {
			    	merge.invoke(MovieSorterInstance, args);
					Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
					print.invoke(sortedDatabase);				
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
					for(int i = 0; i < 8; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
			}
			
			/**int ASCENDING**/
			if(!testPassed){
				//Create and initialize MovieSorter
				intDatabaseCapacity = fieldCapacity.getInt(intDatabaseHighRatingAscending);
				doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseLowRatingDescending);
				MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
				
				//Create input
				args[0] = intDatabaseHighRatingAscending;
			    args[1] = doubleDatabaseLowRatingDescending;			
			    
			    //Create output
			    testPassed = false;
				baos.reset();
				
				try {
			    	merge.invoke(MovieSorterInstance, args);
					Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
					print.invoke(sortedDatabase);				
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
					for(int i = 0; i < 8; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
			}
			
			/**double ASCENDING**/
			if(!testPassed){
				//Create and initialize MovieSorter
				intDatabaseCapacity = fieldCapacity.getInt(intDatabaseHighRatingDescending);
				doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseLowRatingAscending);
				MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
				
				//Create input
				args[0] = intDatabaseHighRatingDescending;
			    args[1] = doubleDatabaseLowRatingAscending;			
			    
			    //Create output
				testPassed = false;
				baos.reset();
				
				try {
			    	merge.invoke(MovieSorterInstance, args);
					Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
					print.invoke(sortedDatabase);				
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
					for(int i = 0; i < 8; i++){
						if(output.equals(expectedOutput[i])){
							testPassed = true;
							break;
						}
					}
				}
			}
			
			if(!testPassed){
				score -= 0.2;
				message += "Merge doesn't work if int database has high ratings.\n";
			}
			
			/**** Test case 7
		     * Both databases are empty *
		     *****/
			/**BOTH DESCENDING**/
			//Create and initialize MovieSorter
			intDatabaseCapacity = fieldCapacity.getInt(intDatabaseEmpty);
			doubleDatabaseCapacity = fieldCapacity.getInt(doubleDatabaseEmpty);
			MovieSorterInstance = MovieSorter.getDeclaredConstructor(int.class).newInstance(intDatabaseCapacity + doubleDatabaseCapacity);
			
            //Create input 
			args[0] = intDatabaseEmpty;
		    args[1] = doubleDatabaseEmpty;			
		    
		    //Create output
			expectedOutput[0] = "";
			testPassed = false;
			baos.reset();
			
			try {
		    	merge.invoke(MovieSorterInstance, args);
				Object sortedDatabase = fieldSortedDatabase.get(MovieSorterInstance);	
				print.invoke(sortedDatabase);				
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
				score -= 0.04;
				message += "Merge doesn't work when both databases are empty\n";
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
		} catch (NoSuchFieldException e3) {
			e3.printStackTrace();
		} catch (SecurityException e3) {			 
			e3.printStackTrace();
		} catch (FileNotFoundException e3) {			 
			e3.printStackTrace();
		} catch (NumberFormatException e3) {			 
			e3.printStackTrace();
		} catch (IOException e3) {			 
			e3.printStackTrace();
		} catch (InstantiationException e3) {			 
			e3.printStackTrace();
		} catch (IllegalAccessException e3) {			 
			e3.printStackTrace();
		} catch (IllegalArgumentException e3) {			 
			e3.printStackTrace();
		} catch (InvocationTargetException e3) {			 
			e3.printStackTrace();
		} catch (NoSuchMethodException e3) {
			e3.printStackTrace();
		}
		return null;
	}
}
