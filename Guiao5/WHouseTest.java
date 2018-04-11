package WH;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class WHouseTest {

	public static void main(String[] args) 
	{
		final WHouse wHouse = new WHouse();
		
		final String p1 = "pregos";
		final String p2 = "martelo";
		final String p3 = "alicate";
		final String p4 = "arame";
		
		wHouse.get(p1);
		wHouse.get(p2);
		wHouse.get(p3);
		wHouse.get(p4);
		
		final String[] lista1 = new String[] {"pregos"};
		final String[] lista2 = new String[] {"pregos","martelo"};
		final String[] lista3 = new String[] {"alicate","arame"};
		
		final HashMap<Integer, String> listaMaterial = new HashMap<Integer, String>();
		listaMaterial.put(0, p1);
		listaMaterial.put(1, p2);
		listaMaterial.put(2, p3);
		listaMaterial.put(3, p4);

		
		final HashMap<Integer, String[]> listaCompras = new HashMap<Integer, String[]>();
		listaCompras.put(0, lista1);
		listaCompras.put(1, lista2);
		listaCompras.put(2, lista3);
		
		
		// thread que vai fazer o supply do material
		Thread t1 = new Thread(new Runnable() 
		{
			
			public void run() 
			{
				Random prod = new Random();
				Random sorte = new Random();
				for( int i = 0; i< 200 ; i++)
				{
					int t = sorte.nextInt(4);
					int p = prod.nextInt(100);
					if(t==3)
					{
						wHouse.supply(p4, p);						
					}
					else
						if(t==2)
						{
							wHouse.supply(p3, p);
						}
						else
							if(t==1)
							{
								wHouse.supply(p2, p);								
							}
							else
							{
								wHouse.supply(p1, p);
							}
	System.out.println("foi colocado para venda: " +listaMaterial.get(t)+ " com quantidade: "+p);
					try {
						Thread.sleep(700);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				
			}
		});
		
		// thread que vai fazer o consumo
		Thread t2 = new Thread(new Runnable() 
		{
			
			public void run() 
			{
				Random random = new Random();
				for( int i = 0 ; i < 200 ; i++)
				{
					int t = random.nextInt(2);
					if(t==0)
					{
						try {
							wHouse.consume(lista1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					else
						if(t==1)
						{
							try {
								wHouse.consume(lista2);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						else
							if(t==2)
							{
								try {
									wHouse.consume(lista3);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

							}
					System.out.println("Foi pedido para comprar a lista: ");
					String[] percorrer = listaCompras.get(t);
					System.out.println(Arrays.toString(percorrer));
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
