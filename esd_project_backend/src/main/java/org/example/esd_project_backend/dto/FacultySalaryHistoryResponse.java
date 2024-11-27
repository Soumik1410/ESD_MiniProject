package org.example.esd_project_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record FacultySalaryHistoryResponse(
        @JsonProperty("id")
        Long id,

        @JsonProperty("payment_date")
        LocalDate payment_date,

        @JsonProperty("amount")
        Double amount,

        @JsonProperty("description")
        String description
) {
}
