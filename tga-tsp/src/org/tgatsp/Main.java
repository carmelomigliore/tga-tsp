package org.tgatsp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		final int populationSize=400;
		final int maxEpoch=1000;
		final int deadlockThreshold=300; //TODO deadlock= a popsize
		final float tabuCoefficient=0.0F;
		final boolean elitism=true;
		Population pop= new Population(populationSize);
		TGA algorithm=new TGA(pop,populationSize,maxEpoch,deadlockThreshold,30,tabuCoefficient,elitism,0,0.3F,400000L,"prova.txt");
		Cliente.init(args[0]);
		Cliente.findNearest(20);
		/*System.out.println(Cliente.listaClienti[1]);
		for(int i=0; i<Cliente.nearest[1].length; i++)
		{
			System.out.println(Cliente.nearest[1][i]);
		}*/
		Random rand= new Random(System.currentTimeMillis());
		
		//System.out.println(Population.calculateDiversity(disa, diesel));
		//System.out.println(e.equals(f));
		/*
		Population.randomPopulation2Opt(0, 2, pop, rand);
		Solution s= pop.getPopulation().get(0);
		Cliente[] c=new Cliente[Cliente.listaClienti.length];
		Cliente[] d=new Cliente[Cliente.listaClienti.length];
		Cliente[] e=new Cliente[Cliente.listaClienti.length];
		c = s.getChromosome().getTour().toArray(c);
		d = s.getChromosome().getTour().toArray(d);
		e = s.getChromosome().getTour().toArray(e);
		boolean noLook[] = new boolean[Cliente.listaClienti.length+1];
		
		for(int i = 0; i< Cliente.listaClienti.length+1; i++)
		{
			noLook[i] = false;
		}
		boolean noLook2[] = new boolean[Cliente.listaClienti.length+1];
		
		for(int i = 0; i< Cliente.listaClienti.length+1; i++)
		{
			noLook2[i] = false;
		}
		boolean again=true;;
		while(again)
		{
			again=Tour.fixedRadiusNolook(c,noLook);
		}
		
		boolean again2=true;;
		while(again2)
		{
			again2=Tour.fixedRadiusNolookNear(d,noLook2);
		}
		Tour.localSearch(e);
		ArrayList<Cliente> ac = new ArrayList<Cliente>(Arrays.asList(c));
		ArrayList<Cliente> ad = new ArrayList<Cliente>(Arrays.asList(d));
		ArrayList<Cliente> ae = new ArrayList<Cliente>(Arrays.asList(e));
		Tour t = new Tour(ac,null);
		Tour u = new Tour(ad,null);
		Tour v = new Tour(ae,null);
		System.out.println(t.getlength());
		System.out.println(u.getlength());
		System.out.println(v.getlength());
		*/
	
	
		//Population.nearestNeighbour(pop, 0, 10, rand);
		//Population.nearestNeighbour(pop, 947);
		Population.randomPopulation2Opt(0, 400, pop, rand);
		//System.out.println(pop);
		algorithm.startEngine();
		
		/*Test t=new Test();
		t.eil51();
		t.eil76();
		t.pr152();*/
		}
		
		
	}

