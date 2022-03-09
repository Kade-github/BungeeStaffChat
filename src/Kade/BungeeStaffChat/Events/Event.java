package Kade.BungeeStaffChat.Events;

import Kade.BungeeStaffChat.Util.PlayerConnection;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

import java.io.File;

public class Event implements Listener {
    PlayerConnection util;
    Configuration config;
    // For some reason bungee changed the way connection works, and you cant get the ProxiedPlayer from a connection anymore.
    // So this is a work around that I made.

    public Event(PlayerConnection con, Configuration cfg) { util = con; config = cfg; }

    @EventHandler
    public void onChat(ChatEvent event)
    {
        Connection pConnection = event.getSender();
        if (event.isCommand() || !util.isPlayer(pConnection))
            return;
        ProxiedPlayer p = util.getPlayer(pConnection);
        if (!event.getMessage().startsWith(config.getString("toggleCharacter")) || !p.hasPermission("bungeestaffchat.chat"))
            return;
        
        for (ProxiedPlayer pl : p.getServer().getInfo().getPlayers())
        {
            if (!pl.hasPermission("bungeestaffchat.chat"))
                continue;
            String server = "";
            if (config.getBoolean("displayServer"))
                server = config.getString("serverPrefix").replace("%server%", p.getServer().getInfo().getName());
            pl.sendMessage("" + ChatColor.translateAlternateColorCodes('&', config.getString("chatPrefix")) + server + p.getDisplayName() + " > " + event.getMessage());
        }
        event.setCancelled(true);
    }
}
