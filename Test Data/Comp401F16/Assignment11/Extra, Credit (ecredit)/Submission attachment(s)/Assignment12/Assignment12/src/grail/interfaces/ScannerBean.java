package grail.interfaces;

import grail.exceptions.ScanningException;
import grail.helpers.TokenHistory;
import util.models.PropertyListenerRegisterer;

public interface ScannerBean extends PropertyListenerRegisterer {
	public void setScannedString(String scannedString) throws ScanningException;
	public String getScannedString();
	public StoreToken[] getTokens();
	public String[] getErrors();
	public TokenHistory getTokenList();
}
