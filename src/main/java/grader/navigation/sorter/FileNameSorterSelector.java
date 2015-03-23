package grader.navigation.sorter;

import java.util.Comparator;


public class FileNameSorterSelector {
	static Comparator<String> sorter = new AnAlphabeticFileNameSorter();

	public static Comparator<String> getSorter() {
		return sorter;
	}

	public static void setSorter(Comparator<String> sorter) {
		FileNameSorterSelector.sorter = sorter;
	}

}
