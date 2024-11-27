package org.example.esd_project_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record FacultySalaryHistoryRequest(
        @NotNull(message = "Access Token should be present")
        @NotEmpty(message = "Access Token should be present")
        @NotBlank(message = "Access Token should be present")
        @JsonProperty("access_token")
        String access_token
) {
}
