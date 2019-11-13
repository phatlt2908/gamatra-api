package vn.gamatra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.gamatra.model.CustomUserDetails;
import vn.gamatra.model.UserEntity;
import vn.gamatra.repository.UserRepository;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByCode(s);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(s);
        }
        return new CustomUserDetails(user);
    }
}
