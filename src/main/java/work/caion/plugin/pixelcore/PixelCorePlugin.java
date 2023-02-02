package work.caion.plugin.pixelcore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import work.caion.plugin.pixelcore.action.ActionHandler;
import work.caion.plugin.pixelcore.httpclient.PixelCoreClient;
import work.caion.plugin.pixelcore.httpserver.HttpServerHandler;

import java.util.logging.Logger;

public class PixelCorePlugin extends JavaPlugin {

    final String prefix = "PixelCorePlugin";
    Logger logger;

    private static PixelCorePlugin instance;
    private static ActionHandler actionHandler;
    public String token;
    public String sid;

    public static PixelCorePlugin getInstance() {
        return instance;
    }

    public static ActionHandler getActionHandler() {
        return actionHandler;
    }

    @Override
    public void onEnable() {
        instance = this;
        logger = getLogger();
        actionHandler = new ActionHandler();
        saveDefaultConfig();
        reload();
    }

    @Override
    public void onDisable() {
        logger.info("================" + prefix + "================");
        logger.info(prefix + "正在卸载插件");
        try {
            PixelCoreClient.stop();
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
//        int port = getConfig().getInt("http.port");
        this.token = getConfig().getString("token");
        this.sid = getConfig().getString("sid");
//        if (HttpServerHandler.isStart()) {
//            logger.info("正在关闭PixelHttpServer");
//            HttpServerHandler.shutdown();
//        }
//        HttpServerHandler.create(port, this.token);
//        logger.info("正在启动PixelHttpServer");
//        try {
//            HttpServerHandler.start();
//            logger.info(prefix + "PixelHttpServer启动完成");
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.info(prefix + "PixelHttpServer启动失败，某些功能将不可用");
//        }

        logger.info(prefix + "正在连接PixelWeb服务");
        PixelCoreClient.create(this.sid, this.token);
        try {
            PixelCoreClient.start();
            logger.info(prefix + "连接PixelWeb服务成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(prefix + "连接PixelWeb服务失败，请检查您的sid和token是否正确！！！");
        }
        logger.info("================" + prefix + "================");
    }
}
