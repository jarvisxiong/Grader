package grader.navigation.sorter;

import java.util.Comparator;

public class AnAlphabeticFileNameSorter implements Comparator<String> {
	public int compare(String s1, String s2) {
		String onyen1 = s1.substring(s1.lastIndexOf('(') + 1,
				s1.lastIndexOf(')'));
		String onyen2 = s2.substring(s2.lastIndexOf('(') + 1,
				s2.lastIndexOf(')'));
		return onyen1.compareTo(onyen2);
	}

}
