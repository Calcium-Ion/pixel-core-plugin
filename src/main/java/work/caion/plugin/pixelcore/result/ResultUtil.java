package work.caion.plugin.pixelcore.result;

public class ResultUtil {

    public static Result success(String msg) {
        return new Result(200, msg);
    }

    public static Result error(String msg) {
        return new Result(901, msg);
    }

    public static Result from(int code, String msg) {
        return new Result(code, msg);
    }
}
