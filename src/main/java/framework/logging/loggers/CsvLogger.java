package framework.logging.loggers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import framework.grading.testing.CheckResult;
import framework.logging.recorder.RecordingSession;
import framework.logging.serializers.SerializationUtils;
import framework.navigation.NotValidDownloadFolderException;
import framework.navigation.SakaiBulkDownloadFolder;
import framework.utils.GraderSettings;

public class CsvLogger implements Logger {

	
	@Override
    public void save(RecordingSession recordingSession) {
        String text = SerializationUtils.getSerializer("text").serialize(recordingSession);

        // Maybe write this to a file
        try {
			SakaiBulkDownloadFolder sakaiFolder = new SakaiBulkDownloadFolder(GraderSettings.get().get("path"));
			File file = sakaiFolder.getGrades();
			
			//Get the onyen
			String id = recordingSession.getUserId();
			String onyen = id.substring(id.lastIndexOf('(')+1, id.lastIndexOf(')'));
			
			//Get the rawScore
			double rawScore = 0;
			for (CheckResult result : recordingSession.getFeatureResults()) {
				rawScore += result.getScore();
			}
			for (CheckResult result : recordingSession.getRestrictionResults()) {
				rawScore += result.getScore();
			}
			
			List<String> lines = FileUtils.readLines(file);
			FileWriter writer = new FileWriter(file);			
			for(String line : lines) {
				if (line.startsWith(onyen + ",")) {
					String[] csvParts = line.split(",");
					for(int i=0; i<4; i++) {
						writer.write(csvParts[i]+",");
					}
					writer.write("" + (rawScore * recordingSession.getLatePenalty()));
					writer.write("\n");
				}else{
					writer.write(line+"\n");
				}
			}
			writer.close();
			
		} catch (NotValidDownloadFolderException e) {
			System.err.println("Error saving csv log");
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println("Error saving csv log");
			System.err.println(e.getMessage());
		}
        File file = new File(GraderSettings.get().get("path") + "/" + recordingSession.getUserId() + "/Feedback Attachment(s)/feedback.txt");
        try {
            FileUtils.writeStringToFile(file, text);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
