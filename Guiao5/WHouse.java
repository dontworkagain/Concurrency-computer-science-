package WH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WHouse 
{
	Lock l = new ReentrantLock();
	
	HashMap<String, Produto> produtos = new HashMap<String, Produto>();
	
	
	public Produto get(String s)
	{
		Produto p = produtos.get(s);
		if(p==null)
		{
			p= new Produto(0, l.newCondition());
			produtos.put(s,p);
		}
		return p;
	}
	
	public void supply(String s, int q)
	{
		l.lock();
		try{
			Produto p = get(s);
			p.quantidade+=q;
			p.cond.signalAll();
		}finally
		{
			l.unlock();
		}
	}
	
	public Produto[] listaProdutos(String[] items)
	{
		Produto[] ap = new Produto[items.length];
		for(int i = 0 ; i<items.length; i++)
		{
			ap[i]=get(items[i]);
		}
		return ap;
	}
	
	public void actualizaProdutos(Produto[] ap)
	{
		for(Produto p : ap)
		{
			p.quantidade--;
		}
	}
	public int teste(Produto[] ap)
	{
		for(Produto p : ap)
		{
			if(p.quantidade == 0)
			{
				return 0;
			}
		}
		return 1;
		
	}
	
	private void reporStock(Produto[] ap) throws InterruptedException 
	{
		ArrayList<Produto> produtoEmFalta = new ArrayList<Produto>();
		for( Produto p : ap)
		{
			if(p.quantidade == 0)
			{
				produtoEmFalta.add(p);
			}
		}
		for(Produto p : produtoEmFalta)
		{
			p.cond.await();
		}
		
	}
	
	public void consume(String[] items) throws InterruptedException
	{
		l.lock();
		try
		{
			Produto[] ap = null;
			ap = listaProdutos(items);
			while(teste(ap)==0)
			{
				reporStock(ap);
			}
			actualizaProdutos(ap);
		}
		finally
		{
			l.unlock();
		}		
	}

}
