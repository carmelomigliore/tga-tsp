package org.tgatsp;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class TGA {
	
	public static String nome;
	public static String comment;	
	//private ExecutorService pool;
	//private ExecutorService secondPool;
	public static Solution DuncanMacLeod; //Global Maximum - The Immortal
	public static Solution ConnorMacLeod; //The old Highlander
	public final static Solution[] highlanders = new Solution[10];
	public final static int highlandersDimension=10;
	public static AtomicInteger mutationCount;
	public static AtomicInteger tabuCount;
	public static AtomicInteger localOptimumBuster;
	public static int currentEpoch;
	private Population currentPopulation;
	private Population sons;
	private Random rand;
	public static int populationSize;
	public static float tabuCoefficient; 
	public static int tabuSize;
	private int maxEpoch;
	public static int offspringsPerEpoch;
	private int deadlockThreshold;
	public static int cloneKilled;
	
	public TGA (Population p, int populationSize, int maxEpoch, int deadlockThreshold, float tabuCoefficient)
	{
		TGA.populationSize=populationSize;
		TGA.mutationCount=new AtomicInteger();
		TGA.tabuCoefficient=tabuCoefficient;
		TGA.tabuSize=(int)(TGA.tabuCoefficient*TGA.populationSize);
		TGA.tabuCount=new AtomicInteger();
		TGA.localOptimumBuster=new AtomicInteger();
		//this.pool=Executors.newFixedThreadPool(threadPoolSize); 
		//this.secondPool=Executors.newCachedThreadPool();
		this.currentPopulation=p;
		TGA.currentEpoch=0;
		this.maxEpoch=maxEpoch;
		this.deadlockThreshold=deadlockThreshold;
		rand = new Random();
		TGA.offspringsPerEpoch=populationSize;
		TGA.cloneKilled=0;
	}
	
	
	public void startEngine()
	{
		final PMXCrossover[] cross= new PMXCrossover[offspringsPerEpoch/2];
		sons=new Population(populationSize);
		Solution[] figli;
		
		//inizializzo i vettori
		for(int j=0; j<offspringsPerEpoch/2; j++)
		{
			cross[j]=new PMXCrossover(deadlockThreshold, rand);		
		}
		
		
		long prima=0;
		long dopo=0;
		while(currentEpoch<maxEpoch)
		{
			currentPopulation.evaluate();
			//if(currentEpoch==0)
			//TGA.ConnorMacLeod=TGA.DuncanMacLeod;
			//System.out.println("\n1Duncan==Connor"+(TGA.ConnorMacLeod==TGA.DuncanMacLeod));
	//		System.out.println(currentPopulation);
			
			if(currentEpoch==0)
				prima=System.currentTimeMillis();
			for (int i=0; i<offspringsPerEpoch/2; i++)
			{
				cross[i].setPopulation(currentPopulation);
				figli=cross[i].call();
				sons.addSolution(figli[0]);
				sons.addSolution(figli[1]);
			}
			if(currentEpoch%999==0)
				dopo=System.currentTimeMillis();
//			System.out.println(sons);
			//System.out.println("\n2Duncan==Connor"+(TGA.ConnorMacLeod==TGA.DuncanMacLeod));
			currentPopulation.survive(sons, rand);
			//System.out.println("\n3Duncan==Connor"+(TGA.ConnorMacLeod==TGA.DuncanMacLeod));
	//		System.out.println(currentPopulation);
			sons.getPopulation().clear();
			System.out.println("\nEpoch:"+currentEpoch+"\nMutations: "+TGA.mutationCount+" Aspiration: "+TGA.tabuCount+" Clones killed: "+TGA.cloneKilled);
		//	for(Solution s: TGA.highlanders)
		//	{
				System.out.println("\nConnor: "+TGA.ConnorMacLeod);
		//	}
			
			//System.out.println("\n4Duncan==Connor"+(TGA.ConnorMacLeod==TGA.DuncanMacLeod));
			currentEpoch++;
		}
		System.out.println("\n\n\n\n----------------Adunanza----------------\n\n");
		for(Solution s: TGA.highlanders)
		{
			Tour.localSearch(s.getChromosome());
			System.out.println(s);
		}
		Tour.localSearch(TGA.ConnorMacLeod.getChromosome());
		System.out.println("\nTime:"+(dopo-prima)+"\nConnor: "+TGA.ConnorMacLeod);
		//System.out.println("\nGlobal optimum:"+TGA.DuncanMacLeod+"Mutation: "+mutationCount+" Tabu: "+tabuCount);
		
	}

}
