package grader.sakai.project;

import java.io.IOException;

public class InvalidOnyenRangeException extends IOException { // will be user error
	public InvalidOnyenRangeException (String aMessage) {
		super(aMessage);
	}

}
