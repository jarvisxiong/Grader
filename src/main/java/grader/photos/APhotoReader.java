package grader.photos;

import grader.sakai.project.SakaiProjectDatabase;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class APhotoReader implements PhotoReader{
	SakaiProjectDatabase projectDatabase;
	
	public  static final String PHOTO_FOLDER = "photos";
	public  static final String NO_PHOTO_FILE_NAME = "nophoto.jpg";
	static String[] pictureSuffixes =  {".jpg", ".gif", ".png"};
	public static final String NO_PHOTO_TITLE = "No Photo"; // when the no photo file is not found
	public static final double PHOTO_HEIGHT = 100;

	String photosFolderFullName;
	ImageIcon noPhotoIcon;
	Image noPhotoOriginalImage;
	Image noPhotoImage;

	public APhotoReader (SakaiProjectDatabase aProjectDatabase) {
		projectDatabase = aProjectDatabase;
		photosFolderFullName = aProjectDatabase.getAssignmentsDataFolderName() + "/" + PHOTO_FOLDER  + "/";
		String noPhotoFileName = photosFolderFullName + NO_PHOTO_FILE_NAME;
		File noPhotoFile = new File (noPhotoFileName);
		if (noPhotoFile.exists()) {
			noPhotoOriginalImage = Toolkit.getDefaultToolkit().getImage(photosFolderFullName + NO_PHOTO_FILE_NAME);
			noPhotoImage = scaledImage(noPhotoOriginalImage);	
			noPhotoIcon = new ImageIcon( noPhotoImage);			
		}

		
	}
	public Image scaledImage (Image original) {
		if (original == null) return null;
//		int originalWidth = original.getWidth(null);
//		int originalHeight = original.getHeight(null);
//		double aspectRatio = originalWidth/originalHeight;
//		int newWidth = (int) (originalWidth*aspectRatio);
		return original.getScaledInstance(-1, (int) PHOTO_HEIGHT, Image.SCALE_DEFAULT);
	}
	public Icon getIcon (String onyen) {
		String aFilePrefix = photosFolderFullName + onyen;
		File pictureFile = findImageFileWithPrefix(aFilePrefix);
		if (pictureFile == null)
			return noPhotoIcon;
		Image image = Toolkit.getDefaultToolkit().getImage(pictureFile.getAbsolutePath());
		return  new ImageIcon(scaledImage(image));	
	}
	@Override
	public Icon getNoPhotoIcon() {
		return noPhotoIcon;
	}
	
	public static File findImageFileWithPrefix(String aFilePrefix) {
		for (String suffix:pictureSuffixes) {
			File file = new File(aFilePrefix + suffix);
			if (file.exists()) return file;
			
		}
		return null;
	}

}
