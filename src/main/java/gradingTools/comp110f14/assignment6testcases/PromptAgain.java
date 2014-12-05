package gradingTools.comp110f14.assignment6testcases;

	import java.util.regex.Pattern;

	import framework.execution.RunningProject;
	import framework.grading.testing.BasicTestCase;
	import framework.grading.testing.NotAutomatableException;
	import framework.grading.testing.NotGradableException;
	import framework.grading.testing.TestCaseResult;
	import framework.project.Project;
	import gradingTools.utils.RunningProjectUtils;

	public class PromptAgain extends BasicTestCase{

		public PromptAgain() {
			//mandatory super call to create instance of BasicTestCase w/ message
			super("Prompt Again Test Case");
		}

		@Override
		public TestCaseResult test(Project project, boolean autoGrade)
				throws NotAutomatableException, NotGradableException {
			//pattern searching for
			Pattern again = Pattern.compile(".*order|print|finish.*");
			//invoke instance of assignment five input string w/ timeout
			RunningProject againInfo = RunningProjectUtils.runProject(project, 10, "order\n5");
			//grab everything after input up to point waiting for input
			String outagainInfo= againInfo.await();

			//If dont ask for another message - fail w/ message
			if(!again.matcher(outagainInfo).find()){
				return fail("Failed to ask again");
			}
			
			//asks for another message
			return pass();
			
		}

		
	}
