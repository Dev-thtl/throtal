package com.throtl.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.throtl.user.entity.RefreshToken;
import com.throtl.user.entity.UserProfile;
import com.throtl.user.entity.UserTokenDetails;
import com.throtl.user.models.ERole;
import com.throtl.user.models.Role;
import com.throtl.user.models.User;
import com.throtl.user.payload.request.LoginRequest;
import com.throtl.user.payload.request.SignupRequest;
import com.throtl.user.payload.response.JwtResponse;
import com.throtl.user.payload.response.MessageResponse;
import com.throtl.user.repository.RoleRepository;
import com.throtl.user.repository.UserProfileRepository;
import com.throtl.user.repository.UserRepository;
import com.throtl.user.repository.UserTokenDetailsRepository;
import com.throtl.user.security.jwt.JwtUtils;
import com.throtl.user.security.services.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
  ObjectMapper objectMapper = new ObjectMapper();
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

  @Autowired
  UserTokenDetailsRepository userTokenDetailsRepository;

  @Autowired
  UserProfileRepository userProfileRepository;
  @Value("${bezkoder.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  @PostMapping("/verify")
  public ResponseEntity<?> authenticateUser(/*@Valid*/ @RequestBody LoginRequest loginRequest ,
                                                       @RequestHeader("Token_Type") String tokenType,
                                                       HttpServletRequest request) {


try{
    logger.info("Verify USer Details : {}", objectMapper.writeValueAsString(loginRequest));
    if(tokenType.equalsIgnoreCase("credential")){

      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
      logger.info("Verify USer Details : {}", loginRequest.getUsername());

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      return ResponseEntity.ok(JwtResponse.builder()
              .accessToken(jwt)
              .accessTokenExpiry(Instant.now().plusMillis(600000))
              .build());

    }
    if(tokenType.equalsIgnoreCase("access_token")){

      String headerAuth = request.getHeader("Authorization");

      if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
        headerAuth =  headerAuth.substring(7);

      }
      UserTokenDetails userTokenDetails = userTokenDetailsRepository.findByAccessToken(headerAuth);

      UserProfile userProfile=userProfileRepository.getUserProfileByPhoneNumber(userTokenDetails.getMobileNumber());


      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(userTokenDetails.getMobileNumber(), userTokenDetails.getOtp()));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      RefreshToken refreshToken = jwtUtils.createRefreshToken();
      return ResponseEntity.ok(JwtResponse.builder()
              .accessToken(jwt)
              .accessTokenExpiry(new Date().toInstant())
              .token(refreshToken.getToken())
              .refreshTokenExpiry(refreshToken.getExpiryDate())
              .userProfile(userProfile)
              .build());

    }

    if(tokenType.equalsIgnoreCase("refresh_token")){

      String headerAuth = request.getHeader("Authorization");

      if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
        headerAuth =  headerAuth.substring(7);

      }
      UserTokenDetails userTokenDetails = userTokenDetailsRepository.findByAccessToken(headerAuth);

      UserProfile userProfile=userProfileRepository.getUserProfileByPhoneNumber(userTokenDetails.getMobileNumber());


      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(userTokenDetails.getMobileNumber(), userTokenDetails.getOtp()));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      RefreshToken refreshToken = jwtUtils.createRefreshToken();

      userTokenDetails.setAccessToken(jwt);
      userTokenDetails.setAccessTokenExpiry(new Date().toInstant().toString());
      userTokenDetails.setRefreshToken(refreshToken.getToken());
      return ResponseEntity.ok(JwtResponse.builder()
              .accessToken(jwt)
              .accessTokenExpiry(new Date().toInstant())
              .token(refreshToken.getToken())
              .refreshTokenExpiry(refreshToken.getExpiryDate())
              .userProfile(userProfile)
              .build());

    }
    }catch (Exception e){

    }

    return null;



//    Authentication authentication = authenticationManager.authenticate(
//        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//    SecurityContextHolder.getContext().setAuthentication(authentication);
//    String jwt = jwtUtils.generateJwtToken(authentication);
//
//    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//    RefreshToken refreshToken = jwtUtils.createRefreshToken();
//    return ResponseEntity.ok(JwtResponse.builder()
//            .accessToken(jwt)
//            .accessTokenExpiry(new Date().toInstant())
//            .token(refreshToken.getToken())
//            .refreshTokenExpiry(refreshToken.getExpiryDate())
//            .build());
  }

//  @PostMapping("/refreshToken")
//  public JwtResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
//    return jwtUtils.findByToken(refreshTokenRequestDTO.getToken())
//            .map(jwtUtils::verifyExpiration)
//            .map(RefreshToken::getId)
//            .map(userInfo -> {
//
//              Authentication authentication = authenticationManager.authenticate(
//                      new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword()));
//
//              SecurityContextHolder.getContext().setAuthentication(authentication);
//              String jwt = jwtUtils.generateJwtToken(authentication);
//              RefreshToken refreshToken = jwtUtils.createRefreshToken();
//              return ResponseEntity.ok(JwtResponse.builder()
//                      .accessToken(jwt)
//                      .accessTokenExpiry(new Date().toInstant())
//                      .token(refreshToken.getToken())
//                      .refreshTokenExpiry(refreshToken.getExpiryDate())
//                      .build());
//            }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
//  }

  @PostMapping("/refresh")
  public ResponseEntity<?> refreshToken(/*@Valid*/ /*@RequestBody LoginRequest loginRequest ,*/
                                                       @RequestHeader("Token_Type") String tokenType,
                                                       HttpServletRequest request) {



    if(tokenType.equalsIgnoreCase("access_token")){

      String headerAuth = request.getHeader("Authorization");

      if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
        headerAuth =  headerAuth.substring(7);

      }
      UserTokenDetails userTokenDetails = userTokenDetailsRepository.findByAccessToken(headerAuth);

      UserProfile userProfile=userProfileRepository.getUserProfileByPhoneNumber(userTokenDetails.getMobileNumber());


      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(userTokenDetails.getMobileNumber(), userTokenDetails.getOtp()));
//      RefreshToken refreshToken = jwtUtils.createRefreshToken();
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);
      userTokenDetails.setAccessToken(jwt);
      userTokenDetails.setAccessTokenExpiry(new Date().toInstant().toString());
//      userTokenDetails.setRefreshToken(userTokenDetails.getRefreshToken());
      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      userTokenDetailsRepository.save(userTokenDetails);
      return ResponseEntity.ok(JwtResponse.builder()
              .accessToken(jwt)
              .accessTokenExpiry(new Date().toInstant())
              .token(userTokenDetails.getRefreshToken())
//              .refreshTokenExpiry(Instant.parse(userTokenDetails.getRefreshTokenExpiry()))
              .refreshTokenExpiry(new Date().toInstant())
              .userProfile(userProfile)
              .build());

    }


    if(tokenType.equalsIgnoreCase("refresh_token")){

      String headerAuth = request.getHeader("Authorization");

      if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
        headerAuth =  headerAuth.substring(7);

      }
      UserTokenDetails userTokenDetails = userTokenDetailsRepository.findByAccessToken(headerAuth);

      UserProfile userProfile=userProfileRepository.getUserProfileByPhoneNumber(userTokenDetails.getMobileNumber());


      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(userTokenDetails.getMobileNumber(), userTokenDetails.getOtp()));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      RefreshToken refreshToken = jwtUtils.createRefreshToken();

      userTokenDetails.setAccessToken(jwt);
      userTokenDetails.setAccessTokenExpiry(new Date().toInstant().toString());
      userTokenDetails.setRefreshToken(refreshToken.getToken());

      userTokenDetailsRepository.save(userTokenDetails);

      return ResponseEntity.ok(JwtResponse.builder()
              .accessToken(jwt)
              .accessTokenExpiry(new Date().toInstant())
              .token(refreshToken.getToken())
              .refreshTokenExpiry(refreshToken.getExpiryDate())
              .userProfile(userProfile)
              .build());

    }


    return null;
  }






    @PostMapping("/signup")
  public ResponseEntity<?> registerUser(/*@Valid*/ @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

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
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
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
}
