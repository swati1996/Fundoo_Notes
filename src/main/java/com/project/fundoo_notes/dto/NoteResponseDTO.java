package com.project.fundoo_notes.dto;

import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponseDTO {
    private List notes;
    private int statuscode;

}
