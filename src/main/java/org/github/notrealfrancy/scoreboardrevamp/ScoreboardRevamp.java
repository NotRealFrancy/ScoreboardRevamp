package org.github.notrealfrancy.scoreboardrevamp;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.github.notrealfrancy.scoreboardrevamp.manager.ScoreboardManager;

public class ScoreboardRevamp extends JavaPlugin {

    @Getter private static ScoreboardRevamp instance;

    @Getter private ScoreboardManager scoreboardManager;

    @Override
    public void onEnable() {
        ScoreboardRevamp.instance = this;

        this.scoreboardManager = new ScoreboardManager();

        Bukkit.getConsoleSender().sendMessage(ScoreboardRevamp.getPrefix() + "Plugin abilitato!");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ScoreboardRevamp.getPrefix() + "Plugin disabilitato!");
    }

    public static String getPrefix() {
        return ChatColor.DARK_RED + "[ScoreboardRevamp] " + ChatColor.DARK_GRAY;
    }
    
}
