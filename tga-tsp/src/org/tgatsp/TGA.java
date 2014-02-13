/* Copyright (C) 2014  Carmelo Migliore, Fabrizio Gueli, Alessio Scicolone, Sergio Paccagnin
 *
 * This file is part of TGA-TSP
 *
 * TGA-TSP is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * TGA-TSP is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with TGA-TSP.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.tgatsp;


import java.util.Random;

public class TGA {
	
	private Population currentPopulation;
	private Population sons;
	private Random rand;
	private int maxEpoch;
	private boolean stopLocal;
	private boolean stop;
	private int offspringsPerEpoch;
	private int currentEpoch;
	private int neighbourhoodSize;
	private String instanceFile;
	private long startTime;

	public static Tour DuncanMacLeod; //Current Global Maximum - The Highlander
	public static Tour ConnorMacLeod; //The old Highlander
	public static Tour Richie;        //best among social disasters
	public static int localOptimumBuster;
	public static int tournamentCoefficient;
	public static int populationSize;
	public static int nameccDisasterThreshold; //threshold to apply the planet Namecc disaster
		
	public TGA (int maxEpoch, String instanceFile, long startTime, Random rand)
	{
		TGA.localOptimumBuster=0;
		currentEpoch=0;
		this.maxEpoch=maxEpoch;
		this.instanceFile=instanceFile;
		this.rand = rand;
		TGA.DuncanMacLeod=null;
		TGA.ConnorMacLeod=null;
		TGA.Richie=null;
		stop=false;
		this.startTime=startTime;
	}
	
	
	public Tour startEngine()
	{
		Cliente.init(instanceFile);
		if(Cliente.listaClienti.length>450)
		{
			this.neighbourhoodSize=90;
			TGA.populationSize=8000;
			currentPopulation= new Population(populationSize);
			TGA.nameccDisasterThreshold=maxEpoch;
			stopLocal=true;
			tournamentCoefficient=2;
		}
		else if(Cliente.listaClienti.length>190)
		{
			TGA.populationSize=600;
			this.neighbourhoodSize=20;
			maxEpoch=700;
			currentPopulation= new Population(populationSize);
			TGA.nameccDisasterThreshold=20;
			stopLocal=false;
			tournamentCoefficient=2;
		}
		else
		{
			TGA.populationSize=1000;
			this.neighbourhoodSize=20;
			currentPopulation= new Population(populationSize);
			TGA.nameccDisasterThreshold=maxEpoch;
			stopLocal=true;
			tournamentCoefficient=1;
		}
		Cliente.findNearest(neighbourhoodSize);
		offspringsPerEpoch=populationSize;
		Population.randomPopulation2Opt(0, populationSize, currentPopulation, rand);
		final Crossover[] cross= new Crossover[offspringsPerEpoch/2];
		sons=new Population(populationSize);
		Tour[] figli;
		
		for(int j=0; j<offspringsPerEpoch/2; j++)
		{
			cross[j]=new Crossover(rand);		
		}
		
		while(currentEpoch<maxEpoch && !stop)
		{
			for (int i=0; i<offspringsPerEpoch/2; i++)
			{
				cross[i].setPopulation(currentPopulation);
				figli=cross[i].call();
				sons.addTour(figli[0]);
				sons.addTour(figli[1]);
			}
			currentPopulation.surviveElitism(sons, rand);
			sons.getPopulation().clear();
			System.out.println("Epoch: "+currentEpoch+" LocalBuster: "+localOptimumBuster+"\nConnor: "+TGA.ConnorMacLeod+"\nRichie: "+TGA.Richie);
		
				
			if((stopLocal && localOptimumBuster>30) || (System.currentTimeMillis()-startTime)>2950000)
			{
				stop=true;
			}
			currentEpoch++;
		}
		if(TGA.Richie==null || (TGA.ConnorMacLeod!=null && TGA.ConnorMacLeod.getFitness()>TGA.Richie.getFitness()))
		{
			return TGA.ConnorMacLeod;
		}
		else
		{
			return TGA.Richie;
		}
	}
	
}
