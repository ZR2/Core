package com.gamerking195.utils.gui;

import java.util.HashMap;

import org.bukkit.inventory.ItemStack;

public abstract class GUI 
{
	//TODO document this shit.

	int slots;
	GuiType type;
	HashMap<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
	String name;

	public GUI(int slots, GuiType type, String name)
	{
		this.slots = slots;
		this.type = type;
		this.name = name;
	}

	public void setItem(int slot, ItemStack item)
	{
		items.put(slot, item);
	}

	public void addItem(ItemStack item)
	{
		int slot = -1;
		for (int i = 0; i < items.keySet().size(); i++)
		{
			if (!items.keySet().contains(i))
			{
				slot = i;
			}
			continue;
		}
		
		items.put(slot, item);
	}
	
	public String getName()
	{
		return name;
	}
	
	public GuiType getType()
	{
		return type;
	}
	
	public int getSlots()
	{
		return slots;
	}
}
