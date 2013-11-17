package org.tgatsp;

import java.util.*;

public class Tour
{
	private ArrayList<Cliente> tour;
	private Float fitness;
	boolean complete;
	
	public Tour ()
	{
		complete=false;
	}
	
	public Tour (ArrayList<Cliente> t, boolean complete)
	{
		this.tour=t;
		this.complete=complete;
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
	
	private void calculateFitness()
	{
		float temp =0;
		for(int i=1; i<tour.size(); i++)
		{
			temp+=tour.get(i).calculateDistance(tour.get(i-1));    // Distance between i and i-1
		}
		fitness= new Float(temp);	
	}
	
	public float getFitness()
	{
		if (fitness!=null)
		{
			return fitness.floatValue();
		}
		else
		{
			this.calculateFitness();
			return fitness.floatValue();
		}
	}
	
	public void setFitness(float fitness)
	{
		this.fitness=fitness;
	}
	
	public boolean isComplete()
	{
		return complete;
	}
	
	public void setComplete(boolean complete)
	{
		this.complete=complete;
	}
	
}