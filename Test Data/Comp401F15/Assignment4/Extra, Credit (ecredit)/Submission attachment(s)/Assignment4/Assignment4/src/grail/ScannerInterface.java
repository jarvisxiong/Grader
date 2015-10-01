package grail;

import mp.RawInput;

//this is for assignment3
public interface ScannerInterface {
	public void setScannedString(String str);
	public String getScannedString();
	public void scanString(String line);
	public RawInput[] getTokens();
	public String getErrLog();
}
