package com.wings.wingsuserservice.controllers;

import com.wings.wingsuserservice.models.Delivery;
import com.wings.wingsuserservice.models.ERole;
import com.wings.wingsuserservice.models.Role;
import com.wings.wingsuserservice.models.User;
import com.wings.wingsuserservice.payload.request.LoginRequest;
import com.wings.wingsuserservice.payload.request.SignupRequest;
import com.wings.wingsuserservice.payload.response.JwtResponse;
import com.wings.wingsuserservice.payload.response.MessageResponse;
import com.wings.wingsuserservice.repository.DeliveryRepository;
import com.wings.wingsuserservice.repository.RoleRepository;
import com.wings.wingsuserservice.repository.UserRepository;
import com.wings.wingsuserservice.security.jwt.JwtUtils;
import com.wings.wingsuserservice.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  DeliveryRepository deliveryRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Value("${wings.facebook.appId}")
  private String appid;

  @Value("${wings.facebook.appSecret}")
  private String appSecret;

  private FacebookConnectionFactory factory = new FacebookConnectionFactory(appid, appSecret);


  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getFirstname(),
            userDetails.getLastname(),
            userDetails.getEmail(),
            roles));
  }

  @PostMapping("/user-signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getFirstname(),
            signUpRequest.getLastname(),
            signUpRequest.getEmail(),
            signUpRequest.getGovernorate(),
            signUpRequest.getAddress(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getPhone());

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
          case "delivery":
            Role modRole = roleRepository.findByName(ERole.ROLE_DELIVERY)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);

            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

  }

  @PostMapping("/delivery-signup")
  public ResponseEntity<?> registerDelivery(@Valid @RequestBody SignupRequest signUpRequest) throws IOException {

    if (deliveryRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    Delivery delivery = new Delivery(signUpRequest.getFirstname(),
            signUpRequest.getLastname(),
            signUpRequest.getEmail(),
            signUpRequest.getGovernorate(),
            signUpRequest.getAddress(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getPhone(),
            signUpRequest.getCin(),
            signUpRequest.getAccount_holder(),
            signUpRequest.getBank_name(),
            signUpRequest.getAgency_name(),
            signUpRequest.getAgency_city(),
            signUpRequest.getRib());

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
          case "delivery":
            Role modRole = roleRepository.findByName(ERole.ROLE_DELIVERY)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);

            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
      });
    }

    delivery.setRoles(roles);
    userRepository.save(delivery);
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

  }

  @GetMapping("/GetUserId/{email}")
  public Long GetUserId(@PathVariable("email") String email) {
    return userRepository.findByEmail(email).get().getId();
  }




  @PutMapping("/UpdateImage/{id}")
  public Delivery uploadFile(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) throws IOException {

    Optional<Delivery> delivery = deliveryRepository.findById(id);

        Delivery _delivery = delivery.get();
        _delivery.setFilename(StringUtils.cleanPath(file.getOriginalFilename()));
        _delivery.setFiletype(file.getContentType());
        _delivery.setFiledata(file.getBytes());
        return deliveryRepository.save(_delivery);
  }

}



