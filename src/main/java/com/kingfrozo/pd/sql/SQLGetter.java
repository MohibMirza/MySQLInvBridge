package com.kingfrozo.pd.sql;

import com.kingfrozo.pd.Main;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLGetter {

    private Main plugin;
    public SQLGetter(Main plugin) {
        this.plugin = plugin;
        plugin.SQL.getConnection();
    }

    public void createTable() {
        PreparedStatement ps;
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS playerdata"
                + "(NAME VARCHAR(100),UUID VARCHAR(100),POINTS INT(100),PRIMARY KEY (NAME))");
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

    public void createPlayer(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            if(!exists(uuid)){
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT INGNORE INFO playerdata" +
                        " (NAME,UUID) VALUES (?,?)");
                ps2.setString(1, player.getName());
                ps2.executeUpdate();

                return;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(UUID uuid) {
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * from playerdata" +
                    " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            return (results.next()) ? true : false; // player is found
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
