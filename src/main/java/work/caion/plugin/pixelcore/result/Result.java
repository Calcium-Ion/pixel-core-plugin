package work.caion.plugin.pixelcore.result;

import cn.hutool.json.JSONObject;

public class Result {

    int code;
    String msg;
    JSONObject data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("code", code);
        jsonObject.set("msg", msg);
        jsonObject.set("data", data);

        return jsonObject;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}
