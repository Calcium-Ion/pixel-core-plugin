package work.caion.plugin.pixelcore.httpclient;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;

public class PixelCoreClient {

    private static HttpRequest post = HttpUtil.createPost("http://localhost:8090/api/core/connect");


    public static void create(String sid, String token) {
        post.setConnectionTimeout(5000);
        post.setReadTimeout(35000);
        JSONObject serverData = new JSONObject();
        serverData.set("serverID", sid);
        serverData.set("token", token);
        post.body(serverData.toString());
    }

    public static void start() throws InterruptedException {
        while (true) {
            try {
                HttpResponse httpResponse = post.execute();
                System.out.println(httpResponse.body());
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
    }
}
