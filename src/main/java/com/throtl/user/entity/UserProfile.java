package com.throtl.user.entity;

import com.throtl.user.util.enums.NameTitleType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "[user_profile]", schema = "dbo")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OID", unique = true, nullable = false)
    private Long userId;

    @CreationTimestamp
    @Column(name = "CREATION_TIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @UpdateTimestamp
    @Column(name = "UPDATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Column(name = "STATUS")
    private String status;
     @Column(name = "RSA_ID")
    private String rsaId;

    @Column(name = "TITLE")
    @Enumerated(EnumType.STRING)
    private NameTitleType title;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL_ID")
    private String emailId;

    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public NameTitleType getTitle() {
        return title;
    }

    public void setTitle(NameTitleType title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRsaId() {
        return rsaId;
    }

    public void setRsaId(String rsaId) {
        this.rsaId = rsaId;
    }
}
