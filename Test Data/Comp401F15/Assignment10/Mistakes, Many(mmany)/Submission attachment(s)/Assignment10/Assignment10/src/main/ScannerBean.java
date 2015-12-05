package main;

import mp.tokens.Token;
import mp.tokens.TokenHistory;

public interface ScannerBean {
	public String getScannedString();
	public void setScannedString(String scannedString);
	public TokenHistory getTokenList();
	public String getErrors();
}
