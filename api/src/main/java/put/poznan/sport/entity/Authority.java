package put.poznan.sport.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}


