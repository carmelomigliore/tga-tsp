package org.tgatsp;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		final int populationSize=20;
		final int maxEpoch=1000;
		final int deadlockThreshold=10; //TODO deadlock= a popsize
		final float tabuCoefficient=0.4F;
		final boolean elitism=true;
		Population pop= new Population(populationSize);
		TGA algorithm=new TGA(pop,populationSize,maxEpoch,deadlockThreshold,100,tabuCoefficient,elitism,2323,"prova.txt");
		Cliente.init(args[0]);
		Random rand= new Random(System.currentTimeMillis());
		//Population.randomPopulation(0, 200, pop, rand);
		//Population.nearestNeighbour(pop, 0, 10, rand);
		//Population.nearestNeighbour(pop, 947);
		Population.randomPopulation2Opt(0, 20, pop, rand);
		//System.out.println(pop);
		algorithm.startEngine();
		
		/*Test t=new Test();
		t.eil51();
		t.eil76();
		t.pr152();*/
		}
		
		
	}

