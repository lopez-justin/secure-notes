package com.justindev.securenotes.repository;

import com.justindev.securenotes.model.AppRole;
import com.justindev.securenotes.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(AppRole appRole);

}
