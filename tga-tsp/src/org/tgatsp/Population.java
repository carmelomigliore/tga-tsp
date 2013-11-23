package org.tgatsp;

import java.util.*;

public class Population {
	
	private List<Solution> population;
	private int size;
	
	public void addSolution (Solution s)
	{
		population.add(s);
	}
	
	public void kill(Solution s)
	{
		for (Iterator<Solution> it = population.iterator(); it.hasNext();)
		{
			if(it.next().equals(s))
			{
				it.remove();
				break;
			}
		}
	}
	
	public int getSize()
	{
		return size;
	}
	
	public void setSize(int s)
	{
		size=s;
	}
	
}
