package com.kingfrozo.pd.events;

import com.kingfrozo.pd.Main;
import com.kingfrozo.pd.sql.MySQL;
import com.kingfrozo.pd.sql.SQLGetter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveSync {

    Main plugin = Main.getPlugin();
    MySQL SQL = Main.getPlugin().SQL;
    SQLGetter data = Main.getPlugin().data;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        data.createPlayer(player);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {

    }

    public static void sync(Player player) {

    }


}
