package me.fishergee.pickaxelevels.utils;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LevelUtil {

    /*
    Methods assume that the provided itemstack
    is a variation of the custom pickaxe
     */

    public static void updateLore(ItemStack item, int broken){
        ItemMeta itemMeta = item.getItemMeta();

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&',"&bTotal blocks mined: &f" + broken));
        lore.add(ChatColor.translateAlternateColorCodes('&',"&bBlocks till next level: &f" + (500 - (broken % 500))));
        lore.add(ChatColor.translateAlternateColorCodes('&',"&bLevel: &f" + (Math.abs(broken / 500) + 1)));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
    }

    public static boolean levelSuffice(ItemStack item){
        NBTItem nbtItem = new NBTItem(item);

        int blocksBroken = nbtItem.getInteger("broken");

        if(blocksBroken % 500 == 0){
            return true;
        }else{
            return false;
        }
    }

    public static boolean levelMax(ItemStack item){
        NBTItem nbtItem = new NBTItem(item);

        int blocksBroken = nbtItem.getInteger("broken");

        if(blocksBroken / 500 == 600){
            return true;
        }else{
            return false;
        }
    }

    public static int findCustomPickaxeIndex(Inventory inventory){
        for(int i = 0; i < inventory.getSize(); i++){
            NBTItem nbtItem = new NBTItem(inventory.getItem(i));
            if(nbtItem.getString("id").equals("pickaxelevels")){
                return i;
            }
        }
        return -1;
    }
}
