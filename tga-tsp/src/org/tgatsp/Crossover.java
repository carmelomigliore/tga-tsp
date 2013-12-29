package org.tgatsp;



import java.util.HashMap;
import java.util.Random;

public class Crossover{

	private Population pop;
	private Random rand;
	private Tour[] parents;
	private final Tour ret[];
	private short[] parent0;
	private short[] parent1;
	private static final Object obj= new Object();
	
	public Crossover(Random rand)
	{
		this.rand=rand;
		this.parents= new Tour[2];
		this.ret= new Tour[2];
		this.parent0=new short[Cliente.listaClienti.length];
		this.parent1=new short[Cliente.listaClienti.length];
	}
	
	public void setPopulation(Population pop)
	{
		this.pop=pop;
	}

	
	public Tour[] call()
	{
		Tour offspring2;
		Tour offspring1;
		parents=pop.tournamentSelection(TGA.tournamentCoefficient, rand);
		parent0=parents[0].getTour();
		parent1=parents[1].getTour();
		offspring1=offspringOX(parent0, parent1, rand);
		offspring2= offspringOX(parent1, parent0, rand);
		ret[0]=offspring1;
		ret[1]=offspring2;

		/*if(rand.nextFloat()<0.01)
		{
			ret[0].mutate(rand);
			ret[1].mutate(rand);
		}*/
		return ret;
	}
	
	/*
	 * Ordered Crossover OX
	 */
	private static final Tour offspringOX(short[] parent1, short[] parent2, Random rand)
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
		
		final short[] off= new short[parent1.length];
		System.arraycopy(parent1, cut1, off, cut1, cut2-cut1);
		HashMap<Short,Object> hs=null;
		if(Cliente.listaClienti.length>400)
		{
			hs= new HashMap<Short,Object>(cut2-cut1);
			for(int k=cut1; k<cut2; k++)
			{
				hs.put(off[k],obj);
			}
		}
		int dim=parent1.length;
		int i=cut2;
		for(int j=cut2; j<cut2+dim; j++)
		{	
			if((i+dim)%dim==cut1)
				break;
			short c= parent2[(j+dim)%dim];
			if(Cliente.listaClienti.length<=400)
			{
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
			else
			{
				if(!hs.containsKey(c))
				{
					off[(i+dim)%dim]= c;
					i++;
				}
			}
		}
		if(rand.nextFloat()<0.001)
		{
			Tour.mutate(off,rand);
		}
		boolean noLook[] = new boolean[Cliente.listaClienti.length+1];
		
		boolean again=true;
		while(again)
		{
			again=Tour.fixedRadiusNolookNearMatrix(off,noLook);
		}		
		
		return new Tour(off,-1);
		
	}
	
	/*
	 * OX5 variant of Ordered Crossover (not used)
	 */
/*	public static Tour offspringOX5(int[] parent1, int[] parent2, Random rand)
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
		
		
		short[] off= new int[parent1.length];
		
		//From first to second cutpoint
		System.arraycopy(parent1, random[0], off, random[0], random[1]-random[0]);
		
		//From third to fourth cutpoint
		System.arraycopy(parent1, random[2], off, random[2], random[3]-random[2]);
		
		HashMap<Integer,Integer> hs= new HashMap<Integer,Integer>((random[3]-random[2])+(random[1]-random[0]));
		for(int k=random[0]; k<random[1]; k++)
		{
			Integer i= off[k];
			hs.put(i,i);
		}
		for(int k=random[2]; k<random[3]; k++)
		{
			Integer i= off[k];
			hs.put(i,i);
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
			int c= parent2[(j+dim)%dim];
			if(!hs.containsKey(c))
			{
				off[(i+dim)%dim] =c;
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
		System.out.println(off);
		*/
	/*	boolean noLook[] = new boolean[Cliente.listaClienti.length+1];
		
		for(int k = 0; k< Cliente.listaClienti.length+1; k++)
		{
			noLook[k] = false;
		}
		
		boolean again=true;
		while(again)
		{
			again=Tour.fixedRadiusNolookNearMatrix(off,noLook);
		}		
		
		return new Tour(off,-1);
	}*/
		
	/*
	 * Partially Matched Crossover (PMX) - Not used
	 */
/*	public static Tour offspringPMX(Cliente[] parent1, Cliente[] parent2, int inf, int sup)
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
	/*	for(int k=sup; k<parent1.length; k++) 
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
		/*for(int k=0; k<inf; k++)
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
		return new Tour(temp,-1);	
		//Tour s=new Tour(new ArrayList<Cliente>(Arrays.asList(temp)),null);
		//System.out.println(s.getlength());
		//return s;
	}*/		
}
	

