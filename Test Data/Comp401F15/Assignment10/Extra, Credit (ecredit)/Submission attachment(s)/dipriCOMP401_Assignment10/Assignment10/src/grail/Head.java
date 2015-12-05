package grail;

import util.annotations.Tags;

@Tags({"Head"})
public interface Head extends Locatable {
public String getImageFileName();
public void setImageFileName(String aString);
}
