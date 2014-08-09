package im.access;

public class AnAdministratorAuthorization extends AMessage<String> {
	public AnAdministratorAuthorization(String theUserName) {
		super(theUserName);
	}
}
