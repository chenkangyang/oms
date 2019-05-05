package com.mrky.domain;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// Merchant and Consumer are the same-level, what is differenet is that their grants is about different entities.
public class MerchantDetails implements UserDetails {

    private final Merchant merchant;

    public MerchantDetails(Merchant merchant) {
        this.merchant = merchant;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_M_" + merchant.getMerchantId()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return merchant.getMerchantPassword();
    }

    @Override
    public String getUsername() {
        return merchant.getMerchantName();
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
