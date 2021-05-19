package com.kingfrozo.pd.sql;

import com.kingfrozo.pd.Main;
import com.kingfrozo.pd.inv.InventoryOps;
import com.kingfrozo.pd.player.GlobalPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Async {

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

    public static void savePlayer(final Player player, FindOneCallback callback) {
        plugin.players.remove(player.getUniqueId());
        System.out.println("PLAYERS AFTER LEAVE: " + plugin.players.size());
        String strInv = InventoryOps.serialize(player.getInventory());

        Bukkit.getScheduler().runTaskAsynchronously(plugin, ()->{
            plugin.data.setInventory(player, strInv);

            Bukkit.getScheduler().runTask(plugin, () -> {
                // SUCCESSFUL/UNSUCCESSFUL SAVE   EVENT
            });
        });
    }



    public interface FindOneCallback {
        void onQueryDone();
    }
}
