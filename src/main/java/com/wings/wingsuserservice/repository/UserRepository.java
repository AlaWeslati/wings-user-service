package com.wings.wingsuserservice.repository;

import com.wings.wingsuserservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
  Optional<User> getAllByEmail(String email);
  Boolean existsByEmail(String email);
}
