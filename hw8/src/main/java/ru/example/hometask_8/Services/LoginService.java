package ru.example.hometask_8.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import ru.example.hometask_8.Annotations.Log;
import ru.example.hometask_8.Annotations.Performance;
import ru.example.hometask_8.Entitys.User;

@Service
@EnableWebSecurity
public class LoginService {
    private String currentUser = "unauthorised";

    public String getCurrentUser() {
        return currentUser;
    }

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginService(InMemoryUserDetailsManager inMemoryUserDetailsManager, AuthenticationManager authenticationManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
        this.authenticationManager = authenticationManager;
    }

    @Log
    @Performance
    public Authentication login(User user)
    {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
    }
}