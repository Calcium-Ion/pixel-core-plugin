import cn.hutool.crypto.SecureUtil;

public class Test {

    public static void main(String[] args) {
        md5();
    }

    public static void md5() {
        String token = "yourtoken";
        System.out.println(SecureUtil.md5(SecureUtil.md5(token) + token));
    }
}
