package com.throtl.user.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.throtl.user.entity.UserProfile;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class JwtResponse {
  private String accessToken;
  private Instant accessTokenExpiry;
  private String token;
  private Instant refreshTokenExpiry;
  private UserProfile userProfile;

  // No-argument constructor
  public JwtResponse() {
  }

  // All-argument constructor
  public JwtResponse(String accessToken, Instant accessTokenExpiry, String token, Instant refreshTokenExpiry,
                     UserProfile userProfile) {
    this.accessToken = accessToken;
    this.accessTokenExpiry = accessTokenExpiry;
    this.token = token;
    this.refreshTokenExpiry = refreshTokenExpiry;
    this.userProfile = userProfile;
  }

  // Getters and Setters


  public UserProfile getUserProfile() {
    return userProfile;
  }

  public void setUserProfile(UserProfile userProfile) {
    this.userProfile = userProfile;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public Instant getAccessTokenExpiry() {
    return accessTokenExpiry;
  }

  public void setAccessTokenExpiry(Instant accessTokenExpiry) {
    this.accessTokenExpiry = accessTokenExpiry;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Instant getRefreshTokenExpiry() {
    return refreshTokenExpiry;
  }

  public void setRefreshTokenExpiry(Instant refreshTokenExpiry) {
    this.refreshTokenExpiry = refreshTokenExpiry;
  }

  // Builder class
  public static class JwtResponseBuilder {
    private String accessToken;
    private Instant accessTokenExpiry;
    private String token;
    private Instant refreshTokenExpiry;
    private UserProfile userProfile;

    public JwtResponseBuilder() {
    }

    public JwtResponseBuilder accessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public JwtResponseBuilder accessTokenExpiry(Instant accessTokenExpiry) {
      this.accessTokenExpiry = accessTokenExpiry;
      return this;
    }

    public JwtResponseBuilder token(String token) {
      this.token = token;
      return this;
    }

    public JwtResponseBuilder refreshTokenExpiry(Instant refreshTokenExpiry) {
      this.refreshTokenExpiry = refreshTokenExpiry;
      return this;
    }
    public JwtResponseBuilder userProfile(UserProfile userProfile) {
      this.userProfile = userProfile;
      return this;
    }

    public JwtResponse build() {
      return new JwtResponse(accessToken, accessTokenExpiry, token, refreshTokenExpiry, userProfile);
    }
  }

  public static JwtResponseBuilder builder() {
    return new JwtResponseBuilder();
  }

  @Override
  public String toString() {
    return "JwtResponse{" +
            "accessToken='" + accessToken + '\'' +
            ", accessTokenExpiry=" + accessTokenExpiry +
            ", token='" + token + '\'' +
            ", refreshTokenExpiry=" + refreshTokenExpiry +
            '}';
  }
}
