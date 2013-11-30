package org.tgatsp;

import java.util.Random;

public class Solution{
	
	private final Tour chromosome;
	private Clan clan;
	private Float fitness;
	private Float window; //used for both parents selection and survival methods
	private boolean killingFlag;
	
	public Solution(Tour t, Clan c, Float fitness)
	{
		chromosome=t;
		clan=c;
		this.fitness=fitness;
	}
	
	public Tour clonechromosome()
	{
		return (Tour)chromosome.copy();
	}
	
	public Tour getChromosome()
	{
		return chromosome;
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
	
	public void setWindow(Float window)
	{
		this.window=window;
	}
	
	public Float getWindow()
	{
		return window;
	}
	
	public void setKillingFlag(boolean flag)
	{
		this.killingFlag=flag;
	}
	
	public boolean getKillingFlag()
	{
		return killingFlag;
	}
	
	public String toString()
	{
		return chromosome.toString() + "|" + clan.toString() + " Fitness: "+fitness + "\n";
	}
	
	public void mutate(Random rand)
	{
		int customerToDisplace = rand.nextInt(chromosome.getSize());
		int insertionPosition;
		do
		{
			insertionPosition = rand.nextInt(chromosome.getSize());
			
		} while (insertionPosition == customerToDisplace);
		
		Cliente c = chromosome.removeCliente(customerToDisplace);
		chromosome.insertCliente(insertionPosition, c);
		synchronized(TGA.mutationCount)
		{
			TGA.mutationCount++;
			clan=new Clan(TGA.populationSize+TGA.mutationCount, TGA.tabuCoefficient*TGA.populationSize);
		}
	}
	
	public boolean equals(Solution s)
	{
		if (this.chromosome.equals(s))
			return true;
		else
			return false;
	}
	

}
