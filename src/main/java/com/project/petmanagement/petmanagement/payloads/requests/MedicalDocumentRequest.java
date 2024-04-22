package com.project.petmanagement.petmanagement.payloads.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalDocumentRequest {
    @NotBlank(message = "Title can not be empty or blank")
    private String title;

    private String note;

    @NotNull(message = "You have to define pet to add medical document")
    private Long petId;

    @NotNull(message = "Document file can not be null")
    private MultipartFile file;
}
