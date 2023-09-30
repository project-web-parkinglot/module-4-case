package com.parkingcar.service.employee;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.employee.Employee;
import com.parkingcar.service.IGenerateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IEmployeeService {
    Page<Employee> findALlEmployee(Pageable pageable, String name, String sortProperty, String condition);
    void deleteById( int id);

    void reActiveEmployee( int id);

    Employee findById(int id);

    Employee getEmployeeByAccount(Account account);

    void save(Employee employee);


}
