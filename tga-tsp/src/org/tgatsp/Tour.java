package org.tgatsp;


import java.util.*;

public class Tour
{
	private final ArrayList<Cliente> tour;
	private Integer length;
	
	
	public Tour (int size)
	{
		this.tour = new ArrayList<Cliente>(size);
		while(tour.size() < size) tour.add(null);
	}
	
	public Tour (ArrayList<Cliente> t, Integer length)
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
		int temp =0;
		int i;
		for(i=1; i<tour.size(); i++)
		{
			temp+=tour.get(i).calculateDistance(tour.get(i-1));    // Distance between i and i-1
		}
		temp+=tour.get(i-1).calculateDistance(tour.get(0)); //Distanza tra l'ultimo ed il primo
		length= new Integer(temp);	
	}
	
	public Integer getlength()
	{
		if (length!=null)
		{
			return length;
		}
		else
		{
			this.calculatelength();
			return length;
		}
	}
	
	public void setFitness(Integer length)
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
		if (this==s || this.tour.equals(s))
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
	    return 0;
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

		Integer len_prec = t.getlength();
		Tour temporaneo = new Tour(t.getSize());
		//int counter = 0;
		
		
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
			//counter++;
		
		
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
	

	
	
	public static void fixedRadius(Tour t)
	{
		int dim = t.getSize();
		float radius;
		LinkedList<Integer> neighbours=new LinkedList<Integer>();
		//	int rand = r.nextInt(dim/10);
		Cliente tmp;
		for(int i = 1; i < dim; i++)
		{
			tmp=t.getCliente(i);
			radius=tmp.calculateDistance(t.getCliente(i-1));
			for(int j=0;j<dim;j++)
			{
				if(j==i-1 || j==i) continue;
				if(tmp.calculateDistance(t.getCliente(j))<radius)
				{
					neighbours.add(j);
				}
			}
			for(Integer k: neighbours)
			{
				if(i<k)
					Tour.twoOpt(t, i, k);
				else
					Tour.twoOpt(t, k, i);
			}
			neighbours.clear();
		}
		
	}
	
}
