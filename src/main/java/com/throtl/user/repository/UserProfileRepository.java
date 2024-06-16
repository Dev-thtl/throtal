package com.throtl.user.repository;

import com.throtl.user.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    @Query(value = "select * from user_profile where MOBILE_NUMBER=:phoneNumber", nativeQuery = true)
    UserProfile getUserProfileByPhoneNumber(@Param("phoneNumber") String phoneNumber);

}
