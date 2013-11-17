package org.tgatsp;

public class Solution implements Cloneable{
	
	private Tour gene;
	private Clan clan;
	private float fitness;
	
	public Solution(Tour t, Clan c, float fitness)
	{
		gene=t;
		clan=c;
		this.fitness=fitness;
	}
	
	public Tour cloneGene()
	{
		return (Tour)gene.clone();
	}
	
	public Clan cloneFitness()
	{
		return (Clan)clan.clone();
	}
	
	public Tour getGene()
	{
		return gene;
	}
	
	public void setGene(Tour t)
	{
		this.gene=t;
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
		return fitness;
	}

}
