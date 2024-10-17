package com.throtl.user.repository;

import com.throtl.user.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    @Query(value = "select * from user_profile where MOBILE_NUMBER=:phoneNumber", nativeQuery = true)
    UserProfile getUserProfileByPhoneNumber(@Param("phoneNumber") String phoneNumber);



    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_profile WHERE MOBILE_NUMBER = :phoneNumber", nativeQuery = true)
    void deleteUserProfile(@Param("phoneNumber") String phoneNumber);
}
