package org.tgatsp;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//import java.util.*;
//import java.math.*;


public class Cliente
{
	private final Integer id;
	private final Coordinates coord;
	public static Cliente[] listaClienti;
	
	
	public Cliente (int id, Coordinates coord)
	{
		this.id = id;
		this.coord = coord;
	}
	
	public float calculateDistance (Cliente target)
	{
		Coordinates targetCoord = target.getCoordinates();
		return (float)Math.sqrt(Math.pow((this.coord.x - targetCoord.x), 2) + Math.pow((this.coord.y - targetCoord.y), 2));	
	}
	
	public Coordinates getCoordinates()
	{
		return coord;
	}
	
	public Integer getId()
	{
		return id;
	}
	
	@Override
	public boolean equals(Object s)
	{
		Cliente c = (Cliente)s;
		if (c==null || this.id.intValue()!=c.getId().intValue())
			return false;
		else
			return true;
	}
	
	@Override
	public int hashCode() {
	    int hash = 3;
	    hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
	    return hash;
	}
	
	public String toString()
	{
		return ""+id;
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
				Coordinates cr;
				Cliente.listaClienti=new Cliente[dimension];
				
				for (int i = 0; i < dimension; i++) 
				{					
					str=b.readLine();
					str_arr= str.split(" ");
					cr=new Coordinates(Float.parseFloat(str_arr[1]),Float.parseFloat(str_arr[2]));
					
					int k= Integer.parseInt(str_arr[0]);
					c=new Cliente(k,cr);
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