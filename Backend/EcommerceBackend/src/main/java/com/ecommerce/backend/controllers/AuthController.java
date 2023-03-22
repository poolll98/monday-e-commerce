package com.ecommerce.backend.controllers;

import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.backend.models.ERole;
import com.ecommerce.backend.models.Role;
import com.ecommerce.backend.models.User;
import com.ecommerce.backend.payload.request.LoginRequest;
import com.ecommerce.backend.payload.request.SignupRequest;
import com.ecommerce.backend.payload.response.JwtResponse;
import com.ecommerce.backend.payload.response.MessageResponse;
import com.ecommerce.backend.repository.RoleRepository;
import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.security.jwt.JwtUtils;
import com.ecommerce.backend.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    //update last login date
    updateLastLogin(userDetails);

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  private void updateLastLogin(UserDetailsImpl userDetailsImpl) {
    User userFromDb = userRepository.findByUsername(userDetailsImpl.getUsername()).get();
    userFromDb.setLastlogin(new Date());
    userRepository.save(userFromDb);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: This Username is not available! Try with a new one."));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getPhone(), signUpRequest.getFirstname(),
            signUpRequest.getLastname(),
            new Date(),
            new Date(),
            signUpRequest.getIsbuyer(),
            signUpRequest.getIsseller());

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();
    String registrationMessage = "User registered successfully with basic permissions!";
    for (String s : strRoles){
      if (s.equals("admin")){
        registrationMessage = "Impossible to register a user with admin permissions." +
                "User registered with basic permissions!";
      }
    }

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      // Policy: We add by default Basic User
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          // We don't allow to create Admin user (for now)
        case "admin":
          Role forcedUserRole = roleRepository.findByName(ERole.ROLE_USER)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(forcedUserRole);
          /*
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);
          break;
         */
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          // Policy: We add by default Basic User
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse(registrationMessage));
  }
}
