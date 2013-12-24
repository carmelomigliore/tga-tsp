package org.tgatsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class PMXCrossover{

	private Population pop;
	//private final ExecutorService pool;
	private Random rand;
	private Solution[] parents;
	private final Solution ret[];
	private Cliente[] parent0;
	private Cliente[] parent1;
	
	public PMXCrossover(int deadlockThreshold, Random rand)
	{
		this.rand=rand;
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
		Tour offspring2;
		Tour offspring1;
		
		
		
		
		
		//while(counter<deadlockThreshold)
		//{		
				/*parents[0]=pop.selectParent(rand);
				do
				{
					parents[1]=pop.selectParent(rand);
				} while(parents[0]==parents[1]);
				
				//long now=System.currentTimeMillis();
				//double current=(double)((now-TGA.prima)-TGA.timelimit)/130000.0;
				//int tournamentSize=(int)(TGA.tournamentCoefficient*(Math.pow(Math.E,current))*TGA.populationSize+1);
				*/
				if(Cliente.listaClienti.length<400)
				{
					parents=pop.tournamentSelection(1, rand);
				}
				else
				{
					if(TGA.currentEpoch<90)
						parents=pop.tournamentSelection(3, rand);
					else
						parents=pop.tournamentSelection(3, rand);
				}
				parent0=parents[0].getChromosome().getTour().toArray(parent0);
				parent1=parents[1].getChromosome().getTour().toArray(parent1);
				
				//if(TGA.currentEpoch<200)
				//{
				/*	inf=rand.nextInt(Cliente.listaClienti.length);
					
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
					offspring1= offspringPMX(parent0, parent1, inf, sup);
					offspring2= offspringPMX(parent1, parent0, inf, sup);
			//	}
				/*
				else*/
				{
					offspring1=offspringOX(parent0, parent1, rand);
					offspring2= offspringOX(parent1, parent0, rand);
				}
		
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
		if(rand.nextFloat()<0.01)
		{
			ret[0].mutate(rand);
			ret[1].mutate(rand);
		}
		return ret;
	
		
	}
	
	
	public static Tour offspringOX(Cliente[] parent1, Cliente[] parent2, Random rand)
	{
		int cut1= rand.nextInt(parent1.length);
		int cut2;
		do
		{
			cut2= rand.nextInt(parent1.length);
		}while(cut1==cut2);
		
		if(cut2<cut1)
		{
			int temp=cut1;
			cut1=cut2;
			cut2=temp;
		}
		
		final Cliente[] off= new Cliente[parent1.length];
		
		//From first to second cutpoint
		for(int j=cut1; j<cut2; j++)
		{
			off[j]= parent1[j];
		}
		
		
		int dim=parent1.length;
		int i=cut2;
		for(int j=cut2; j<cut2+dim; j++)
		{	
			if((i+dim)%dim==cut1)
				break;
			Cliente c= parent2[(j+dim)%dim];
			boolean contained=false;
			for(int k=cut1; k<cut2; k++)
			{
				if(off[k]==c)
				{
					contained=true;
					break;
				}
			}
			if(!contained)
			{
				off[(i+dim)%dim]= c;
				i++;
			}
		}
		boolean noLook[] = new boolean[Cliente.listaClienti.length+1];
		
		for(int k = 0; k< Cliente.listaClienti.length+1; k++)
		{
			noLook[k] = false;
		}
		
		boolean again=true;
		while(again)
		{
			again=Tour.fixedRadiusNolookNear(off,noLook);
		}		
		
		return new Tour((new ArrayList<Cliente>(Arrays.asList(off))),-1);
		
	}
	
	public static Tour offspringOX5(Cliente[] parent1, Cliente[] parent2, Random rand)
	{
		ArrayList<Integer> ar= new ArrayList<Integer>();
		for (int i=0; i<4; i++)
		{
			Integer random= rand.nextInt(parent1.length);
			if(!ar.contains(random))
				ar.add(random);
			else
				i--;
		}
		Collections.sort(ar);
		
		int[] random= new int[4];
		random[0]=ar.get(0);
		random[1]=ar.get(1);
		random[2]=ar.get(2);
		random[3]=ar.get(3);
		
		
		ArrayList<Cliente> off= new ArrayList<Cliente>(parent1.length);
		while(off.size() < parent1.length) off.add(null);
		
		//From first to second cutpoint
		for(int j=random[0]; j<random[1]; j++)
		{
			off.set(j, parent1[j]);
		}
		
		//From third to fourth cutpoint
		for(int j=random[2]; j<random[3]; j++)
		{
			off.set(j, parent1[j]);
		}
		
		//Copy from parent 2
		int i=random[3];
		int dim=parent1.length;
		for(int j=random[3]; j<random[3]+dim; j++)
		{	
			if((i+dim)%dim==random[0])
				i=random[1];
			else if((i+dim)%dim==random[2])
				break;
			Cliente c= parent2[(j+dim)%dim];
			if(!off.contains(c))
			{
				off.set((i+dim)%dim, c);
				i++;
			}
		}
		/*for(Cliente c: parent1)
			System.out.print(c.toString()+", ");
		System.out.println();
		System.out.println();
		for(Cliente c: parent2)
			System.out.print(c.toString()+", ");
		System.out.println();
		System.out.println();
		System.out.println(off);*/
		
		Cliente[] temp = new Cliente[parent1.length];
		temp=off.toArray(temp);
		boolean noLook[] = new boolean[Cliente.listaClienti.length+1];
		
		for(int k = 0; k< Cliente.listaClienti.length+1; k++)
		{
			noLook[k] = false;
		}
		
		boolean again=true;
		while(again)
		{
			again=Tour.fixedRadiusNolookNear(temp,noLook);
		}		
		
		return new Tour((new ArrayList<Cliente>(Arrays.asList(temp))),-1);
	}
		
	public static Tour offspringPMX(Cliente[] parent1, Cliente[] parent2, int inf, int sup)
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
		return new Tour(new ArrayList<Cliente>(Arrays.asList(temp)),-1);	
		//Tour s=new Tour(new ArrayList<Cliente>(Arrays.asList(temp)),null);
		//System.out.println(s.getlength());
		//return s;
	}		
}
	

