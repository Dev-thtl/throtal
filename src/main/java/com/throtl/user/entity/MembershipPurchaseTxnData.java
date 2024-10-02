package com.throtl.user.entity;

import com.throtl.user.util.enums.NameTitleType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name ="[purchase_txn_data]", schema = "dbo")
public class MembershipPurchaseTxnData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OID", unique = true, nullable = false)
    private Long oid;

    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "TRANSACTION_STATUS")
    private String transactionStatus;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "MEMBERSHIP_NAME")
    private String membershipName;

    @Column(name = "MEMBERSHIP_ID")
    private String membershipId;

    @Column(name = "MEMBERSHIP_AMOUNT")
    private String membershipAmount;

    @Column(name = "MEMBERSHIP_DURATION")
    private String membershipDuration;

  @Column(name = "ORDER_ID")
    private String orderId;

    @Column(name = "PAYMENT_GATEWAY_KEY")
    private String paymentGatewayKey;

    @Column(name = "TRANSACTION_STATUS_DESC")
    private String transactionStatusDesc;

    @Column(name = "STATE_ID")
    private String stateId;

    @Column(name = "CITY_NAME")
    private String cityName;

    @Column(name = "VEHICLE_BRAND")
    private String vehicleBrand;

    @Column(name = "VEHICLE_MODEL")
    private String vehicleModel;

    @Column(name = "VEHICLE_NO")
    private String vehicleNo;

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public String getMembershipAmount() {
        return membershipAmount;
    }

    public void setMembershipAmount(String membershipAmount) {
        this.membershipAmount = membershipAmount;
    }

    public String getMembershipDuration() {
        return membershipDuration;
    }

    public void setMembershipDuration(String membershipDuration) {
        this.membershipDuration = membershipDuration;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentGatewayKey() {
        return paymentGatewayKey;
    }

    public void setPaymentGatewayKey(String paymentGatewayKey) {
        this.paymentGatewayKey = paymentGatewayKey;
    }

    public String getTransactionStatusDesc() {
        return transactionStatusDesc;
    }

    public void setTransactionStatusDesc(String transactionStatusDesc) {
        this.transactionStatusDesc = transactionStatusDesc;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }
}
