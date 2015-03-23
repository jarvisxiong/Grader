package old.echoerAndIM;


public interface AccessController  extends Vetoer{	
	boolean canInput(String theUser) ;
	boolean canAdminister(String theUser);
	void addAdministrator(String newAdminsitrator) ;
	void addInputter(String newInputter) ;
	void addAdministratorLocal(String newAdminsitrator) ;
	void addInputterLocal(String newInputter) ;
	void setOwner(String theOwner);		
}
