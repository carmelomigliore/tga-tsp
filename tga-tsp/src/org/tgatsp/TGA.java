package org.tgatsp;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TGA {
	
	private ExecutorService pool;
	public static Solution DuncanMacLeod; //Global Maximum - The Immortal
	private int epoch;
	private Population parents;
	private Population sons;
	private Random rand;
	private int populationSize;
	private int maxEpoch;
	private int offspringsPerEpoch;
	private int deadlockThreshold;
	
	public TGA (int populationSize)
	{
		this.pool=Executors.newCachedThreadPool(); //TODO provare pure fixedThreadPool (sopratutto per il problema della TABU)
		this.epoch=0;
		rand = new Random();
		this.offspringsPerEpoch=populationSize;
	}
	
	
	public void startEngine()
	{
		Solution[] genitori;
		Solution[] figli;
		int offspringCounter=0;
		DuncanMacLeod=parents.evaluate();
		sons=new Population(populationSize);
		while(offspringCounter<offspringsPerEpoch)
		{
			//TODO verificare se conviene in multithread o no
			PMXCrossover cross= new PMXCrossover(parents,pool,rand,deadlockThreshold);
			Future<Solution[]> offs= pool.submit(cross);
			try{
				figli=offs.get();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			
			
		
		}
	}
	
	
	

}
