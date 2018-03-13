package BancoHashMap;

public class NotEnoughFunds extends Exception
{
	int montante;
	public NotEnoughFunds(int m)
	{
		this.montante = m;
	}

}
