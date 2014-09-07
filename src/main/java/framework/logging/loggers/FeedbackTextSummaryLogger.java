package framework.logging.loggers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import util.misc.Common;
import framework.logging.recorder.RecordingSession;
import framework.logging.serializers.SerializationUtils;
import framework.utils.GraderSettings;
import grader.project.AProject;
import grader.sakai.StudentCodingAssignment;
import grader.sakai.project.ASakaiProjectDatabase;
import grader.trace.feedback.FeedbackSaved;

/**
 * Saves the text summary to the student's feedback folder
 */
public class FeedbackTextSummaryLogger implements Logger {

	public static void logNoSrcFolder(AProject project) {
		String text = "No project folder found that could be used to grade your project. This is likely because you did not submit a .zip file or your .zip file was corrupted.";
		
		try {
			String studentId = new File(project.getOutputFolder()).getParentFile().getName();
			File file = new File(staticLogFileName(studentId));
			FileUtils.writeStringToFile(file, text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void logNoSubmission(StudentCodingAssignment assignment) {
		String text = "Could not find a submitted project to grade.";
		
		try {
			String studentId = new File(assignment.getStudentFolder().getMixedCaseAbsoluteName()).getName();
			File file = new File(staticLogFileName(studentId));
			FileUtils.writeStringToFile(file, text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @Override
    public void save(RecordingSession recordingSession) {
        String text = SerializationUtils.getSerializer("text").serialize(recordingSession);

        // Maybe write this to a file
//        File file = new File(GraderSettings.get().get("path") + "/" + recordingSession.getUserId() + "/Feedback Attachment(s)/feedback.txt");
        File file = new File(logFileName(recordingSession.getUserId()));

        try {
            FileUtils.writeStringToFile(file, text);
            String aLogFileName = ASakaiProjectDatabase.getCurrentSakaiProjectDatabase().getAssignmentDataFolder().getLogFileName();
            Common.appendText(aLogFileName, text);
            FeedbackSaved.newCase(null, null, null, file.getAbsolutePath(), text, this);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public static  String restore (RecordingSession recordingSession) {
    	return restore(recordingSession.getUserId());
    }
    
    public static  String restore (String aUserId) {
    	File file = new File(GraderSettings.get().get("path") + "/" + aUserId + "/Feedback Attachment(s)/feedback.txt");
        try {
            return FileUtils.readFileToString(file);
        } catch (IOException e) {
            return "";
        }
    }
    
    private static String staticLogFileName(String aUserId) {
    	return GraderSettings.get().get("path") + "/" + aUserId + "/Feedback Attachment(s)/feedback.txt";
    }
    
	public String logFileName(String aUserId) {
		return staticLogFileName(aUserId);
	}

	
	@Override
	public boolean isSaved(String aUserId) {
		File file = new File(logFileName(aUserId));
		return file.exists();
	}
    
 

}
