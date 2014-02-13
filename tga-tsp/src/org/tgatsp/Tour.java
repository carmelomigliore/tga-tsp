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


import java.util.*;

public final class Tour implements Comparable<Tour>
{
	private final short[] tour;
	private int length;
	private float fitness;
	private float window;  //not used in the current implementation
	private boolean killingFlag;  //not used in the current implementation
	
	public Tour (int size)
	{
		this.tour = new short[size];
		//while(tour.size() < size) tour.add(null);
		length=-1;
		fitness=-1;
	}
	
	public Tour (short[] t, int length)
	{
		this.tour=t;
		this.length=length;
		this.fitness=1/(float)length;
	}
	
	
	public final int compareTo(Tour o)
	{
		if(this.length!=-1 && o.length!=-1)
			return this.length-o.length;
		else if(this.length!=-1)
			return this.length-o.getlength();
		else if(o.length!=-1)
			return this.getlength()-o.length;
		return this.getlength()-o.getlength();
	}
	
	
	private final void calculatelength()
	{
		int temp =0;
		int i;
		for(i=1; i<tour.length; i++)
		{
			temp+=Cliente.distancesMatrix[tour[i]][tour[i-1]];    // Distance between i and i-1
		}
		temp+=Cliente.distancesMatrix[tour[i-1]][tour[0]]; //Distanza tra l'ultimo ed il primo
		length= temp;	
	}
	
	public final int getlength()
	{
		if (length!=-1)
		{
			return length;
		}
		else
		{
			this.calculatelength();
			return length;
		}
	}
	
	public final float getFitness()
	{
		if(fitness!=-1)
			return fitness;
		else if(length!=-1)
		{
			fitness=1/(float)length;
			return fitness;
		}
		else
		{
			calculatelength();
			fitness=1/(float)length;
			return fitness;
		}
	}
	
	public final void setWindow(Float window)
	{
		this.window=window;
	}
	
	public final Float getWindow()
	{
		return window;
	}
	
	public final void setKillingFlag(boolean flag)
	{
		this.killingFlag=flag;
	}
	
	public final boolean getKillingFlag()
	{
		return killingFlag;
	}
	
	public final short[] getTour()
	{
		return tour;
	}
	
	public final void markToRecalculate()
	{
		this.length = -1;
	}
	
	/*
	 * Insertion mutation
	 */
	public final void mutate(Random rand)
	{
		
		int customerToDisplace = rand.nextInt(this.tour.length);
		int insertionPosition;
		do
		{
			insertionPosition = rand.nextInt(this.tour.length);
			
		} while (insertionPosition == customerToDisplace);
		if(insertionPosition>customerToDisplace)
		{
			short temp = tour[customerToDisplace];
			for(int i=customerToDisplace; i<insertionPosition; i++)
			{
				tour[i]=tour[i+1];
			}
			tour[insertionPosition]=temp;
		}
		else
		{
			short temp = tour[customerToDisplace];
			for(int i=customerToDisplace; i>insertionPosition; i--)
			{
				tour[i]=tour[i-1];
			}
			tour[insertionPosition]=temp;
		}
	}
	
	/*
	 * Insertion mutation (static)
	 */
	public final static void mutate(short[] t, Random rand)
	{
		
		int customerToDisplace = rand.nextInt(t.length);
		int insertionPosition;
		do
		{
			insertionPosition = rand.nextInt(t.length);
			
		} while (insertionPosition == customerToDisplace);
		if(insertionPosition>customerToDisplace)
		{
			short temp = t[customerToDisplace];
			for(int i=customerToDisplace; i<insertionPosition; i++)
			{
				t[i]=t[i+1];
			}
			t[insertionPosition]=temp;
		}
		else
		{
			short temp = t[customerToDisplace];
			for(int i=customerToDisplace; i>insertionPosition; i--)
			{
				t[i]=t[i-1];
			}
			t[insertionPosition]=temp;
		}
	}
	
	public final int getSize()
	{
		return tour.length;
	}
	
	public final String toString()
	{
		return ""+length;
	}
	
	/*
	 * Full 2-Opt (not used)
	 */
	public final static boolean full2Opt(short[] t)
	{
		int dim = t.length;
		boolean again=false;;
		int maxImpr;
		int indexMaxImpr;;
		for(int i = 0; i < dim - 1; i++)
		{
			maxImpr=0;
			indexMaxImpr=-1;
			for(int k = i + 2; k < dim; k++)
			{
				int improvement= (Cliente.distancesMatrix[t[i]][t[(i-1+dim)%dim]]+Cliente.distancesMatrix[t[k]][t[k-1]])-(Cliente.distancesMatrix[t[i]][t[k]]+Cliente.distancesMatrix[t[(i-1+dim)%dim]][t[k-1]]);
				if(improvement>maxImpr)
				{
					maxImpr=improvement;
					indexMaxImpr=k;
				}
			}
			if(indexMaxImpr!=-1)
			{
				for (int j = i; j < i +((indexMaxImpr-i)/2); j++) 
				{
					  short temp = t[j];
					  t[j] = t[indexMaxImpr+i-1-j];
					  t[indexMaxImpr+i-1-j] = temp;
				}
				again=true;
			}
		}
		return again;
		
	}
	
	
	/*
	 * Fast implementation of 2-Opt local search heuristic 
	 */
	public final static boolean fixedRadiusNolookNearMatrix(short[] t, boolean noLook[])
	{
		int dim = t.length;
		int radius;
		boolean improve_flag;
		boolean global_improve=false;
		int improvement;
		int index=-1;
		int IdxMaxImpr=-1;
		for(int i = 0; i < dim; i++)
		{
			if(noLook[t[i]]) continue;
			improve_flag = false;
			radius=Cliente.distancesMatrix[t[i]][t[(i-1+dim)%dim]];
			improvement=0;
			IdxMaxImpr=-1;
			for(int j=0;j<Cliente.nearest[0].length;j++)
			{
				index=-1;
				if(Cliente.distancesMatrix[t[i]][Cliente.nearest[t[i]-1][j]]>=radius)
					break;
				else
				{		
					for(int k=0; k<dim; k++)
					{
						if(Cliente.nearest[t[i]-1][j]==t[k])
						{
							index=k;
							break;
						}
					}
					
					final int prev_edges=Cliente.distancesMatrix[t[i]][t[(i-1+dim)%dim]]+Cliente.distancesMatrix[t[index]][t[(index-1+dim)%dim]];
					final int after_edges=Cliente.distancesMatrix[t[(i-1+dim)%dim]][t[(index-1+dim)%dim]]+Cliente.distancesMatrix[t[i]][t[index]];
					if(after_edges<prev_edges && (prev_edges-after_edges)>improvement)
					{
						improvement=prev_edges-after_edges;
						IdxMaxImpr=index;
						improve_flag = true;
						global_improve=true;
					}
				}
			}
			if(improve_flag)
			{	
				if(i>IdxMaxImpr)
				{
					int temp=i;
					i=IdxMaxImpr;
					IdxMaxImpr=temp;
				}
				for (int j = i; j < i +((IdxMaxImpr-i)/2); j++) 
				{
					  short temp = t[j];
					  t[j] = t[IdxMaxImpr +i  - 1 - j];
					  t[IdxMaxImpr+i - 1 - j] = temp;
				}
				noLook[t[i]] = false;
				noLook[t[IdxMaxImpr]] = false;
				
			}
			else
				noLook[t[i]] = true;
		}
		return global_improve;
	}
	
	/*
	 * Full 3-Opt (not used)
	 */
	public final static void fullThreeOpt(short[] t)
	{
		int dim = t.length;
		
		for(int i=0; i<dim;i++)
		{
			for(int j=i+1; j<dim;j++)
			{
				for(int k=j+1; k<dim; k++)
				{
					threeOptSwap(t,i,j,k);
				}
				
			}
		}
	}
	
	public final static boolean threeOptSwap(short[] t, int first, int second, int third)
	{
		int dim= t.length;
		int prev_length=Cliente.distancesMatrix[t[(first-1+dim)%dim]][t[first]]+Cliente.distancesMatrix[t[second-1]][t[second]]+Cliente.distancesMatrix[t[third-1]][t[third]];
		int after_length=Cliente.distancesMatrix[t[(first-1+dim)%dim]][t[second]]+Cliente.distancesMatrix[t[second-1]][t[third-1]]+Cliente.distancesMatrix[t[third]][t[first]];
		
		if(after_length<prev_length)
		{
			ArrayList<Short> tmp= new ArrayList<Short>(t.length);
			for(int i=0; i<t.length;i++)
				tmp.add(t[i]);
		//	System.out.println(tmp);
			Collections.reverse(tmp.subList(first, second));
			ArrayList<Short> swap;
			swap= new ArrayList<Short>(tmp.subList(second, third));
			tmp.subList(second, third).clear();
			tmp.addAll(first, swap);
		//	System.out.println(tmp);
			Short[] arr= new Short[t.length];
			tmp.toArray(arr);
			for(int i=0; i<t.length;i++)
				t[i]=arr[i];
		/*	for(Cliente c : t)
			{
				System.out.println(c);
			}*/
			return true;
		}
		return false;
	}
			
	
}
