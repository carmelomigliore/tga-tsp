package org.tgatsp;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TGA {
	
	private ExecutorService pool;
	private ExecutorService secondPool;
	public static Solution DuncanMacLeod; //Global Maximum - The Immortal
	public static Integer mutationCount;
	private int currentEpoch;
	private Population currentPopulation;
	private Population sons;
	private Random rand;
	public static int populationSize;
	public static int tabuCoefficient;                   
	private int maxEpoch;
	private int offspringsPerEpoch;
	private int deadlockThreshold;
	
	public TGA (int populationSize)
	{
		TGA.populationSize=populationSize;
		TGA.mutationCount=new Integer(0);
		this.pool=Executors.newFixedThreadPool(4); 
		this.secondPool=Executors.newCachedThreadPool();
		this.currentEpoch=0;
		rand = new Random();
		this.offspringsPerEpoch=populationSize;
	}
	
	
	public void startEngine()
	{
		Solution[] figli;
		final PMXCrossover[] cross= new PMXCrossover[offspringsPerEpoch/2];
		final ArrayList<Future<Solution[]>> offs= new ArrayList<Future<Solution[]>>(offspringsPerEpoch/2);
		while(currentEpoch<maxEpoch)
		{
			currentPopulation.evaluate();
			sons=new Population(populationSize);
			
			for (int i=0; i<cross.length; i++)
			{
				//TODO verificare se conviene in multithread o no
				
				cross[i]= new PMXCrossover(currentPopulation,secondPool,deadlockThreshold);
				offs.add(pool.submit(cross[i]));
			}	
			for (Future<Solution[]> f : offs)
			{
				try{
					figli=f.get();
					sons.addSolution(figli[0]);
					sons.addSolution(figli[1]);
				}catch(Exception e){e.printStackTrace();}
			}
			sons.survive(currentPopulation, rand);
			currentPopulation=sons;
			currentEpoch++;
		}
		
	}
	
	
	

}
