package BarreiraGuiao4;

public class Barreira
{
	private int n;
	private int c = 0;
	
	public Barreira (int n)
	{
		this.n = n;
	}
	
	public synchronized void await() throws InterruptedException
	{
		c++;
		if(c<n)
		{
			while(c<n)
			{
				wait();
			}
		}
		else
		{
			notifyAll();
		}		
	} 
}
