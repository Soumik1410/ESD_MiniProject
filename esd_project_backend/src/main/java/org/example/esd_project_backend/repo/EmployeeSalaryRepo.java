package org.example.esd_project_backend.repo;

import org.example.esd_project_backend.entity.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.example.esd_project_backend.entity.EmployeeSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface EmployeeSalaryRepo extends JpaRepository<EmployeeSalary, Long> {

    //@Query(value = "SELECT * FROM employee_salary WHERE employee_id = ?1", nativeQuery = true)
    //List<EmployeeSalary> findSalaryHistoryByEmployeeId(double empId);

    @Query(value = "SELECT * FROM employee_salary WHERE employee_id = ?1", nativeQuery = true)
    List<EmployeeSalary> findSalaryHistoryByEmployeeId(long empId);

    @Query(value = "SELECT * FROM employee_salary WHERE employee_id = ?1 and id = ?2", nativeQuery = true)
    EmployeeSalary findMonthlySalary(long empId, long id);

    /*
    @Modifying
    @Transactional
    @Query(value = "UPDATE customer SET access_token =?2 WHERE email = ?1", nativeQuery = true)
    void insertAccessToken(String email, String token);

    @Modifying
    @Transactional
    @Query(value = "UPDATE customer SET first_name =?2, last_name=?3, address=?4, city=?5, pincode=?6 WHERE email = ?1", nativeQuery = true)
    void updateCustomer(String email, String first_name, String last_name, String address, String city, String pincode);*/

}


