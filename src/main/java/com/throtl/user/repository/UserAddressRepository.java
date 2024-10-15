package com.throtl.user.repository;

import com.throtl.user.entity.UserAddressEntity;
import com.throtl.user.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {

    @Query(value = "select * from user_address where USER_ID=:userID", nativeQuery = true)
    UserAddressEntity getBuyUserID(@Param("userID") String userID);

}
