package work.caion.plugin.pixelcore.server;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import work.caion.plugin.pixelcore.result.ResultUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TokenFilter extends Filter {
    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {

    }

    @Override
    public String description() {
        return null;
    }
}
