package com.avanes.vkTask.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public enum GetAuthUserLogin {
    INSTANCE;

    public Map<String, String> getAuthUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Set<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());
        HashMap<String, String> result = new HashMap<>();
        StringBuilder totalRoles = new StringBuilder();
        for (String role : roles) {
            totalRoles.append(role).append(" ");
        }
        result.put(currentPrincipalName, totalRoles.toString().trim());
        return result;
    }
}
