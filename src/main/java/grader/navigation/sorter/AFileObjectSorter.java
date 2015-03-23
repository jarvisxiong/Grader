package grader.navigation.sorter;

import java.io.File;
import java.util.Comparator;

public class AFileObjectSorter implements Comparator{
	Comparator<String> fileNameSorter;
	public AFileObjectSorter(Comparator<String> aFileNameSorter) {
		fileNameSorter = aFileNameSorter;
	}

	@Override
	public int compare(Object o1, Object o2) {
		if (!(o1 instanceof File && o2 instanceof File)) {
			throw new RuntimeException("Invalid Type.  Must be of type File.");
		}
		File f1 = (File) o1;
		File f2 = (File) o2;
		if (!f1.isDirectory() || !f2.isDirectory()) {
			return f1.getName().compareTo(f2.getName());
		}
		return fileNameSorter.compare(f1.getName(), f2.getName());
		
	}
	

}
