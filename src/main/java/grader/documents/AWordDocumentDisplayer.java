package grader.documents;


import grader.settings.GraderSettingsManagerSelector;
import grader.trace.file.open.DefaultProgramOpenedFile;
import grader.trace.file.open.WordOpenedFile;
import util.misc.Common;

// TODO: There are x86 Windows-specific paths hard-coded here. That should probably be changed
public class AWordDocumentDisplayer implements DocumentDisplayer {
	// should be in config file
//    public static final String WORD_14 = "C:\\Program Files\\Microsoft Office\\Office14\\WINWORD";
//    public static final String WORD_12 = "C:\\Program Files\\Microsoft Office\\Office12\\WINWORD";
//    public static final int DEFAULT_WORD_NO = 14;
     String wordPath;
    int officeNo = 15;

    public AWordDocumentDisplayer() {
        setWordPath();
    }

    public AWordDocumentDisplayer(int anOfficeNo) {
        officeNo = anOfficeNo;
        setWordPath();
    }

    void setWordPath() {
//        wordPath = "C:/Program Files/Microsoft Office/Office" + officeNo + "/WINWORD";
        wordPath = GraderSettingsManagerSelector.getGraderSettingsManager().getWordPath();
//        wordPath = "C:/Program Files (x86)/Microsoft Office/Office15/WINWORD";
    }

    public void displayFile(String aFileName) {
        String windowsName = Common.toWindowsFileName(aFileName);

        String[] command = {wordPath, windowsName};
        Common.exec(command);
       WordOpenedFile.newCase(aFileName, this);

    }
    
    public static void main (String[] args) {
    	DocumentDisplayer displayer = new AWordDocumentDisplayer();
    	displayer.displayFile("config/dynamicconfig.properties");
    }

}
