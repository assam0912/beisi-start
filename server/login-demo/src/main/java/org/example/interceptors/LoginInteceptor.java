package org.example.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.stores.TokenStore;
import org.example.utils.JwtUtil;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInteceptor implements HandlerInterceptor {
    @Autowired
    private TokenStore tokenStore;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行 OPTIONS 预检请求
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        // 令牌验证
        String token = request.getHeader("Authorization");
        // 验证token
        try {
            // 从currentHashMap里取出相同的token
            String localToken = tokenStore.getToken(token);
            if (localToken == null) {
                throw new RuntimeException();
            }
            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 把业务数据存储到ThreadLocal中
            ThreadLocalUtil.set(claims);
            // 放行
            return true;
        } catch (RuntimeException e) {
            // http响应状态码401
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清楚ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
