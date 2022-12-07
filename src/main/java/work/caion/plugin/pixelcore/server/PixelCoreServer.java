package work.caion.plugin.pixelcore.server;

import cn.hutool.http.server.SimpleServer;

public class PixelCoreServer {

    private SimpleServer httpServer;

    protected PixelCoreServer(SimpleServer simpleServer) {
        httpServer = simpleServer;
    }

    public void start() {
        httpServer.start();
    }

    public void addAction(ServerAction action) {
        httpServer.addAction(action.path, action.getAction());
    }

    public void stop() {
        httpServer.getRawServer().stop(10);
    }
}
