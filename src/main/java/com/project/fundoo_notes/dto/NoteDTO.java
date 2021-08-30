package com.project.fundoo_notes.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoteDTO {
    private String title;
    private String description;
    private String color;
    private String emailid;
    private boolean inPin;
    private boolean inarchieved;
}
