package com.gamerking195.utils;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class UtilVector 
{
	/**
	 * 
	 * Turns a speed into a vector.
	 * 
	 * @param loc The location from which the vector will be calculated.
	 * @param speed The speed in meters per second.
	 * @return The vector with the speed amount
	 */
	public static Vector getSpeedAsVector(Location loc, double speed)
	{
		Vector direction = loc.getDirection();
		return direction.multiply(speed/25);
	}
}
