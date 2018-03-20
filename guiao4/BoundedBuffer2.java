package Aula4NotifyAll;

public class BoundedBuffer2
{
	private int n;
	private int[] boundedBuffer2;
	private int iput;
	private int iget;
	private int elems = 0;
	int quantos = 0;

	BoundedBuffer2(int n)
	{
		this.boundedBuffer2 = new int[n];
	}
	
	public synchronized void putBoundedBuffer2(int item) throws InterruptedException
	{
		while(elems ==boundedBuffer2.length)
		{
			wait();				
		}
		boundedBuffer2[iput]=item;
		iput = (iput+1)%boundedBuffer2.length;
		elems++;
		quantos++;
		notifyAll();
		
	}	
	
	public synchronized int getBoundedBuffer2() throws InterruptedException
	{
		int res;
		while(elems==0)
		{
			wait();
		}
		res = boundedBuffer2[iget];
		iget= (iget+1)%boundedBuffer2.length;
		elems--;
		quantos--;
		notifyAll();
		return res;
	}
}
