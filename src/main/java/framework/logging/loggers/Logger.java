package framework.logging.loggers;

import framework.logging.recorder.RecordingSession;

/**
 * This interface defines how grade loggers should behave.
 */
public interface Logger {
	public String logFileName(String aUserId);
    public void save(RecordingSession recordingSession);
    boolean isSaved(String aUserId);

}
