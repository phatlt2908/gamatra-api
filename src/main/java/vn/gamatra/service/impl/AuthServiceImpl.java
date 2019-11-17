package vn.gamatra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.gamatra.dto.BaseDto;
import vn.gamatra.form.SignupForm;
import vn.gamatra.repository.UserRepository;
import vn.gamatra.service.AuthService;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> registUser(SignupForm form) {

        if (Objects.nonNull(userRepository.findByCode(form.getUserCode()))) {
            BaseDto base = new BaseDto();
            base.setAppCode("TODO"); // TODO
            base.setMessage("User code is already taken!");
            base.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity(base, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
