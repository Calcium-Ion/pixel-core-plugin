package work.caion.plugin.pixelcore.httpserver;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.http.server.SimpleServer;
import cn.hutool.http.server.action.Action;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import work.caion.plugin.pixelcore.result.ResultUtil;

import java.io.IOException;

public class HttpServerHandler {

    private static PixelCoreServer pixelCoreServer;

    public static void addAction(ServerAction serverAction) {
        pixelCoreServer.addAction(serverAction);
    }

    public static void shutdown() {
        if (pixelCoreServer != null) {
            pixelCoreServer.stop();
        }
    }

    public static boolean isStart() {
        return pixelCoreServer != null;
    }

    public static void create(int port, String token) {
        SimpleServer httpServer = HttpUtil.createServer(port);
        httpServer.addFilter(new Filter() {
            @Override
            public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
                String pToken = exchange.getRequestHeaders().getFirst("p-token");
                exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
                if (!StrUtil.equals(pToken, SecureUtil.md5(SecureUtil.md5(token) + token))) {
                    HttpServerResponse response = new HttpServerResponse(exchange);
                    response.write(ResultUtil.from(900, "权限错误").toString());
                } else {
                    chain.doFilter(exchange);
                }
            }

            @Override
            public String description() {
                return "";
            }
        });

        httpServer.addAction("/", new Action() {
            @Override
            public void doAction(HttpServerRequest httpServerRequest, HttpServerResponse httpServerResponse) throws IOException {
                httpServerResponse.write(ResultUtil.ok("running").toString());
            }
        });
        pixelCoreServer = new PixelCoreServer(httpServer);
    }

    public static void start() {
        pixelCoreServer.start();
    }
}
