package work.caion.plugin.pixelcore.server;

import cn.hutool.http.server.action.Action;
import org.bukkit.Bukkit;
import work.caion.plugin.pixelcore.PixelCorePlugin;


public abstract class ServerAction {

    public String path;
    public String token;

    public ServerAction(String path) {
        this.token = PixelCorePlugin.getInstance().token;
        this.path = path;
    }

    public abstract Action getAction();

}
