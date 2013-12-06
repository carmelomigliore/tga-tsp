package org.tgatsp;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	public Float getFitness()
	{
		if(fitness!=null)
			return fitness;
		else
		{
			fitness=1/(float)chromosome.getlength();
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
		//return chromosome.toString() + "|" + clan.toString() + " Fitness: "+fitness + "\n";
		
		return "\nLenght: "+chromosome.getlength()+"\n"+chromosome;
	}
	
	public static Solution randomOffspring(Random rand)
	{
		Tour t = new Tour(Cliente.listaClienti.length);
		ArrayList<Cliente> temp = new ArrayList<Cliente> (Arrays.asList(Cliente.listaClienti));
		int k=Cliente.listaClienti.length;
		int rand1;
		for(int i =0; i<Cliente.listaClienti.length; i++)
		{
		   	rand1 = rand.nextInt(k);
		   	t.addCliente(i, temp.remove(rand1));
		   	k--;
		}
		Clan c = new Clan(TGA.populationSize+TGA.mutationCount.incrementAndGet(),TGA.tabuSize);
		return new Solution(t,c,null);
	}
	
	public void mutate(Random rand)
	{
		
		int customerToDisplace = rand.nextInt(chromosome.getSize());
		int insertionPosition;
		do
		{
			insertionPosition = rand.nextInt(chromosome.getSize());
			
		} while (insertionPosition == customerToDisplace);
		//System.out.println(chromosome);
		Cliente c = chromosome.removeCliente(customerToDisplace);
		chromosome.insertCliente(insertionPosition, c);
		//System.out.println(chromosome);
		clan=new Clan(TGA.populationSize+TGA.mutationCount.incrementAndGet(), TGA.tabuSize);
			
	}
	@Override
	public boolean equals(Object s)
	{
		if (this==s || this.chromosome.equals(s))
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
	    return 0;
	}
	
	

}
