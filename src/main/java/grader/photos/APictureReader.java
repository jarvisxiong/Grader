package grader.photos;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import grader.sakai.project.SakaiProjectDatabase;

public class APictureReader implements PictureReader{
	SakaiProjectDatabase projectDatabase;
	public  static final String PHOTO_PREFIX = "photos";
	static String[] pictureSuffixes =  {".jpg", ".gif", "png"};
	String photosFolderName;
	public APictureReader (SakaiProjectDatabase aProjectDatabase) {
		projectDatabase = aProjectDatabase;
		photosFolderName = aProjectDatabase.getAssigmentDataFolder().getAbsoluteName() + "/" + photosFolderName + "/";
	}
	public Icon getIcon (String onyen) {
		String aFilePrefix = photosFolderName + onyen;
		File pictureFile = findImageFileWithPrefix(aFilePrefix);
		if (pictureFile == null) return null;
		return new ImageIcon(pictureFile.getAbsolutePath());		
	}
	
	public static File findImageFileWithPrefix(String aFilePrefix) {
		for (String suffix:pictureSuffixes) {
			File file = new File(aFilePrefix + suffix);
			if (file.exists()) return file;
			
		}
		return null;
	}

}
