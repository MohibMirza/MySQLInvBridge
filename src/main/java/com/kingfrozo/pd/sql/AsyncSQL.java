package com.kingfrozo.pd.sql;

import com.kingfrozo.pd.Main;
import com.kingfrozo.pd.player.GlobalPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AsyncSQL {

    static Main plugin = Main.getPlugin();

    public static void initPlayer(final Player player, final GlobalPlayer gPlayer, FindOneCallback callback) {

        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                System.out.println("AsyncSQL.java: 1");
                gPlayer.setPlayerData(plugin.data.getPlayer(player));
                System.out.println("AsyncSQL.java: 2");
                plugin.players.put(player.getUniqueId(), gPlayer);

                Bukkit.getScheduler().runTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        callback.onQueryDone();
                    }
                });



            }
        });

    }

    public interface FindOneCallback {
        void onQueryDone();
    }
}
