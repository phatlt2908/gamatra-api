package vn.gamatra.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.gamatra.constant.CommonConst;
import vn.gamatra.dto.BaseDto;
import vn.gamatra.form.RoleForm;
import vn.gamatra.form.SignupForm;
import vn.gamatra.model.RoleEntity;
import vn.gamatra.model.UserEntity;
import vn.gamatra.model.UserRoleEntity;
import vn.gamatra.repository.RoleRepository;
import vn.gamatra.repository.UserRepository;
import vn.gamatra.repository.UserRoleRepository;
import vn.gamatra.service.AuthService;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> saveUser(SignupForm form) {
        if (Objects.nonNull(userRepository.findByCode(form.getUserCode()))) {
            BaseDto base = new BaseDto();
            base.setAppCode("TODO"); // TODO
            base.setMessage("User code is already taken!");
            base.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity(base, HttpStatus.BAD_REQUEST);
        }

        UserEntity user;

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        user = modelMapper.map(form, UserEntity.class);
        user.setPassword(passwordEncoder.encode(form.getPassword()));

        UserEntity registeredUser = userRepository.save(user);

        // save user role
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setUserCode(registeredUser.getCode());
        userRole.setRoleCode(CommonConst.CUSTOMER_ROLE_CODE);

        userRoleRepository.save(userRole);

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveRole(RoleForm form) {
        if (Objects.nonNull(roleRepository.findByCode(form.getCode()))) {
            BaseDto base = new BaseDto();
            base.setAppCode("TODO"); // TODO
            base.setMessage("Role code is already taken!");
            base.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity(base, HttpStatus.BAD_REQUEST);
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        RoleEntity role;
        role = modelMapper.map(form, RoleEntity.class);
        roleRepository.save(role);

        return new ResponseEntity(HttpStatus.OK);
    }
}
