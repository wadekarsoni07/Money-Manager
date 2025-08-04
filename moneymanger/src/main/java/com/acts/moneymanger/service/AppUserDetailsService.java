package com.acts.moneymanger.service;

import com.acts.moneymanger.entity.ProfileEntity;
import com.acts.moneymanger.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AppUserDetailsServie implements UserDetailsService {

    private final ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          ProfileEntity existingProfile=  profileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
          return User.builder()
                  .username(existingProfile.getEmail())
                  .password(existingProfile.getPassword())
                  .authorities(Collections.emptyList())
                  .build();
    }
}
