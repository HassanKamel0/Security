package com.example.demoSecurity.registration;
import com.example.demoSecurity.appuser.AppUser;
import com.example.demoSecurity.appuser.AppUserRepository;
import com.example.demoSecurity.appuser.AppUserRole;
import com.example.demoSecurity.appuser.AppUserService;
import com.example.demoSecurity.email.EmailSender;
import com.example.demoSecurity.gaming.GamesService;
import com.example.demoSecurity.registration.token.ConfirmationToken;
import com.example.demoSecurity.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class RegistrationService {
    @Autowired private final AppUserService appUserService;
    private final GamesService gamesService;
    private final AppUserRepository appUserRepository;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    public String register(RegistrationRequest request) {
        boolean isValidEmail= emailValidator.test(request.getEmail());
        if (!isValidEmail) throw new IllegalStateException("Email is not valid");
        boolean userExists=appUserRepository.findByEmail(request.getEmail()).isPresent();
        String token;
        if (userExists) {
            AppUser user = appUserRepository.findByEmail(request.getEmail()).get();
            if (user.getEnabled())
                throw new IllegalStateException("Email already taken");
            else
                token = appUserService.activateUser(user);
        }
        else {
            token = appUserService.signUpUser(
                    new AppUser(
                            request.getFirstName(),
                            request.getLastName(),
                            request.getEmail(),
                            request.getPassword(),
                            AppUserRole.USER)
            );
        }
        String link="http://localhost:8080/api/v1/security/confirm?token="+token;
        Map<String,Object> templateModel=new HashMap<>();
        templateModel.put("name",request.getFirstName());
        templateModel.put("link",link);
        emailSender.sendMessageUsingThymeleafTemplate(request.getEmail(),"Confirm your email",templateModel);
        return token;
    }
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token).orElseThrow(() ->
                        new IllegalStateException("Token not found"));
        if (confirmationToken.getConfirmedAt() != null)
            throw new IllegalStateException("Email already confirmed");
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now()))
            throw new IllegalStateException("Token expired");
        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());
        return "Confirmed";
    }
    public String login(LoginRequest request) {
        AppUser appUser=appUserRepository.findByEmail(request.getEmail()).get();
        if (appUserRepository.existsById(appUser.getId())) {
            appUser.setLocked(true);
            gamesService.setAppUserId(appUser.getId());
            return "Login Done";
        }
        else
            throw new IllegalStateException("You have to register first");
    }
}