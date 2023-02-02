package work.caion.plugin.pixelcore.action;

import work.caion.plugin.pixelcore.result.Result;

public abstract class CoreAction {

    String type;

    public CoreAction(String type) {
        this.type = type;
    }

    public abstract Result execute(Result data);
}
