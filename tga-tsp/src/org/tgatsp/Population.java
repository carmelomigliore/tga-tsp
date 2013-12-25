package org.tgatsp;


import java.util.*;

public class Population {
	
	private ArrayList<Tour> population; //TODO provare pure LinkedList
	private Float sumFitness;
	private Float sumLength;
	
	public Population(int size)
	{
		this.population=new ArrayList<Tour>(size);
	}
	
	public Population(ArrayList<Tour> pop)
	{
		this.population=pop;
	}
	
	public ArrayList<Tour> getPopulation()
	{
		return population;
	}
	
	public void addTour (Tour s)
	{
		population.add(s);
	}
	
	public void kill(Tour s)
	{
		for (Iterator<Tour> it = population.iterator(); it.hasNext();)
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
		return population.size();
	}
	
	private void calculateFitnessSum()
	{
		float currentSum=0;
		for (Tour s : population)
		{
			currentSum+=s.getFitness();
		}
		sumFitness=currentSum;
	}
	
	private void calculateLengthSum()
	{
		float currentSum=0;
		for (Tour s : population)
		{
			currentSum+=s.getlength();
		}
		sumLength=currentSum;
	}
	
	public void evaluate()
	{
		calculateFitnessSum();
		population.get(0).setWindow(population.get(0).getFitness()/sumFitness);
		for(int i=1; i<population.size(); i++)
		{
			population.get(i).setWindow(population.get(i-1).getWindow()+(population.get(i).getFitness()/sumFitness));
		}
		
		//System.out.println(population);
	}
	
	public Tour[] tournamentSelection(int size, Random rand)
	{
		int counter=0;
		Tour[] parents=new Tour[2];
		Tour max=null;
		while(counter<size)
		{
			int random=rand.nextInt(population.size());
			if(max==null || population.get(random).getFitness()>max.getFitness())
			{
				max=population.get(random);
			}
			counter++;
		}
		parents[0]=max;
		max=null;
		counter=0;
		while(counter<size)
		{
			int random=rand.nextInt(population.size());
			Tour s= population.get(random);
			if(s==parents[0]) continue;
			if(max==null || s.getFitness()>max.getFitness())
			{
				max=s;
			}
			counter++;
		}
		parents[1]=max;
		return parents;
	}
	
	public Tour selectParent(Random rand)
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
	
	
	
	
	public void survive(Population newGeneration, Random rand)
	{
		this.population.ensureCapacity(this.getSize()+newGeneration.getSize());
		this.population.addAll(this.getSize(), newGeneration.getPopulation());
		//calculateDuncan(); //We set the new Highlander
		if(TGA.DuncanMacLeod==TGA.ConnorMacLeod)
		{
			TGA.localOptimumBuster++;
		}
		
		if(TGA.ConnorMacLeod==null || TGA.DuncanMacLeod.getFitness()>TGA.ConnorMacLeod.getFitness())
		{
			TGA.ConnorMacLeod=TGA.DuncanMacLeod;	
			TGA.localOptimumBuster=0;
		}
		
		if(TGA.Richie==null || TGA.ConnorMacLeod.getFitness()>TGA.Richie.getFitness())
		{
			TGA.Richie=TGA.ConnorMacLeod;
		}
		
		//System.out.println(evaluateDiversity(this));
		
			
		if(TGA.localOptimumBuster>TGA.nameccDisasterThreshold) //Namekian disaster
		{
			population.clear();
			Population.randomPopulation2Opt(0, TGA.populationSize, this, rand);
			TGA.ConnorMacLeod=null;
			TGA.localOptimumBuster=0;
			population.trimToSize();
			return;
		}
		
		
		calculateLengthSum();
		TGA.avgLength=(int)((this.sumLength/population.size())+0.5F);
		//System.out.println(sumFitness);
		population.get(0).setWindow(population.get(0).getlength()/sumLength);
		for(int i=1; i<population.size(); i++)
		{
			population.get(i).setWindow(population.get(i-1).getWindow()+((population.get(i).getlength())/sumLength));
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
		
		for (Iterator<Tour> it = population.iterator(); it.hasNext();)
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
		Collections.sort(population);
		TGA.DuncanMacLeod=population.get(0); //We set the new Highlander
		//int DuncanLength=TGA.DuncanMacLeod.getChromosome().getlength();
		//int ConnorLength=TGA.ConnorMacLeod==null?Integer.MAX_VALUE:TGA.ConnorMacLeod.getChromosome().getlength();
		if(TGA.DuncanMacLeod==TGA.ConnorMacLeod)
		{
			TGA.localOptimumBuster++;
		}
		
		if(TGA.ConnorMacLeod==null || TGA.DuncanMacLeod.getFitness()>TGA.ConnorMacLeod.getFitness())
		{
			TGA.ConnorMacLeod=TGA.DuncanMacLeod;	
			TGA.localOptimumBuster=0;
		}
		
		if(TGA.Richie==null || TGA.ConnorMacLeod.getFitness()>TGA.Richie.getFitness())
		{
			TGA.Richie=TGA.ConnorMacLeod;
		}
		
		//System.out.println(evaluateDiversity(this));
		
			
		if(TGA.localOptimumBuster>TGA.nameccDisasterThreshold) //Namekian disaster
		{
			population.clear();
			Population.randomPopulation2Opt(0, TGA.populationSize, this, rand);
			TGA.ConnorMacLeod=null;
			TGA.localOptimumBuster=0;
			population.trimToSize();
			return;
		}
		population=new ArrayList<Tour>(population.subList(0, newGeneration.getSize()));	
	}
	
	/*public static void randomPopulation(int startIndex, int num, Population p, Random r)
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
			p.addTour(t);
			j++;
			
		}
	}*/
	
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
			boolean noLook[] = new boolean[Cliente.listaClienti.length+1];
			
			for(int i = 0; i< Cliente.listaClienti.length+1; i++)
			{
				noLook[i] = false;
			}
			boolean again=true;
			
			while(again)
			{
				again=Tour.fixedRadiusNolookNear(t,noLook); //cheat the jit
			}
			//System.out.println("\n"+t.getlength());
			Tour tour= new Tour(t,-1);
			//System.out.println(s);
			p.addTour(tour);
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
			
			boolean again=true;
			
			while(again)
			{
				again=Tour.fixedRadiusNolookNear(t,noLook); //cheat the jit
			}
	                //Tour s = new Tour(t,c,null);
			//System.out.println(s.toString());
			//p.addTour(s);
			Tour s=new Tour(t,-1);
			//System.out.println(s);
			p.addTour(s);
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
	                //Tour s = new Tour(t,c,null);
			//System.out.println(s.toString());
			//p.addTour(s);
			Tour s=new Tour(t,c,null);
			//System.out.println(s);
			p.addTour(s);
			j++;
			index++;
			temp.clear();
		}
		Tour t = new Tour(arrayClienti.size());
		t.getTour().clear();
		t.getTour().addAll(Arrays.asList(Cliente.listaClienti));
		Clan c = new Clan(j+1,TGA.tabuSize);
		Tour sol=new Tour(t,c,null);
		p.addTour(sol);
	}*/
	
	
}
