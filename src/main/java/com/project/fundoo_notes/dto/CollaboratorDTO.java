package com.project.fundoo_notes.dto;

import com.project.fundoo_notes.model.NoteModel;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollaboratorDTO {
    private List<String> collList;
}
