package im.access;

public class AnInputAuthorization extends AMessage<String> {
	public AnInputAuthorization(String theUserName) {
		super(theUserName);
	}
}
