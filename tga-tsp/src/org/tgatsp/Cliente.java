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


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//import java.util.*;
//import java.math.*;


public final class Cliente
{
	private final short id;
	private final double x;
	private final double y;
	public static Cliente[] listaClienti;
	public static short[][] nearest;
	public static int[][] distancesMatrix;
	
	
	public Cliente (short id, double x, double y)
	{
		this.id = id;
		this.x = x;
		this.y=y;
	}
	
	public final int calculateDistance (Cliente target)
	{
		return (int)(Math.sqrt((this.x - target.x)*(this.x - target.x) + (this.y - target.y)*(this.y - target.y))+0.5);	
	}
	
	public final short getId()
	{
		return id;
	}
	
	@Override
	public final boolean equals(Object obj)
	{
		if(this!=obj)
			return false;
		else return true;
	}
	
	@Override
	public final int hashCode() {
	    return this.id;
	}
	
	public final String toString()
	{
		return ""+id;
	}
	
	public final static void findNearest(int numNeighbours)
	{
		nearest= new short[listaClienti.length][numNeighbours];
		distancesMatrix= new int[listaClienti.length+1][listaClienti.length+1];
		for(int i=0; i<listaClienti.length; i++)
		{	
			ArrayList<Cliente> temp=new ArrayList<Cliente>(Arrays.asList(Cliente.listaClienti));
			for(int j=0; j<numNeighbours; j++)
			{
				int min=Integer.MAX_VALUE;
				int minIdx=-1;
				for(int k=0; k<temp.size(); k++)
				{
					if(temp.get(k)==listaClienti[i]) continue;
					int dist=listaClienti[i].calculateDistance(temp.get(k));
					if(j==0)
						distancesMatrix[i+1][k+1]=dist;
					if(dist<min)
					{
						min=dist;
						minIdx=k;
					}
				}
				nearest[i][j]=temp.remove(minIdx).getId();		
			}
		}
	}
	
	public final static void init(String file)
	{
		
		//String type;
		int dimension;//Size di Tour
		//String edge_weight_type;
		try
		{	
	
				FileReader f;
				f=new FileReader(file);
    
				BufferedReader b;
				b=new BufferedReader(f);	
				
				String str;
				
				str=b.readLine();
				String[] str_arr;
				str=b.readLine();
				b.readLine();
				//str_arr= str.split(": ");
				//type=str_arr[1];
				str=b.readLine();
				str_arr= str.split(": ");
				dimension=Integer.parseInt(str_arr[1]);
				b.readLine();
				//str_arr= str.split(": ");
				//edge_weight_type=str_arr[1];
				//Lettura a vuoto
				b.readLine();	
				Cliente c;
				int k;
				Cliente.listaClienti=new Cliente[dimension];
				StringTokenizer st;
				for (int i = 0; i < dimension; i++) 
				{					
					str=b.readLine();
					st=new StringTokenizer(str);
					k= Integer.parseInt(st.nextToken());
					c=new Cliente((short)k,Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
					Cliente.listaClienti[i]=c;
					//System.out.println(Cliente.listaClienti[i]);			
				}
				b.close();
		}
		catch(IOException io)
		{
			io.printStackTrace();
		}
	}
}