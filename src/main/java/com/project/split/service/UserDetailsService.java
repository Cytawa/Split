package com.project.split.service;

import com.project.split.repository.UserRepo;
import lombok.RequiredArgsConstructor;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public
class UserDetailsService // implements
                         // org.springframework.security.core.userdetails.UserDetailsService
 {
    private final UserRepo userRepo;

  // @Override
  //   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  //      return userRepo.findByUsername(username);
  //  }
}
