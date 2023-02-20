package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.User;
import tn.devteam.immonexus.Entities.UserPrincipal;
import tn.devteam.immonexus.Interfaces.IUserService;
import tn.devteam.immonexus.Security.JWTTokenProvider;
import tn.devteam.immonexus.exception.ExceptionHandling;
import tn.devteam.immonexus.exception.domain.EmailExistException;
import tn.devteam.immonexus.exception.domain.UserNotFoundException;
import tn.devteam.immonexus.exception.domain.UsernameExistException;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static tn.devteam.immonexus.constant.SecurityConstant.JWT_TOKEN_HEADER;

@RestController
@RequestMapping(path = { "/", "/user"})
public class UserController extends ExceptionHandling {

    private AuthenticationManager authenticationManager;
    private IUserService iUserService;
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, IUserService iUserService, JWTTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.iUserService = iUserService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticate(user.getUsername(), user.getPassword());
        User loginUser = iUserService.findUserByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser, jwtHeader, OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, UsernameExistException, EmailExistException {
        User newUser = iUserService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
        return new ResponseEntity<>(newUser, OK);
    }

    private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
        return headers;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }



}
