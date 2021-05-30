package com.kingfrozo.pd.libs;

import com.kingfrozo.pd.Main;
import com.kingfrozo.pd.player.Title;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PrefixNode;
import org.bukkit.entity.Player;

public class LP {

    public static String getPrefix(Player player) {
        User user = Main.lp_api.getPlayerAdapter(Player.class).getUser(player);
        String prefix = user.getCachedData().getMetaData().getPrefix();

        return stripPrefix(prefix);
    }

    public static void setPrefix(Player player, String prefix) {
        User user = Main.lp_api.getPlayerAdapter(Player.class).getUser(player);
        user.data().clear(NodeType.PREFIX.predicate(mn -> mn.getPriority() == 2));
        PrefixNode newPrefix = PrefixNode.builder(Title.titles.get(prefix).getTitle("#22aaff"),2).build();
        user.data().add(newPrefix);
        Main.lp_api.getUserManager().saveUser(user);
    }

    public static String stripPrefix(String prefix) {
        int strlen = prefix.length();

        while(true) {
            if(!prefix.contains("§")) break;

            int i = prefix.indexOf("§");
            System.out.println("i: " + i);
            String replace = "§";
            if( (i+1) < strlen) {
                replace += prefix.charAt(i+1);
            }
            prefix = prefix.replace(replace, "");
        }
        return prefix;
    }

    public static String getColor(String prefix) {

        return null;
    }


}
