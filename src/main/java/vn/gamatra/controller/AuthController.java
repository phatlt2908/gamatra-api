package vn.gamatra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.gamatra.config.JwtTokenProvider;
import vn.gamatra.constant.CommonConst;
import vn.gamatra.dto.LoginDto;
import vn.gamatra.form.LoginForm;
import vn.gamatra.model.CustomUserDetails;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = CommonConst.BASE_API_URL + "/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public LoginDto authenticateUser(@Valid @RequestBody LoginForm form) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        form.getUserCodeOrPhoneOrEmail(),
                        form.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginDto(jwt);
    }

    @GetMapping(value = "/test", produces = CommonConst.JSON_UTF8)
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Teng teng teng");
    }
}