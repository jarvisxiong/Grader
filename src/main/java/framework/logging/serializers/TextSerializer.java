package framework.logging.serializers;

import framework.grading.testing.CheckResult;
import framework.logging.recorder.RecordingSession;
import grader.assignment.GradingFeature;
import grader.assignment.GradingFeatureList;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/12/13
 * Time: 9:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextSerializer implements RecordingSessionSerializer {
     String resultsBasedSerialize(RecordingSession recordingSession) {
        String log = "";
        double awarded = 0;
        double deducted = 0;

        log += "Grading result for: " + recordingSession.getUserId() + "\n\n";

        log += "Grading features...\n";
        log += "----------------------------------\n";
        for (CheckResult result : recordingSession.getFeatureResults()) {
            awarded += result.getScore();
            log += String.format(result.getTarget().getSummary(), result.getScore()) + "\n";
        }
        log += "----------------------------------\n";
        log += "  Points Awarded: " + awarded + "\n\n";

        if (!recordingSession.getRestrictionResults().isEmpty()) {
            log += "Grading restrictions...\n";
            log += "----------------------------------\n";
            for (CheckResult result : recordingSession.getRestrictionResults()) {
                deducted += result.getScore();
                log += String.format(result.getTarget().getSummary(), result.getScore()) + "\n";
            }
            log += "----------------------------------\n";
            log += "  Points Deducted: " + deducted + "\n\n";
        }

        log += "  Total Score: " + (awarded + deducted) + "\n";

        log += "\nNotes from grading features:\n";
        log += "----------------------------------\n";
        for (CheckResult result : recordingSession.getFeatureResults()) {
            String note = result.getSummary();
            if (!note.isEmpty())
                log += note + "\n";
        }

        log += "\nNotes from grading restrictions:\n";
        log += "----------------------------------\n";
        for (CheckResult result : recordingSession.getRestrictionResults()) {
            String note = result.getSummary();
            if (!note.isEmpty())
                log += note + "\n";
        }

        if (recordingSession.getLatePenalty() < 1)
            log += "\nLate penalty: " + (recordingSession.getLatePenalty() * 100) + "%\n";
        if (recordingSession.getLatePenalty()> 1)
            log += "\nEarly benefit: " + (recordingSession.getLatePenalty() * 100) + "%\n";

        log += "\nTA Comments:\n";
        log += "----------------------------------\n";
        log += recordingSession.getComments();
        return log;
    }
     // need to change this
     String featuresBasedSerialize(RecordingSession recordingSession) {
         String log = "";
         double awarded = 0;
         double deducted = 0;

         log += "Grading result for: " + recordingSession.getUserId() + "\n\n";

         log += "Grading features...\n";
         log += "----------------------------------\n";
         boolean hasRestrictions = false;
         for (GradingFeature gradingFeature:recordingSession.getGradingFeatures()) {
        	 if (gradingFeature.isRestriction()) {
        		 hasRestrictions = true;
        		 continue;
        	 }
             awarded += gradingFeature.getScore();
             log += String.format(gradingFeature.getResult(), gradingFeature.getScore()) + "\n";
         }
         log += "----------------------------------\n";
         log += "  Points Awarded: " + awarded + "\n\n";
         

//         if (!recordingSession.getRestrictionResults().isEmpty()) {
         if (hasRestrictions) {
             log += "Grading restrictions...\n";
             log += "----------------------------------\n";
             for (GradingFeature gradingFeature:recordingSession.getGradingFeatures()) {
            	 if (!gradingFeature.isRestriction()) {
            		 continue;
            	 }
                 deducted += gradingFeature.getScore();
                 log += String.format(gradingFeature.getResult(), gradingFeature.getScore()) + "\n";
             }
             log += "----------------------------------\n";
             log += "  Points Deducted: " + deducted + "\n\n";
         }

         log += "  Total Score: " + (awarded + deducted) + "\n";

         log += "\nNotes from grading features:\n";
         log += "----------------------------------\n";
         for (GradingFeature gradingFeature:recordingSession.getGradingFeatures()) {
        	 if (gradingFeature.isRestriction()) {
        		 continue;
        	 }
         
             String note = gradingFeature.getNotes();
             if (!note.isEmpty())
                 log += note + "\n";
         }
         if (hasRestrictions) {

         log += "\nNotes from grading restrictions:\n";
         log += "----------------------------------\n";
         for (GradingFeature gradingFeature:recordingSession.getGradingFeatures()) {
        	 if (!gradingFeature.isRestriction()) {
        		 continue;
        	 }
         
             String note = gradingFeature.getNotes();
             if (!note.isEmpty())
                 log += note + "\n";
         }
         }

         if (recordingSession.getLatePenalty() < 1)
             log += "\nLate penalty: " + (recordingSession.getLatePenalty() * 100) + "%\n";
         if (recordingSession.getLatePenalty()> 1)
             log += "\nEarly benefit: " + (recordingSession.getLatePenalty() * 100) + "%\n";

         log += "\nTA Comments:\n";
         log += "----------------------------------\n";
         log += recordingSession.getComments();
         return log;
     }
    @Override
    public String serialize(RecordingSession recordingSession) {
        if (recordingSession.getGradingFeatures() == null) {
        	return resultsBasedSerialize(recordingSession);        	
        } else {
        	return featuresBasedSerialize(recordingSession);
        }
    }
}
