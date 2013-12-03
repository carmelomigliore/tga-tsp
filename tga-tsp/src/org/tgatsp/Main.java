package org.tgatsp;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		final int populationSize=2000;
		final int maxEpoch=1000;
		final int deadlockThreshold=1000;
		final float tabuCoefficient=0.4F;
		Population pop= new Population(populationSize);
		TGA algorithm=new TGA(pop,populationSize,maxEpoch,deadlockThreshold,tabuCoefficient);
		Cliente.init(args[0]);
		Random rand= new Random();
		Population.randomPopulation(0, 500, pop, rand);
		Population.nearestNeighbour(pop, 500, 1500, rand);
		//Population.randomPopulation2Opt(0, 1500, pop, rand);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		algorithm.startEngine();
		}
		
		
	}

