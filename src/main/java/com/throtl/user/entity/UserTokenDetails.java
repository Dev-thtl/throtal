package com.throtl.user.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "user_token_details")
public class UserTokenDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OID", unique = true, nullable = false)
    private Long oid;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "ACCESS_TOKEN")
    private String accessToken;

    @Column(name = "ACCESS_TOKEN_EXPIRY")
    private String accessTokenExpiry;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    @Column(name = "REFRESH_TOKEN_EXPIRY")
    private String refreshTokenExpiry;

    @Column(name = "OTP")
    private String otp;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenExpiry() {
        return accessTokenExpiry;
    }

    public void setAccessTokenExpiry(String accessTokenExpiry) {
        this.accessTokenExpiry = accessTokenExpiry;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshTokenExpiry() {
        return refreshTokenExpiry;
    }

    public void setRefreshTokenExpiry(String refreshTokenExpiry) {
        this.refreshTokenExpiry = refreshTokenExpiry;
    }
}
