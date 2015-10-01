package main;

import java.util.Iterator;

public interface TokenScanner extends Iterator<Token>{
	public void setScannedString(String scannedString);
	public String getScannedString();
}
