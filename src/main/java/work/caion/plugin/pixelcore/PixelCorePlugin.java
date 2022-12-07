package work.caion.plugin.pixelcore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import work.caion.plugin.pixelcore.server.HttpServerHandler;

import java.util.logging.Logger;

public class PixelCorePlugin extends JavaPlugin {

    final String prefix = "PixelCorePlugin";
    Logger logger;

    private static PixelCorePlugin instance;
    public String token;

    public static PixelCorePlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        logger = getLogger();
        saveDefaultConfig();
        reload();
    }

    @Override
    public void onDisable() {
        logger.info("================" + prefix + "================");
        logger.info(prefix + "正在卸载插件");
        try {
            logger.info("正在关闭PixelHttpServer");
            HttpServerHandler.shutdown();
            logger.info(prefix + "卸载完成，感谢您的使用");
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("================" + prefix + "================");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equals("pcore")) {
            if (sender.hasPermission("pcore.admin")) {
                if (args[0].equals("reload")) {
                    reload();
                }
            }
        }
        return true;
    }

    public void reload() {
        logger.info("================" + prefix + "================");
        logger.info( "正在加载插件");
        reloadConfig();
        int port = getConfig().getInt("http.port");
        this.token = getConfig().getString("token");
        if (HttpServerHandler.isStart()) {
            logger.info("正在关闭PixelHttpServer");
            HttpServerHandler.shutdown();
        }
        HttpServerHandler.create(port, this.token);
        logger.info("正在启动PixelHttpServer");
        try {
            HttpServerHandler.start();
            logger.info(prefix + "启动完成");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(prefix + "启动失败，服务器即将关闭！！！");
            Bukkit.shutdown();
        }
        logger.info("================" + prefix + "================");
    }
}
