package widgets;

public interface TextField {
	public void insert(int index, char ch);
	public void addTextFieldListener(TextFieldListener theListener);
	public String getName();

}
