package org.tgatsp;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//import java.util.*;
//import java.math.*;


public class Cliente
{
	private final int id;
	private final double x;
	private final double y;
	public static Cliente[] listaClienti;
	public static Cliente[][] nearest;
	
	
	public Cliente (int id, double x, double y)
	{
		this.id = id;
		this.x = x;
		this.y=y;
	}
	
	public int calculateDistance (Cliente target)
	{
		return (int)(Math.sqrt((this.x - target.x)*(this.x - target.x) + (this.y - target.y)*(this.y - target.y))+0.5);	
	}
	
	public int getId()
	{
		return id;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj==null)
			return false;
		
		Cliente c = (Cliente)obj;
		if (this==obj || this.id==c.id)
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
	    return this.id;
	}
	
	public String toString()
	{
		return ""+id;
	}
	
	public static void findNearest(int numNeighbours)
	{
		nearest= new Cliente[listaClienti.length][numNeighbours];
		for(int i=0; i<listaClienti.length; i++)
		{	
			ArrayList<Cliente> temp=new ArrayList<Cliente>(Arrays.asList(Cliente.listaClienti));
			for(int j=0; j<numNeighbours; j++)
			{
				int min=Integer.MAX_VALUE;
				int minIdx=-1;
				for(int k=0; k<temp.size(); k++)
				{
					if(temp.get(k).equals(listaClienti[i])) continue;
					int dist=listaClienti[i].calculateDistance(temp.get(k));
					if(dist<min)
					{
						min=dist;
						minIdx=k;
					}
				}
				nearest[i][j]=temp.remove(minIdx);		
			}
		}
	}
	
	public static void init(String file)
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
				String[] str_arr= str.split(": ");
				TGA.nome=str_arr[1];
				str=b.readLine();
				str_arr= str.split(": ");
				TGA.comment=str_arr[1];
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
					c=new Cliente(k,Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
					Cliente.listaClienti[i]=c;
					//System.out.println(Cliente.listaClienti[i]);			
				}
				b.close();
		}catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
		catch(IOException io)
		{
			io.printStackTrace();
		}
	}
}