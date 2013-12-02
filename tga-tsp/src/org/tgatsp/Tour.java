package org.tgatsp;


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
		this.tour=t.tour;
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
	
	public boolean equals(Tour t)
	{
		if (tour.equals(t.getTour()))
			return true;
		else
			return false;
	}
	
	public int getSize()
	{
		return tour.size();
	}
	
	public String toString()
	{
		return tour.toString();
	}
	
	
	public void twoOpt(Random r)
	{
		int dim = this.getSize();
		int inf = r.nextInt(dim);
		int sup;
		int temp;
		do
		{
			sup= r.nextInt(dim);
			if(inf>sup)
			{
			  temp = inf;
			  inf = sup;
			  sup = temp;
			}
			
		}while(inf == sup);
		
		for(int j = 0; j<= inf-1; j++)
		{
			this.addCliente(j, this.getCliente(j));
		}
		temp = sup;
		
		for(int j=inf; j<=sup; j++)
		{
			this.addCliente(j,this.getCliente(temp));
			temp--;
		}

		for(int j=sup+1; j<dim; j++)
		{
			this.addCliente(j, this.getCliente(j));
		}
				
	}
	

	
}