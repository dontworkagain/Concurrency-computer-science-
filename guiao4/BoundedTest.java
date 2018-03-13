package Guiao4;

import java.util.Random;

public class BoundedTest
{

	public static void main(String[] args) throws InterruptedException
	{
		int quantos = 10;
		
		final BoundedBuffer boundedBuffer = new BoundedBuffer(quantos);
		
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
						boundedBuffer.putBoundedBuffer(num);
						System.out.println("a inserir "+num+" tenho no array "+boundedBuffer.quantos);
						Thread.sleep(300);
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
						item = boundedBuffer.getBoundedBuffer();
						System.out.println("a tirar do buffer "+item+" tenho no array  "+boundedBuffer.quantos);
						Thread.sleep(500);
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
