package BancoHashMap;

import java.util.Random;

public class TesteBancoHashMap {

	/**
	 * @param args
	 * @throws InvalidAccount 
	 * @throws NotEnoughFunds 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InvalidAccount, NotEnoughFunds, InterruptedException
	{
		final int n = 100;
		final Banco b = new Banco();
		final int[] accounts = {1,3,2,4};

		b.creatAccount(1000);
		b.creatAccount(2000);
		b.creatAccount(3000);
		b.creatAccount(4000);
		
		System.out.println("saldo da conta #1: "+b.verSaldoConta(1)+"€");
		System.out.println("saldo da conta #2: "+b.verSaldoConta(2)+"€");
		System.out.println("saldo da conta #3: "+b.verSaldoConta(3)+"€");
		System.out.println("saldo da conta #4: "+b.verSaldoConta(4)+"€");
		
		System.out.println("vamos depositar 500 € na conta 2, o que tem de dar 2500€");
		b.deposit(2, 500);
		System.out.println(b.verSaldoConta(2));
		System.out.println("vamos levnatar 300 € na conta 1, o que tem de dar 700€");
		b.levantamento(1, 300);
		System.out.println(b.verSaldoConta(1));
		
		Thread t1 = new Thread(new Runnable() {
			
			public void run()
			{
				try {
					Random r = new Random();
					for(int i = 0; i<n; i++)
					{
						if(r.nextBoolean())
						{
							System.out.println("Thread 1 ----> a transferir da conta 1 para a conta 2");
							b.transfer(1, 2, 20);
							b.deposit(1, 33);
							System.out.println("transferido 20€");
						}
						else
						{
							System.out.println("Thread 1 ----> a transferir da conta 2 para a conta 1");
							b.levantamento(3,55);
							b.transfer(2, 1, 30);
							System.out.println("transferido 30€");
						}
					}
				} catch (Exception e) {
					System.out.println(e.getStackTrace());
				}
				
			}
		});
		
		Thread t3 = new Thread(new Runnable() {

			public void run()
			{
				try {
					Random r = new Random();
					for(int i = 0; i<n; i++)
					{
						if(r.nextBoolean())
						{
							System.out.println("Thread 3 ----> a transferir da conta 3 para a conta 4");
							b.transfer(3, 4, 20);
							b.deposit(4, 33);
							System.out.println("transferido 20€");
						}
						else
						{
							System.out.println("Thread 3 ----> a transferir da conta 4 para a conta 3");
							b.levantamento(3,55);
							b.transfer(4, 3, 30);
							System.out.println("transferido 30€");
						}
					}
				} catch (Exception e) {
					System.out.println(e.getStackTrace());
				}

			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			public void run()
			{
				Random random = new Random();
				for(int i = 0 ; i < n ; i++)
				{
					if(random.nextInt(100)<50)
					{
						try {
							System.out.println("Thread 2 ----> a contar o saldo das contas");
							System.out.println("o total das contas e: --->"+b.totalBalance(accounts));
						} catch (InvalidAccount e) {
							e.printStackTrace();
						}
					}
				}
				
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
		
		float t = b.closeAccount(1);
		System.out.println("------------------------------------------------>"+t);
		
	}

}
