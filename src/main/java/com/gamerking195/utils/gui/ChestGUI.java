package com.gamerking195.utils.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gamerking195.utils.exceptions.InvalidGUIException;

public class ChestGUI 
extends GUI
{

	public ChestGUI(int slots,  String name) 
	{
		super(slots, GuiType.CHEST, name);
	}
	
	public Inventory toInventory() throws InvalidGUIException
	{		
		if (slots % 9 != 0) throw new InvalidGUIException(slots+" is not divisible by 9!");
		
		Inventory inv = Bukkit.createInventory(null, slots);
		
		for (int key : items.keySet())
		{
			ItemStack item = items.get(key);
			
			inv.setItem(key-1, item);
		}
		return inv;
	}
	
	public void displayToPlayer(Player p)
	{
		try 
		{
			p.openInventory(toInventory());
		}
		catch (InvalidGUIException e) 
		{
			e.printStackTrace();
		}
	}
}
