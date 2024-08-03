package com.throtl.user.repository;

import com.throtl.user.entity.UserTokenDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenDetailsRepository extends JpaRepository<UserTokenDetails, Long> {


    @Query(value="select * from user_token_details where ACCESS_TOKEN=:accessToken",nativeQuery = true)
    UserTokenDetails findByAccessToken(@Param("accessToken") String accessToken);
}
