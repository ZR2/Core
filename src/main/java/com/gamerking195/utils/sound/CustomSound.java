package com.gamerking195.utils.sound;

import org.bukkit.entity.Player;

public abstract class CustomSound 
{
	String name;
	String path;
	
	public CustomSound(String name, String path)
	{
		this.name = name;
		this.path = path;
	}
	
	public abstract void play(float volume, float pitch, Player... players);
	
	public abstract void stop(float volume, float pitch, Player... players);

	public String getName() {return name;}
	public String getPath() {return path;}
}
