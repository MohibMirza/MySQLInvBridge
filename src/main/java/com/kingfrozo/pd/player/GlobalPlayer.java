package com.kingfrozo.pd.player;

import com.kingfrozo.pd.Main;
import com.kingfrozo.pd.sql.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GlobalPlayer {

    public static Main plugin = Main.getPlugin();

    private PlayerData playerData;

    public GlobalPlayer(UUID uuid) {

        Player player = Bukkit.getPlayer(uuid);
        playerData = plugin.data.getPlayer(player);

        System.out.println("PLAYERS x: " + plugin.players.size());
        plugin.players.put(uuid, this); // Automatically adds the reference upon instantiation
        System.out.println("PLAYERS x: " + plugin.players.size());
    }

    public String toString() {

        return playerData.toString();

    }


}
