package main;

import grail.TokenInterface;
import util.annotations.Tags;
@Tags ({"ScannerBean"})

public interface ScannerBeanInterface {
	public String getScannedString();
	public void setScannedString(String newString);
	public String[] getScannedStringArray();
	public TokenInterface[] getTokens();
	public void setTokens(TokenInterface[] newTokens);
}
