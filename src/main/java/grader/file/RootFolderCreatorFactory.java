package grader.file;


public class RootFolderCreatorFactory {
	static RootFolderCreator singleton;
	public static RootFolderCreator getSingleton() {
		if (singleton == null) {
			singleton = new ARootFolderCreator();			
		}
		return singleton;
	}

}
