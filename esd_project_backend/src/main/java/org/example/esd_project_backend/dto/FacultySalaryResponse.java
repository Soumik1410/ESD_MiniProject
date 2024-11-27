package org.example.esd_project_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record FacultySalaryResponse(
        @JsonProperty("emp_id")
        Long employee_id,

        @JsonProperty("first_name")
        String first_name,

        @JsonProperty("last_name")
        String last_name,

        @JsonProperty("email")
        String email,

        @JsonProperty("title")
        String title,

        @JsonProperty("department")
        String department,

        @JsonProperty("payment_date")
        LocalDate payment_date,

        @JsonProperty("amount")
        Double amount,

        @JsonProperty("description")
        String description
) {
}

