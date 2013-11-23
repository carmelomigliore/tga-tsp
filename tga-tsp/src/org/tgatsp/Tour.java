package org.tgatsp;

import java.util.*;

public class Tour
{
	private ArrayList<Cliente> tour;
	private Float length;
	
	public Tour (int size)
	{
		this.tour = new ArrayList<Cliente>(size);
	}
	
	public Tour (ArrayList<Cliente> t, Float length)
	{
		this.tour=t;
		this.length=length;
	}
	
	public Tour (Tour t)
	{
		this.tour=t.tour;
		this.length=t.length;
	}
	

	public Tour copy()
	{ 
		ArrayList <Cliente> temp = new ArrayList<Cliente>(tour.size());
		for (Iterator<Cliente> it= tour.iterator(); it.hasNext();)
		{
			temp.add(it.next());
		}
		return new Tour (temp, null);
	}
	
	public boolean addCliente(Cliente c)
	{
		if(!tour.contains(c))
		{
			tour.add(c);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void calculatelength()
	{
		float temp =0;
		for(int i=1; i<tour.size(); i++)
		{
			temp+=tour.get(i).calculateDistance(tour.get(i-1));    // Distance between i and i-1
		}
		length= new Float(temp);	
	}
	
	public float getlength()
	{
		if (length!=null)
		{
			return length.floatValue();
		}
		else
		{
			this.calculatelength();
			return length.floatValue();
		}
	}
	
	public void setFitness(float length)
	{
		this.length=length;
	}
	
	public ArrayList<Cliente> getTour()
	{
		return tour;
	}
	
	public boolean equals(Tour t)
	{
		if (tour.equals(t.getTour()))
			return true;
		else
			return false;
	}
	
	
}