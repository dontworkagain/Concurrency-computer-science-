package BancoHashMap;

public class InvalidAccount extends Exception 
{
	int id;
	public InvalidAccount(int id)
	{
		this.id=id;
	}

}
