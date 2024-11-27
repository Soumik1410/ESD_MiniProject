package org.example.esd_project_backend.service;

import org.example.esd_project_backend.dto.*;
import org.example.esd_project_backend.entity.*;
import org.example.esd_project_backend.mapper.*;
import org.example.esd_project_backend.repo.*;
import org.example.esd_project_backend.helper.*;
import org.example.esd_project_backend.helper.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final EmployeeRepo repo;
    private final EmployeeSalaryRepo salaryRepo;
    private final DepartmentRepo departmentRepo;
    private final EmployeeMapper mapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;


    @Transactional
    public String loginFaculty(FacultyLoginRequest request) {
        Employee employee = repo.findByEmail(request.email());
        if(employee == null)
            return "Login Failed";
        if(!encryptionService.validates(request.password(), employee.getPassword()))
            return "Login Failed";
        if (employee.getDepartment() != 100)
            return "Not a Faculty Member";
        //repo.insertAccessToken(request.email(), token);
        return jwtHelper.generateToken(request.email());
    }

    public List<FacultySalaryHistoryResponse> getFacultySalaryHistory(FacultySalaryHistoryRequest request) {
        boolean validity = jwtHelper.validateToken(request.access_token());
        if(!validity)
            return null;
        String email = jwtHelper.extractUsername(request.access_token());
        Employee employee = repo.findByEmail(email);
        List<EmployeeSalary> emp_sal = salaryRepo.findSalaryHistoryByEmployeeId(employee.getId());
        return mapper.toFacultySalaryHistoryResponse(emp_sal);
    }

    public FacultySalaryResponse getFacultySalary(FacultySalaryHistoryRequest request, Long id) {
        boolean validity = jwtHelper.validateToken(request.access_token());
        if(!validity)
            return null;
        String email = jwtHelper.extractUsername(request.access_token());
        Employee employee = repo.findByEmail(email);
        EmployeeSalary emp_sal = salaryRepo.findMonthlySalary(employee.getId(), id);
        Department department = departmentRepo.findDepartment(employee.getDepartment());
        return mapper.toFacultySalaryResponse(employee, emp_sal, department);
    }
}

