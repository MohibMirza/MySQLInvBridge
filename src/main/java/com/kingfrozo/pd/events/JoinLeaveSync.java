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

    // TODO: JOIN/LEAVE DATA SYNC
    // TODO: ENSURE JOIN/LEAVE DATA SYNC WORKS

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        new GlobalPlayer(player.getUniqueId());
        player.sendMessage("PLAYERS: " + plugin.players.size());
        player.sendMessage(plugin.players.get(player.getUniqueId()).toString() + "");
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.players.remove(player.getUniqueId());
        System.out.println(plugin.players.size());
    }

    public static void sync(Player player) {

    }


}
