package grader.interaction_logger.manual_grading_stats;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class FileTimeSorterAndComparator implements Comparator<File>{

	@Override
		public int compare(File f1, File f2) {
	        return Long.compare(f1.lastModified(), f2.lastModified());
	    }
	
	public static File[] sort (File aFolder) {
		File[] files = aFolder.listFiles();

		Arrays.sort(files, new FileTimeSorterAndComparator());
		return files;
		    
		
	}
	
	

}
