package mp.CommanProcessing;
import bus.uigen.ObjectEditor;

public class Driver {

	public static void main(String[] args) {
		
		UppercaseFilter filter = new AnUppercaseFilter();
		filter.setInputString("Initial Input");
		ObjectEditor.edit(filter);

	}

}
