package grader.feedback;

import grader.assignment.GradingFeature;
import grader.documents.DocumentDisplayerRegistry;

public class APrintingManualFeedbackManager implements ManualFeedback {

    @Override
    public void comment(GradingFeature aGradingFeature) {
    	System.out.println("Manual feedback needed");
//        DocumentDisplayerRegistry.display(aGradingFeature.getFeedbackFileName());


    }

}
