package ru.nellyd3v.mentioner;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public void onEnable() {
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new Events(this), this);
        System.out.println("Hello from Mentioner!!!");
    }
}