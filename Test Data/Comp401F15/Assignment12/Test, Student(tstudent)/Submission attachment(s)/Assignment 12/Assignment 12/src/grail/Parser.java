package grail;

import util.models.PropertyListenerRegisterer;

public interface Parser extends PropertyListenerRegisterer{
	public void setCommandText (String commandText);
	public String getCommandText();
	public Runnable parseCommand();
	public Runnable parseMove(TokenInterface[] newTokens);
	public Runnable parseSay(TokenInterface[] newTokens);
	public Runnable parsePass();
	public Runnable parseFail();
	public Runnable parseApproach(TokenInterface[] newTokens);
	public Runnable parseRepeat(TokenInterface[] newTokens);
	public Runnable parseCommandList(TokenInterface[] newTokens);
}
