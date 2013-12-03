package org.tgatsp;


import Tour;
import twopt;

import java.util.*;

public class Tour
{
	private final ArrayList<Cliente> tour;
	private Float length;
	
	
	public Tour (int size)
	{
		this.tour = new ArrayList<Cliente>(size);
		while(tour.size() < size) tour.add(null);
	}
	
	public Tour (ArrayList<Cliente> t, Float length)
	{
		this.tour=t;
		this.length=length;
	}
	
	public Tour (Tour t)
	{
		this.tour=new ArrayList<Cliente>(t.getTour());
		this.length=t.length;
	}
	

	public Tour copy()
	{ 
		ArrayList <Cliente> temp = new ArrayList<Cliente>(tour.size());
		for (Iterator<Cliente> it= tour.iterator(); it.hasNext();)
		{
			temp.add(it.next());
		}
		return new Tour (temp, null);
	}
	
	public void addCliente(Cliente c)
	{
		if(!tour.contains(c))
		{
			tour.add(c);
		}
		else
		{
			throw new RuntimeException("Customer already present in tour");
		}
	}
	
	public void addCliente(int i, Cliente c)
	{
		if(!tour.contains(c))
		{
			tour.set(i,c);
		}
		else
		{
			throw new RuntimeException("Customer already present in tour");
		}
	}
	
	public void setCliente(int i, Cliente c)
	{
		tour.set(i,c);
	}
	
	public void insertCliente(int i, Cliente c)
	{
		tour.add(i,c);
	}
	
	public Cliente removeCliente(int i)
	{
		return tour.remove(i);
	}
	
	public Cliente getCliente(int i)
	{
		return tour.get(i);
	}
	
	private void calculatelength()
	{
		float temp =0;
		int i;
		for(i=1; i<tour.size(); i++)
		{
			temp+=tour.get(i).calculateDistance(tour.get(i-1));    // Distance between i and i-1
		}
		temp+=tour.get(i-1).calculateDistance(tour.get(0)); //Distanza tra l'ultimo ed il primo
		length= new Float(temp);	
	}
	
	public float getlength()
	{
		if (length!=null)
		{
			return length.floatValue();
		}
		else
		{
			this.calculatelength();
			return length.floatValue();
		}
	}
	
	public void setFitness(float length)
	{
		this.length=length;
	}
	
	public ArrayList<Cliente> getTour()
	{
		return tour;
	}
	
	public void markToRecalculate()
	{
		this.length = null;
	}
	
	@Override
	public boolean equals(Object s)
	{
		if (this.tour.equals(s))
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
	    int hash = 3;
	    hash = 53 * hash + (this.tour != null ? this.tour.hashCode() : 0);
	    return hash;
	}
	
	public int getSize()
	{
		return tour.size();
	}
	
	public String toString()
	{
		return tour.toString();
	}
	
	public static void localSearch(Tour t)
	{
		int dim = t.getSize();
	//	int rand = r.nextInt(dim/10);
		for(int i = 0; i < dim - 1; i++)
		{
			for(int k = i + 1; k < dim; k++)
			{
				Tour.twoOpt(t, i, k);
			}
		}
		
	}
		
	public static void twoOpt(Tour t, int inf, int sup)
	{

		float len_prec = t.getlength();
		Tour temporaneo = new Tour(t.getSize());
		int counter = 0;
		
		
			for(int i =0; i<inf; i++)
			{
				temporaneo.setCliente(i, t.getCliente(i));
			}
		
			for(int j=0; j<sup-inf; j++)
				{
				temporaneo.setCliente(j+inf, t.getCliente(sup-j-1));
				}
		
			for(int k=sup; k<t.getSize(); k++)
			{
				temporaneo.setCliente(k, t.getCliente(k));
			}
			counter++;
		
		
		if(temporaneo.getlength() < len_prec)
		{
			for(int k = inf; k<sup; k++)
			{
				t.setCliente(k,temporaneo.getCliente(k));
			}
			t.markToRecalculate();
			//twopt.twoOptc(t, r);
		}
				
	}
	

	
}
