package com.kingfrozo.pd.events;

import com.kingfrozo.pd.Main;
import com.kingfrozo.pd.player.GlobalPlayer;
import com.kingfrozo.pd.sql.MySQL;
import com.kingfrozo.pd.sql.SQLGetter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveSync implements Listener {

    Main plugin = Main.getPlugin();
    MySQL SQL = Main.getPlugin().SQL;
    SQLGetter data = Main.getPlugin().data;

    // TODO: MAKE SURE JOIN/LEAVE DATA SYNC IS ASYNC

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        new GlobalPlayer(player.getUniqueId());
        player.sendMessage("PLAYERS onJoin: " + plugin.players.size());
        player.sendMessage(plugin.players.get(player.getUniqueId()).toString() + "");
        System.out.println("PLAYERS onJoin: " + plugin.players.size());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        System.out.println(plugin.players.get(player.getUniqueId()).toString());
        plugin.players.remove(player.getUniqueId());
        System.out.println(plugin.players.size());
    }

    public static void sync(Player player) {

    }


}
