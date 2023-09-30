package com.example.securitydemp.repository;

import com.example.securitydemp.model.AppUser;
import com.example.securitydemp.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    List<UserRole> findByAppUser(AppUser appUser);
}
