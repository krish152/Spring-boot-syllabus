package com.krishna.security2.security;

import com.krishna.security2.model.User;
import com.krishna.security2.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;


    public MyUserDetailService(UserRepo userRepo){
        this.userRepo=userRepo;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByUsername(username);

        if(user ==null){
            throw  new UsernameNotFoundException("User Not Found");
        }
        else {

        return new UserPrincipal(user);
        }
    }
}
