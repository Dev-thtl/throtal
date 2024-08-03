package com.throtl.user.entity;

import com.throtl.user.models.User;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String token;
    private Instant expiryDate;


//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private UserInfo userInfo;

    // No-argument constructor
    public RefreshToken() {
    }

    // All-argument constructor
    public RefreshToken(int id, String token, Instant expiryDate) {
        this.id = id;
        this.token = token;
        this.expiryDate = expiryDate;
//        this.userInfo = userInfo;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

//    public UserInfo getUserInfo() {
//        return userInfo;
//    }
//
//    public void setUserInfo(UserInfo userInfo) {
//        this.userInfo = userInfo;
//    }

    // Builder class
    public static class RefreshTokenBuilder {
        private int id;
        private String token;
        private Instant expiryDate;
//        private UserInfo userInfo;

        public RefreshTokenBuilder() {
        }

        public RefreshTokenBuilder id(int id) {
            this.id = id;
            return this;
        }

        public RefreshTokenBuilder token(String token) {
            this.token = token;
            return this;
        }

        public RefreshTokenBuilder expiryDate(Instant expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

//        public RefreshTokenBuilder userInfo(UserInfo userInfo) {
//            this.userInfo = userInfo;
//            return this;
//        }

        public RefreshToken build() {
            return new RefreshToken(id, token, expiryDate);
        }
    }

    public static RefreshTokenBuilder builder() {
        return new RefreshTokenBuilder();
    }

    @Override
    public String toString() {
        return "RefreshToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", expiryDate=" + expiryDate +
//                ", userInfo=" + userInfo +
                '}';
    }

}
