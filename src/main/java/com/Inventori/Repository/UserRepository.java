package com.Inventori.Repository;

import com.Inventori.Models.Users;
import com.Inventori.Utils.MyUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface  UserRepository extends JpaRepository<Users, Long> {

   Users findByUsername(String username);

}
