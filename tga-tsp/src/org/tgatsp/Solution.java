package org.tgatsp;

import java.util.Random;

public class Solution{
	
	private Tour chromosome;
	private Clan clan;
	private Float fitness;
	private Float window;
	
	public Solution(Tour t, Clan c, float fitness)
	{
		chromosome=t;
		clan=c;
		this.fitness=fitness;
	}
	
	public Tour clonechromosome()
	{
		return (Tour)chromosome.copy();
	}
	
	public Clan cloneClan()
	{
		return (Clan)clan.copy();
	}
	
	public Tour getchromosome()
	{
		return chromosome;
	}
	
	public void setchromosome(Tour t)
	{
		this.chromosome=t;
	}
	
	public Clan getClan()
	{
		return clan;
	}
	
	public void setClan(Clan c)
	{
		this.clan=c;
	}
	
	public void setFitness(float fit)
	{
		fitness=fit;
	}
	
	public float getFitness()
	{
		if(fitness!=null)
			return fitness;
		else
		{
			fitness=1/chromosome.getlength();
			return fitness;
		}
	}
	
	public void setWindow(float window)
	{
		this.window=window;
	}
	
	public float getWindow()
	{
		return window;
	}
	
	public String toString()
	{
		return chromosome.toString() + "|" + clan.toString() + " Fitness: "+fitness + "\n";
	}
	
	public void mutate(Random rand)
	{
		Tour t = new Tour(chromosome.getSize());
		int cut = rand.nextInt(chromosome.getSize());
		
		for(int j=cut; j<chromosome.getSize(); j++)
		{
			
		}
	}

}
