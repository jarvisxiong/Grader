package grail.exceptions;

import util.annotations.Tags;

@SuppressWarnings("serial")
@Tags({"ScanningException"})
public class ScanningException extends Exception {

	public ScanningException(String message) {
		super(message);
	}

}
