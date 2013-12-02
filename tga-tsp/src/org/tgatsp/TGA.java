package org.tgatsp;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class TGA {
	
	public static String nome;
	public static String comment;	
	//private ExecutorService pool;
	//private ExecutorService secondPool;
	public static Solution DuncanMacLeod; //Global Maximum - The Immortal
	public static AtomicInteger mutationCount;
	private int currentEpoch;
	private Population currentPopulation;
	private Population sons;
	private Random rand;
	public static int populationSize;
	public static float tabuCoefficient; 
	public static int tabuSize;
	private int maxEpoch;
	public static int offspringsPerEpoch;
	private int deadlockThreshold;
	
	public TGA (int populationSize, int maxEpoch, int deadlockThreshold, int tabuCoefficient, int threadPoolSize)
	{
		TGA.populationSize=populationSize;
		TGA.mutationCount=new AtomicInteger();
		TGA.tabuCoefficient=tabuCoefficient;
		TGA.tabuSize=(int)(TGA.tabuCoefficient*TGA.populationSize);
		//this.pool=Executors.newFixedThreadPool(threadPoolSize); 
		//this.secondPool=Executors.newCachedThreadPool();
		this.currentEpoch=0;
		this.maxEpoch=maxEpoch;
		this.deadlockThreshold=deadlockThreshold;
		rand = new Random();
		TGA.offspringsPerEpoch=populationSize;
	}
	
	
	public void startEngine()
	{
		final PMXCrossover[] cross= new PMXCrossover[offspringsPerEpoch/2];
		sons=new Population(populationSize);
		
		//inizializzo i vettori
		for(int j=0; j<offspringsPerEpoch/2; j++)
		{
			cross[j]=new PMXCrossover(deadlockThreshold, rand);		}
		
		while(currentEpoch<maxEpoch)
		{
			currentPopulation.evaluate();
			
			for (int i=0; i<offspringsPerEpoch/2; i++)
			{
				cross[i].setPopulation(currentPopulation);
				sons.addSolution(cross[i].call()[0]);
				sons.addSolution(cross[i].call()[1]);
			}	
			currentPopulation.survive(sons, rand);	
			currentEpoch++;
		}
		
	}
	
	
	

}
