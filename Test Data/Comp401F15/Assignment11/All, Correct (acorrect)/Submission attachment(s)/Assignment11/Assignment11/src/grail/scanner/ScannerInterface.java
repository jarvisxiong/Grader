package grail.scanner;

import grail.scanner.ClearableHistory;
import mp.tokens.RawInput;

//this is for assignment3
public interface ScannerInterface {
	public void setScannedString(String str);
	public String getScannedString();
	public void scanString(String line);
	public RawInput[] getCompactTokenArray();
	public String getErrLog();
	public ClearableHistory getTokenList();

}
