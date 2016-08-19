package grail.interfaces;

import grail.helpers.TokenHistory;
import util.models.PropertyListenerRegisterer;

public interface ScannerBean extends PropertyListenerRegisterer {
	public void setScannedString(String scannedString);
	public String getScannedString();
	public StoreToken[] getTokens();
	public String[] getErrors();
	public TokenHistory getTokenList();
}
