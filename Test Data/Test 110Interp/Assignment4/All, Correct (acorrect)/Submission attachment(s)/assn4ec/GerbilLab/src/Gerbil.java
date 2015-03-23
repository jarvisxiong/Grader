
public class Gerbil {

	private int[] numberItems;
	private boolean gerbBite, gerbEscape;
	private String gerbName, labId;
	
	public Gerbil()
	{
		
	}
	
	public void setId(String id)
	{
		labId = id;
	}
	
	public void setName(String name)
	{
		gerbName = name;
	}
	
	public String getName()
	{
		return gerbName;
	}
	
	public void setSize(int size)
	{
		numberItems = new int[size];
	}
	
	public void setNumbers(int index, int num)
	{
		numberItems[index] = num;
	}
	
	public void setFoodNumbers(int[] numItems)
	{
		numberItems = numItems;
	}
	
	public int[] getFoodNumbers()
	{
		return numberItems;
	}
	
	public void setBite(boolean bite)
	{
		gerbBite = bite;
	}
	
	public String getBite()
	{
		if (gerbBite)
			return "will bite";
		return "will not bite";
	}
	
	public void setEscape(boolean escape)
	{
		gerbEscape = escape;
	}
	
	public String getEscape()
	{
		if (gerbEscape)
			return "will escape";
		return "will not escape";
	}
	
	public String getId()
	{
		return labId;
	}
	
	
}
