package framework.logging.serializers;

import framework.grading.testing.CheckResult;
import framework.logging.recorder.RecordingSession;
import grader.assignment.GradingFeature;
import grader.spreadsheet.csv.ASakaiCSVFeatureGradeManager;

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
     
    String headingFeatureNotes() {
    	String retVal = "\nNotes from grading features:\n";
        retVal += "----------------------------------\n";
        return retVal;
    	
    }
    
    String headingRestrictionNotes() {
    	String retVal = "\nNotes from grading restrictions:\n";
        retVal += "----------------------------------\n";
        return retVal;
    	
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
             log += String.format(gradingFeature.getResultFormat(), gradingFeature.getScore()) + "\n";
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
                 log += String.format(gradingFeature.getResultFormat(), gradingFeature.getScore()) + "\n";
             }
             log += "----------------------------------\n";
             log += "  Points Deducted: " + deducted + "\n\n";
         }
         
         double featureTotal = (awarded + deducted);

//         log += "  Features Score: " + (awarded + deducted) + "\n";
         log += "  Features Score: " + featureTotal + "\n";


//         log += "\nNotes from grading features:\n";
//         log += "----------------------------------\n";
         boolean headingFeatureNotesPrinted = false;
//         log += headingFeatureNotes();
         for (GradingFeature gradingFeature:recordingSession.getGradingFeatures()) {
        	 if (gradingFeature.isRestriction()) {
        		 continue;
        	 }
        	 String autoNote = gradingFeature.getAutoNotes();
             String manualNote = gradingFeature.getManualNotes();
             if (autoNote.isEmpty() && manualNote.isEmpty())
            	 continue;
             if (!headingFeatureNotesPrinted) {
            	 log += headingFeatureNotes();
            	 headingFeatureNotesPrinted = true;
             }
             log += gradingFeature.getFeatureName() + ":" + "\n";
             if (!autoNote.isEmpty())
            	 log +=  autoNote + "\n";
             if (!manualNote.isEmpty())
            	 log +=  manualNote + "\n";
//             if (!autoNote.isEmpty()) {
//                 log += gradingFeature.getFeatureName() + ": "+  autoNote + "\n";
//             }
         
//             String manualNote = gradingFeature.getManualNotes();
//             if (!manualNote.isEmpty()) {
//                 log += gradingFeature.getFeatureName() + ": "+  manualNote + "\n";
//             }
             
         }
         if (hasRestrictions) {

//         log += "\nNotes from grading restrictions:\n";
//         log += "----------------------------------\n";
             boolean headingRestrictionNotesPrinted = false;
//        	 log += headingRestrictionNotes();
         for (GradingFeature gradingFeature:recordingSession.getGradingFeatures()) {
        	 if (!gradingFeature.isRestriction()) {
        		 continue;
        	 }
        	 String autoNote = gradingFeature.getAutoNotes();
             String manualNote = gradingFeature.getManualNotes();
             if (autoNote.isEmpty() && manualNote.isEmpty())
            	 continue;
             if (!headingRestrictionNotesPrinted) {
            	 log += headingRestrictionNotes();
            	 headingRestrictionNotesPrinted = true;
             }
            	 
             log += gradingFeature.getFeatureName() + ":" + "\n";
             if (!autoNote.isEmpty())
            	 log +=  autoNote + "\n";
             if (!manualNote.isEmpty())
            	 log +=  manualNote + "\n";
         
//             String note = gradingFeature.getManualNotes();
//             if (!note.isEmpty())
//                 log += note + "\n";
         }
         }

         if (recordingSession.getLatePenalty() < 1)
             log += "\nLate penalty: " + (recordingSession.getLatePenalty() * 100) + "%\n";
         if (recordingSession.getLatePenalty()> 1)
             log += "\nEarly benefit: " + (recordingSession.getLatePenalty() * 100) + "%\n";
         if (!recordingSession.getSourceCodeTAComments().isEmpty()) {
         log += "\nSource Code Comments:\n";
         log += "----------------------------------\n";
         log += recordingSession.getSourceCodeTAComments() + "\n";
         }
         if (recordingSession.getSourcePoints() != 0) {
             log += "\nSource Points: ";
//             log += "----------------------------------\n";
             log += recordingSession.getSourcePoints()+ "\n";
//             log += "----------------------------------\n";

             }
         
         if (!recordingSession.getComments().isEmpty()) {
         log += "\nOverall Comments:\n";
         log += "----------------------------------\n";
         log += recordingSession.getComments();
         log += "----------------------------------\n";
         }
         log += "  Total Score: " + ASakaiCSVFeatureGradeManager.getTotalGrade(featureTotal, recordingSession.getLatePenalty(), recordingSession.getSourcePoints()) + "\n";

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
