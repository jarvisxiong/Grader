package grail;

public class GenericWordToken extends GenericToken implements WordInterface{
	public GenericWordToken (){}
	public GenericWordToken (String newInput) {input = newInput;}
	public String getValue () {return input.toLowerCase();}
}
