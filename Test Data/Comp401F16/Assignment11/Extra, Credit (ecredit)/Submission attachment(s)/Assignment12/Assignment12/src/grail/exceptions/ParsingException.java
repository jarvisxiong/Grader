package grail.exceptions;

import util.annotations.Tags;

@SuppressWarnings("serial")
@Tags({"ParsingException"})
public class ParsingException extends Exception {

	public ParsingException(String message) {
		super(message);
	}


}
