package org.tgatsp;

import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		Tour prova = new Tour(15);
		Tour prova2= new Tour(15);
		Random rand = new Random();
		Coordinates coord;
		ArrayList<Integer> serie= new ArrayList<Integer>();
		
		int j=0;
		Integer temp;
		while(serie.size()<15)
		{
			temp = rand.nextInt(15);
			if (!serie.contains(temp))
			{
				serie.add(j,temp);
				j++;
			}
		}
		
		System.out.println("Serie: "+serie+"\n");
		Cliente c;
		for(int i=0; i<15; i++)
		{
			
			coord=new Coordinates(rand.nextFloat()*i+2, rand.nextFloat()*i*i);
			c=new Cliente(i,coord);
			prova.addCliente(i,c);
			prova2.addCliente(serie.get(i),c);
		}
		
		
		System.out.println("prova1: "+prova+"\n");
		System.out.println("prova2: "+prova2+"\n");
		
		Solution s1 = new Solution(prova,null,1/prova.getlength());
		Solution s2 = new Solution(prova2,null,1/prova2.getlength());
		
		//Solution vett[] = s1.PMXCrossover(s2);
		//System.out.println("Figlio1: "+vett[0].getchromosome() +"\nFiglio2: "+vett[1].getchromosome()+"\n");
		
	}

}
