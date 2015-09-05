package grader.util;

public class GraderCommon {
	public static String[] docSuffixes = {".doc", ".docx", ".pdf", ".ppt", ".pptx", ".txt", ".png", ".jpg", ".zip", ".gif", ".tiff", ".bmp"};
	public static boolean isDocumentName(String aName) {
		for (String suffix:docSuffixes) {
			if (aName.endsWith(suffix))
				return true;
		}
		return false;
		
	}

}
