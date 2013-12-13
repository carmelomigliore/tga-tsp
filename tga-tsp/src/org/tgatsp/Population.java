package org.tgatsp;


import java.util.*;

public class Population {
	
	private ArrayList<Solution> population; //TODO provare pure LinkedList
	private Float sumFitness;
	private Float sumLength;
	
	public Population(int size)
	{
		this.population=new ArrayList<Solution>(size);
	}
	
	public Population(ArrayList<Solution> pop)
	{
		this.population=pop;
	}
	
	public ArrayList<Solution> getPopulation()
	{
		return population;
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
	
	public Integer getSize()
	{
		return population.size();
	}
	
	/*
	 * Returns the new Highlander
	 */
	private void calculateDuncan()
	{
		float max =0;
		//float debug;
		for(int i=0; i<TGA.highlandersDimension; i++)
		{
			TGA.highlanders[i]=null;
		}
		//TGA.highlanders.clear();
		Solution temp=null;
		int count=0;
		boolean contained;
		//System.out.println("\n6Duncan==Connor"+(TGA.ConnorMacLeod==TGA.DuncanMacLeod));
		//if(TGA.DuncanMacLeod!=null)
			//System.out.println("6fitness"+TGA.DuncanMacLeod.getChromosome().getlength());
		while(count<TGA.highlandersDimension)
		{
			max=0;
		//System.out.println(population);
			for (Solution s : this.population)
			{
				contained=false;
				if(s.getFitness()>max)
				{
					for (Solution sol : TGA.highlanders)
					{
						//boolean debug=(sol==s);
						if(sol==s)
						{
							contained=true;
							break;
						}
					}
					if(!contained)
					{
						max=s.getFitness();
						temp=s;
					}
				}
			}
			TGA.highlanders[count]=temp;
			count++;
		}
		TGA.DuncanMacLeod=TGA.highlanders[0];
		
	}
	
	private void calculateFitnessSum()
	{
		float currentSum=0;
		for (Solution s : population)
		{
			currentSum+=s.getFitness();
		}
		sumFitness=currentSum;
	}
	
	private void calculateLengthSum()
	{
		float currentSum=0;
		for (Solution s : population)
		{
			currentSum+=s.getChromosome().getlength();
		}
		sumLength=currentSum;
	}
	
	public void evaluate()
	{
		calculateFitnessSum();
		calculateDuncan();
		population.get(0).setWindow(population.get(0).getFitness()/sumFitness);
		for(int i=1; i<population.size(); i++)
		{
			population.get(i).setWindow(population.get(i-1).getWindow()+(population.get(i).getFitness()/sumFitness));
		}
		
		//System.out.println(population);
	}
	
	public Solution selectParent(Random rand)
	{
		Float random;
		int i=0;
			random=rand.nextFloat();
			for(i=0; i<population.size(); i++)
			{
				if(population.get(i).getWindow()>random)
				{
					break;
				}
			}
			if(i==population.size())
				i--;
		return population.get(i);
	}
	
	public Solution[] selectParentsDiversity(Random rand)
	{
		float random;
		Solution[] parents=new Solution[2];
		int i=0;
		random=rand.nextFloat();
		for(i=0; i<population.size(); i++)
		{
			if(population.get(i).getWindow()>random)
			{
				break;
			}
		}
		if(i==population.size())
			i--;
		
		parents[0]=population.get(i);
		int edgesInCommon=Integer.MAX_VALUE;
		Set <Edge> temp = new HashSet<Edge>(Cliente.listaClienti.length);
		Solution min=null;
		for(Solution s : population)
		{
			if(parents[0]!=s)
			{
				temp.addAll(s.getChromosome().getEdges());
				temp.retainAll(parents[0].getChromosome().getEdges());
				if(temp.size()<edgesInCommon)
				{
					edgesInCommon=temp.size();
					min=s;
				}
			}
			temp.clear();
		}
		parents[1]=min;
		return parents;
	}
	
	public Solution checkLength(Solution s)
	{
		for (Solution sol : population)
		{
			if(sol.getChromosome().getlength().equals(s.getChromosome().getlength()))
			{
				return sol;
			}
		}
		return null;
	}
	
	
	
	
	public void survive(Population newGeneration, Random rand)
	{
		this.population.ensureCapacity(this.getSize()+newGeneration.getSize());
		this.population.addAll(this.getSize(), newGeneration.getPopulation());
		calculateDuncan(); //We set the new Highlander
		if(TGA.DuncanMacLeod==TGA.ConnorMacLeod)
		{
			TGA.localOptimumBuster.incrementAndGet();
		}
		else if(TGA.ConnorMacLeod==null || TGA.DuncanMacLeod.getFitness()>TGA.ConnorMacLeod.getFitness())
		{
			TGA.ConnorMacLeod=TGA.DuncanMacLeod;
			TGA.localOptimumBuster.set(0);
		}
		
		//System.out.println(evaluateDiversity(this));
		
			
		if(TGA.localOptimumBuster.get()>TGA.nameccDisasterThreshold) //Namekian disaster
		{
			population.clear();
			population.add(TGA.ConnorMacLeod);   
			Population.randomPopulation2Opt(1, TGA.populationSize-1, this, rand);
			TGA.localOptimumBuster.set(0);
			population.trimToSize();
			return;
		}
		
		
		calculateLengthSum();
		TGA.avgLength=(int)((this.sumLength/population.size())+0.5F);
		//System.out.println(sumFitness);
		population.get(0).setWindow(population.get(0).getChromosome().getlength()/sumLength);
		for(int i=1; i<population.size(); i++)
		{
			population.get(i).setWindow(population.get(i-1).getWindow()+((population.get(i).getChromosome().getlength())/sumLength));
		}
		
		//System.out.println(population);
		int toKill=TGA.offspringsPerEpoch;
		/*if(TGA.localOptimumBuster.get()>100)
		{
			TGA.DuncanMacLeod.setKillingFlag(true); //Take the head!
			toKill--;
			TGA.localOptimumBuster.set(0);
		}
		*/
		
		//Controllo cloni
		/*for(int i=0;i<population.size() && toKill>0 ;i++)
		{
			for(int j=i+1; j<population.size() && toKill>0 ;j++)
			{
				if(population.get(i).getFitness().equals(population.get(j).getFitness()))
				{
					System.out.println(population.remove(j));
					System.out.println(population.get(i));
					j--;
					toKill--;
					TGA.cloneKilled++;
				}
			}
		}*/
		
		float victim;
		int j=0;
		while(toKill>0)
		{
			victim=rand.nextFloat();
			for (j=0; j<population.size(); j++)
			{
				if(population.get(j).getWindow()>victim)
					break;
			
			}
			if(j==population.size())
				j--;
			//boolean killingFlag=population.get(j).getKillingFlag();
			boolean isHighlander= TGA.elitism && (population.get(j)==TGA.DuncanMacLeod);
			
			
			if(!isHighlander && population.get(j).getKillingFlag()==false)
			{
				population.get(j).setKillingFlag(true); //marked to be killed
				toKill--;
			}
			//if(isHighlander) 
			//	System.out.println("\n"+population.get(j).getKillingFlag());
		}
		
		for (Iterator<Solution> it = population.iterator(); it.hasNext();)
		{
			if(it.next().getKillingFlag())
			{
				it.remove();
			}
		}
		population.trimToSize();
		//System.out.println(population.contains(TGA.DuncanMacLeod));
	//	int popsize= population.size();
	//	int sonSize= newGeneration.getSize();
		if(population.size()!=newGeneration.getSize())
			throw new RuntimeException("You killed more than expected!");
		
	}
	
	public void surviveElitism(Population newGeneration, Random rand)
	{
		this.population.ensureCapacity(this.getSize()+newGeneration.getSize());
		this.population.addAll(this.getSize(), newGeneration.getPopulation());
		calculateDuncan(); //We set the new Highlander
		int DuncanLength=TGA.DuncanMacLeod.getChromosome().getlength();
		int ConnorLength=TGA.ConnorMacLeod==null?Integer.MAX_VALUE:TGA.ConnorMacLeod.getChromosome().getlength();
		if((ConnorLength-DuncanLength)<=(ConnorLength/2000))
		{
			TGA.localOptimumBuster.incrementAndGet();
		}
		else if((ConnorLength-DuncanLength)>(ConnorLength/2000))
		{
			TGA.localOptimumBuster.set(0);
		}
		
		if(TGA.ConnorMacLeod==null || TGA.DuncanMacLeod.getFitness()>TGA.ConnorMacLeod.getFitness())
		{
			TGA.ConnorMacLeod=TGA.DuncanMacLeod;	
		}
		
		//System.out.println(evaluateDiversity(this));
		
			
		if(TGA.localOptimumBuster.get()>TGA.nameccDisasterThreshold) //Namekian disaster
		{
			population.clear();
			population.add(TGA.ConnorMacLeod);   
			Population.randomPopulation2Opt(1, TGA.populationSize-1, this, rand);
			TGA.localOptimumBuster.set(0);
			population.trimToSize();
			return;
		}
		Collections.sort(population);
		population=new ArrayList<Solution>(population.subList(0, newGeneration.getSize()));	
	}
	
	public static void randomPopulation(int startIndex, int num, Population p, Random r)
	{
		ArrayList<Cliente> arrayClienti = new ArrayList<Cliente> (Arrays.asList(Cliente.listaClienti));
		int rand1;
		int j=startIndex;
		while(j<num+startIndex)
		{
			int k = arrayClienti.size();
			Tour t = new Tour(arrayClienti.size());
			ArrayList<Cliente> temp = new ArrayList<Cliente> (arrayClienti);
			for(int i =0; i<arrayClienti.size(); i++)
			{
			   	rand1 = r.nextInt(k);
			   	t.addCliente(i, temp.remove(rand1));
			   	k--;
			}
			Clan c = new Clan(j,TGA.tabuSize);
			Solution s = new Solution(t,c,null);
			p.addSolution(s);
			j++;
			
		}
	}
	
	public static void randomPopulation2Opt(int startIndex, int num, Population p, Random r)
	{
		ArrayList<Cliente> arrayClienti = new ArrayList<Cliente> (Arrays.asList(Cliente.listaClienti));
		int rand1;
		int j=startIndex;
		while(j<num+startIndex)
		{
			int k = arrayClienti.size();
			Cliente[] t = new Cliente[arrayClienti.size()];
			ArrayList<Cliente> temp = new ArrayList<Cliente> (arrayClienti);
			for(int i =0; i<arrayClienti.size(); i++)
			{
			   	rand1 = r.nextInt(k);
			   	t[i]= temp.remove(rand1);
			   	k--;
			}
			Clan c = new Clan(j,TGA.tabuSize);
			boolean noLook[] = new boolean[Cliente.listaClienti.length+1];
			
			for(int i = 0; i< Cliente.listaClienti.length+1; i++)
			{
				noLook[i] = false;
			}
			while(Tour.fixedRadiusNolook(t,noLook))
			{
				int x=0; //cheat the jit
			}
			//System.out.println("\n"+t.getlength());
			Tour tour= new Tour(new ArrayList<Cliente>(Arrays.asList(t)),null);
			Solution s = new Solution(tour,c,1/(float)tour.getlength());
			//System.out.println(s);
			p.addSolution(s);
			j++;
			
		}
	}
	
	public String toString()
	{
		return population.toString();
	}
	
	
	public static void nearestNeighbour(Population p,int startIndex, int num, Random r)
	{
		ArrayList<Cliente> arrayClienti = new ArrayList<Cliente> (Arrays.asList(Cliente.listaClienti));
		int rand1;
		int j=startIndex;
		ArrayList<Cliente> temp=new ArrayList<Cliente> ();
		while(j<num+startIndex)
		{
			temp.ensureCapacity(arrayClienti.size());
			temp.addAll(arrayClienti);
			int k = arrayClienti.size();
			Cliente[] t = new Cliente[arrayClienti.size()];
			rand1 = r.nextInt(k);
			float tmp = 0;
		   	t[0]= temp.remove(rand1);
		   //	System.out.println(t+"\n"+temp);
			for(int i =0; i<arrayClienti.size()-1; i++)
			{
				Cliente ctmp;
				//System.out.println("Nodo:"+ctmp.toString());
				tmp=t[i].calculateDistance(temp.get(0));
				ctmp = temp.get(0);
				float current;
				for(int l =1; l<temp.size(); l++)
				{
					current= t[i].calculateDistance(temp.get(l));
					if(current < tmp)
					{
						tmp = current;
						ctmp= temp.get(l);
					}
				}
				
			//	System.out.println(t.toString());
				//System.out.println(temp+"\n"+ctmp);
				t[i+1]=ctmp;
				temp.remove(ctmp);
			//	System.out.println(t.toString());
			}
			boolean noLook[] = new boolean[Cliente.listaClienti.length+1];
			
			for(int i = 0; i< Cliente.listaClienti.length+1; i++)
			{
				noLook[i] = false;
			}
			while(Tour.fixedRadiusNolook(t,noLook))
			{
				int x=0; //cheat the jit
			}
			Clan c = new Clan(j,TGA.tabuSize);
	                //Solution s = new Solution(t,c,null);
			//System.out.println(s.toString());
			//p.addSolution(s);
			Solution s=new Solution(new Tour(new ArrayList<Cliente>(Arrays.asList(t)),null),c,null);
			//System.out.println(s);
			p.addSolution(s);
			j++;
			temp.clear();
		}	
	}
	
	
	/*public static void nearestNeighbour(Population p,int startIndex)
	{
		ArrayList<Cliente> arrayClienti = new ArrayList<Cliente> (Arrays.asList(Cliente.listaClienti));
		int j=startIndex;
		int index=0;
		ArrayList<Cliente> temp=new ArrayList<Cliente> ();
		while(j<Cliente.listaClienti.length+startIndex)
		{
			temp.ensureCapacity(arrayClienti.size());
			temp.addAll(arrayClienti);
			Tour t = new Tour(arrayClienti.size());
			float tmp = 0;
		   	t.addCliente(0, temp.remove(index));
		   //	System.out.println(t+"\n"+temp);
			for(int i =0; i<arrayClienti.size()-1; i++)
			{
				Cliente ctmp;
				//System.out.println("Nodo:"+ctmp.toString());
				tmp=t.getCliente(i).calculateDistance(temp.get(0));
				ctmp = temp.get(0);
				float current;
				for(int l =1; l<temp.size(); l++)
				{
					current= t.getCliente(i).calculateDistance(temp.get(l));
					if(current < tmp)
					{
						tmp = current;
						ctmp= temp.get(l);
					}
				}
				
			//	System.out.println(t.toString());
				//System.out.println(temp+"\n"+ctmp);
				t.addCliente(i+1,ctmp);
				temp.remove(ctmp);
			//	System.out.println(t.toString());
			}
			Clan c = new Clan(j,TGA.tabuSize);
	                //Solution s = new Solution(t,c,null);
			//System.out.println(s.toString());
			//p.addSolution(s);
			Solution s=new Solution(t,c,null);
			//System.out.println(s);
			p.addSolution(s);
			j++;
			index++;
			temp.clear();
		}
		Tour t = new Tour(arrayClienti.size());
		t.getTour().clear();
		t.getTour().addAll(Arrays.asList(Cliente.listaClienti));
		Clan c = new Clan(j+1,TGA.tabuSize);
		Solution sol=new Solution(t,c,null);
		p.addSolution(sol);
	}*/
	
	private int evaluateDiversity(Population p)
	{
		ArrayList<Set<Edge>> edges= new ArrayList<Set<Edge>>(p.getSize());
		Set<Edge> temp;
		for(Solution s : p.getPopulation())
		{
			temp=new HashSet<Edge>(Cliente.listaClienti.length);
			
			edges.add(temp);
		}
		int diversity=0;
		for(int i=0; i<edges.size(); i++)
		{
			for(int j=i+1; j<edges.size(); j++)
			{
				diversity+=Tour.calculateDiversity(edges.get(i), edges.get(j));
			}
		}
		
		return diversity;
	}
	
	
}
