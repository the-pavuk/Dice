package ru.the_pavuk.dice;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventsListener implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent e){
        if(e.getHand() == EquipmentSlot.OFF_HAND) return;
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
            ItemMeta itemMeta = item.getItemMeta();
            if(item.getType().equals(Material.QUARTZ) && itemMeta.getDisplayName().equals("Кубик")){
                List<Entity> nearbyEntities = e.getPlayer().getNearbyEntities(5,5,5);
                if(nearbyEntities == null) {
                    nearbyEntities = new ArrayList<Entity>();
                }
                nearbyEntities.add(e.getPlayer());
                if(item.getAmount() > 1){
                    String results = multiplyDice();
                    for(Entity entity:nearbyEntities){
                        if(entity instanceof Player){
                            Player p = (Player) entity;
                            p.sendMessage(e.getPlayer().getName() + " бросил кубики: " + results);
                        }
                    }
                } if(item.getAmount() == 1){
                    String result = dice();
                    for(Entity entity:nearbyEntities){
                        if(entity instanceof Player){
                            Player p = (Player) entity;
                            p.sendMessage(e.getPlayer().getName() + " бросил кубик: " + result);
                        }
                    }

                }
                e.getPlayer().setCooldown(e.getMaterial(),300);
            }
        }
    }
    public String dice(){
        Random random = new Random();
        int randInt = random.nextInt(6)+1;
        switch (randInt){
            case 1: return "\uEC81";
            case 2: return "\uEC82";
            case 3: return "\uEC83";
            case 4: return "\uEC84";
            case 5: return "\uEC85";
            case 6: return "\uEC86";
        }
        return "what?";
    }
    public String multiplyDice(){
        String results = "";
        for(int i =1; i<= 10; i++){
            results = results + dice();
        }
        return results;
    }
}

