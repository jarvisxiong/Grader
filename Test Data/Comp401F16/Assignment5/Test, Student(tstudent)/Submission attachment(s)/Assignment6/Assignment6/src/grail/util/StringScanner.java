/**
 * @author Ian Hoyt-McCullough
 * @version 9/10/2015
 */

package grail.util;

import grail.tokens.TokenBean;

public interface StringScanner {
	void setScannedString(String newInput);
	String getScannedString();
	int scanString();
	TokenBean[] getTokens();
}
