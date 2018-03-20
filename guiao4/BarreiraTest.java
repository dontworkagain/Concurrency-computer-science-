package BarreiraGuiao4;

import java.util.Random;

public class BarreiraTest 
{
	public static void main(String[] args) throws InterruptedException 
	{
		final Barreira barreira = new Barreira(3);
		
		Thread t1 = new Thread(new Runnable() {
			
			public void run() 
			{
				int sum = 0;
				Random random = new Random();
				try {
					System.out.println("thread 1 iniciou a sua tarefa: ");
					for(int i = 0; i < 10 ; i++)
					{
						sum+=3;
						System.out.println("O somatorio da thread 1 vai em: "+sum);
						int t = random.nextInt(1000);
						Thread.sleep(t);
					}
					System.out.println("soma da primeira tarefa --> "+sum);
					barreira.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			public void run() 
			{
				int sum = 0;
				Random random = new Random();
				try {
					System.out.println("thread 2 iniciou a sua tarefa :");
					for(int i = 0; i<10;i++)
					{
						sum++;
						System.out.println("O somatorio da thread 2 vai em: "+sum);
						int t = random.nextInt(1000);
						Thread.sleep(t);

					}
					System.out.println("soma da segunda tarefa --> "+sum);
					barreira.await();
					//Thread.sleep(900);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t3 = new Thread(new Runnable() {
			
			public void run()
			{
				int sum = 0;
				Random random = new Random();
				try {
					System.out.println("thread 3 iniciou a sua tarefa");
					for(int i = 0 ; i < 20; i++)
					{
						sum+=2;
						System.out.println("O somatorio da thread 3 vai em: "+sum);
						int t = random.nextInt(1000);
						Thread.sleep(t);
					}
					System.out.println("soma da terceira tarefa --> "+sum);
					barreira.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
		
		System.out.println("terminamos");
		
		
	}

}
