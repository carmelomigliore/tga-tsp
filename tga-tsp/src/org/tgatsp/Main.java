package org.tgatsp;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		final int maxEpoch=150;
		final int deadlockThreshold=150; //TODO deadlock= a popsize
		final float tabuCoefficient=0.0F;
		final boolean elitism=true;
//	for(;;)
	{
		
		long seed=System.currentTimeMillis();
		System.out.println(seed);
		//Random rand= new Random(1387807812941L); //600
		//Random rand= new Random(1387816411881L); //600
		//Random rand= new Random(1387842593170L);
		Random rand= new Random(1387901792703L);
		Cliente.init(args[0]);
		Cliente.findNearest(20);
		final int populationSize;
		if(Cliente.listaClienti.length>400)
			populationSize=Cliente.listaClienti.length; //per quello da mille meglio 600
			//populationSize=1600;
		else
			populationSize=350;
		Population pop= new Population(populationSize);
		TGA algorithm=new TGA(pop,populationSize,maxEpoch,deadlockThreshold,300,tabuCoefficient,elitism,0,0.3F,400000L,"prova.txt", rand);
		TGA.Richie=null;
		
		/*System.out.println(Cliente.listaClienti[1]);
		for(int i=0; i<Cliente.nearest[1].length; i++)
		{
			System.out.println(Cliente.nearest[1][i]);
		}*/
		
		
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
	
	
		//Population.nearestNeighbour(pop, 0, 100, rand);
		//Population.nearestNeighbour(pop, 947);
		Population.randomPopulation2Opt(0, populationSize, pop, rand);
		//System.out.println(pop);
		Tour best=algorithm.startEngine();
		PrintStream ps=null;
		try {
			ps=new PrintStream(new FileOutputStream("seed.txt",true));
			System.out.println("\nLength: "+best.getlength()+". "+"Time: "+(System.currentTimeMillis()-seed));
			ps.close();		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
	}
		/*Test t=new Test();
		t.eil51();
		t.eil76();
		t.pr152();*/
		}
		
		
	}

