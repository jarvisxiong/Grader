package grader.sakai.project;

import grader.navigation.NavigationListManager;

import java.util.ArrayList;
import java.util.List;

public class AnUnsortedNavigationListCreator implements NavigationListManager {

	@Override
	public List<String> getOnyenNavigationList(
			SakaiProjectDatabase aSakaiProjectDatabase) {
		 return new ArrayList(aSakaiProjectDatabase.getOnyens());
	}

}
