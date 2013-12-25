package org.tgatsp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class TGA {
	
	public static String nome;
	public static String comment;	
	//private ExecutorService pool;
	//private ExecutorService secondPool;
	public static Tour DuncanMacLeod; //Global Maximum - The Immortal
	public static Tour ConnorMacLeod; //The old Highlander
	public static Tour Richie;
	public final static Tour[] highlanders = new Tour[10];
	public final static int highlandersDimension=10;
	public static int mutationCount;
	public static int localOptimumBuster;
	public static boolean elitism;
	public static int currentEpoch;
	private Population currentPopulation;
	private Population sons;
	private Random rand;
	public static int populationSize;
	public static float tabuCoefficient; 
	public static int tabuSize;
	private int maxEpoch;
	public static int offspringsPerEpoch;
	private int deadlockThreshold;
	public static int cloneKilled;
	public static int nameccDisasterThreshold; //threshold to apply the planet Namecc disaster
	public static int avgLength;
	public static int optimalLength;
	public static float tournamentCoefficient;
	public static long prima;
	public static long timelimit;
	
	private String logfile;
	
	public TGA (Population p, int populationSize, int maxEpoch, int deadlockThreshold, int namecc, float tabuCoefficient, boolean elitism, int optimal, float tournamentCoefficient, long timelimit, String logfile, Random rand)
	{
		TGA.populationSize=populationSize;
		TGA.mutationCount=0;
		TGA.tabuCoefficient=tabuCoefficient;
		TGA.tabuSize=(int)(TGA.tabuCoefficient*TGA.populationSize);
		TGA.localOptimumBuster=0;
		//this.pool=Executors.newFixedThreadPool(threadPoolSize); 
		//this.secondPool=Executors.newCachedThreadPool();
		this.currentPopulation=p;
		TGA.currentEpoch=0;
		this.maxEpoch=maxEpoch;
		this.deadlockThreshold=deadlockThreshold;
		this.rand = rand;
		TGA.offspringsPerEpoch=populationSize;
		TGA.cloneKilled=0;
		TGA.nameccDisasterThreshold=namecc;
		TGA.elitism=elitism;
		TGA.optimalLength=optimal;
		TGA.DuncanMacLeod=null;
		TGA.ConnorMacLeod=null;
		TGA.tournamentCoefficient=tournamentCoefficient;
		TGA.timelimit=timelimit;
		this.logfile=logfile;
	}
	
	
	public Tour startEngine()
	{
		final PMXCrossover[] cross= new PMXCrossover[offspringsPerEpoch/2];
		sons=new Population(populationSize);
		Tour[] figli;
		
		//inizializzo i vettori
		for(int j=0; j<offspringsPerEpoch/2; j++)
		{
			cross[j]=new PMXCrossover(deadlockThreshold, rand);		
		}
		
		prima=System.currentTimeMillis();
		long dopo=0;
		while(currentEpoch<maxEpoch)
		{
			//currentPopulation.evaluate();
			//if(currentEpoch==0)
			//TGA.ConnorMacLeod=TGA.DuncanMacLeod;
			//System.out.println("\n1Duncan==Connor"+(TGA.ConnorMacLeod==TGA.DuncanMacLeod));
	//		System.out.println(currentPopulation);
			
			
			for (int i=0; i<offspringsPerEpoch/2; i++)
			{
				cross[i].setPopulation(currentPopulation);
				figli=cross[i].call();
				sons.addTour(figli[0]);
				sons.addTour(figli[1]);
			}
			if(currentEpoch==999)
				dopo=System.currentTimeMillis();
//			System.out.println(sons);
			//System.out.println("\n2Duncan==Connor"+(TGA.ConnorMacLeod==TGA.DuncanMacLeod));
			//if(TGA.currentEpoch<50)
			currentPopulation.surviveElitism(sons, rand);
			//else
			//currentPopulation.survive(sons, rand);
			//currentPopulation.survive(sons, rand);
			//System.out.println("\n3Duncan==Connor"+(TGA.ConnorMacLeod==TGA.DuncanMacLeod));
	//		System.out.println(currentPopulation);
			sons.getPopulation().clear();
			System.out.println("\nEpoch:"+currentEpoch+"\nMutations: "+TGA.mutationCount+" LocalBuster: "+TGA.localOptimumBuster+" AVGLen: "+TGA.avgLength);
			/*long now=System.currentTimeMillis();
			double current=(double)((now-TGA.prima)-TGA.timelimit)/130000.0;
			int tournamentSize=(int)(TGA.tournamentCoefficient*(Math.pow(Math.E,current))*TGA.populationSize+1);
			System.out.println("Size: "+tournamentSize);
			*/
			//	for(Tour s: TGA.highlanders)
		//	{
			System.out.println("\nConnor: "+TGA.ConnorMacLeod+"\nRichie: "+TGA.Richie);
		//	}
			//if(TGA.ConnorMacLeod.getChromosome().getlength()<224600)
				//break;
			//System.out.println("\n4Duncan==Connor"+(TGA.ConnorMacLeod==TGA.DuncanMacLeod));
			/*if(TGA.ConnorMacLeod.getChromosome().getlength().intValue()==optimalLength)
			{
				dopo=System.currentTimeMillis();
				PrintStream ps=null;
				try {
					ps=new PrintStream(new FileOutputStream(logfile,true));
					ps.println("\nOptimum "+TGA.optimalLength+" found after "+currentEpoch+" epochs. Time: "+(dopo-prima));
					ps.close();
					break;
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					if(ps!=null)
					ps.close();
					}
			}*/
				
			currentEpoch++;
		}
		return TGA.Richie;
		
		/*if(currentEpoch==maxEpoch)
		{
			dopo=System.currentTimeMillis();
			PrintStream ps=null;
			try {
				ps=new PrintStream(new FileOutputStream(logfile,true));
				ps.println("\nConnor's length: "+ConnorMacLeod.getChromosome().getlength()+". "+"Time: "+(dopo-prima));
				ps.close();		
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(ps!=null)
				ps.close();
				}
		}
		
		/*System.out.println("\n\n\n\n----------------Adunanza----------------\n\n");
		for(Tour s: TGA.highlanders)
		{
			Tour.localSearch(s.getChromosome());
			System.out.println(s);
		}
		Tour.localSearch(TGA.ConnorMacLeod.getChromosome());
		System.out.println("\nTime:"+(dopo-prima)+"\nConnor: "+TGA.ConnorMacLeod);
		//System.out.println("\nGlobal optimum:"+TGA.DuncanMacLeod+"Mutation: "+mutationCount+" Tabu: "+tabuCount);
		*/
	}
	

}
