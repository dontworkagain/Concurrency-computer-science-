package Aula4NotifyAll;

import java.util.Random;

public class BoundedBufferTest 
{
	public static void main(String[] args) throws InterruptedException
	{
		int num = 10;
		
		final BoundedBuffer2 boundedBuffer2 = new BoundedBuffer2(num);
		
		Thread t1 = new Thread(new Runnable()
		{
			public void run() 
			{
				Random random = new Random();
				int num;
				while(true)
				{
					try {
						num = random.nextInt(100);
						boundedBuffer2.putBoundedBuffer2(num);
						System.out.println("a inserir "+num+" tenho no array "+boundedBuffer2.quantos);
						Thread.sleep(600);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			public void run() 
			{
				int item;
				while(true)
				{
					try {
						item = boundedBuffer2.getBoundedBuffer2();
						System.out.println("a tirar do buffer "+item+" tenho no array "+boundedBuffer2.quantos);
						Thread.sleep(800);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
			}
		});
		
		t1.start();
		t2.start();
		t1.join();
		t1.join();
		
		System.out.println("so far so good");
	}
}
