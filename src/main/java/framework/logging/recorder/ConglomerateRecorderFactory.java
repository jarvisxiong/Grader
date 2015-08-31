package framework.logging.recorder;

import grader.sakai.project.SakaiProjectDatabase;
import grader.spreadsheet.FeatureGradeRecorder;
import grader.spreadsheet.FeatureGradeRecorderFactory;
import grader.spreadsheet.FinalGradeRecorder;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/13/13
 * Time: 9:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConglomerateRecorderFactory implements FeatureGradeRecorderFactory {

    @Override
    public FeatureGradeRecorder createGradeRecorder(SakaiProjectDatabase aSakaiProjectDatabase) {
        return ConglomerateRecorder.getInstance();
    }

	@Override
	public FinalGradeRecorder getGradeRecorder(
			SakaiProjectDatabase aSakaiProjectDatabase) {
		return createGradeRecorder(aSakaiProjectDatabase);
	}
}
