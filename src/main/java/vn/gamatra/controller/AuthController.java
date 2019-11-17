package vn.gamatra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.gamatra.config.JwtTokenProvider;
import vn.gamatra.constant.APIConst;
import vn.gamatra.constant.CommonConst;
import vn.gamatra.dto.BaseDto;
import vn.gamatra.dto.LoginDto;
import vn.gamatra.form.LoginForm;
import vn.gamatra.form.SignupForm;
import vn.gamatra.model.CustomUserDetails;
import vn.gamatra.service.AuthService;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = CommonConst.BASE_API_URL + APIConst.BASE_AUTH)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthService authService;

    @PostMapping(APIConst.LOGIN)
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

    @PostMapping(APIConst.SIGNUP)
    public ResponseEntity<?> registUser(@Valid @RequestBody SignupForm form) {
        return authService.registUser(form);
    }
}