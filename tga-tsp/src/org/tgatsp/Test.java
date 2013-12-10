package org.tgatsp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class Test {
	
	private int populationSize;
	private int maxEpoch;
	private int deadlockThreshold; 
	private int nameccDisaster;
	private int optimalLength;
	private float tabuCoefficient;
	private TGA tga;
	private boolean elitism;
	private Population pop;
	private Random rand;
	private String logfile;
	public Test()
	{
		maxEpoch=1000;
	}
	
	public void eil51()
	{
		/*****************eil51********************/
		
		populationSize=100;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		optimalLength=426;
		tabuCoefficient=0.4F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil51.tsp");
		logfile="eil51.res";
		PrintStream ps=null;
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("Population size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			pop=new Population(populationSize);
			
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile); 
			Population.randomPopulation2Opt(0, populationSize, pop, rand);
			tga.startEngine();
		}
		
		populationSize=50;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		optimalLength=426;
		tabuCoefficient=0.4F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil51.tsp");
		logfile="eil51.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile); 
			Population.randomPopulation2Opt(0, populationSize, pop, rand);
			tga.startEngine();
		}
		
		populationSize=10;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		optimalLength=426;
		tabuCoefficient=0.4F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil51.tsp");
		logfile="eil51.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);
			tga.startEngine();
		}
		

		populationSize=100;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		optimalLength=426;
		tabuCoefficient=0.4F;
		elitism=false;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil51.tsp");
		logfile="eil51.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=50;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		optimalLength=426;
		tabuCoefficient=0.4F;
		elitism=false;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil51.tsp");
		logfile="eil51.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=10;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		optimalLength=426;
		tabuCoefficient=0.4F;
		elitism=false;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil51.tsp");
		logfile="eil51.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile); 
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		
		populationSize=50;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		optimalLength=426;
		tabuCoefficient=0.1F;
		elitism=false;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil51.tsp");
		logfile="eil51.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=50;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		optimalLength=426;
		tabuCoefficient=0.2F;
		elitism=false;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil51.tsp");
		logfile="eil51.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=50;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		optimalLength=426;
		tabuCoefficient=0.2F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil51.tsp");
		logfile="eil51.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
	}
	
	
	
	
	public void eil76()
	{
		PrintStream ps=null;
		optimalLength=538;
		populationSize=100;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		tabuCoefficient=0.4F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil76.tsp");
		logfile="eil76.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		
		populationSize=50;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		tabuCoefficient=0.4F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil76.tsp");
		logfile="eil76.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=10;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		tabuCoefficient=0.4F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil76.tsp");
		logfile="eil76.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		
		populationSize=100;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		tabuCoefficient=0.4F;
		elitism=false;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil76.tsp");
		logfile="eil76.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=50;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		tabuCoefficient=0.4F;
		elitism=false;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil76.tsp");
		logfile="eil76.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=10;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		tabuCoefficient=0.4F;
		elitism=false;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil76.tsp");
		logfile="eil76.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=100;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		tabuCoefficient=0.2F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil76.tsp");
		logfile="eil76.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=100;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		tabuCoefficient=0.1F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil76.tsp");
		logfile="eil76.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=50;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		tabuCoefficient=0.1F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil76.tsp");
		logfile="eil76.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=100;
		deadlockThreshold=populationSize;
		nameccDisaster=50;
		tabuCoefficient=0.4F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("eil76.tsp");
		logfile="eil76.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
	}
	
	public void pr152()
	{
		PrintStream ps=null;
		optimalLength=73682;
		populationSize=100;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		tabuCoefficient=0.4F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("pr152.tsp");
		logfile="pr152.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=50;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		tabuCoefficient=0.4F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("pr152.tsp");
		logfile="pr152.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=50;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		tabuCoefficient=0.4F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("pr152.tsp");
		logfile="pr152.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=10;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		tabuCoefficient=0.4F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("pr152.tsp");
		logfile="pr152.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=50;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		tabuCoefficient=0.4F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("pr152.tsp");
		logfile="pr152.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism+" Nearest+random");
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, 40, pop, rand);
			Population.nearestNeighbour(pop, 40, 10, rand);
			tga.startEngine();
		}
		
		populationSize=50;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		tabuCoefficient=0.4F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("pr152.tsp");
		logfile="pr152.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism+" Nearest+ only");
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.nearestNeighbour(pop, 40, 10, rand);
			tga.startEngine();
		}
		
		populationSize=10;
		deadlockThreshold=populationSize;
		nameccDisaster=100;
		tabuCoefficient=0.1F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("pr152.tsp");
		logfile="pr152.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=10;
		deadlockThreshold=populationSize;
		nameccDisaster=50;
		tabuCoefficient=0.4F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("pr152.tsp");
		logfile="pr152.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
		populationSize=50;
		deadlockThreshold=populationSize;
		nameccDisaster=50;
		tabuCoefficient=0.4F;
		elitism=true;
		rand=new Random(System.currentTimeMillis());
		Cliente.init("pr152.tsp");
		logfile="pr152.res";
		try {
			ps=new PrintStream(new FileOutputStream(logfile,true));
			ps.println("\n\n\n\nPopulation size="+populationSize+", deadlock="+deadlockThreshold+", Namecc Disaster="+nameccDisaster+", Tabu Coefficient="+tabuCoefficient+", Elitism: "+elitism);
			ps.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		
		for(int i=0; i<5; i++)
		{
			
			pop=new Population(populationSize);
			tga=new TGA(pop, populationSize, maxEpoch,deadlockThreshold,nameccDisaster,tabuCoefficient,elitism,optimalLength,logfile);
			Population.randomPopulation2Opt(0, populationSize, pop, rand);

			tga.startEngine();
		}
		
	}
	
}
