package org.tgatsp;

import java.util.*;

public class Population {
	
	private ArrayList<Solution> population; //TODO provare pure LinkedList
	private int size;
	private float sumFitness;
	
	public Population(int size)
	{
		this.population=new ArrayList<Solution>(size);
		this.size=size;
	}
	
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
	
	public Solution evaluate()
	{
		float max =0;
		float currentSum=0;
		Solution temp=null;
		for (Solution s : population)
		{
			if(s.getFitness()>max)
			{
				max=s.getFitness();
				temp=s;
			}
			currentSum+=s.getFitness();
			
		}
		sumFitness=currentSum;
		
		population.get(0).setWindow(population.get(0).getFitness()/sumFitness);
		for(int i=1; i<size; i++)
		{
			population.get(i).setWindow(population.get(i-1).getWindow()+(population.get(i).getFitness()/sumFitness));
		}
		
		return temp;
	}
	
	public Solution[] selectParents(Random rand)
	{
		float random=rand.nextFloat();
		int i;
		Solution[] ret= new Solution[2];
		for(i=0; i<size; i++)
		{
			if(population.get(i).getWindow()>random)
				break;
		}
		ret[0]=population.get(i);
		
		do
		{
			random=rand.nextFloat();
			for(i=0; i<size; i++)
			{
				if(population.get(i).getWindow()>random)
					break;
			}
		} while(population.get(i)==ret[0]);
		ret[1]=population.get(i);
		return ret;
	}
	
}
