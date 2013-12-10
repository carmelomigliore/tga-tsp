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
	
	public void subLength(Integer sub)
	{
		this.length-=sub;
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
			for(int k = i + 2; k < dim; k++)
			{
				Tour.twoOpt(t, i, k,dim);
			}
		}
		
	}
	
	 public static void twoOpt(Tour t, int inf, int sup)
     {

             Integer len_prec = t.getlength();
             Tour temporaneo = new Tour(t.getSize());
             //int counter = 0;
             
         //    System.out.println(t);
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
        //     System.out.println(temporaneo);
             
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
		
	 
	 /*2-opt sbagliato, ma migliore!!!*/
	public static void twoOpt(Tour t, int inf, int sup, int dim)
	{

		Integer len_prev_edges = t.getCliente(inf).calculateDistance(t.getCliente((inf-1+dim)%dim))+t.getCliente(sup).calculateDistance(t.getCliente((sup-1+dim)%dim));
		Integer len_new_edges;
		Cliente[] temporaneo = new Cliente[sup-inf+2];
		//int counter = 0;
		
		
		//	System.out.println(t);
			temporaneo[0]=t.getCliente((inf-1+dim)%dim);
		
			for(int j=0; j<sup-inf; j++)
				{
					temporaneo[j+1]= t.getCliente(sup-j-1);
				}
		
			temporaneo[sup-inf+1]=t.getCliente(sup);
			//counter++;
		//	for(Cliente c : temporaneo)
		//	System.out.println(c);
			
		len_new_edges= temporaneo[0].calculateDistance(temporaneo[1])+temporaneo[sup-inf+1].calculateDistance(temporaneo[sup-inf]);
		
		if(len_new_edges<len_prev_edges)
		{
			
			for(int k = inf; k<=sup; k++)
			{
				t.setCliente(k,temporaneo[k-inf+1]);
			}
			
			t.markToRecalculate();
			//twopt.twoOptc(t, r);
		}
				
	}
	
	
	public static boolean twoOptbool(Tour t, int inf, int sup, int dim)
	{

		Integer len_prev_edges = t.getCliente(inf).calculateDistance(t.getCliente((inf-1+dim)%dim))+t.getCliente(sup).calculateDistance(t.getCliente((sup-1+dim)%dim));
		Integer len_new_edges;
		Cliente[] temporaneo = new Cliente[sup-inf+2];
		//int counter = 0;
		
		
		//	System.out.println(t);
			temporaneo[0]=t.getCliente((inf-1+dim)%dim);
		
			for(int j=0; j<sup-inf; j++)
				{
					temporaneo[j+1]= t.getCliente(sup-j-1);
				}
		
			temporaneo[sup-inf+1]=t.getCliente(sup);
			//counter++;
		//	for(Cliente c : temporaneo)
		//	System.out.println(c);
			
		len_new_edges= temporaneo[0].calculateDistance(temporaneo[1])+temporaneo[sup-inf+1].calculateDistance(temporaneo[sup-inf]);
		
		if(len_new_edges<len_prev_edges)
		{
			
			for(int k = inf; k<=sup; k++)
			{
				t.setCliente(k,temporaneo[k-inf+1]);
			}
			
			t.markToRecalculate();
			return true;
			//twopt.twoOptc(t, r);
		}
		return false;
				
	}
	
	public static void twoOptSublist(Tour t, int inf, int sup, int dim)
	{

		Integer len_prev_edges = t.getCliente(inf).calculateDistance(t.getCliente((inf-1+dim)%dim))+t.getCliente(sup).calculateDistance(t.getCliente((sup-1+dim)%dim));
		Integer len_new_edges;
		LinkedList<Cliente> temporaneo;
		//int counter = 0;
		
		//if(inf!=0)
			temporaneo=new LinkedList<Cliente>(t.getTour().subList(inf, sup)); //sup+1 escluso, quindi da inf-1 a sup
		/*else
		{
			//temporaneo[0]=t.getCliente((inf-1+dim)%dim);
			for(int j=0; j<sup-inf; j++)
			{
				temporaneo.add(t.getCliente(inf+j));
			}
	
			//temporaneo[sup-inf+1]=t.getCliente(sup);
		}*/
		//System.out.println(t);
		//System.out.println(temporaneo);
			//System.out.println(t)
			/*temporaneo[0]=t.getCliente((inf-1+dim)%dim);
		
			for(int j=0; j<sup-inf; j++)
				{
					temporaneo[j+1]= t.getCliente(sup-j-1);
				}
		
			temporaneo[sup-inf+1]=t.getCliente(sup);
			*/
			//counter++;
			//for(Cliente c : temporaneo)
			//System.out.println(c);
			
		len_new_edges= t.getCliente((inf-1+dim)%dim).calculateDistance(t.getCliente((sup-1+dim)%dim))+t.getCliente(sup).calculateDistance(t.getCliente(inf));
		
		if(len_new_edges<len_prev_edges)
		{
			int count=0;
			for(Iterator<Cliente> it = temporaneo.descendingIterator(); it.hasNext();)
			{
				t.setCliente(inf+count, it.next());
				count++;
			}
			/*for(int k = 0; k<sup-inf; k++)
			{
				t.setCliente(k+inf,temporaneo[sup-inf-k]);
			}
			*/
			
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
					Tour.twoOpt(t, i, k,dim);
				else
					Tour.twoOpt(t, k, i,dim);
			}
			neighbours.clear();
		}
		
	}
	
	
	public static void fixedRadiusNolook(Tour t, boolean noLook[])
	{
		int dim = t.getSize();
		float radius;
		boolean improve_flag;
		LinkedList<Integer> neighbours=new LinkedList<Integer>();
		//	int rand = r.nextInt(dim/10);
		Cliente tmp;
		for(int i = 1; i < dim; i++)
		{
			if(noLook[t.getCliente(i).getId()] == true) continue;
			improve_flag = false;
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
				{	
					if(Tour.twoOptbool(t, i, k,dim))
					{
						noLook[t.getCliente(i).getId()] = false;
						noLook[t.getCliente(k).getId()] = false;
						improve_flag = true;
					}
				}
				else
				{
					if(Tour.twoOptbool(t, k, i,dim))
					{
						noLook[t.getCliente(i).getId()] = false;
						noLook[t.getCliente(k).getId()] = false;
						improve_flag = true;
						
					}
				}
			}
			neighbours.clear();
			if(improve_flag == false) noLook[t.getCliente(i).getId()] = true;
		}
		
	}
	
}
