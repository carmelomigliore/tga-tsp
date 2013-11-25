package org.tgatsp;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TGA {
	
	private ExecutorService pool;
	public static Solution DuncanMacLeod; //Global Maximum - The Immortal
	public static Integer mutationCount;
	private int currentEpoch;
	private Population parents;
	private Population sons;
	private Random rand;
	public static int populationSize;
	private int maxEpoch;
	private int offspringsPerEpoch;
	private int deadlockThreshold;
	
	public TGA (int populationSize)
	{
		mutationCount=new Integer(0);
		this.pool=Executors.newCachedThreadPool(); //TODO provare pure fixedThreadPool (sopratutto per il problema della TABU)
		this.currentEpoch=0;
		rand = new Random();
		this.offspringsPerEpoch=populationSize;
	}
	
	
	public void startEngine()
	{
		Solution[] figli;
		DuncanMacLeod=parents.evaluate();
		sons=new Population(populationSize);
		PMXCrossover[] cross= new PMXCrossover[offspringsPerEpoch/2];
		ArrayList<Future<Solution[]>> offs= new ArrayList<Future<Solution[]>>(offspringsPerEpoch/2);
		for (int i=0; i<cross.length; i++)
		{
			//TODO verificare se conviene in multithread o no
			
			cross[i]= new PMXCrossover(parents,pool,rand,deadlockThreshold);
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
	}
	
	
	

}
