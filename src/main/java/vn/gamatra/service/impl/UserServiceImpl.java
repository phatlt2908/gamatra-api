package vn.gamatra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.gamatra.model.CustomUserDetails;
import vn.gamatra.model.UserEntity;
import vn.gamatra.model.UserRoleEntity;
import vn.gamatra.repository.UserRepository;
import vn.gamatra.repository.UserRoleRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findByCode(s);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(s);
        }

        List<UserRoleEntity> userRoles = this.userRoleRepository.findByUserCode(s);
        List<GrantedAuthority> authorities = userRoles.stream().map(role ->
                new SimpleGrantedAuthority(role.getRoleCode())
        ).collect(Collectors.toList());

        return new CustomUserDetails(user, authorities);
    }
}
