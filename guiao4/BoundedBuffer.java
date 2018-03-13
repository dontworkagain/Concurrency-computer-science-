package Guiao4;

import java.util.concurrent.Semaphore;


public class BoundedBuffer
{
	private int iput;
	private int iget;	
	private int[] boundedBuffer;
	int quantos;
	
	Semaphore items;
	Semaphore slots;
	Semaphore getItem;
	Semaphore putItem;
	
	public BoundedBuffer(int quantos)
	{
		this.boundedBuffer = new int[quantos];
		this.items = new Semaphore(0);
		this.slots = new Semaphore(quantos);
		this.getItem = new Semaphore(1);
		this.putItem = new Semaphore(1);
	}
	
	public void putBoundedBuffer(int item) throws InterruptedException
	{
		slots.acquire();
		putItem.acquire();
		boundedBuffer[iput]=item;
		iput = (iput+1)%boundedBuffer.length;
		quantos++;
		putItem.release();
		items.release();
	}
	
	public int getBoundedBuffer() throws InterruptedException
	{
		int take;
		items.acquire();
		getItem.acquire();
		take = boundedBuffer[iget];
		iget= (iget+1)%boundedBuffer.length;
		quantos--;
		getItem.release();
		slots.release();
		return take;
	}
}
