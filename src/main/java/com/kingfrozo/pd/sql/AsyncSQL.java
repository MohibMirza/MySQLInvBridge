package com.kingfrozo.pd.sql;

import com.kingfrozo.pd.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AsyncSQL {

    public static void retrievePlayer(Player player) {

        Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {

            }
        });

    }

    public interface FindOneCallback {
        void onQueryDone();
    }
}
