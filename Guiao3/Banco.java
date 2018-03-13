package BancoHashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Package referenta ao guiao3
 * aula pratica dia 27-02-2018
 * banco com hashmap
 *  
 */


public class Banco
{
	public static class Conta
	{
		private int saldo;
		private Lock contaLock = new ReentrantLock();
		
		Conta(int saldo)
		{
			this.saldo = saldo;
		}	
	}

	private int id = 0;
	private Map<Integer, Conta> contas = new HashMap<Integer, Banco.Conta>();
	private Lock bancoLock = new ReentrantLock();
	
	public int verSaldoConta(int id) throws InvalidAccount
	{
		Conta c;
		bancoLock.lock();
		try
		{
			c = contas.get(id);
			if(c==null)
			{
				throw new InvalidAccount(id);
			}
			return c.saldo;
		}
		finally
		{
			bancoLock.unlock();
		}
	}
		
	
	public void creatAccount(int montanteInicial)
	{
		Conta c1 = new Conta(montanteInicial);
		bancoLock.lock();
		try
		{
			id+=1;
			contas.put(id, c1);
		}
		finally
		{
			bancoLock.unlock();
		}
	}
	
	public void deposit(int i, int m) throws InvalidAccount
	{
		Conta c = null;
		bancoLock.lock();
		try
		{
			c = contas.get(i);
			if(c==null)
			{
				throw new InvalidAccount(i);
			}
			c.contaLock.lock();
		}
		finally
		{
			bancoLock.unlock();

		}
		c.saldo+=m;
		c.contaLock.unlock();
	}
	
	public void levantamento (int i, int m) throws InvalidAccount, NotEnoughFunds
	{
		Conta c = null;
		bancoLock.lock();
		try
		{
			c = contas.get(i);
			if(c==null)
			{
				throw new InvalidAccount(i);
			}
			c.contaLock.lock();
		}
		finally
		{
			bancoLock.unlock();
		}
		try
		{
			int s = c.saldo-m;
			if(s<0)
			{
				throw new NotEnoughFunds(i);
			}
			c.saldo-=m;
		}
		finally
		{
			c.contaLock.unlock();
		}
	}
	
	public void transfer(int i, int j, int m) throws InvalidAccount, NotEnoughFunds
	{
		Conta c1= null;
		Conta c2 = null;
		bancoLock.lock();
		try
		{
			c1 = contas.get(i);
			System.out.println(c1.saldo);
			if(c1==null) throw new InvalidAccount(i);
			c2 = contas.get(j);
			System.out.println(c2.saldo);
			if(c2==null) throw new InvalidAccount(j);
			
			if(i < j)
			{
				c1.contaLock.lock();
				c2.contaLock.lock();
			}
			else
			{
				c2.contaLock.lock();
				c1.contaLock.lock();
			}
		}
		finally
		{
			bancoLock.unlock();	
		}
		try
		{
			if(c1.saldo < m) 
			{
				c1.contaLock.unlock();
				c2.contaLock.unlock();
				throw new NotEnoughFunds(m);
			}
		}
		finally
		{
		c1.saldo-=m;
		c1.contaLock.unlock();
		c2.saldo+=m;
		c2.contaLock.unlock();
		}
	}	

	public float totalBalance(int accounts[]) throws InvalidAccount
	{		
		int sum = 0;
		accounts = accounts.clone();
		Arrays.sort(accounts);
		bancoLock.lock();

		Conta[] novo = new Conta[accounts.length];
		
		try
		{
			for(int i = 0; i<accounts.length;i++)
			{
				novo[i]=contas.get(accounts[i]);
				if(novo[i]==null)
				{
					throw new InvalidAccount(accounts[i]);
				}
			}
			for(Conta c : novo)
			{
				c.contaLock.lock();
			}
		}
		finally
		{
			bancoLock.unlock();			
		}
		for(Conta c : novo)
		{
			sum+=c.saldo;
			c.contaLock.unlock();
		}
		return sum;
	}
	
	public float closeAccount(int id) throws InvalidAccount
	{
		Conta c;
		bancoLock.lock();
		try
		{
			c = contas.get(id);

			if(c == null)
			{
				throw new InvalidAccount(id);
			}
			contas.remove(id);
			c.contaLock.lock();
		}
		finally
		{
			bancoLock.unlock();
		}
		try
		{
			return c.saldo;
		}
		finally
		{
			c.contaLock.unlock();
		}
	}
}
	

