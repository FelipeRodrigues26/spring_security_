package br.com.security.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * By default, authorization is made through scope claims from the JWT Access Token.
 * In order to authorize users by roles, we override the default configuration by implementing
 * a custom converter.
 */

public class JwtGrantedAuthConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
    	
        Map<String, Object> realmAccess = source.getClaimAsMap("realm_access");
        Map<String, Object> resourcesAccess = (Map<String, Object>) source.getClaimAsMap("resource_access").get("put client here");

        if (Objects.nonNull(realmAccess) || Objects.nonNull(resourcesAccess)) {
            List<String> roles = (List<String>) realmAccess.get("roles");
            roles.addAll( (List<String>) resourcesAccess.get("roles"));
            if (Objects.nonNull(roles)) {
                return roles.stream()
                        .map(rn -> new SimpleGrantedAuthority("ROLE_" + rn))
                        .collect(Collectors.toList());
            }

        }
//        Collection<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
//        list.add(new SimpleGrantedAuthority("ROLE_" + "admin"));
        return List.of();
    }

}
