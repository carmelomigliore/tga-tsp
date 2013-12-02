package org.tgatsp;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class TGA {
	
	public static String nome;
	public static String comment;	
	private ExecutorService pool;
	private ExecutorService secondPool;
	public static Solution DuncanMacLeod; //Global Maximum - The Immortal
	public static AtomicInteger mutationCount;
	private int currentEpoch;
	private Population currentPopulation;
	private Population sons;
	private Random rand;
	public static int populationSize;
	public static int tabuCoefficient;                   
	private int maxEpoch;
	public static int offspringsPerEpoch;
	private int deadlockThreshold;
	
	public TGA (int populationSize, int maxEpoch, int deadlockThreshold, int tabuCoefficient, int threadPoolSize)
	{
		TGA.populationSize=populationSize;
		TGA.mutationCount=new AtomicInteger();
		TGA.tabuCoefficient=tabuCoefficient;
		this.pool=Executors.newFixedThreadPool(threadPoolSize); 
		this.secondPool=Executors.newCachedThreadPool();
		this.currentEpoch=0;
		this.maxEpoch=maxEpoch;
		this.deadlockThreshold=deadlockThreshold;
		rand = new Random();
		TGA.offspringsPerEpoch=populationSize;
	}
	
	
	public void startEngine()
	{
		Solution[] figli;
		final PMXCrossover[] cross= new PMXCrossover[offspringsPerEpoch/2];
		final ArrayList<Future<Solution[]>> offs= new ArrayList<Future<Solution[]>>(offspringsPerEpoch/2);
		sons=new Population(populationSize);
		
		//inizializzo i vettori
		for(int j=0; j<offspringsPerEpoch/2; j++)
		{
			cross[j]=new PMXCrossover(secondPool, deadlockThreshold);
			offs.add(null);
		}
		
		while(currentEpoch<maxEpoch)
		{
			currentPopulation.evaluate();
			
			for (int i=0; i<offspringsPerEpoch/2; i++)
			{
				//TODO verificare se conviene in multithread o no
				cross[i].setPopulation(currentPopulation);
				offs.set(i,pool.submit(cross[i]));
			}	
			for (Future<Solution[]> f : offs)
			{
				try{
					figli=f.get();
					sons.addSolution(figli[0]);
					sons.addSolution(figli[1]);
				}catch(Exception e){e.printStackTrace();}
			}
			currentPopulation.survive(sons, rand);	
			currentEpoch++;
		}
		
	}
	
	
	

}
