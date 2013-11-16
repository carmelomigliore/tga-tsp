package org.tgatsp;

import java.util.*;
import java.math.*;


public class Cliente
{
	int id;
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
}