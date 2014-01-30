package gradingTools.comp110.assignment1.testcases;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.Project;

public class ProperHeaderTestCase extends BasicTestCase {

	public ProperHeaderTestCase() {
		super("Proper header test case");
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		if (project.getClassesManager().isEmpty())
			throw new NotGradableException();

		for (ClassDescription description : project.getClassesManager().get()
				.getClassDescriptions()) {
			try {
				// Get the comment free code
				String code = FileUtils.readFileToString(description.getSource());
				code = code.replaceAll("\\s+", " ");

				// Half credit for not filling in header
				if (code.contains("/****************************************************************** * Program or Assignment #: Insert assignment name * * Programmer: Insert your name * * Due Date: Insert due date * * COMP110-002, Spring 2014 Instructor: Prof. Jay Aikat * * Pledge: I have neither given nor received unauthorized aid * on this program. * * Description: Insert a brief paragraph describing the program * * Input: Insert a brief description of user inputs, or \"None\" if * there is no user input * * Output: Insert a brief description of the program output * ******************************************************************/")) {
					return partialPass(0.5, "You must fill in the information in the header");
				}

				String headerRegex = "/[*]{1,} [*]{1,} Program or Assignment #: .+ [*]{1,} [*]{1,} Programmer: [^\\s]+ [*]{1,} [*]{1,} Due Date: .+ [*]{1,} [*]{1,} COMP110-002, Spring 2014 Instructor: Prof. Jay Aikat [*]{1,} [*]{1,} Pledge: I have neither given nor received unauthorized aid [*]{1,} on this program. [*]{1,} [*]{1,} Description: .+ [*]{1,} [*]{1,} Input: .+ [*]{1,} [*]{1,} Output: .+ [*]{1,} [*]{1,}/";
				if (Pattern.compile(headerRegex).matcher(code).find()) {
					return pass();
				}

				throw new NotAutomatableException();

			} catch (IOException e) {
				throw new NotGradableException();
			}
		}

		return pass();
	}
}
