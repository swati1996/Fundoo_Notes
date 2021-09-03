package com.project.fundoo_notes.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoteDTO {
    @NotBlank(message = "Title should not be empty")
    private String title;
    @NotBlank(message = "Please enter description")
    private String description;
    private String color;
    @NotBlank(message = "Please enter valid email")
    @Email
    private String emailid;
    private boolean inPin;
    private boolean inarchieved;
}
