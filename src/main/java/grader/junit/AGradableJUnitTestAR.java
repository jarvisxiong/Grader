package grader.junit;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.models.AMainClassListLauncher;
import bus.uigen.undo.ExecutableCommand;

public class AGradableJUnitTestAR implements ExecutableCommand {

	@Override
	public Object execute(Object arg0) {
		ObjectEditor.setAttribute(AGradableJUnitTest.class, AttributeNames.USE_NAME_AS_LABEL, true);

		return null;
	}

}
