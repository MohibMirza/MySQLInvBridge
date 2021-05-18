package com.kingfrozo.pd.player;

import com.kingfrozo.pd.Main;
import com.kingfrozo.pd.inv.InventoryOps;
import com.kingfrozo.pd.sql.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GlobalPlayer {

    // TODO: LUCKPERMS SET PREFIX/ICON UNLESS BUNGEECHAT WILL BE USED (LOOK INTO BUNGEECHAT)
    // TODO: ADD INVENTORY TO MYSQL DB
    // TODO:

    public static Main plugin = Main.getPlugin();

    private PlayerData playerData;

    public GlobalPlayer(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        playerData = plugin.data.getPlayer(player); // replace this with async getPlayer

        plugin.players.put(uuid, this); // Automatically adds the reference upon instantiation
        System.out.println("Global Player created: " + player.getName());
        System.out.println("    PlayerData Async warning: " + (playerData == null));
    }

    public void join() {
        Player player = playerData.getPlayer();
        player.sendMessage("PLAYERS onJoin: " + plugin.players.size());
        player.sendMessage(plugin.players.get(player.getUniqueId()).toString() + "");
        System.out.println("PLAYERS onJoin: " + plugin.players.size());


    }

    public void leave() {
        // System.out.println(playerData.toString());
        Player player = playerData.getPlayer();
        String strInv = InventoryOps.serialize(player.getInventory());
        System.out.println(strInv);
        plugin.data.setInventory(player, strInv);

    }

    public String toString() {

        return playerData.toString();

    }


}
