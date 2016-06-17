package framework.logging.loggers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import framework.logging.recorder.RecordingSession;
import framework.logging.serializers.SerializationUtils;
import framework.utils.BasicGradingEnvironment;

/**
 * Saves a text summary to a local file.
 */
public class LocalTextSummaryLogger implements Logger {
    @Override
    public void save(RecordingSession recordingSession) {
        String text = SerializationUtils.getSerializer("text").serialize(recordingSession);

        // Maybe write this to a file
//        File folder = new File("log/" + GradingEnvironment.get().getAssignmentName());
        try {
//            FileUtils.writeStringToFile(new File(folder, recordingSession.getUserId() + ".txt"), text);
            FileUtils.writeStringToFile(logFile(recordingSession.getUserId()), text);

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    File logFile(String aUserId) {
		File folder = new File("log/" + BasicGradingEnvironment.get().getAssignmentName());
		return new File(folder, aUserId + ".txt");
	}

    

	@Override
	public String logFileName(String aUserId) {
		File file = logFile(aUserId);
		if (file != null && file.exists())
			return file.getAbsolutePath();
		else
			return null;
	}

	@Override
	public boolean isSaved(String aUserId) {
		File file = logFile(aUserId);
		return (file != null && file.exists());
	}
}
