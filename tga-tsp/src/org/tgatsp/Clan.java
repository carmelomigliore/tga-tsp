package org.tgatsp;

import java.util.Iterator;
import java.util.concurrent.*;

public class Clan {
	
	private final Integer id;
	private final LinkedBlockingQueue<Integer> tabu;
	
	
	public Clan (Integer id, int tabuSize)
	{
		this.id=id;
		this.tabu= new LinkedBlockingQueue<Integer>(tabuSize);
	}
	
	//public Clan (int tabuSize)
	//{
	//	this.tabu= new LinkedBlockingQueue<Integer>(tabuSize);
	//}
	
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
	
	//public synchronized void setId(int id)
	//{
	//	this.id=id;
	//}
	
	public Integer getId()
	{
		return id; 
	}

	
	public LinkedBlockingQueue<Integer> getTabu()
	{
		return tabu;
	}
	
	public boolean isTabu (Integer clan)
	{
		if (tabu.contains(clan))
			return true;
		else
			return false;
	}
	
	public void addTabu(Integer clan)
	{
		while(!tabu.offer(clan))
		{
			tabu.poll();
		}
	}

}
