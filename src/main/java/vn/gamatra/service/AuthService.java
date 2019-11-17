package vn.gamatra.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.gamatra.form.RoleForm;
import vn.gamatra.form.SignupForm;

@Service
public interface AuthService {
    ResponseEntity<?> saveUser(SignupForm form);

    ResponseEntity<?> saveRole(RoleForm form);
}
