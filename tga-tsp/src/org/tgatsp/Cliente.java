package org.tgatsp;

//import java.util.*;
//import java.math.*;


public class Cliente
{
	Integer id;
	Coordinates coord;
	
	
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
	
	public int getId()
	{
		return id;
	}
	
	public boolean equals(Cliente c)
	{
		if (this.id==c.id)
			return true;
		else
			return false;
		
	}
	
	public String toString()
	{
		return ""+id;
	}
}