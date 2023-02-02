package work.caion.plugin.pixelcore.httpclient;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import work.caion.plugin.pixelcore.PixelCorePlugin;
import work.caion.plugin.pixelcore.result.Result;

import java.util.ArrayList;
import java.util.logging.Level;

public class PixelCoreClient {

    private static HttpRequest post = HttpUtil.createPost("http://localhost:8090/api/core/connect");

    private static JSONObject postData = new JSONObject();

    private static Thread thread = null;

    private static ArrayList<Result> needToPost = new ArrayList<>();

    public static void create(String sid, String token) {
        post.setConnectionTimeout(5000);
        post.setReadTimeout(35000);
        postData.set("serverID", sid);
        postData.set("token", token);
        post.body(postData.toString());
    }

    public static synchronized void post(Result result) {
        needToPost.add(result);
    }

    public static synchronized void stop() {
        thread.stop();
    }

    public static void start() {
        thread = new Thread(() -> {
            while (true) {
                try {
                    if (needToPost.size() > 0) {
                        postData.set("data", needToPost);
                        post.body(postData.toString());
                        needToPost.clear();
                    } else {
                        postData.set("data", null);
                        post.body(postData.toString());
                    }
                    HttpResponse httpResponse = post.execute();
                    Result result = JSONUtil.toBean(httpResponse.body(), Result.class);
                    if (result.getCode() == 200) {
                        // 轮询完成，开始下一次轮询
                        PixelCorePlugin.getInstance().getLogger()
                                .log(Level.FINE, "连接PixelWeb服务失败，" + result.getMsg());
                    }
                    else {
                        PixelCorePlugin.getInstance().getLogger().log(Level.INFO, result.getMsg());
                        if (result.getCode() == 702) {
                            // 玩家请求网页登录
                            PixelCorePlugin.getActionHandler().handle("login", result);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ignored) {}
                }
            }
        });
    }
}
