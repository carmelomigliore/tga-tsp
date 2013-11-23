package org.tgatsp;

import java.util.Iterator;
import java.util.concurrent.*;

public class Clan {
	
	private int id;
	private LinkedBlockingQueue<Integer> tabu;
	
	
	public Clan (int id, int tabuSize)
	{
		this.id=id;
		this.tabu= new LinkedBlockingQueue<Integer>(tabuSize);
	}
	
	public Clan (int id, LinkedBlockingQueue<Integer> tabu)
	{
		this.id=id;
		this.tabu=tabu;
	}
	
	public Clan copy()
	{
		LinkedBlockingQueue<Integer> temp = new LinkedBlockingQueue<Integer>(tabu.size()+tabu.remainingCapacity());
		for (Iterator<Integer> it=tabu.iterator(); it.hasNext();)
		{
			temp.offer(it.next());
		}
		return new Clan(this.id, temp);
	}
	
	public void setId(int id)
	{
		this.id=id;
	}
	
	public int getId()
	{
		return id; 
	}

	public void setTabu(LinkedBlockingQueue<Integer> tabu)
	{
		this.tabu = tabu;
	}
	
	public LinkedBlockingQueue<Integer> getTabu()
	{
		return tabu;
	}
	
	
	public boolean equals(Clan c)
	{
		if (tabu.equals(c.getTabu()))
			return true;
		else
			return false;
	}

}
