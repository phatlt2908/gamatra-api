package vn.gamatra.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.gamatra.form.SignupForm;

@Service
public interface AuthService {
    ResponseEntity<?> registUser(SignupForm form);
}
