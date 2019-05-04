package com.mrky.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// User entity abstracts as UserDetails
public class ConsumerDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Consumer consumer;

    public ConsumerDetails(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_C_" + consumer.getConsumerId()));

        return authorities;
    }

    @Override
    public String getPassword() {
        return consumer.getConsumerPassword();
    }

    @Override
    public String getUsername() {
        return consumer.getConsumerName();
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