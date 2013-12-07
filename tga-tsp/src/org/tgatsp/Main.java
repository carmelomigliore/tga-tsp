package org.tgatsp;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		final int populationSize=50;
		final int maxEpoch=1000;
		final int deadlockThreshold=100; //TODO deadlock= a popsize
		final float tabuCoefficient=0.4F;
		Population pop= new Population(populationSize);
		TGA algorithm=new TGA(pop,populationSize,maxEpoch,deadlockThreshold,tabuCoefficient);
		Cliente.init(args[0]);
		Random rand= new Random(System.currentTimeMillis());
		//Population.randomPopulation(0, 200, pop, rand);
		//Population.nearestNeighbour(pop, 80, 20, rand);
		//Population.nearestNeighbour(pop, 947);
		Population.randomPopulation2Opt(0, 50, pop, rand);
		//System.out.println(pop);
		algorithm.startEngine();
		}
		
		
	}

