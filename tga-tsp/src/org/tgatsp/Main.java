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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	
	public static String DataFileDir;
	public static long seed;
	public static int maxEpoch;
	public static int repetitions;
	public static LinkedList<String> instances = new LinkedList<String>();

	public static void main(String[] args) {
		
	
		readParams(args[0]);
		Random rand= new Random(seed);
		PrintStream ps=null;
		try {
			ps=new PrintStream(new FileOutputStream("results.csv",true));
			ps.println("Instance,Mean,Best,Worst,Mean_Time,Min_Time,Max_Time");
			ps.close();		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
		for(String s : instances)
		{
			int lengthSum=0;
			int min=Integer.MAX_VALUE;
			int max=0;
			Tour best=null;
			long timeSum=0;
			long timeMin=Integer.MAX_VALUE;
			long timeMax=0;
			for(int i=1; i<=repetitions; i++)
			{
				System.out.println(s+" run "+i);
				long prima=System.currentTimeMillis();
				String file=DataFileDir+"/"+s;
				TGA algorithm=new TGA(maxEpoch,file,prima, rand);	
				best=algorithm.startEngine();
				long dopo=System.currentTimeMillis();
				long time=dopo-prima;
				System.out.println("Final length: "+best.getlength()+" Time: "+time);
				writeTour(s,best.getTour(),i);	
				timeSum+=time;
				if(time<timeMin)
					timeMin=time;
				if(time>timeMax)
					timeMax=time;
				lengthSum+=best.getlength();
				if(best.getlength()<min)
					min=best.getlength();
				if(best.getlength()>max)
					max=best.getlength();
			}
			ps=null;
			try {
				ps=new PrintStream(new FileOutputStream("results.csv",true));
				ps.println(s+","+(float)lengthSum/(float)repetitions+","+min+","+max+","+((float)timeSum/(1000f*repetitions))+","+(float)timeMin/1000f+","+(float)timeMax/1000f);
				ps.close();		
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(ps!=null)
				ps.close();
				}
		}
	}
	
	public static void readParams(String file)
	{
		BufferedReader br=null;
		try
		{	
	
				FileReader f;
				f=new FileReader(file);
    
				
				br=new BufferedReader(f);	
				
				String str;
				br.readLine();
				str=br.readLine();
				StringTokenizer st= new StringTokenizer(str);
				st.nextToken();
				DataFileDir=st.nextToken();
				str=br.readLine();
				st= new StringTokenizer(str);
				st.nextToken();
				maxEpoch=Integer.parseInt(st.nextToken());
				str=br.readLine();
				st= new StringTokenizer(str);
				st.nextToken();
				repetitions=Integer.parseInt(st.nextToken());
				str=br.readLine();
				st= new StringTokenizer(str);
				st.nextToken();
				seed=Long.parseLong(st.nextToken());
				br.readLine();
				br.readLine();
				while(!(str=br.readLine()).equals("ENDINSTANCES"))
				{
					instances.add(str);
				}
				
		}catch(IOException fne){fne.printStackTrace();}
		finally{try {br.close();} catch (IOException e) {e.printStackTrace();}}
				
	}
	
	public static void writeTour(String instance, short[] tour, int run)
	{
		PrintStream ps=null;
		try {
			File f=new File(DataFileDir+"/Output_tour/"+instance+".run"+run+".tour");
			ps=new PrintStream(new FileOutputStream(f,false));
			ps.print("NAME : "+instance+".run"+run+".tour\n");
			ps.print("TYPE : TOUR\n");
			ps.print("DIMENSION : "+tour.length+"\n");
			ps.print("TOUR_SECTION\n");
			for(int i=0; i<tour.length; i++)
			{
				ps.println(tour[i]);
			}
			ps.println("-1\nEOF");
			ps.close();		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null)
			ps.close();
			}
	}
		
		
}

