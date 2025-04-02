package com.myUber.myUber.service;

import com.myUber.myUber.Entities.User;
import com.myUber.myUber.Repositories.UserRepo;
import com.myUber.myUber.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService implements UserDetailsService {
  private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username).orElse(null);

    }
    public User getUserById(Long id){
return userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found with id "+id));
    }
}
