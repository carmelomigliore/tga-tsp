package org.tgatsp;

import java.util.*;

public class Clan implements Cloneable{
	
	private int id;
	private ArrayList<Integer> tabu;
	private int tabuSize;
	
	
	private Clan (int id, ArrayList<Integer> tabu, int tabuSize)
	{
		this.id=id;
		this.tabu=tabu;
		this.tabuSize=tabuSize;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object clone()
	{
			Clan c = new Clan(this.id, (ArrayList<Integer>)tabu.clone(), this.tabuSize);
			return c;
	}
	
	public void setId(int id)
	{
		this.id=id;
	}
	
	public int getId()
	{
		return id; 
	}

	public void setTabu(ArrayList<Integer> tabu)
	{
		this.tabu = tabu;
	}
	
	public ArrayList<Integer> getTabu()
	{
		return tabu;
	}
	
	public int getTabuSize()
	{
		return tabuSize;
	}
	
	public void setTabuSize(int tabuSize)
	{
		this.tabuSize=tabuSize;
	}
	
	
	public boolean equals(Clan c)
	{
		if (tabu.equals(c.getTabu()))
			return true;
		else
			return false;
	}

}
