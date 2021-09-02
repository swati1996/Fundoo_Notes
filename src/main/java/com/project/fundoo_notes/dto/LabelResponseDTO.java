package com.project.fundoo_notes.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabelResponseDTO {
    private String message;
    private int statuscode;
}
