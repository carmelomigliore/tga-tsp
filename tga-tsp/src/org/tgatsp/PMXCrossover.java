package org.tgatsp;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class PMXCrossover implements Callable<Solution[]>{

	private Population pop;
	private ExecutorService pool;
	private Random rand;
	private int deadlockThreshold;
	
	public PMXCrossover(Population pop, ExecutorService pool, Random rand, int deadlockThreshold)
	{
		this.pop=pop;
		this.pool=pool;
		this.rand=rand;
		this.deadlockThreshold=deadlockThreshold;
	}
	


	
	public Solution[] call()
	{
		int inf; 
		int sup;
		Solution[] parents;
		Solution ret[]= new Solution[2];
		//TODO ricordarsi di escludere gli estremi in contemporanea DONE
		int counter=0;
		
		while(counter<deadlockThreshold)
		{
			parents=pop.selectParents(rand);
			inf=rand.nextInt(parents[0].getchromosome().getSize());
		
			//verifica che sup!=inf e che sup e inf non siano contemporaneamente agli estremi
			do
			{
				sup = rand.nextInt(parents[0].getchromosome().getSize());
			}while(sup==inf || (inf==0 && sup == parents[0].getchromosome().getSize()) || (inf==parents[0].getchromosome().getSize() && sup == 0));
		
			if (sup<inf)
			{
				int temp = inf;
				inf = sup;
				sup = temp;
			}
		
			Offspring off1= new Offspring(parents[0].getchromosome(), parents[1].getchromosome(), inf, sup);
			Offspring off2= new Offspring(parents[1].getchromosome(), parents[0].getchromosome(), inf, sup);
			Future<Tour> res= pool.submit(off1);
		
			Tour offspring2= off2.call();
		
			Tour offspring1=null;
			try
			{
				offspring1= res.get();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
			ret[0]=new Solution(offspring1,null, 1/offspring1.getlength());
			ret[1]=new Solution(offspring2,null, 1/offspring2.getlength());
			
			//se non sono tabu
			Integer idclan0=parents[0].getClan().getId();
			Integer idclan1=parents[1].getClan().getId();
			if(!((parents[0].getClan().isTabu(idclan1))) || (parents[1].getClan().isTabu(idclan0)))
			{
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
				return ret;
			}
			counter++;
		}
		//ret[0].mutation();
		//ret[1].mutation();
		return ret;
		
	}
	
	private class Offspring implements Callable<Tour>
	{
		private Tour parent1; //ricordarsi di invertire i genitori chiamando i thread
		private Tour parent2;
		private int inf;
		private int sup;
		
		public Offspring(Tour parent1, Tour parent2, int inf, int sup)
		{
			this.parent1=parent1;
			this.parent2=parent2;
			this.inf=inf;
			this.sup=sup;
		}
		
		public Tour call()
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
				while((crossmap.get((c))!=null))
				{
					c=crossmap.get(c);
				}
				temp.addCliente(k, c);
			}
			return temp;		
		}
		
		
	}
	
}

