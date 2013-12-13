package org.tgatsp;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		final int populationSize=100;
		final int maxEpoch=1000;
		final int deadlockThreshold=100; //TODO deadlock= a popsize
		final float tabuCoefficient=0.1F;
		final boolean elitism=true;
		Population pop= new Population(populationSize);
		TGA algorithm=new TGA(pop,populationSize,maxEpoch,deadlockThreshold,15,tabuCoefficient,elitism,73682,"prova.txt");
		Cliente.init(args[0]);
		Random rand= new Random(System.currentTimeMillis());
		
		//System.out.println(Population.calculateDiversity(disa, diesel));
		//System.out.println(e.equals(f));
		//Population.randomPopulation(0, 200, pop, rand);
		//Population.nearestNeighbour(pop, 0, 10, rand);
		//Population.nearestNeighbour(pop, 947);
		Population.randomPopulation2Opt(0, 100, pop, rand);
		//System.out.println(pop);
		algorithm.startEngine();
		
		/*Test t=new Test();
		t.eil51();
		t.eil76();
		t.pr152();*/
		}
		
		
	}

