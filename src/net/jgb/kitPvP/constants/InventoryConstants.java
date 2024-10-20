package net.jgb.kitPvP.constants;

import java.util.Arrays;
import java.util.List;

import net.jgb.kitPvP.utils.customs.CustomPaginatedInventory;

public class InventoryConstants {
	
	public static List<Integer> MIDDLE_INVENTORY = Arrays.asList(11, 12, 13, 14, 15, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 38, 39, 40, 41, 42);

	public static CustomPaginatedInventory MENU_INVENTORY = new CustomPaginatedInventory("§e§lSelection Kit Menu", 54);
	public static CustomPaginatedInventory BOX_INVENTORY = new CustomPaginatedInventory("§e§lMystery Box", 54);
	
	public static String CONFIG_WARP_INVENTORY = "§e§lWARP CONFIG";
}
