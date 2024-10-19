package net.jgb.kitPvP.utils.customs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class CustomInventory {

    private Inventory inventory;
    private int pageSize;
    private int currentPage;
    private List<ItemStack> items;
    private List<Integer> slots;
    private HashMap<Integer, ItemStack> unusableSlots;
    
    public CustomInventory(String title, int size) {
        this.inventory = Bukkit.createInventory(null, size, title);
        this.pageSize = size;
        this.currentPage = 0;
        this.items = new ArrayList<>();
        this.slots = null;
        this.unusableSlots = new HashMap<>();
    }

    public CustomInventory(String title, InventoryType type) {
        this.inventory = Bukkit.createInventory(null, type, title);
        this.pageSize = type.getDefaultSize();
        this.currentPage = 0;
        this.items = new ArrayList<>();
        this.slots = null;
        this.unusableSlots = new HashMap<>();
    }

    public void openInventory(Player player) {
        refreshInventory();
        player.openInventory(inventory);
    }

    public void nextPage() {
        int totalItems = 0;

        if (this.slots != null && !this.slots.isEmpty()) {
            int slotCount = Math.min(pageSize, this.slots.size());
            for (int i = 0; i < slotCount; i++) {
                int currentSlot = this.slots.get(i);
                if (!unusableSlots.containsKey(currentSlot)) {
                    totalItems++;
                }
            }
        } else {
            totalItems = pageSize;
        }

        int totalPages = (int) Math.ceil((double) items.size() / totalItems);

        if (currentPage + 1 < totalPages) {
            currentPage++;
            refreshInventory();
        }
    }

    public void previousPage() {
        if (currentPage > 0) {
            currentPage--;
            refreshInventory();
        }
    }

    public void refreshInventory() {
        inventory.clear();

        int slotCount = (this.slots != null && !this.slots.isEmpty()) ? this.slots.size() : pageSize;
        
        int startIndex = currentPage * slotCount;

        if (this.slots != null && !this.slots.isEmpty()) {
            int itemIndex = startIndex;

            for (int slotIndex = 0; slotIndex < slotCount; slotIndex++) {
                int currentSlot = this.slots.get(slotIndex);
                
                while (unusableSlots.containsKey(currentSlot)) {
                    slotIndex++;
                    if (slotIndex >= slotCount) return;
                    currentSlot = this.slots.get(slotIndex);
                }
                
                if (itemIndex < items.size()) {
                    inventory.setItem(currentSlot, items.get(itemIndex));
                    itemIndex++;
                }
            }
        } else {
            int itemIndex = currentPage * (pageSize - unusableSlots.size());
            int slotIndex = 0;

            while (itemIndex < items.size() && slotIndex < inventory.getSize()) {
                while (unusableSlots.containsKey(slotIndex) || !(slotIndex >= 0 && slotIndex < inventory.getSize())) {
                    slotIndex++;
                    if (slotIndex >= inventory.getSize()) {
                        currentPage++;
                        slotIndex = 0;
                        itemIndex = currentPage * (pageSize - unusableSlots.size());
                        if (itemIndex >= items.size()) return;
                    }
                }

                if (slotIndex < inventory.getSize() && !unusableSlots.containsKey(slotIndex)) {
                    inventory.setItem(slotIndex, items.get(itemIndex));
                    itemIndex++;
                }

                slotIndex++;
            }
        }
        for (Entry<Integer, ItemStack> entry : this.unusableSlots.entrySet()) {
        	ItemStack item = new ItemStack(entry.getValue());
        	ItemMeta itemMeta = entry.getValue().getItemMeta();
        	if (itemMeta.getLore() != null) {
        		List<String> updatedLore = itemMeta.getLore().stream()
                .map(line -> line.replace("{page}", String.valueOf(currentPage)))
                .collect(Collectors.toList());
        		itemMeta.setLore(updatedLore);
        	}
        	item.setItemMeta(itemMeta);
        	
            this.inventory.setItem(entry.getKey(), item);
        }
    }

    
    public List<Integer> getSlots() {
    	return this.slots;
    }
    
    public void setSlots(List<Integer> slots) {
    	this.slots = slots;
    }
    
    public void setItems(List<ItemStack> items) {
    	this.items = items;
    }
    
    public void setItem(int slot, ItemStack item) {
    	this.inventory.setItem(slot, item);
    }
    
    public void addItem(ItemStack item) {
        this.items.add(item);
    }
    
    public HashMap<Integer, ItemStack> getUnusableSlots() {
    	return this.unusableSlots;
    }
    
    public void setUnusableSlots(HashMap<Integer, ItemStack> unusableSlots) {
    	this.unusableSlots = unusableSlots;
    }
    
    public Inventory getInventory() {
    	return this.inventory;
    }
    
    public Integer getCurrentPage() {
    	return this.currentPage;
    }
}