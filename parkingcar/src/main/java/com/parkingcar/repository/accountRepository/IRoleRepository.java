package com.parkingcar.repository.accountRepository;

import com.parkingcar.model.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Integer> {

}
