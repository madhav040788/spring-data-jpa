package com.cwjt.common;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    //if i am usig security then
    //  SecurityContextHolder
    // get the principal
    // get the user
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Madhav");
    }
}
