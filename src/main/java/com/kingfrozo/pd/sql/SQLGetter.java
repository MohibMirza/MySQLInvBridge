package com.kingfrozo.pd.sql;

import com.kingfrozo.pd.Main;

public class SQLGetter {

    private Main plugin;
    public SQLGetter(Main plugin) {
        this.plugin = plugin;
        plugin.SQL.getConnection();
    }

}
