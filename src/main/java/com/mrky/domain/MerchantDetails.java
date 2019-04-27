package com.mrky.domain;

import java.util.Collection;
import java.util.Arrays;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// Merchant and Consumer are the same-level, what is differenet is that their grants is about different entities.
public class MerchantDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private final Merchant merchant;
    private final SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ONLY");

    public MerchantDetails(Merchant merchant) {
        this.merchant = merchant;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + merchant.getId()), authority);
    }

    @Override
    public String getPassword() {
        return merchant.getPassword();
    }

    @Override
    public String getUsername() {
        return String.valueOf(merchant.getId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
