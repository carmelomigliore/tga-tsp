package org.tgatsp;

import java.util.HashMap;
import java.util.Random;

public class PMXCrossover{

	private Population pop;
	//private final ExecutorService pool;
	private Random rand;
	private final int deadlockThreshold;
	private final Solution[] parents;
	private final Solution ret[];
	
	public PMXCrossover(int deadlockThreshold, Random rand)
	{
		this.rand=rand;
		this.deadlockThreshold=deadlockThreshold;
		this.parents= new Solution[2];
		this.ret= new Solution[2];
	}
	
	public void setPopulation(Population pop)
	{
		this.pop=pop;
	}

	
	public Solution[] call()
	{
		int inf; 
		int sup;
		int temp;
		int counter=0;
		Tour offspring2;
		Tour offspring1;
		
		parents[0]=pop.selectParent(rand);
		
		while(counter<deadlockThreshold)
		{
				do
				{
					parents[1]=pop.selectParent(rand);
				} while(parents[0]==parents[1]);
			
				inf=rand.nextInt(Cliente.listaClienti.length);
		
				//verifica che sup!=inf e che sup e inf non siano contemporaneamente agli estremi
				do
				{
					sup = rand.nextInt(Cliente.listaClienti.length);
				}while(sup==inf || (inf==0 && sup == Cliente.listaClienti.length) || (inf==Cliente.listaClienti.length && sup == 0));
		
				if (sup<inf)
				{
					temp = inf;
					inf = sup;
					sup = temp;
				}
		
				offspring1= offspring(parents[0].getChromosome(), parents[1].getChromosome(), inf, sup);
				offspring2= offspring(parents[1].getChromosome(), parents[0].getChromosome(), inf, sup);
		
				ret[0]=new Solution(offspring1,null, 1/(float)offspring1.getlength());
				ret[1]=new Solution(offspring2,null, 1/(float)offspring2.getlength());
				//Tour.localSearch(ret[0].getChromosome());
				//TODO provare pure ad applicare 2-opt (specialmente se la convergenza è lenta)
			
				//controllo tabu
				Integer idclan0=parents[0].getClan().getId();
				Integer idclan1=parents[1].getClan().getId();
				if(!(((parents[0].getClan().isTabu(idclan1))) || (parents[1].getClan().isTabu(idclan0))))
				{
					//Tour.localSearch(ret[0].getChromosome());
					//Tour.localSearch(ret[1].getChromosome());
					parents[0].getClan().addTabu(idclan1);
					ret[0].setClan(parents[0].getClan().copy());
					parents[1].getClan().addTabu(idclan0);
					ret[1].setClan(parents[1].getClan().copy());
					return ret;
				}
				//else
				//aspiration criteria
					if(ret[0].getFitness()>TGA.DuncanMacLeod.getFitness() || ret[1].getFitness()>TGA.DuncanMacLeod.getFitness())
					{
					
						parents[0].getClan().addTabu(idclan1);
						ret[0].setClan(parents[0].getClan().copy());
						parents[1].getClan().addTabu(idclan0);
						ret[1].setClan(parents[1].getClan().copy());
						TGA.tabuCount.incrementAndGet();
						return ret;
					}
				counter++;
		}
		
		ret[0].mutate(rand);
		ret[1].mutate(rand);
		return ret;
		
	}
	
		
	public static Tour offspring(Tour parent1, Tour parent2, int inf, int sup)
	{
		Tour temp = new Tour(parent1.getSize());
		for(int i=inf; i<sup; i++)
		{
			temp.addCliente(i,parent1.getCliente(i));
		}
		
		HashMap<Cliente,Cliente> crossmap = new HashMap<Cliente,Cliente>(sup-inf);
		for(int j=inf; j<sup; j++)
		{
			crossmap.put(parent1.getCliente(j), parent2.getCliente(j));
		}
			
		Cliente c;
			
			/*
			 * ciclo for del secondo cutting point
			 */
		//System.out.println("1) "+parent1+"\n2)"+parent2);
		for(int k=sup; k<parent1.getSize(); k++) 
		{
			c=parent2.getCliente(k);
			while((crossmap.get((c))!=null))	
			{
				c=crossmap.get(c);
			}
			temp.addCliente(k, c);
		}
			
			/*
			 * ciclo for del primo cutting point
			 */
		for(int k=0; k<inf; k++)
		{
			c=parent2.getCliente(k);
			while((crossmap.get(c)!=null))
			{
				c=crossmap.get(c);
			}
			temp.addCliente(k, c);
		}
	
		Tour.localSearch(temp);
		return temp;		
	}		
}
	

