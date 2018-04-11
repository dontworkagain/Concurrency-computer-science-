package WH;

import java.util.concurrent.locks.Condition;

class Produto 
{
	int quantidade;
	Condition cond;
	
	Produto (int q, Condition c)
	{
		this.quantidade = q;
		this.cond = c;
	}

}
