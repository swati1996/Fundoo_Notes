package com.project.fundoo_notes.dto;

import lombok.*;

import java.util.List;
/**
 * purpose : Response DTO for note
 * @author : Swati
 * @version : 1.0
 * @since : 4-7-21
 **/

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponseDTO {
    private List notes;
    private int statuscode;

}
