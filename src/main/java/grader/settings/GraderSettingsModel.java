package grader.settings;

import util.annotations.ComponentHeight;
import util.annotations.Row;
import util.annotations.Visible;

public interface GraderSettingsModel {
	public GraderFilesSetterModel getFileBrowsing() ;
	public void setFileBrowsing(GraderFilesSetterModel fileBrowsing);
	public OnyenRangeModel getOnyens() ;
	public void setOnyens(OnyenRangeModel onyens) ;
	public  void begin() ;
	public  void awaitBegin() ;

}
