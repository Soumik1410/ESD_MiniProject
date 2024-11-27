package org.example.esd_project_backend.mapper;

import org.example.esd_project_backend.dto.FacultyLoginRequest;
//import com.prashantjain.yummyrest.dto.CustomerResponse;
import org.example.esd_project_backend.entity.Department;
import org.example.esd_project_backend.entity.Employee;
import org.example.esd_project_backend.dto.*;
import org.example.esd_project_backend.entity.EmployeeSalary;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeMapper {
    /*public Employee toEntity(FacultyRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .address(request.address())
                .city(request.city())
                .pincode(request.pincode())
                .build();
    }*/

    public List<FacultySalaryHistoryResponse> toFacultySalaryHistoryResponse(List<EmployeeSalary> emp_sal) {
        return emp_sal.stream()
                .map(emp -> new FacultySalaryHistoryResponse(emp.getId(), emp.getPaymentDate(), emp.getAmount(), emp.getDescription()))
                .collect(Collectors.toList());
        //return new CustomerResponse(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getAddress(), customer.getCity(), customer.getPincode());
    }

    public FacultySalaryResponse toFacultySalaryResponse(Employee employee, EmployeeSalary emp_sal, Department department) {
        return new FacultySalaryResponse(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getTitle(), department.getName(), emp_sal.getPaymentDate(), emp_sal.getAmount(), emp_sal.getDescription());
        //return new CustomerResponse(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getAddress(), customer.getCity(), customer.getPincode());
    }
}
