package com.javarush.jira.bugtracking.jwt;

import com.javarush.jira.login.AuthUser;
import com.javarush.jira.login.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import  com.javarush.jira.login.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        /*var user = User.builder()
                .username(request.getEmail())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();*/
        User user=new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<Role> role=new HashSet<>();
        role.add(Role.DEV);
        user.setRoles(role);
        userService.create(user);
        AuthUser authUser=new AuthUser(user);
        String jwtAccess = jwtService.generateToken(authUser,10 * 60 * 24 * 60, "access");
        String jwtRefreshToken=jwtService.generateToken(authUser,1000 * 60 * 24, "refresh");
        System.out.println("JWT sign UP = ");////+jwtAccess+" refresh = "+jwtRefreshToken);
        return new JwtAuthenticationResponse(jwtAccess,jwtRefreshToken);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signIn(SignUpRequest request) {
//        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            ));
//        }catch(DisabledException e){
//
//        }catch(BadCredentialsException e){
//
//        }
        System.out.println("Authentication has been completed username!");
        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getEmail());

        ////var jwt = jwtService.generateToken(user,);
        String jwtAccess = jwtService.generateToken(user,10 * 60 * 24 * 60,"access");
        String jwtRefreshToken=jwtService.generateToken(user,1000 * 60 * 24 , "refresh" );
        System.out.println(user.getUsername()+"username, password"+user.getPassword()+"! JWTAccess = "+jwtAccess);
        return new JwtAuthenticationResponse(jwtAccess, jwtRefreshToken);
    }
}

