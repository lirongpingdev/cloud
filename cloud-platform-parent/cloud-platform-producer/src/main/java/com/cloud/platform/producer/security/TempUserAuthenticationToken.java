package com.cloud.platform.producer.security;


import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class TempUserAuthenticationToken extends AbstractAuthenticationToken {
    private final UserContext principal;
    private final Object credential;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public TempUserAuthenticationToken(UserContext principal,Object credential, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credential = credential;
    }

    @Override
    public Object getCredentials() {
        return this.credential;

    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
