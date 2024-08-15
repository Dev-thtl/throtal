package com.throtl.user.repository;

import com.throtl.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//  Optional<User> findByUsername(String username);
  @Query(value = "SELECT * FROM [dbo].[user] WHERE username = 'mod';", nativeQuery = true)
  Optional<User> findByUsername(String username);
  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
