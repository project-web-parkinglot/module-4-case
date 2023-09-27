package com.parkingcar.service.accountService;

import com.parkingcar.model.account.Role;
import com.parkingcar.repository.accountRepository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository iRoleRepository;

    @Override
    public List<Role> findAll() {
        return iRoleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return iRoleRepository.findById(id);
    }

    @Override
    public void save(Role role) {

    }

    @Override
    public void remove(Integer id) {

    }
}
