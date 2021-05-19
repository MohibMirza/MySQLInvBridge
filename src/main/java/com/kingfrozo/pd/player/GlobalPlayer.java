package com.kingfrozo.pd.player;

import com.kingfrozo.pd.Main;
import com.kingfrozo.pd.events.custom.PlayerSuccessfullyJoinedEvent;
import com.kingfrozo.pd.inv.InventoryOps;
import com.kingfrozo.pd.sql.Async;
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

    private boolean isLoaded;

    public GlobalPlayer(UUID uuid) {
        System.out.println("Generating global player");
        Player player = Bukkit.getPlayer(uuid);

        Async.initPlayer(player, this, () -> {
            // callback
            playerData.syncInventory();
            player.sendMessage("Inventory Synced!!!");
            PlayerSuccessfullyJoinedEvent event = new PlayerSuccessfullyJoinedEvent(player);
            Bukkit.getPluginManager().callEvent(event);
        });

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

    public PlayerData getPlayerData() {
        return playerData;
    }

    public void setPlayerData(PlayerData playerData) {
        this.playerData = playerData;
        isLoaded = true;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    public String toString() {

        return playerData.toString();

    }


}
