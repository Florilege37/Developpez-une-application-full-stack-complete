package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.entity.Posts;
import com.openclassrooms.mddapi.entity.Topics;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.mappers.PostMapper;
import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.SignupRequest;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;
import com.openclassrooms.mddapi.security.services.UserDetailsImpl;
import com.openclassrooms.mddapi.services.interfaces.PostService;
import com.openclassrooms.mddapi.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.openclassrooms.mddapi.payload.response.JwtResponse;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    private final static String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    AuthController(AuthenticationManager authenticationManager,
                   PasswordEncoder passwordEncoder,
                   JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *  Permet d'inscrire un utilisateur si :
     *   - l'email n'est pas déjà utilisé
     *   - le mot de passe est assez sécurisé
     * @param signUpRequest
     * @return
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existByEmail(signUpRequest.getEmail()) || userService.existByNickname(signUpRequest.getNickname())) {
            throw new BadRequestException(new MessageResponse("Erreur: Email ou nom d'utilisateur déjà utilisé"));
        } else if(!isValid(signUpRequest.getPassword())){
            throw new BadRequestException(new MessageResponse("Erreur: Mot de passe pas assez sécurisé"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getNickname(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        userService.save(user);
    }

    /**
     * Génère un token JWT pour un User si l'email et le mot de passe sont corrects
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public JwtResponse loginUser(@Valid @RequestBody LoginRequest loginRequest) {

        try {
            String requestEmail = loginRequest.getEmailOrNickname();

            // On test si on login avec le couple "Nickname/password". Si on a un ce couple, on récupère l'email.
            if (!requestEmail.contains("@")){
                User user = userService.findByNickname(loginRequest.getEmailOrNickname());
                if (user == null) {
                    throw new BadRequestException(new MessageResponse("Erreur technique"));
                } else {
                    requestEmail = user.getEmail();
                }
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestEmail, loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            return new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getNickname()
            );
        } catch (AuthenticationException e) {
            throw new BadRequestException(new MessageResponse("Erreur technique"));
        }
    }

    /**
     *
     * @param password
     * @return true si le password est assez sécurisé
     */
    public static boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


}
