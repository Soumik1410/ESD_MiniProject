package org.example.esd_project_backend.controller;

import org.example.esd_project_backend.dto.*;
import org.example.esd_project_backend.service.FacultyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    @PostMapping("/login")
    public ResponseEntity<String> loginFaculty(@RequestBody @Valid FacultyLoginRequest request) {
        String response = facultyService.loginFaculty(request);
        if(!response.equals("Login Failed") && !response.equals("Not a Faculty Member"))
            return ResponseEntity.ok("Login successful\nJWT Token: " + response);
        else
            return ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/salary_history")
    public ResponseEntity<?> getSalaryHistory(@RequestHeader(value = "x-access-token") String token) {
        FacultySalaryHistoryRequest request = new FacultySalaryHistoryRequest(token);
        List<FacultySalaryHistoryResponse> obj = facultyService.getFacultySalaryHistory(request);
        if(obj != null)
            return ResponseEntity.ok(obj);
        else
            return ResponseEntity.badRequest().body("Invalid/Expired Access Token");
    }

    @GetMapping("/salary_history/{id}")
    public ResponseEntity<?> getSalary(@PathVariable Long id, @RequestHeader(value = "x-access-token") String token) {
        FacultySalaryHistoryRequest request = new FacultySalaryHistoryRequest(token);
        FacultySalaryResponse obj = facultyService.getFacultySalary(request, id);
        if(obj != null)
            return ResponseEntity.ok(obj);
        else
            return ResponseEntity.badRequest().body("Invalid/Expired Access Token");
    }
    /*@DeleteMapping("/deleteCustomer")
    public ResponseEntity<String> deleteCustomer(@RequestBody @Valid CustomerDetailsRequest request) {
        CustomerResponse obj = customerService.deleteCustomer(request);
        if(obj != null)
            return ResponseEntity.ok("Deleted customer entry : " + obj.toString());
        else
            return ResponseEntity.badRequest().body("Invalid/Expired Access Token");
    }

    @PutMapping("/updateDetails")
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustomerDetailsUpdateRequest request) {
        String result = customerService.updateCustomer(request);
        if(result != null)
            //return ResponseEntity.ok("Old details : " + obj.get(0).toString() + "\nNew updated details : " + obj.get(1).toString());
            return ResponseEntity.ok(result);
        else
            return ResponseEntity.badRequest().body("Invalid/Expired Access Token");
    }
     */
}
