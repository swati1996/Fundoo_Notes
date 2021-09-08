package com.project.fundoo_notes.dto;

import com.project.fundoo_notes.model.NoteModel;
import lombok.*;

import java.util.List;
/**
 * purpose : DTO for collaborator
 * @author : Swati
 * @version : 1.0
 * @since : 7-7-21
 **/

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollaboratorDTO {
    private long noteId;
    private String email;
}
