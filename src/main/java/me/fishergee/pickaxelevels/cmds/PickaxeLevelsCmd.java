package me.fishergee.pickaxelevels.cmds;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PickaxeLevelsCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

        if(!(cmd.getName().equalsIgnoreCase("pickaxelevels"))){
            return true;
        }

        if(args.length == 0){
            sender.sendMessage(ChatColor.GREEN + "Use /pickaxelevels give");
        }

        if(args.length == 1){
            if(sender.isOp()){
                if(args[0].equalsIgnoreCase("give")){
                    ItemStack vanPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
                    ItemMeta itemMeta = vanPickaxe.getItemMeta();

                    itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lSUPER &f&lPICKAXE"));
                    List<String> lore = new ArrayList<>();
                    lore.add(ChatColor.translateAlternateColorCodes('&',"&bTotal blocks mined: &f0"));
                    lore.add(ChatColor.translateAlternateColorCodes('&',"&bBlocks till next level: &f500"));
                    lore.add(ChatColor.translateAlternateColorCodes('&',"&bLevel: &f1"));

                    itemMeta.setLore(lore);
                    itemMeta.setUnbreakable(true);
                    itemMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                    itemMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
                    vanPickaxe.setItemMeta(itemMeta);

                    NBTItem customPickaxe = new NBTItem(vanPickaxe);

                    customPickaxe.setInteger("broken", 0);
                    customPickaxe.setInteger("level", 1);
                    customPickaxe.setString("id", "pickaxelevels");


                    if(!(sender instanceof Player)){
                        sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
                    }
                    else{
                        Player player = (Player) sender;
                        Inventory inventory = player.getInventory();

                        if(inventory.firstEmpty() == - 1){
                            player.sendMessage(ChatColor.RED + "Your inventory is full.");
                        }
                        else{
                            inventory.addItem(customPickaxe.getItem());
                        }
                    }
            }else{
                    sender.sendMessage(ChatColor.RED + "You must be OP to perform this command!");
                }

            }
        }

        return true;
    }
}
