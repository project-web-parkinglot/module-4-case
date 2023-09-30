package com.parkingcar.repository.employee;

import com.parkingcar.model.account.Account;
import com.parkingcar.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "select * from employee as em " +
            "join account as acc on acc.id = em.account_id " +
            "where em.name like :name or acc.email like :name " +
            "order by :sort :condition "
            , nativeQuery = true)
    Page<Employee> findALlEmployee(Pageable pageable, @Param("name") String name,
                                   @Param("sort") String sortProperty, @Param("condition") String condition
    );

    @Transactional
    @Modifying
    @Query(value = "update account as acc " +
            "join employee as em " +
            "on acc.id = em.account_id " +
            "set acc.status = 1 " +
            "where em.id = :id ", nativeQuery = true)
    void deleteById(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "update account as acc " +
            "join employee as em " +
            "on acc.id = em.account_id " +
            "set acc.status = 0 " +
            "where em.id = :id ", nativeQuery = true)
    void reActiveEmployee(@Param("id") int id);

    Employee getEmployeeByAccount(Account account);

}
