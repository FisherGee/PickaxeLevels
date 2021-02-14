package me.fishergee.pickaxelevels.utils;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LevelUtil {

    /*
    Methods assume that the provided itemstack
    is a variation of the custom pickaxe
     */

    public static ItemStack updateLore(ItemStack item, int broken){
        ItemMeta itemMeta = item.getItemMeta();

        List<String> lore = new ArrayList<>();
        lore.add("Total blocks mined: " + broken);
        lore.add("Blocks till next level: " + (55 - broken));
        lore.add("Level: " + (Math.abs(broken / 55)));

        item.setItemMeta(itemMeta);

        return item;
    }

    public static boolean levelSuffice(ItemStack item){
        NBTItem nbtItem = new NBTItem(item);

        int blocksBroken = nbtItem.getInteger("broken");

        if(blocksBroken % 55 == 0){
            return true;
        }else{
            return false;
        }
    }

    public static boolean levelMax(ItemStack item){
        NBTItem nbtItem = new NBTItem(item);

        int blocksBroken = nbtItem.getInteger("broken");

        if(blocksBroken / 55 == 600){
            return true;
        }else{
            return false;
        }
    }

    public static void upgradePickaxe(ItemStack item){
        ItemMeta itemMeta = item.getItemMeta();
        NBTItem nbtItem = new NBTItem(item);
        int level = nbtItem.getInteger("level");

        itemMeta.addEnchant(Enchantment.DIG_SPEED, level,true);

        item.setItemMeta(itemMeta);
    }
}
