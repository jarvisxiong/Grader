package grail;

public class Util {
	public static boolean isLetter(char c)
	{
		return 'a'<=c&&c<='z'||'A'<=c&&c<='Z';
	}
	public static String char2String(char c)
	{
		String res="";
		res+=c;
		return res;
	}
}
