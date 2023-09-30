package com.parkingcar.service.accountService;

import com.parkingcar.model.account.Role;
import com.parkingcar.repository.accountRepository.IRoleRepository;
import com.parkingcar.service.IGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface IRoleService extends IGenerateService<Role> {
}
