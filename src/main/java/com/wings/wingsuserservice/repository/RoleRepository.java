package com.wings.wingsuserservice.repository;

import com.wings.wingsuserservice.models.ERole;
import com.wings.wingsuserservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
