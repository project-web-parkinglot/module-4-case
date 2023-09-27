package com.parkingcar.service.employee;

import com.parkingcar.model.employee.Employee;
import com.parkingcar.repository.employee.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    private IEmployeeRepository iEmployeeRepository;
    @Override
    public Page<Employee> findALlEmployee(Pageable pageable, String name, String sortProperty, String condition) {
        return iEmployeeRepository.findALlEmployee(pageable,name,sortProperty,condition);
    }

    @Override
    public void deleteById(int id) {
        iEmployeeRepository.deleteById(id);
    }

    @Override
    public void reActiveEmployee(int id) {
        iEmployeeRepository.reActiveEmployee(id);
    }
}
