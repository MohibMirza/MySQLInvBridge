package com.kingfrozo.pd.events;

import com.kingfrozo.pd.Main;
import com.kingfrozo.pd.events.custom.PlayerSuccessfullyJoinedEvent;
import com.kingfrozo.pd.inv.InventoryOps;
import com.kingfrozo.pd.libs.LP;
import com.kingfrozo.pd.player.GlobalPlayer;
import com.kingfrozo.pd.sql.MySQL;
import com.kingfrozo.pd.sql.SQLGetter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

public class JoinLeaveSync implements Listener {

    Main plugin = Main.getPlugin();
    MySQL SQL = Main.getPlugin().SQL;
    SQLGetter data = Main.getPlugin().data;

    // TODO: MAKE SURE JOIN/LEAVE DATA SYNC IS ASYNC (DONE!)
    // TODO:

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        new GlobalPlayer(player.getUniqueId());

        System.out.println(LP.getPrefix(event.getPlayer()));

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        GlobalPlayer gPlayer = plugin.players.get(player.getUniqueId());
        System.out.println("GPLAYER:  " + (gPlayer == null) + " ");
        gPlayer.leave();
        plugin.players.remove(player.getUniqueId());
        System.out.println(plugin.players.size());
    }

    public static void syncInventory(Player player, Inventory inv) { }

    public static void syncInventory(Player player) {
        Inventory inv = player.getInventory();

        String invStr = InventoryOps.serialize(inv);

    }


}
