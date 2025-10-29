package org.example.stores;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenStore {
    // jvm内存存储token
    private final Map<String, String> tokenStore = new ConcurrentHashMap<>();

    public void saveToken(String token) {
        tokenStore.put(token, token);
    }

    public String getToken(String token) {
        return tokenStore.get(token);
    }

    public void removeToken(String token) {
        tokenStore.remove(token);
    }
}
