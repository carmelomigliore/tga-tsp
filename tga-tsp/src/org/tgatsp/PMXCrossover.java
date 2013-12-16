package org.tgatsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class PMXCrossover{

	private Population pop;
	//private final ExecutorService pool;
	private Random rand;
	private final int deadlockThreshold;
	private Solution[] parents;
	private final Solution ret[];
	private Cliente[] parent0;
	private Cliente[] parent1;
	
	public PMXCrossover(int deadlockThreshold, Random rand)
	{
		this.rand=rand;
		this.deadlockThreshold=deadlockThreshold;
		this.parents= new Solution[2];
		this.ret= new Solution[2];
		this.parent0=new Cliente[Cliente.listaClienti.length];
		this.parent1=new Cliente[Cliente.listaClienti.length];
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
		
		
		//while(counter<deadlockThreshold)
		//{
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
				parent0=parents[0].getChromosome().getTour().toArray(parent0);
				parent1=parents[1].getChromosome().getTour().toArray(parent1);
				
		
				offspring1= offspring(parent0, parent1, inf, sup);
				offspring2= offspring(parent1, parent0, inf, sup);
		
				ret[0]=new Solution(offspring1,null, 1/(float)offspring1.getlength());
				ret[1]=new Solution(offspring2,null, 1/(float)offspring2.getlength());
				//Tour.localSearch(ret[0].getChromosome());
			
			if(TGA.tabuSize!=0)
			{
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
			}
		//	counter++;
		//}
		if(rand.nextFloat()<0.2)
		{
			ret[0].mutate(rand);
			ret[1].mutate(rand);
		}
		return ret;
	
		
	}
	
		
	public static Tour offspring(Cliente[] parent1, Cliente[] parent2, int inf, int sup)
	{
		Cliente[] temp = new Cliente[parent1.length];
		for(int i=inf; i<sup; i++)
		{
			temp[i]=parent1[i];
		}
		
		HashMap<Cliente,Cliente> crossmap = new HashMap<Cliente,Cliente>(sup-inf);
		for(int j=inf; j<sup; j++)
		{
			crossmap.put(parent1[j], parent2[j]);
		}
			
		Cliente c;
			
			/*
			 * ciclo for del secondo cutting point
			 */
		//System.out.println("1) "+parent1+"\n2)"+parent2);
		for(int k=sup; k<parent1.length; k++) 
		{
			c=parent2[k];
			while((crossmap.get((c))!=null))	
			{
				c=crossmap.get(c);
			}
			temp[k]= c;
		}
			
			/*
			 * ciclo for del primo cutting point
			 */
		for(int k=0; k<inf; k++)
		{
			c=parent2[k];
			while((crossmap.get(c)!=null))
			{
				c=crossmap.get(c);
			}
			temp[k]= c;
		}
		
		boolean noLook[] = new boolean[Cliente.listaClienti.length+1];
		
		for(int i = 0; i< Cliente.listaClienti.length+1; i++)
		{
			noLook[i] = false;
		}
	
		//if(TGA.currentEpoch%10==0)
		//	Tour.localSearch(temp);
		//else
		//while(Tour.fixedRadiusNolook(temp,noLook));
		//for(int i=0; i<5; i++)
		boolean again=true;
		//Tour.threeOpt(temp);
		//for(int i=0; i<5; i++)
		//Tour.threeOpt(temp);
		
		while(again)
		{
			again=Tour.fixedRadiusNolookNear(temp,noLook);
		}
		
		//Tour t=new Tour(new ArrayList<Cliente>(Arrays.asList(temp)),null);
		//System.out.println(t.getlength());
		//for(int i=0; i<5; i++)
		//	Tour.fixedRadius3Opt(temp);
		return new Tour(new ArrayList<Cliente>(Arrays.asList(temp)),null);	
		//Tour s=new Tour(new ArrayList<Cliente>(Arrays.asList(temp)),null);
		//System.out.println(s.getlength());
		//return s;
	}		
}
	

