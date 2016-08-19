package main;

import java.util.Iterator;

import mp.tokens.Token;

public interface TokenScanner extends Iterator<Token>{
	public void setScannedString(String scannedString);
	public String getScannedString();
	public String getErrors();
}
