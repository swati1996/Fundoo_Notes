package com.project.fundoo_notes.dto;

import lombok.*;
/**
 * purpose : Response DTO for Label
 * @author : Swati
 * @version : 1.0
 * @since : 5-7-21
 **/

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabelResponseDTO {
    private String message;
    private int statuscode;
}
