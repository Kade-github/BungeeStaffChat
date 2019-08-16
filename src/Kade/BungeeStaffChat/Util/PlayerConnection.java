package Kade.BungeeStaffChat.Util;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public class PlayerConnection {
    Plugin plugin;

    public PlayerConnection(Plugin pl) { plugin = pl; }

    public boolean isPlayer(Connection c)
    {
        for (ServerInfo srv : plugin.getProxy().getServers().values())
        {
            for (ProxiedPlayer p : srv.getPlayers())
            {
                if (p.getAddress() == c.getAddress())
                    return true;
            }
        }
        return false;
    }

    public ProxiedPlayer getPlayer(Connection c)
    {
        for (ServerInfo srv : plugin.getProxy().getServers().values())
        {
            for (ProxiedPlayer p : srv.getPlayers())
            {
                if (p.getAddress() == c.getAddress())
                    return p;
            }
        }
        return null;
    }

}
