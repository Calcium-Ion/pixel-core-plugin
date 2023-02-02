package work.caion.plugin.pixelcore.action;

import cn.hutool.json.JSONObject;
import work.caion.plugin.pixelcore.httpclient.PixelCoreClient;
import work.caion.plugin.pixelcore.result.Result;

import java.util.ArrayList;

public class ActionHandler {

    ArrayList<CoreAction> coreActions = new ArrayList<>();

    public void registerAction(CoreAction coreAction) {
        coreActions.add(coreAction);
    }

    public void handle(String type, Result data) {
        for (CoreAction coreAction : coreActions) {
            if (coreAction.type.equals(type)) {
                Result result = coreAction.execute(data);
                PixelCoreClient.post(result);
            }
        }
    }
}
