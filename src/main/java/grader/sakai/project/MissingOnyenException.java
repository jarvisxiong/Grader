package grader.sakai.project;

import java.io.IOException;

public class MissingOnyenException extends IOException { // will be user error
	public MissingOnyenException (String aMessage) {
		super(aMessage);
	}

}
