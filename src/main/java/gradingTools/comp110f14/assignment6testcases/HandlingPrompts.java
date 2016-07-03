package gradingTools.comp110f14.assignment6testcases;

import java.util.regex.Pattern;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class HandlingPrompts extends BasicTestCase {

	public HandlingPrompts() {
		super("Handling prompts test Case");
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {

		Pattern prompts = Pattern.compile(".*order|print|finish.*");
		int numwrong = 0;
		String partialMessage = "";

		// -------------order stuff---------------//
		RunningProject order = RunningProjectUtils.runProject(project, 10,
				"order\n5\n");
		String outputorder = order.await();
		// break up output
		String[] outputorderfoo = outputorder.split("\n");
		int numencounters = 0;
		// loop through output given by specified input
		for (int i = 0; i < outputorderfoo.length; i++) {
			if (prompts.matcher(outputorderfoo[i]).find()) {
				numencounters++;
			}
		}
		// if didn't find prompt twice then didn't handle order correctly
		if (numencounters < 2) {
			numwrong++;
			partialMessage += "Order_Case: Did not prompt again for input following order.\n";
		}
		// ---------------end order stuff---------------//

		// ---------------print stuff--------------------//
		RunningProject print = RunningProjectUtils.runProject(project, 10,
				"order\n5\nprint\n");
		String outputprint = print.await();
		// break up output
		String[] outputprintfoo = outputprint.split("\n");

		if (outputprintfoo.length < outputorderfoo.length
				|| outputprintfoo.length == outputorderfoo.length) {
			numwrong++;
			partialMessage += "Print_Case: Did not handle print correctly.\n";
		} else {

			int numencountersprint = 0;
			// loop through output given by specified input
			for (int i = 0; i < outputprintfoo.length; i++) {
				if (prompts.matcher(outputprintfoo[i]).find()) {
					numencountersprint++;
				}
			}
			// if didn't find prompt thrice then didn't handle order correctly
			if (numencountersprint < 3) {
				numwrong++;
				partialMessage += "Print_Case: Did not prompt again for input following print.\n";
			}
		}
		// ---------------end print stuff---------------//
		// --------Begin finish----------//

		RunningProject finish = RunningProjectUtils.runProject(project, 10,
				"finish\n");
		String outputfinish = finish.await();
		String[] outputfinishfoo = outputfinish.split("\n");
		if (outputfinishfoo.length > outputorderfoo.length) {
			numwrong++;
			partialMessage += "Finish_Case: Did not handle finish correctly.\n";
		}
		
		// ---------end finish-------//
		
		// if handled order, print and finish then pass
		if (numwrong == 0)
			return pass();
		// if didn't handle either right fail
		if (numwrong == 3)
			return fail(partialMessage);
		// if handled yes or no but not both
		return partialPass((1 - ((1 / 3) * numwrong)), partialMessage);
	}

}