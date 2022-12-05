package com.example.demoSecurity.registration;

import com.example.demoSecurity.appuser.AppUserRepository;
import com.example.demoSecurity.gaming.GamesController;
import com.example.demoSecurity.gaming.GamesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/security")
@AllArgsConstructor
public class RegistrationController {
    @Autowired
    private final RegistrationService registrationService;
    @PostMapping(path = "/register")
    public String register(@RequestBody RegistrationRequest registrationRequest){
        return registrationService.register(registrationRequest);
    }
    @PostMapping(path = "/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return registrationService.login(loginRequest);
    }
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}
