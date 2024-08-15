package com.throtl.user.security.services;

import com.throtl.user.models.User;
import com.throtl.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  private JdbcTemplate jdbcTemplate;
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//    try{
//      String sql = "SELECT * FROM [dbo].[user] WHERE username = ?";
//      List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, "mod");
//
//    }catch (Exception e){
//      e.printStackTrace();
//    }

    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }

}
