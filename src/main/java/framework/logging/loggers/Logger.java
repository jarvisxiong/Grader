package framework.logging.loggers;

import framework.grading.testing.CheckResult;
import framework.logging.recorder.RecordingSession;

import java.util.List;

/**
 * This interface defines how grade loggers should behave.
 */
public interface Logger {
	public String logFileName(String aUserId);
    public void save(RecordingSession recordingSession);
    boolean isSaved(String aUserId);

}
