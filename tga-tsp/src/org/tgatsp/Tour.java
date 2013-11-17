package org.tgatsp;

import java.util.*;

public class Tour implements Cloneable
{
	private ArrayList<Cliente> tour;
	private Float lenght;
	
	private Tour (ArrayList<Cliente> t, float lenght)
	{
		this.tour=t;
		this.lenght=lenght;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object clone()
	{
		Tour t = new Tour((ArrayList<Cliente>)tour.clone(), lenght.floatValue());
		return t;
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
	
	private void calculateLenght()
	{
		float temp =0;
		for(int i=1; i<tour.size(); i++)
		{
			temp+=tour.get(i).calculateDistance(tour.get(i-1));    // Distance between i and i-1
		}
		lenght= new Float(temp);	
	}
	
	public float getLenght()
	{
		if (lenght!=null)
		{
			return lenght.floatValue();
		}
		else
		{
			this.calculateLenght();
			return lenght.floatValue();
		}
	}
	
	public void setFitness(float lenght)
	{
		this.lenght=lenght;
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