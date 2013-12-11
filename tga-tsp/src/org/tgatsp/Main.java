package org.tgatsp;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		final int populationSize=100;
		final int maxEpoch=1000;
		final int deadlockThreshold=10; //TODO deadlock= a popsize
		final float tabuCoefficient=0.1F;
		final boolean elitism=true;
		Population pop= new Population(populationSize);
		TGA algorithm=new TGA(pop,populationSize,maxEpoch,deadlockThreshold,70,tabuCoefficient,elitism,73682,"prova.txt");
		Cliente.init(args[0]);
		Random rand= new Random(System.currentTimeMillis());
		Set<Edge> disa= new HashSet<Edge>();
		Set<Edge> diesel= new HashSet<Edge>();
		Edge e = new Edge(1,2);
		Edge f = new Edge(2,1);
		Edge g = new Edge(3,4);
		
		disa.add(e);
		diesel.add(f);
		diesel.add(e);
		//System.out.println(Population.calculateDiversity(disa, diesel));
		//System.out.println(e.equals(f));
		//Population.randomPopulation(0, 200, pop, rand);
		//Population.nearestNeighbour(pop, 0, 100, rand);
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

