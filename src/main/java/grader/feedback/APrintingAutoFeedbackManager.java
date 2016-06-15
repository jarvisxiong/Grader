package grader.feedback;

import grader.assignment.GradingFeature;
import grader.checkers.CheckResult;

public class APrintingAutoFeedbackManager extends AManualFeedbackManager implements AutoFeedback {

    @Override
    public void recordAutoGrade(GradingFeature aGradingFeature,
                                CheckResult result) {

//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(aGradingFeature.getFeedbackFileName());
//            PrintStream outStream = new PrintStream(fileOutputStream);
//
//            outStream.println("Score:" + result.getScore());
            for (String string : result.getLog()) {
//                outStream.println(string);
                System.out.println(string);
            }
//            outStream.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (aGradingFeature.getScore() != aGradingFeature.getMax())
//            super.comment(aGradingFeature);
    }
}
