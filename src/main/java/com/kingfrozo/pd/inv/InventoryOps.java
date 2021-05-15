package com.kingfrozo.pd.inv;

import dev.lone.itemsadder.api.ItemsAdder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryOps {

    private static final int INV_SIZE = 41;

    public static String serialize(Inventory inv) {
        String strInv = "";

        for(int i = 0; i < inv.getSize(); i++){
            ItemStack item =  inv.getItem(i);

            if( (item != null) && !(item.getType() == Material.AIR || !ItemsAdder.isCustomItem(item) )) {
                strInv += ItemsAdder.getCustomItemName(item);
            }

            strInv += ",";
        }
        return strInv;
    }

    public static Inventory deserialize(String s) {

        Inventory inv = Bukkit.createInventory(null, 45);

        int beginIndex = 0;
        for(int i = 0; i < INV_SIZE; i++){
            int endIndex = s.indexOf(',', beginIndex);
            String itemName = s.substring(beginIndex, endIndex);
            beginIndex = endIndex+1;
            inv.setItem(i, ItemsAdder.getCustomItem(itemName));
        }

        return inv;
    }

    public static void setInventory(Player player, String s) {
        int beginIndex = 0;
        for(int i = 0; i < INV_SIZE; i++) {
            int endIndex = s.indexOf(',', beginIndex);
            String itemName = s.substring(beginIndex, endIndex);
            beginIndex = endIndex+1;
            player.getInventory().setItem(i, ItemsAdder.getCustomItem(itemName));
        }
    }
}
