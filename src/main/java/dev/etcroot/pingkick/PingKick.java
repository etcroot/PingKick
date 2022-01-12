package dev.etcroot.pingkick;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;


public final class PingKick extends JavaPlugin implements Listener {

    int maxPing = getConfig().getInt("max-ping");
    String msg = getConfig().getString("kick-msg");
    public String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @EventHandler
    public void CheckPings(PlayerJoinEvent e) {
        for(Player p : getServer().getOnlinePlayers()) {
            int ping = p.getPing();
            if(ping > maxPing) {
                e.getPlayer().kickPlayer(color(msg));
            }
        }
    }

    @Override
    public void onLoad() {
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();
    }
}
