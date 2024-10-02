package com.throtl.user.repository;

import com.throtl.user.entity.MembershipPurchaseTxnData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipPurchaseTxnDataRepository extends JpaRepository<MembershipPurchaseTxnData, Long> {

    @Query(value = "select * from purchase_txn_data where MOBILE_NUMBER=:mobileNumber AND USER_ID = :userId", nativeQuery = true)
    List<MembershipPurchaseTxnData> getByMobileNumberAndUserId(@Param("mobileNumber") String mobileNumber, @Param("userId") String userId);

}
