package grail;

import util.annotations.Tags;
@Tags({"BridgeScene"})

public class BridgeScene implements BridgeSceneInterface{
//Arthur, Lancelot, Robin, Galahad, and Guard
	AvatarInterface arthur;
	AvatarInterface lancelot;
	AvatarInterface robin;
	AvatarInterface galahad;
	AvatarInterface guard;
	
	public BridgeScene()
	{
		String path="images/";
		arthur=new Avatar("Arthur",path+"arthur.jpg",500,100);
		lancelot=new Avatar("Lancelot",path+"lancelot.jpg",400,100);
		robin=new Avatar("Robin",path+"robin.jpg",200,100);
		galahad=new Avatar("Galahad",path+"galahad.jpg",100,100);
		guard=new Avatar("Guard",path+"guard.jpg",600,100);
	}
	
	public AvatarInterface getArthur()
	{
		return arthur;
	}
	public AvatarInterface getLancelot()
	{
		return lancelot;
	}
	
	public AvatarInterface getRobin()
	{
		return robin;
	}
	public AvatarInterface getGalahad()
	{
		return galahad;
	}
	public AvatarInterface getGuard()
	{
		return guard;
	}
}
